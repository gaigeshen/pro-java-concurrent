package me.gaigeshen.projava.concurrent.blocking;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.DelayQueue;
import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

/**
 * 延迟队列
 *
 * @author gaigeshen
 */
public class DelayQueueTest {
  public static void main(String[] args) {
    BlockingQueue<Delayed> queue = new DelayQueue<>();

    // 延迟三秒
    Delayed person = new Person("gaigeshen", 3000);
    try {
      queue.put(person);
      System.out.println("after put");
    } catch (InterruptedException e) {
      e.printStackTrace();
    }

    try {
      // 三秒后可以取出数据
      Delayed delayed = queue.take();
      System.out.println(delayed);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }

}

class Person implements Delayed {

  private long start;
  private long end;

  private long delay;

  private String name;

  Person(String name, long delay) {
    this.start = System.currentTimeMillis();
    this.end = this.start + delay;
    this.name = name;
    this.delay = delay;
  }

  @Override
  public int compareTo(Delayed other) {
    if (other == this) {
      return 0;
    }
    long d = (getDelay(TimeUnit.NANOSECONDS) - other.getDelay(TimeUnit.NANOSECONDS));
    return (d == 0) ? 0 : ((d < 0) ? -1 : 1);
  }

  @Override
  public long getDelay(TimeUnit unit) {

    long remaining = end - System.currentTimeMillis();

    return unit.convert(remaining, TimeUnit.MILLISECONDS);
  }

  public String getName() {
    return name;
  }

  @Override
  public String toString() {
    return "Person [delay=" + delay + ", name=" + name + "]";
  }

}

