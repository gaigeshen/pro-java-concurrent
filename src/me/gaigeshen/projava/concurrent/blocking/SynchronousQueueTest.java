package me.gaigeshen.projava.concurrent.blocking;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.SynchronousQueue;

/**
 *
 * 没有数据缓冲的同步阻塞队列
 *
 * @author gaigeshen
 */
public class SynchronousQueueTest {
  public static void main(String[] args) throws InterruptedException {

    BlockingQueue<String> queue = new SynchronousQueue<>();

    new Thread(() -> {
      try {
        Thread.sleep(3000);
        System.out.println(queue.take());

        Thread.sleep(3000);
        System.out.println(queue.take());
      } catch (InterruptedException e) {
        e.printStackTrace();
      }

    }).start();


    // 除非另线程视图移除某个元素，否则不能添加元素
    // 不能迭代元素，因为没有数据缓冲区，没有元素可被迭代

    queue.put("first");
    queue.put("second");

  }

}
