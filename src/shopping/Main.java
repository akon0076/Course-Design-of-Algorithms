package shopping;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

public class Main {
    public static void main(String[] args) {
        ArrayList<Commodity> commodities = new ArrayList<>();
        HashMap<Integer, Integer> item = new HashMap<>();
        item.put(1, 3);
        item.put(2, 2);
        HashMap<Integer, Integer> c1 = new HashMap<Integer, Integer>();
        HashMap<Integer, Integer> c2 = new HashMap<Integer, Integer>();
        HashMap<Integer, Integer> c3 = new HashMap<Integer, Integer>();
        HashMap<Integer, Integer> c4 = new HashMap<Integer, Integer>();
        c1.put(1, 1); //花
        c2.put(2, 1); //花瓶
        c3.put(1, 3); //3朵花
        c4.put(1, 1);
        c4.put(2, 2); //两个花瓶加一朵花
        commodities.add(new Commodity(c1, 2));
        commodities.add(new Commodity(c2, 5));
        commodities.add(new Commodity(c3, 5));
        commodities.add(new Commodity(c4, 10));
        System.out.println(solution(commodities, item, 0));
    }

    public static int solution(ArrayList<Commodity> commodities, HashMap<Integer, Integer> item, int countPrice) {
        //如果item中每个商品的需求为0则递归完成
        Iterator<Integer> itemIterator = item.keySet().iterator();
        boolean isReturn = true;
        while (itemIterator.hasNext()) {
            int next = itemIterator.next();
            if (item.get(next) > 0) {
                isReturn = false;
            }
        }
        if (isReturn == true) {
            return countPrice;
        }
        //初始化最小价格
        int minPrice = 65536;
        //以每种套餐来匹配需要的商品
        for (Commodity commodity : commodities) {
            boolean suit = true;//判断是否能匹配成功
            //获取套餐商品列表
            Iterator<Integer> temp = commodity.commodityMap.keySet().iterator();
            while (temp.hasNext()) {
                Integer cId = temp.next();
                Integer isNeed = item.get(cId);
                if (isNeed == null || isNeed - commodity.commodityMap.get(cId) < 0)  {
                    suit = false;
                    break;
                }
            }
            if (suit == true) {
                Iterator<Integer> commodityList = commodity.commodityMap.keySet().iterator();
                HashMap<Integer, Integer> newItem = (HashMap<Integer, Integer>)item.clone();
                while (commodityList.hasNext()) {
                    int cId = commodityList.next();
                    newItem.put(cId, newItem.get(cId) - commodity.commodityMap.get(cId));
                }
                int price = solution(commodities, newItem, commodity.P + countPrice);
                if (minPrice > price) {
                    minPrice = price;
                }
            }
        }
        return minPrice;
    }
}
