package com.gzs.learn.io;

import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;

public class ByteInputStream extends FilterInputStream {
    private static int DEFAULT_BUFFER_SIZE = 8192;

    private static int MAX_BUFFER_SIZE = Integer.MAX_VALUE - 8;

    protected volatile byte buf[];

    private static final AtomicReferenceFieldUpdater<ByteInputStream, byte[]> bufUpdater =
            AtomicReferenceFieldUpdater.newUpdater(ByteInputStream.class, byte[].class, "buf");

    protected int count;

    protected int pos;

    protected int markpos = -1;

    protected int marklimit;

    private InputStream getInIfOpen() throws IOException {
        InputStream input = in;
        if (input == null) {
            throw new IOException("Stream closed");
        }
        return input;
    }

    private byte[] getBufIfOpen() throws IOException {
        byte[] buffer = buf;
        if (buffer == null) {
            throw new IOException("Stream closed");
        }
        return buffer;
    }

    public ByteInputStream(InputStream in) {
        this(in, DEFAULT_BUFFER_SIZE);
    }

    public ByteInputStream(InputStream in, int size) {
        super(in);
        if (size <= 0) {
            throw new IllegalArgumentException("Buffer size <= 0");
        }
        buf = new byte[size];
    }

    private void fill() throws IOException {
        byte[] buffer = this.getBufIfOpen();
        if (markpos < 0) {
            pos = 0; /* no mark: throw away the buffer */
        } else if (pos >= buffer.length) {
            if (markpos > 0) { /* can throw away early part of the buffer */
                int sz = pos - markpos;
                System.arraycopy(buffer, markpos, buffer, 0, sz);
                pos = sz;
                markpos = 0;
            } else if (buffer.length >= marklimit) {
                markpos = -1; /* buffer got too big, invalidate mark */
                pos = 0; /* drop buffer contents */
            } else if (buffer.length >= MAX_BUFFER_SIZE) {
                throw new OutOfMemoryError("Required array size too large");
            } else { /* grow buffer */
                int nsz = (pos <= MAX_BUFFER_SIZE - pos) ? pos * 2 : MAX_BUFFER_SIZE;
                if (nsz > marklimit) {
                    nsz = marklimit;
                }
                byte nbuf[] = new byte[nsz];
                System.arraycopy(buffer, 0, nbuf, 0, pos);
                if (!bufUpdater.compareAndSet(this, buffer, nbuf)) {
                    throw new IOException("Stream closed");
                }
                buffer = nbuf;
            }
        }
        count = pos;
        int n = this.getInIfOpen().read(buffer, pos, buffer.length - pos);
        if (n > 0) {
            count = n + pos;
        }
    }

    @Override
    public synchronized int read() throws IOException {
        if (pos >= count) {
            this.fill();
            if (pos >= count) {
                return -1;
            }
        }
        return this.getBufIfOpen()[pos++] & 0xff;
    }

    private int read1(byte[] b, int off, int len) throws IOException {
        int avail = count - pos;
        if (avail <= 0) {
            /*
             * If the requested length is at least as large as the buffer, and if there is no
             * mark/reset activity, do not bother to copy the bytes into the local buffer. In this
             * way buffered streams will cascade harmlessly.
             */
            if (len >= this.getBufIfOpen().length && markpos < 0) {
                return this.getInIfOpen().read(b, off, len);
            }
            this.fill();
            avail = count - pos;
            if (avail <= 0) {
                return -1;
            }
        }
        int cnt = (avail < len) ? avail : len;
        System.arraycopy(this.getBufIfOpen(), pos, b, off, cnt);
        pos += cnt;
        return cnt;
    }

    @Override
    public synchronized int read(byte b[], int off, int len) throws IOException {
        this.getBufIfOpen(); // Check for closed stream
        if ((off | len | (off + len) | (b.length - (off + len))) < 0) {
            throw new IndexOutOfBoundsException();
        } else if (len == 0) {
            return 0;
        }

        int n = 0;
        for (;;) {
            int nread = this.read1(b, off + n, len - n);
            if (nread <= 0) {
                return (n == 0) ? nread : n;
            }
            n += nread;
            if (n >= len) {
                return n;
            }
            // if not closed but no bytes available, return
            InputStream input = in;
            if (input != null && input.available() <= 0) {
                return n;
            }
        }
    }

    @Override
    public synchronized long skip(long n) throws IOException {
        this.getBufIfOpen(); // Check for closed stream
        if (n <= 0) {
            return 0;
        }
        long avail = count - pos;

        if (avail <= 0) {
            // If no mark position set then don't keep in buffer
            if (markpos < 0) {
                return this.getInIfOpen().skip(n);
            }

            // Fill in buffer to save bytes for reset
            this.fill();
            avail = count - pos;
            if (avail <= 0) {
                return 0;
            }
        }

        long skipped = (avail < n) ? avail : n;
        pos += skipped;
        return skipped;
    }

    @Override
    public synchronized int available() throws IOException {
        int n = count - pos;
        int avail = this.getInIfOpen().available();
        return n > (Integer.MAX_VALUE - avail) ? Integer.MAX_VALUE : n + avail;
    }

    @Override
    public synchronized void mark(int readlimit) {
        marklimit = readlimit;
        markpos = pos;
    }

    @Override
    public synchronized void reset() throws IOException {
        this.getBufIfOpen(); // Cause exception if closed
        if (markpos < 0) {
            throw new IOException("Resetting to invalid mark");
        }
        pos = markpos;
    }

    @Override
    public boolean markSupported() {
        return true;
    }

    @Override
    public void close() throws IOException {
        byte[] buffer;
        while ((buffer = buf) != null) {
            if (bufUpdater.compareAndSet(this, buffer, null)) {
                InputStream input = in;
                in = null;
                if (input != null) {
                    input.close();
                }
                return;
            }
            // Else retry in case a new buf was CASed in fill()
        }
    }
}
