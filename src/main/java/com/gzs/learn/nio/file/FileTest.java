package com.gzs.learn.nio.file;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.ByteBuffer;
import java.nio.channels.Channels;
import java.nio.channels.WritableByteChannel;

import org.springframework.stereotype.Component;

import com.gzs.learn.CostLog;

@Component
public class FileTest {
    int len = 1024 * 1024 * 10;
    int buf_size = 1024;

    @CostLog
    public void writeFile() throws IOException {
        int loopTimes = len / buf_size;
        byte[] buf = new byte[buf_size];
        for (int i = 0; i < buf_size; i++) {
            buf[i] = (byte) i;
        }
        OutputStream os = new FileOutputStream("/tmp/file.data");
        for (int i = 0; i < loopTimes; i++) {
            os.write(buf, 0, buf_size);
        }
        os.flush();
        os.close();
    }


    @CostLog
    public void writeNioFile() throws IOException {
        int loopTimes = len / buf_size;
        byte[] buf = new byte[buf_size];
        for (int i = 0; i < buf_size; i++) {
            buf[i] = (byte) i;
        }
        WritableByteChannel channel =
                Channels.newChannel(new FileOutputStream("/tmp/nio_file.data"));
        for (int i = 0; i < loopTimes; i++) {
            channel.write(ByteBuffer.wrap(buf));
        }
        channel.close();
    }
}
