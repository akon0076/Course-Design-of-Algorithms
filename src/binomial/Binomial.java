package binomial;

public class Binomial {
    Integer COUNT = 0;
    Integer COUNT_MEMORANDUM = 0;

    //递归算法
    public int calculateBinomial(int k, int n) {
        this.COUNT++;
        if (k > n) {
            k = n - k;
        }
        if (k == 1) {
            return n;
        }
        if (k == n) {
            return 1;
        }
        return calculateBinomial(k - 1, n - 1) + calculateBinomial(k, n - 1);
    }

    //备忘录方法
    public int calculateBinomial_Memorandum(int k, int n, int[][] array) {
        this.COUNT_MEMORANDUM++;
        int result = 0;
        if (k == 1) {
            return n;
        }
        if (k == n) {
            return 1;
        }
        if (array[k - 1][n - 1] > 0) {
            result += array[k - 1][n - 1];
        } else {
            array[k - 1][n - 1] = calculateBinomial_Memorandum(k - 1, n - 1, array);
            result += array[k - 1][n - 1];
        }
        if (array[k][n - 1] > 0) {
            result += array[k][n - 1];
        } else {
            array[k][n - 1] = calculateBinomial_Memorandum(k, n - 1, array);
            result += array[k][n - 1];
        }
        return result;
    }
}
