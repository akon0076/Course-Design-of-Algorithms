package intervalScheduling;

import java.util.ArrayList;
import java.util.Collections;

class Task implements Comparable<Task> {
  int start;//开始时间
  int end;//结束时间
  int value;//工作价值
  int p;//若完成该工作最近完成的工作

  public Task(int begin, int end, int value) {
    this.start = begin;
    this.end = end;
    this.value = value;
    this.p = -1;
  }

  public static void computeP(ArrayList<Task> tasks) {
    //将工作排序
    Collections.sort(tasks);
    for (int i = 0; i < tasks.size(); i++) {
      for (int j = 0; j < tasks.size(); j++) {
        if (tasks.get(j).start > tasks.get(i).end) {
          tasks.get(j).p = i;
        }
      }
    }
  }

  @Override
  public int compareTo(Task o) {
    return new Integer(end).compareTo(new Integer(o.end));
  }

  @Override
  public String toString() {
    return String.format("(%3d,%3d,%3d)", start, end, value);
  }
}
