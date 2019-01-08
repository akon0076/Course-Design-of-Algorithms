package binomial;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;

public class Main {
    public static void main(String[] args) {
        int k = 25;
        int n = 30;
        int[][] array = new int[k + 1][n + 1];
        Binomial binomial = new Binomial();
        System.out.println("从" + n + "个中挑选出" + k + "个");
        System.out.println("递归算法结果：" + binomial.calculateBinomial(k, n) + " 共计调用" + binomial.COUNT + "次");
        System.out.println("备忘录方法结果：" + binomial.calculateBinomial_Memorandum(k, n, array) + " 共计调用" + binomial.COUNT_MEMORANDUM + "次");

//        Writer file = null;
//        try {
//            file = new FileWriter("diagram.csv");
//            file.write("k,n,calculateBinomialResult,COUNT,calculateBinomial_MemorandumResult,COUNT_MEMORANDUM\n");
//            String line = null;
//            for (int i = 5; i <= 30; i += 5) {
//                binomial.COUNT = 0;
//                binomial.COUNT_MEMORANDUM = 0;
//                array = new int[i / 4 + 1][i + 1];
//                int calculateBinomialResult = binomial.calculateBinomial(i / 4, i);
//                int calculateBinomial_MemorandumResult = binomial.calculateBinomial_Memorandum(i / 4, i, array);
//                line = i / 4 + "," + i + "," + calculateBinomialResult + "," + binomial.COUNT + "," + calculateBinomial_MemorandumResult + "," + binomial.COUNT_MEMORANDUM +"\n";
//                System.out.println(line);
//                file.write(line);
//            }
//            file.close();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
    }
}
