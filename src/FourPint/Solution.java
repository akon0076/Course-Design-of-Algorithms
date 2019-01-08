package FourPint;

import java.util.*;

public class Solution {
    static Scanner in = new Scanner(System.in);
    static int a = 8, b = 5, c = 3, des = 4;
    static boolean[][][] status = new boolean[9][6][4];
    public static void main(String[] args) {
        for (int i = 0; i < 9; i++)
            for (int j = 0; j < 6; j++)
                for (int k = 0; k < 4; k++)
                    status[i][j][k] = false;
        Node node = new Node(8, 0, 0, null);
        Queue<Node> result = new LinkedList<>();
        System.out.println("初始: 8 0 0");
        findFourPint(result, node);
        System.out.println("正确操作:");
        result.add(node);
        while (result.size() > 0) {
            Node tempNode = ((LinkedList<Node>) result).pollLast();
            System.out.println(tempNode.a + " " + tempNode.b + " " + tempNode.c);
        }
    }

    private static boolean findFourPint(Queue<Node> result, Node node) {
        if (node.a == des || node.b == des || node.c == des) {
            return true;
        }
        Queue<Node> queue = new LinkedList<>();
        int minSize;
        minSize = Math.min(node.a, b - node.b);
        if (!status[node.a - minSize][node.b + minSize][node.c]) {
            status[node.a - minSize][node.b + minSize][node.c] = true;
            Node nextNode = new Node(node.a - minSize, node.b + minSize, node.c, node);
            System.out.println("尝试: " + nextNode.a + " " + nextNode.b + " " + nextNode.c);
            queue.add(nextNode);
        }
        //a=>c
        minSize = Math.min(node.a, c - node.c);
        if (!status[node.a - minSize][node.b][node.c + minSize]) {
            status[node.a - minSize][node.b][node.c + minSize] = true;
            Node nextNode = new Node(node.a - minSize, node.b, node.c + minSize, node);
            System.out.println("尝试: " + nextNode.a + " " + nextNode.b + " " + nextNode.c);
            queue.add(nextNode);
        }
        //b=>c
        minSize = Math.min(node.b, c - node.c);
        if (!status[node.a][node.b - minSize][node.c + minSize]) {
            status[node.a][node.b - minSize][node.c + minSize] = true;
            Node nextNode = new Node(node.a, node.b - minSize, node.c + minSize, node);
            System.out.println("尝试: " + nextNode.a + " " + nextNode.b + " " + nextNode.c);
            queue.add(nextNode);
        }
        //b=>a
        minSize = Math.min(node.b, a - node.a);
        if (!status[node.a + minSize][node.b - minSize][node.c]) {
            status[node.a + minSize][node.b - minSize][node.c] = true;
            Node nextNode = new Node(node.a + minSize, node.b - minSize, node.c, node);
            System.out.println("尝试: " + nextNode.a + " " + nextNode.b + " " + nextNode.c);
            queue.add(nextNode);
        }
        //c=>b
        minSize = Math.min(node.c, b - node.b);
        if (!status[node.a][node.b + minSize][node.c - minSize]) {
            status[node.a][node.b + minSize][node.c - minSize] = true;
            Node nextNode = new Node(node.a, node.b + minSize, node.c - minSize, node);
            System.out.println("尝试: " + nextNode.a + " " + nextNode.b + " " + nextNode.c);
            queue.add(nextNode);
        }
        //c=>a
        minSize = Math.min(node.c, a - node.a);
        if (!status[node.a + minSize][node.b][node.c - minSize]) {
            status[node.a + minSize][node.b][node.c - minSize] = true;
            Node nextNode = new Node(node.a + minSize, node.b, node.c - minSize, node);
            System.out.println("尝试: " + nextNode.a + " " + nextNode.b + " " + nextNode.c);
            queue.add(nextNode);
        }
        while (queue.size() > 0) {
            Node tempNode = queue.poll();
            boolean isAdd = findFourPint(result, tempNode);
            if (isAdd) {
                result.add(tempNode);
                return true;
            }
        }
        return false;
    }

}

class Node {
    int a, b, c;
    Node parent;//父节点，打印路径

    Node(int a, int b, int c, Node p) {
        this.a = a;
        this.b = b;
        this.c = c;
        this.parent = p;
    }
}
