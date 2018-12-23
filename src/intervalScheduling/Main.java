package intervalScheduling;

import java.util.ArrayList;

public class Main {
  private ArrayList<Task> tasks = new ArrayList<>();

  private void initTasks() {
    tasks.add(new Task(1, 4, 2));
    tasks.add(new Task(1, 11, 18));
    tasks.add(new Task(2, 6, 4));
    tasks.add(new Task(3, 10, 7));
    tasks.add(new Task(5, 7, 4));
    tasks.add(new Task(7, 12, 17));
    Task.computeP(tasks);
  }

  private int[] M;

  private void initM() {
    M = new int[tasks.size()];
    for (int i = 0; i < M.length; i++) {
      M[i] = -1;
    }
  }

  public void init() {
    initTasks();
    initM();
  }

  private int M_Compute_Opt(int i) {
    if (i < 0) {
      return 0;
    }
    if (M[i] == -1) {
      M[i] = Math.max(tasks.get(i).weight + M_Compute_Opt(tasks.get(i).p),
        M_Compute_Opt(i - 1));
    }
    return M[i];
  }

  private void Find_Solution(int i) {
    if (i < 0) {

    } else if (tasks.get(i).weight + M_Compute_Opt(tasks.get(i).p) > M_Compute_Opt(i - 1)) {
      System.out.println(tasks.get(i));
      Find_Solution(tasks.get(i).p);
    } else {
      Find_Solution(i - 1);
    }
  }

  public void Find_Solution() {
    Find_Solution(tasks.size() - 1);
  }


  public static void main(String[] args) {
//        ArrayList<Job> jobs = new ArrayList<>();
////        jobs.add(new Job(1, 3, 1));
////        jobs.add(new Job(2, 5, 3));
////        jobs.add(new Job(4, 8, 1));
////        jobs.add(new Job(0, 1, 3));
//        jobs.add(new Job(0, 0, 0));
//        jobs.add(new Job(1, 4, 2));
//        jobs.add(new Job(1, 11, 18));
//        jobs.add(new Job(2, 6, 4));
//        jobs.add(new Job(3, 10, 7));
//        jobs.add(new Job(5, 7, 4));
//        jobs.add(new Job(7, 12, 17));
//
//        ArrayList<Job> result = guihua(jobs);
//        int value = 0;
//        for (int i = 0; i < result.size(); i++) {
//            value += result.get(i).value;
//            System.out.println(result.get(i).start + " " + result.get(i).end + " " + result.get(i).value);
//        }
//        System.out.println(value);
    Main wis = new Main();
    wis.init();
    wis.Find_Solution();
  }

  private static ArrayList<Job> guihua(ArrayList<Job> jobs) {
    int[] value = new int[jobs.size()];
    value[0] = 0;
    value[1] = jobs.get(1).value;
    ArrayList<Job> result = new ArrayList<>();
    result.add(jobs.get(1));
    int select = 1;
    for (int i = 2; i < jobs.size(); i++) {
      if (jobs.get(i).start <= jobs.get(select).end) {
        int add = jobs.get(i).value + value[i - 2];
        int[] tempValue = new int[i - 1];
        int tempSelect = 1;
        int tempAdd = 0;
        tempValue[0] = 0;
        tempValue[1] = jobs.get(1).value;
        for (int j = 2; j < i; j++) {
          if (j != select && jobs.get(j).end > jobs.get(i).start) {
            if (jobs.get(j).start <= jobs.get(tempSelect).end) {
              tempAdd = jobs.get(j).value + value[j - 2];
              if (tempAdd > tempValue[tempSelect]) {
                tempValue[j] = tempAdd;
              } else {
                tempValue[j] = tempValue[j - 1];
              }
            }
          }
        }
        add += tempAdd;
        if (add > value[select]) {
          value[i] = add;
          result.remove(result.size() - 1);
          result.add(jobs.get(i));
          select = i;
        } else {
          value[i] = value[i - 1];
        }
      } else {
        value[i] = jobs.get(i).value + value[i - 1];
        result.add(jobs.get(i));
      }
    }


//        int select = 0;
//        for (int i = 1; i < jobs.size(); i++) {
//            if (i == 1) {
//                if (jobs.get(i).start <= endTiem) {
//                    if (value[i] < jobs.get(i).value) {
//                        value[i] = jobs.get(i).value;
//                        result.remove(0);
//                        result.add(jobs.get(i));
//                        endTiem = jobs.get(i).end;
//                        select = i;
//                    } else {
//                        value[2] = value[1];
//                    }
//                } else {
//                    value[2] = value[i] + jobs.get(i).value;
//                    result.add(jobs.get(i));
//                    select = i;
//                }
//                endTiem = jobs.get(i).end;
//            }
//            else
//            if (jobs.get(i).start <= endTiem) {
//                int add = value[select - 1] + jobs.get(i).value;
//                for (int j = select + 1; j < i; j++) {
//                    if (jobs.get(j).start < result.get(result.size() - 1).end) {
//                        int tempAdd = value[j - 2] + jobs.get(j).value;
//                        if (tempAdd > value[j - 1]) {
//                            add += jobs.get(j).value;
//                        }
//                    }
//                }
//                if (add > value[select]) {
//                    value[i] = add;
//                    for (int j = select + 1; j < i; j++) {
//                       if (jobs.get(select + 1).start < result.get(result.size() - 1).end) {
//                           int tempAdd = value[j - 2] + jobs.get(j).value;
//                           if (tempAdd > value[j - 1]) {
//                               result.remove(result.size() - 1);
//                               value[j] = tempAdd;
//                               result.add(jobs.get(j));
//                           }
//                       }
//                    }
//                    result.remove(result.size() - 1);
//                    result.add(jobs.get(i));
//                    endTiem = jobs.get(i).end;
//                    select = i;
//                } else {
//                    value[i] = value[select];
//                }
//            } else {
//                value[i] = value[i - 1] + jobs.get(i).value;
//                result.add(jobs.get(i));
//                select = i;
//                endTiem = jobs.get(i).end;
//            }
//        }
    return result;
  }

  private static int solution(ArrayList<Job> jobs, ArrayList<Job> result) {
    if (jobs.size() <= 0) {
      return 0;
    }
    int maxValue = 0, maxJob = -1;
    for (int i = 0; i < jobs.size(); i++) {
      ArrayList<Job> cloneJob = (ArrayList<Job>) jobs.clone();
      int value = cloneJob.get(i).value;
      cloneJob.remove(i);
      for (int j = 0; j < cloneJob.size(); j++) {
        if (cloneJob.get(j).start <= jobs.get(i).end) {
          cloneJob.remove(j);
          j--;
        }
      }
      value += solution(cloneJob, result);
      if (maxValue < value) {
        maxValue = value;
        maxJob = i;
      }
    }
    result.add(jobs.get(maxJob));
    return maxValue;
  }
}
