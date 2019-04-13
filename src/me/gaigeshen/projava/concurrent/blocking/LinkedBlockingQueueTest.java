package me.gaigeshen.projava.concurrent.blocking;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 *
 *
 * @author gaigeshen
 */
public class LinkedBlockingQueueTest {
  public static void main(String[] args) {

    // BlockingQueue<String> unbounded = new LinkedBlockingQueue<>();

    BlockingQueue<String> bounded = new LinkedBlockingQueue<>(1024);

    try {
      bounded.put("gaigeshen");

      String value = bounded.take();
      System.out.println(value);

    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }

}
