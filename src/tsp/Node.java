package tsp;

import java.util.HashMap;

public class Node implements Comparable<Node> {
    int lowerBound;
    HashMap<Integer, Integer> partialSolution;
    int depth ;

    public Node(){
        partialSolution = new HashMap<>();
        lowerBound = 0;
        depth = 0;
    }
    @Override
    public int compareTo(Node o) {
        return this.lowerBound - o.lowerBound;
    }
}
