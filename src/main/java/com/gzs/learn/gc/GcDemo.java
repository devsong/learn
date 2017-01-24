package com.gzs.learn.gc;

public class GcDemo {
	public static byte[] s_arr = new byte[1024 * 1024 * 2];

	public static void main(String[] args) throws InterruptedException {
		/*
		 * byte[] b = null; for (int i = 0; i < 10; i++) { byte[] arr = new
		 * byte[1024 * 128]; System.out.println(i); b=arr; System.gc(); }
		 * System.out.println(b.length);
		 * 
		 * CountDownLatch latch = new CountDownLatch(1); latch.await();
		 */

		StringBuffer sb1 = new StringBuffer("a");

		StringBuffer sb2 = new StringBuffer("a");

		System.out.println(sb1.toString().equals(sb2.toString()));

		Byte a = new Byte((byte) 3);
		Byte b = new Byte((byte) 3);

		System.out.println(a.equals(b));
	}
}
