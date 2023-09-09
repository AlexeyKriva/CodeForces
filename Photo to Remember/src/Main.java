import java.util.Scanner;
public class Main {
    static final int MAX = 2 * 10000 + 5;
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int[] w = new int[MAX];
        int[] h = new int[MAX];
        int mw = 0;
        int sumw;
        int res = Integer.MAX_VALUE;
        for (int i = 1; i <= n; i++) {
            w[i] = scanner.nextInt();
            h[i] = scanner.nextInt();
            if (w[i] > h[i]) {
                int temp = w[i];
                w[i] = h[i];
                h[i] = temp;
            }
            mw = Math.max(mw, w[i]);
        }
        for (int o = mw; o <= 1000; o++) {
            sumw = 0;
            for (int i = 1; i <= n; i++) {
                if (h[i] <= o) sumw += w[i];
                else sumw += h[i];
            }
            res = Math.min(res, sumw * o);
        }
        System.out.println(res);
    }
}
