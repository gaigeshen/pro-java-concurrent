package me.gaigeshen.projava.concurrent.locks;

import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 *
 *
 *
 * @author gaigeshen
 */
public class ReadWriteLockTest {

  // 读写锁
  // 适用于读多写少的场景

  public static void main(String[] args) {

    Resource res = new Resource();

    new Thread(res::write).start();

    // 阻塞 15 秒 然后才能执行 read 操作

    new Thread(res::read).start();

  }

}

class Resource {

  private ReadWriteLock lock = new ReentrantReadWriteLock();

  void read() {

    lock.readLock().lock();
    try {
      System.out.println("Read something by: " + Thread.currentThread().getName());
    } finally {
      lock.readLock().unlock();
    }

  }


  void write() {

    lock.writeLock().lock();
    try {
      System.out.println("Write something by: " + Thread.currentThread().getName());
      Thread.sleep(15000);
    } catch (InterruptedException e) {
      e.printStackTrace();
    } finally {
      lock.writeLock().unlock();
    }

  }
}

