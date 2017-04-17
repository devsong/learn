package com.gzs.learn.juc.lock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

/**
 * ticket lock impl
 * 
 * @author guanzhisong
 * @date 2017年4月17日
 */
public class TicketLock implements Lock {
    // 服务号
    private AtomicInteger serviceNum = new AtomicInteger(0);
    // 票据号
    private AtomicInteger ticketNum = new AtomicInteger(0);
    // 线程持有的票据号
    private ThreadLocal<Integer> holder = new ThreadLocal<>();

    @Override
    public void lock() {
        // 领取票据
        int myTicket = ticketNum.getAndIncrement();
        // 将票据塞进口袋
        holder.set(myTicket);
        while (serviceNum.get() != myTicket) {
            // 等待叫号 or dosomething else
        }
    }

    @Override
    public void unlock() {
        int expect = holder.get();
        int next = expect + 1;
        serviceNum.compareAndSet(expect, next);
    }

    @Override
    public void lockInterruptibly() throws InterruptedException {
        // TODO Auto-generated method stub

    }

    @Override
    public boolean tryLock() {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean tryLock(long time, TimeUnit unit) throws InterruptedException {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public Condition newCondition() {
        // TODO Auto-generated method stub
        return null;
    }
}
