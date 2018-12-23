package intervalScheduling;

import java.util.ArrayList;
import java.util.Collections;

class Task implements Comparable<Task> {
  int begin;
  int end;
  int weight;
  int p;

  public Task(int begin, int end, int weight) {
    this.begin = begin;
    this.end = end;
    this.weight = weight;
    this.p = -1;
  }

  public static void computeP(ArrayList<Task> tasks) {
    Collections.sort(tasks);
    for (int i = 0; i < tasks.size(); i++) {
      for (int j = 0; j < tasks.size(); j++) {
        if (tasks.get(j).begin > tasks.get(i).end) {
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
    return String.format("(%3d,%3d,%3d)", begin, end, weight);
  }
}
