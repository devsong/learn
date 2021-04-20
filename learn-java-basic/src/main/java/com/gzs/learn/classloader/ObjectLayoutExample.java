package com.gzs.learn.classloader;

import java.math.BigInteger;
import org.apache.commons.lang3.StringUtils;
import org.junit.Test;
import org.openjdk.jol.info.ClassLayout;

public class ObjectLayoutExample {

    public static void main(String[] args) {
        Object o = new Object();
        int h = o.hashCode();
        System.out.println(ClassLayout.parseInstance(o).toPrintable());
        System.out.println(h);
        synchronized (o) {
            System.out.println(ClassLayout.parseInstance(o).toPrintable());
        }
    }
    
    @Test
    public void testBiginteger () {
        System.out.println(System.identityHashCode(new Object()));
        BigInteger big = new BigInteger(StringUtils.reverse( "000000000000000000000000101101100110000011010110011100110000000"), 2);
        System.out.println(big);
        System.out.println((big.longValue()<<25)>>33);
    }
}
