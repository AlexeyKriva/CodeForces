import java.util.Scanner;

public class Main {
    static double[] prob = new double[256];
    static double[] temp = new double[256];

    static void upgradeTemp() {
        double[] a = new double[256];
        for (int i = 0; i < 256; i++) {
            for (int j = 0; j < 256; j++) {
                a[i ^ j] += temp[i] * prob[j];
            }
        }
        temp = a;
    }

    static void upgradeProb() {
        double[] a = new double[256];
        for (int i = 0; i < 256; i++) {
            for (int j = 0; j < 256; j++) {
                a[i ^ j] += prob[i] * prob[j];
            }
        }
        prob = a;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int m = scanner.nextInt();
        String s;
        for (int i = 0; i <= m; i++) {
            s = scanner.next();
            prob[i] = Double.parseDouble(s);
        }
        temp[0] = 1;
        while (n > 0) {
            if ((n & 1) == 1) {
                upgradeTemp();
            }
            upgradeProb();
            n >>= 1;
        }
        double result = 1 - temp[0];
        System.out.printf("%.6f", result);
    }
}
