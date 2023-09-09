import java.util.Scanner;

public class Main {
    static final int N = 500005;
    static long n = 0, k = 0, money[] = new long[N], sum = 0;
    static long rich() {
        long left = 0, right = sum, middle = 0, cou = 0, res = 0;
        if (sum % n == 0) left = sum / n;
        else left = sum / n + 1;
        while (left <= right) {
            middle = (left + right) >> 1;
            cou = 0;
            for (int i = 1; i <= n; i++) {
                if (money[i] > middle) cou += money[i] - middle;
            }
            if (cou <= k) {
                res = middle;
                right = middle - 1;
            } else {
                left = middle + 1;
            }
        }
        return res;
    }

    static long poor() {
        long left = 1, right = sum / n, middle = 0, cou = 0, res = 0;
        while (left <= right) {
            middle = (left + right) >> 1;
            cou = 0;
            for (int i = 1; i <= n; i++) {
                if (money[i] < middle) cou += middle - money[i];
            }
            if (cou <= k) {
                res = middle;
                left = middle + 1;
            } else {
                right = middle - 1;
            }
        }
        return res;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        n = scanner.nextLong();
        k = scanner.nextLong();
        for (int i = 1; i <= n; i++) {
            money[i] = scanner.nextLong();
        }
        for (int i = 1; i <= n; i++) {
            sum += money[i];
        }
        System.out.println(rich() - poor());
        scanner.close();
    }
}
