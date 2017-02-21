package com.hf.test;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * 使用Lock Condition实现阻塞队列FIFO
 */
public class BoundedBuffer {
	private static final Logger log =LoggerFactory.getLogger(BoundedBuffer.class);
    final Lock lock = new ReentrantLock();
    final Condition notFull = lock.newCondition();
    final Condition notEmpty = lock.newCondition();

    final Object[] items = new Object[2]; // 阻塞队列
    int putptr, takeptr, count;

    public void put(Object x) throws InterruptedException {
        log.info("进入put");
        lock.lock();
        log.info("put lock 锁住");
        try {
            while (count == items.length) { // 如果队列满了，notFull就一直等待
                log.info("put notFull 等待");
                notFull.await(); // 调用await的意思取反，及not notFull -> Full
            }
            items[putptr] = x; // 终于可以插入队列
            if (++putptr == items.length)
                putptr = 0; // 如果下标到达数组边界，循环下标置为0
            ++count;
            log.info("put notEmpty 唤醒");
            notEmpty.signal(); // 唤醒notEmpty
        } finally {
            log.info("put lock 解锁");
            lock.unlock();
        }
    }

    public Object take() throws InterruptedException {
        lock.lock();
        log.info("take lock 锁住");
        try {
            while (count == 0) {
                log.info("take notEmpty 等待");
                notEmpty.await();
            }
            Object x = items[takeptr];
            if (++takeptr == items.length)
                takeptr = 0;
            --count;
            log.info("take notFull 唤醒");
            notFull.signal();
            return x;
        } finally {
            lock.unlock();
            log.info("take lock 解锁");
        }
    }

    @SuppressWarnings("resource")
	public static void main(String[] args) throws InterruptedException {
		ApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring-context-mybatis.xml","dubbo-provider.xml");
        applicationContext.getBean(DataSource.class);
		final BoundedBuffer bb = new BoundedBuffer();
        log.info(Thread.currentThread() + "," + bb);

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(1000L);
                    log.info(Thread.currentThread() + "," + bb);
                    bb.put("xx");
                    bb.put("yy");
                    bb.put("zz");
                    bb.put("zz");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
        bb.take();
    }
}

