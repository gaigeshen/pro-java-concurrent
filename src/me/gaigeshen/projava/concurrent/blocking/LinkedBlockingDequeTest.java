package me.gaigeshen.projava.concurrent.blocking;

import java.util.concurrent.BlockingDeque;
import java.util.concurrent.LinkedBlockingDeque;

/**
 * @author gaigeshen
 */
public class LinkedBlockingDequeTest {
  public static void main(String[] args) throws InterruptedException {

    BlockingDeque<String> deque = new LinkedBlockingDeque<>();

    deque.addFirst("first");
    deque.addLast("second");

    System.out.println(deque.takeFirst());
    System.out.println(deque.takeLast());
  }

}
