import java.util.*;

public class Main {
    static final int mod = 1000000007;
    static int n, k;
    static long[] d, h, f;
    static int[] mu, p;
    static boolean[] st;
    static int tot;
    static final int mn = 2000010;
    static final int N = 2000000;
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        n = scanner.nextInt();
        k = scanner.nextInt();
        init();
        for (int i = 1; i <= k; i++) {
            for (int j = 1; j <= k / i; j++) {
                f[i * j] += mu[j] * h[i];
                f[i * j] %= mod;
            }
        }
        long res = 0, sum = 0;
        for (int i = 1; i <= k; i++) {
            sum += f[i];
            sum = (sum % mod + mod) % mod;
            res += powMod(sum, i);
            res %= mod;
        }
        System.out.println(res);
    }

    static void init() {
        d = new long[k + 1];
        h = new long[k + 1];
        f = new long[k + 1];
        mu = new int[mn];
        p = new int[mn];
        st = new boolean[mn];
        mu[1] = 1;
        for (int i = 2; i <= N; i++) {
            if (!st[i]) {
                p[++tot] = i;
                mu[i] = -1;
            }
            for (int j = 1; i * p[j] <= N; j++) {
                st[i * p[j]] = true;
                if (i % p[j] == 0) {
                    mu[i * p[j]] = 0;
                    break;
                }
                mu[i * p[j]] = -mu[i];
            }
        }
        for (int i = 1; i <= k; i++) {
            d[i] = qmi(i, n);
            h[i] = d[i] - d[i - 1];
        }
    }
    static long qmi(long a, long b) {
        long s = 1;
        while (b > 0) {
            if ((b & 1) == 1) {
                s = s * a % mod;
            }
            a = a * a % mod;
            b >>= 1;
        }
        return s;
    }
    static long powMod(long base, long exponent) {
        long result = 1;
        while (exponent > 0) {
            if ((exponent & 1) == 1) {
                result = result * base % mod;
            }
            base = base * base % mod;
            exponent >>= 1;
        }
        return result;
    }
}
