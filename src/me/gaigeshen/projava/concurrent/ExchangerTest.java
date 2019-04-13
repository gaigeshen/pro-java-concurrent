package me.gaigeshen.projava.concurrent;

import java.util.concurrent.Exchanger;

/**
 * 交换器
 *
 * @author gaigeshen
 */
public class ExchangerTest {
  public static void main(String[] args) {
    Exchanger<Object> exchanger = new Exchanger<>();

    ExchangerRunnable runnable1 = new ExchangerRunnable(exchanger, "A");
    ExchangerRunnable runnable2 = new ExchangerRunnable(exchanger, "B");

    new Thread(runnable1).start();
    new Thread(runnable2).start();
  }
}

class ExchangerRunnable implements Runnable {

  private Exchanger<Object> exchanger;
  private Object object;

  ExchangerRunnable(Exchanger<Object> exchanger, Object object) {
    this.exchanger = exchanger;
    this.object = object;
  }

  public void run() {
    try {
      Object previous = object;

      object = exchanger.exchange(this.object);

      System.out.println(Thread.currentThread().getName() + " exchanged " + previous + " for " + object);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }
}
