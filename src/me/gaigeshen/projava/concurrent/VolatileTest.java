package me.gaigeshen.projava.concurrent;

/**
 * @author gaigeshen
 */
public class VolatileTest {
  public static void main(String[] args) throws InterruptedException {
    Demo demo = new Demo();

    Thread threadA = new Thread(demo::set);
    Thread threadB = new Thread(demo::loop);

    threadA.start();
    threadB.start();

    threadA.join();
    threadB.join();

    /*
     * 由于指令重排序
     * 导致结果不稳定
     */
  }
}

class Demo {
  int a = 0;
  int b = 1;

  void set() {
    // 如果顺序不变则打印的结果是期望的XXX
    // 如果顺序重排则打印的结果有可能是YYY
    a = 1;
    b = 1;
  }

  void loop() {
    while (b == 0) { }
    if (a == 1) {
      System.out.println("XXX");
    } else {
      System.out.println("YYY");
    }
  }

}
