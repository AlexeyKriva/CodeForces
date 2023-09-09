import java.util.*;
import java.math.*;
import java.io.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int l = sc.nextInt();
        int r = sc.nextInt();
        int[] x = new int[20];
        int[] y = new int[20];
        int[] a = new int[20];
        double[] deg = new double[20];
        for (int i = 0; i < n; i++) {
            x[i] = sc.nextInt();
            y[i] = sc.nextInt();
            a[i] = sc.nextInt();
        }
        double pi = Math.acos(0.5) * 3;
        double eps = 1e-10;
        double[] dp = new double[1 << 20];
        for (int i = 0; i < (1 << n); i++) {
            dp[i] = -1e9;
        }
        dp[0] = l;
        for (int i = 0; i < (1 << n); i++) {
            if (dp[i] != -1e9) {
                double c = dp[i];
                for (int j = 0; j < n; j++) {
                    if ((i >> j & 1) == 0) {
                        double d = dist(c, 0, x[j], y[j]);
                        double d2 = Math.abs(Math.atan2(Math.abs(x[j] - c), y[j]));
                        double D = pi / 2 - Math.abs(deg[j] - d2);
                        if (c > x[j] + eps) {
                            D = pi / 2 - d2 - deg[j];
                            if (D < eps) {
                                System.out.printf("%.10f", (double) (r - l));
                                System.out.println();
                                return;
                            }
                        }
                        double xx = d * Math.sin(deg[j]) / Math.sin(D);
                        dp[i | (1 << j)] = Math.max(dp[i | (1 << j)], c + xx);
                    }
                }
            }
        }
        System.out.printf("%.10f", Math.min((double) (r - l), dp[(1 << n) - 1] - l));
        System.out.println();
    }

    static double sq(double x) {
        return x * x;
    }

    static double dist(double x, double y, double xx, double yy) {
        return Math.sqrt(sq(x - xx) + sq(y - yy));
    }
}
