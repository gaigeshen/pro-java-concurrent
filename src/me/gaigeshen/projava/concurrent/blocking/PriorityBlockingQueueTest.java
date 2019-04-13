package me.gaigeshen.projava.concurrent.blocking;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.PriorityBlockingQueue;

/**
 * 优先级阻塞队列
 *
 * @author gaigeshen
 */
public class PriorityBlockingQueueTest {

  public static void main(String[] args) {

    BlockingQueue<Animal> queue = new PriorityBlockingQueue<>();

    try {
      queue.put(new Animal("dog"));
      queue.put(new Animal("pig"));
      queue.put(new Animal("cat"));
      queue.put(new Animal("panda"));

      // 按字母的排序输出
      System.out.println(queue.take());
      System.out.println(queue.take());
      System.out.println(queue.take());
      System.out.println(queue.take());

      queue.put(new Animal("dog"));
      queue.put(new Animal("pig"));
      queue.put(new Animal("cat"));
      queue.put(new Animal("panda"));

      System.out.println("===================");

      // 不一定符合排序规则
      for (Animal animal : queue) {
        System.out.println(animal);
      }

    } catch (InterruptedException e) {
      e.printStackTrace();
    }

  }

}

class Animal implements Comparable<Animal> {

  private String name;

  Animal(String name) {
    this.name = name;
  }

  @Override
  public int compareTo(Animal other) {
    if (other == this) {
      return 0;
    }
    return name.compareTo(other.name);
  }

  public String getName() {
    return name;
  }

  @Override
  public String toString() {
    return "Animal [name=" + name + "]";
  }

}
