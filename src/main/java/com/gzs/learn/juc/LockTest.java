package com.gzs.learn.juc;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import org.junit.Test;

public class LockTest {

	@Test
	public void testLock(){
		Lock lock = new ReentrantLock();
		lock.lock();
		System.out.println(11);
		lock.unlock();
	}
}
