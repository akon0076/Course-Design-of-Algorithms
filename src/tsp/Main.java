package tsp;

import java.util.ArrayList;
import java.util.PriorityQueue;

public class Main {
    static int bestSoFar = 1000;
    static int infinity = 1000;
    private static int[][] array = {{0, 3, 1, 5, 8},
            {3, 0, 6, 7, 9},
            {1, 6, 0, 4, 2},
            {5, 7, 4, 0, 3},
            {8, 9, 2, 3, 0}};
//    private static int[][] array =
//           {{0, 2, 5, 7},
//            {2, 0, 8, 3},
//            {5, 8, 0, 1},
//            {7, 3, 1, 0}};

    public static void main(String[] args) {
        PriorityQueue<Node> S = new PriorityQueue();
        Node root = new Node();
        root.lowerBound = getLowerBound(root);
        S.add(root);
        while (S.isEmpty() == false) {
            Node node = S.remove();
            if (node.lowerBound > bestSoFar) {
                continue;
            }

            for (Node child: allFeasibleChildren(node)) {
                if (isACompleteSolution(child))
                    bestSoFar = Math.min(bestSoFar, child.lowerBound);
                else {
                    S.add(child);
                }
            }
        }
        System.out.println(bestSoFar);
    }

    private static Iterable<Node> allFeasibleChildren(Node node) {
        ArrayList<Node> nodeList = new ArrayList<>();
        int depth = node.depth + 1;
        for (int i = 1; i < array[0].length; i++) {
            if (node.partialSolution.containsValue(i) || i == depth - 1)
                continue;
            Node child = new Node();
            child.partialSolution.putAll(node.partialSolution);
            child.partialSolution.put(depth, i);
            child.depth = depth;
            child.lowerBound = getLowerBound(child);
            nodeList.add(child);
        }
        return nodeList;
    }

    private static int getLowerBound(Node node) {
        int lb = 0;
        for (int i = 0; i < array.length; i++) {
            //如果当前节点被选过并且已经选了下一个结
            if (node.partialSolution.containsKey(i + 1) && node.partialSolution.containsValue(i)) {
              int need = 0;
              for (int x = 1; x <= node.partialSolution.size(); x++) {
                if (node.partialSolution.get(x) == i) {
                  need = x - 1;
                  break;
                }
              }
              lb += array[i][node.partialSolution.get(i + 1)] + array[i][need];
            }//如果这个节点已经被选过
            else if (node.partialSolution.containsKey(i + 1)) {
                int tempMin = infinity;
                for (int j = 0; j < array[0].length; j++) {
                    if (array[i][j] == 0 || j == node.partialSolution.get(i + 1))
                        continue;
                    if(array[i][j] < tempMin)
                        tempMin = array[i][j];
                }
                lb += tempMin + array[i][node.partialSolution.get(i + 1)];
            } else if (node.partialSolution.containsValue(i)) {
                int need = 0;
                for (int x = 1; x <= node.partialSolution.size(); x++) {
                    if (node.partialSolution.get(x) == i) {
                        need = x - 1;
                        break;
                    }
                }
                int tempMin = infinity;
                for (int j = 0; j < array[0].length; j++) {
                    if (array[i][j] == 0 || j == need)
                        continue;
                    if(array[i][j] < tempMin)
                        tempMin = array[i][j];
                }
                lb += tempMin + array[i][need];
            }
            else {
                int m1, m2;//存储两个最小值
                m1 = infinity;
                m2 = infinity;
                for (int j = 0; j < array[i].length; j++) {
                    if (array[i][j] <= 0) {
                        continue;
                    }
                    if (array[i][j] < m1) {
                        m2 = m1;
                        m1 = array[i][j];
                    } else if (array[i][j] < m2) {
                        m2 = array[i][j];
                    }
                }
                lb += m1 + m2;
            }

        }
        int result = (int) Math.ceil((double)lb / (double)2);
        return result;
    }

    private static boolean isACompleteSolution(Node child) {
        return child.depth == array.length - 1;
    }
}
