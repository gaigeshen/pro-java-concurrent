package me.gaigeshen.projava.concurrent.forkjoin;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.RecursiveAction;

/**
 * @author gaigeshen
 */
public class MyRecursiveAction extends RecursiveAction {
  private long workLoad;

  MyRecursiveAction(long workLoad) {
    this.workLoad = workLoad;
  }

  @Override
  protected void compute() {
    if (workLoad > 16) {
      System.out.println("Splitting workLoad: " + workLoad);

      List<MyRecursiveAction> subTasks = new ArrayList<>(createSubTasks());

      for (MyRecursiveAction action : subTasks) {
        action.fork();
      }
    } else {
      System.out.println("Doing action myself: " + workLoad);
    }

  }

  private List<MyRecursiveAction> createSubTasks() {

    List<MyRecursiveAction> subTasks = new ArrayList<>();

    MyRecursiveAction action1 = new MyRecursiveAction(workLoad / 2);
    MyRecursiveAction action2 = new MyRecursiveAction(workLoad / 2);

    subTasks.add(action1);
    subTasks.add(action2);

    return subTasks;
  }

}
