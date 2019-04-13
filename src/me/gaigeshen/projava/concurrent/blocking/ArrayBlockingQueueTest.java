package me.gaigeshen.projava.concurrent.blocking;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * 阻塞队列适合做生产者消费者模型的程序
 *
 * @author gaigeshen
 */
public class ArrayBlockingQueueTest {
  public static void main(String[] args) {

    BlockingQueue<String> queue = new ArrayBlockingQueue<>(10);

    Producer producer = new Producer(queue);
    Consumer consumer = new Consumer(queue);

    Thread thread1 = new Thread(producer);
    thread1.start();

    Thread thread2 = new Thread(consumer);
    thread2.start();

    try {
      thread1.join();
      thread2.join();
    } catch (InterruptedException e) {
      e.printStackTrace();
    }

  }

}

class Producer implements Runnable {

  private BlockingQueue<String> queue;

  Producer(BlockingQueue<String> queue) {
    this.queue = queue;
  }

  @Override
  public void run() {
    try {
      queue.put("ele1");
      Thread.sleep(1000);
      queue.put("ele2");
      Thread.sleep(1000);
      queue.put("ele3");
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }

}

class Consumer implements Runnable {

  private BlockingQueue<String> queue;

  Consumer(BlockingQueue<String> queue) {
    this.queue = queue;
  }

  @Override
  public void run() {
    try {
      System.out.println(queue.take());
      System.out.println(queue.take());
      System.out.println(queue.take());
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }

}
