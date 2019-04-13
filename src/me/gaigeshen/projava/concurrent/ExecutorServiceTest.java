package me.gaigeshen.projava.concurrent;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author gaigeshen
 */
public class ExecutorServiceTest {

  // ThreadPoolExecutor
  // ScheduledThreadPoolExecutor

  public static void main(String[] args) {
    ExecutorService executorService = Executors.newFixedThreadPool(10, r -> {
      Thread thread = new Thread(r);

      // 设置是否是守护线程
      // 当JVM只存在守护线程则会退出
      // 当此处设置为守护线程的时候，后面的任务可能不会被正常执行
      thread.setDaemon(false);
      System.out.println("Return thread created: " + thread.getName());
      return thread;
    });

    executorService.execute(() -> {
      for (int i = 0; i < 99999; i++) {
        System.out.println("Current: " + i);
      }
      System.out.println("Task end");
    });

    // 取决于线程是否可停止
    // 睡眠状态的可停止
    // 如果不调用该方法，且存在非守护线程，则程序不会终止
    executorService.shutdownNow();
  }
}
