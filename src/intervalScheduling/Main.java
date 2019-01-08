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

    //储存以第index个工作为最后一个工作的情况下的最大权值
    private int[] values;

    private void initValue() {
        //初始化用于存储权值之和的数组
        values = new int[tasks.size()];
        for (int i = 0; i < values.length; i++) {
            values[i] = -1;
        }
    }

    public void init() {
        initTasks();
        initValue();
    }

    private int maxValueEndWith(int i) {
        if (i < 0) {
            return 0;
        }
        //如果改工作之前的最大权值没有算过则计算，计算过则直接返回
        if (values[i] == -1) {
            values[i] = Math.max(tasks.get(i).value + maxValueEndWith(tasks.get(i).p),
                    maxValueEndWith(i - 1));
        }
        return values[i];
    }

    private void Find_Solution(int i) {
        if (i < 0) {

        } else if (tasks.get(i).value + maxValueEndWith(tasks.get(i).p) > maxValueEndWith(i - 1)) {
            //若当前工作的权值与前面所有能与它相容的权值之和大于它前面的任务的最大权值，则最大权值包含当前工作
            System.out.println(tasks.get(i));
            //计算与当前工作相容的前一个任务极其前面的任务构成的最大权值
            Find_Solution(tasks.get(i).p);
        } else {
            //不需要当前的任务的情况下权值之和更大，所以丢弃当前工作
            Find_Solution(i - 1);
        }
    }

    public void Find_Solution() {
        Find_Solution(tasks.size() - 1);
    }


    public static void main(String[] args) {
        ArrayList<Job> jobs = new ArrayList<>();
//        jobs.add(new Job(1, 3, 1));
//        jobs.add(new Job(2, 5, 3));
//        jobs.add(new Job(4, 8, 1));
//        jobs.add(new Job(0, 1, 3));
        jobs.add(new Job(1, 4, 2));
        jobs.add(new Job(1, 11, 18));
        jobs.add(new Job(2, 6, 4));
        jobs.add(new Job(3, 10, 7));
        jobs.add(new Job(5, 7, 4));
        jobs.add(new Job(7, 12, 17));
        jobs.add(new Job(18, 88, 17));
//        int value = solution(jobs);
//        System.out.println(value);
        Main wis = new Main();
        wis.init();
        wis.Find_Solution();
    }

//    private static int solution(ArrayList<Job> jobs) {
//        if (jobs.size() <= 0) {
//            return 0;
//        }
//        int maxValue = 0;
//        for (int i = 0; i < jobs.size(); i++) {
//            ArrayList<Job> cloneJob = (ArrayList<Job>) jobs.clone();
//            int value = cloneJob.get(i).value;
//            cloneJob.remove(i);
//            for (int j = 0; j < cloneJob.size(); j++) {
//                if (cloneJob.get(j).start <= jobs.get(i).end) {
//                    cloneJob.remove(j);
//                    j--;
//                }
//            }
//            value += solution(cloneJob);
//            if (maxValue < value) {
//                maxValue = value;
//            }
//        }
//        return maxValue;
//    }
}
