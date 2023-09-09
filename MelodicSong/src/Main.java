import java.util.Scanner;

public class Main {
    static final int maxn = 500005;
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String s = scanner.next();
        int n = s.length();
        s = " " + s;
        double[] sum = new double[maxn];
        double[] ans = new double[maxn];
        double res = 0;
        for (int i = 1; i <= n; i++) {
            sum[i] = sum[i - 1] + 1.0 / i;
        }
        ans[1] = sum[n];
        for (int i = 1; i <= n; i++) {
            ans[i] = ans[i - 1] - sum[i - 1] + sum[n - i + 1];
        }
        for (int i = 1; i <= n; i++) {
            if (check(s.charAt(i))) {
                res += ans[i];
            }
        }
        System.out.printf("%.7f%n", res);
    }
    static boolean check(char c) {
        return c == 'A' || c == 'E' || c == 'I' || c == 'O' || c == 'U' || c == 'Y';
    }
}
