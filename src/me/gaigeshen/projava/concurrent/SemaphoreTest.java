package me.gaigeshen.projava.concurrent;

import java.util.concurrent.Semaphore;

/**
 * 信号量
 *
 * @author gaigeshen
 */
public class SemaphoreTest {
  public static void main(String[] args) {

    Semaphore semaphore = new Semaphore(1);

    Thread th1 = new Thread(() -> {
      try {
        semaphore.acquire();

        System.out.println("Semaphore acquired by thread1...");

        Thread.sleep(5000);

      } catch (InterruptedException e) {
        e.printStackTrace();
      } finally {
        System.out.println("Semaphore release now by thread1...");
        semaphore.release();
      }
    });

    Thread th2 = new Thread(() -> {
      try {
        semaphore.acquire();

        System.out.println("Semaphore acquired by thread2...");

        Thread.sleep(5000);
      } catch (InterruptedException e) {
        e.printStackTrace();
      } finally {
        System.out.println("Semaphore release now by thread2...");
        semaphore.release();
      }

    });

    th1.start();
    th2.start();
    try {
      th1.join();
      th2.join();
    } catch (InterruptedException e) {
      e.printStackTrace();
    }

  }

}
