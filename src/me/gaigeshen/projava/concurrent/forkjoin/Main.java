package me.gaigeshen.projava.concurrent.forkjoin;

import java.util.concurrent.ForkJoinPool;

/**
 * @author gaigeshen
 */
public class Main {
  public static void main(String[] args) {

    ForkJoinPool pool = new ForkJoinPool(4);

    // MyRecursiveAction action = new MyRecursiveAction(24);
    MyRecursiveTask task = new MyRecursiveTask(24);

    // pool.invoke(action);
    Long result = pool.invoke(task);
    System.out.println("Result is: " + result);

    pool.shutdown();
  }

}
