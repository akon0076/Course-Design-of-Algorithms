package shopping;

import java.util.HashMap;

public class Commodity {
    HashMap<Integer, Integer> commodityMap;
    int P;

    public Commodity(HashMap<Integer, Integer> commodityMap, int P) {
        this.commodityMap = commodityMap;
        this.P = P;
    }
}
