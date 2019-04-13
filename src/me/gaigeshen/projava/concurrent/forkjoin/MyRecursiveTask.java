package me.gaigeshen.projava.concurrent.forkjoin;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.RecursiveTask;

/**
 * @author gaigeshen
 */
public class MyRecursiveTask extends RecursiveTask<Long> {
  private long workLoad;

  MyRecursiveTask(long workLoad) {
    this.workLoad = workLoad;
  }

  @Override
  protected Long compute() {
    if (workLoad > 16) {
      System.out.println("Splitting workLoad: " + workLoad);

      List<MyRecursiveTask> subTasks = new ArrayList<>(createSubTasks());

      for (MyRecursiveTask task : subTasks) {
        task.fork();
      }

      long result = 0;

      for (MyRecursiveTask task : subTasks) {
        Long returnValue = task.join();
        System.out.println("Return value from task: " + task);
        result += returnValue; // 合并多个任务的执行结果
      }

      return result;

    } else {

      System.out.println("Doing workLoad myself: " + workLoad);
      try {
        Thread.sleep(2000); // 模拟子任务的耗时操作
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
      return workLoad * 3;
    }

  }


  // 拆分成两个任务
  private List<MyRecursiveTask> createSubTasks() {

    List<MyRecursiveTask> subtasks = new ArrayList<>();

    MyRecursiveTask subtask1 = new MyRecursiveTask(this.workLoad / 2);
    MyRecursiveTask subtask2 = new MyRecursiveTask(this.workLoad / 2);

    subtasks.add(subtask1);
    subtasks.add(subtask2);

    return subtasks;
  }
}
