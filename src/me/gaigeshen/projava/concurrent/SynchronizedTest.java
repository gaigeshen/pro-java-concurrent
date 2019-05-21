package me.gaigeshen.projava.concurrent;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @author gaigeshen
 */
public class SynchronizedTest {

  // 可重入，不可中断
  // 同时刻只有单个线程执行该段代码


  private static int result1 = 0;
  private static int result2 = 0;

  // result++ 并不是原子操作

  private static void unSynchronizedTest() {
    result1++;
  }
  private static synchronized void synchronizedTest() {
    result2++;
  }

  public static void main(String[] args) throws Exception {
    ExecutorService executorService = Executors.newFixedThreadPool(5000);

    for (int i = 0; i < 5000; i++) {
      System.out.println("Submit " + (i + 1) + " Thread");
      executorService.submit(SynchronizedTest::unSynchronizedTest);
      executorService.submit(SynchronizedTest::synchronizedTest);
    }

    // 停止并等待一定的时间
    // 如果所有的任务都完成了才会打印出结果
    executorService.shutdown();
    if (executorService.awaitTermination(10, TimeUnit.SECONDS)) {
      // result of unSynchronizedTest: 4993
      // result of synchronizedTest: 5000
      System.out.println("result of unSynchronizedTest: " + result1);
      System.out.println("result of synchronizedTest: " + result2);
    }
  }
}
