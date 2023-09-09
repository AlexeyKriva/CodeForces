import java.util.Arrays;
import java.util.Scanner;

public class Main {
    static final long BIG_NUMBER = (long) 1e18;
    static long[][] prosedComplexity = new long[82][82];
    static long[][][][] info = new long[82][82][82][2];

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int k = scanner.nextInt();
        int m = scanner.nextInt();




        for (long[][][] info1 : info) {
            for (long[][] info2 : info1) {
                for (long[] info3 : info2) {
                    Arrays.fill(info3, -1);
                }
            }
        }

        long result = findResult(0, n, k, true);
        if (result >= BIG_NUMBER) {
            System.out.println(-1);
        } else {
            System.out.println(result);
        }
    }

    static long findResult(int a, int b, int l, boolean isLeft) {
        if (a > b) {
            return BIG_NUMBER;
        }

        if (l == 0) {
            return 0;
        }
        int t = 0;
        if (isLeft){
            t = 1;
        }
        long result = info[a][b][l][t];
        if (result != -1) {
            return result;
        }

        result = BIG_NUMBER;
        if (isLeft) {
            for (int i = a + 1; i <= b; i++) {
                result = Math.min(result, findResult(i, b, l - 1, true) + prosedComplexity[a][i]);
                result = Math.min(result, findResult(a + 1, i, l - 1, false) + prosedComplexity[a][i]);
            }
        } else {
            for (int i = a; i < b; i++) {
                result = Math.min(result, findResult(i, b - 1, l - 1, true) + prosedComplexity[b][i]);
                result = Math.min(result, findResult(a, i, l - 1, false) + prosedComplexity[b][i]);
            }
        }

        return info[a][b][l][t] = result;
    }
}