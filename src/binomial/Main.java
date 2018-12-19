package binomial;

public class Main {
    public static void main(String[] args) {
        int k = 4;
        int n = 36;
        int[][] array = new int[k + 1][n + 1];
        Binomial binomial = new Binomial();
        System.out.println("从" + n + "个中挑选出" + k + "个");
        System.out.println("递归算法结果：" + binomial.calculateBinomial(k, n) + " 共计调用" + binomial.COUNT + "次");
        System.out.println("备忘录方法结果：" + binomial.calculateBinomial_Memorandum(k, n, array) + " 共计调用" + binomial.COUNT_MEMORANDUM + "次");
    }
}
