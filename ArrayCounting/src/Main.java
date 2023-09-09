import java.util.Scanner;

public class Main {
    static final int MOD = 1000000007;
    static final int N = 2000005;
    static int[] fact = new int[N];
    static int[] invFact = new int[N];

    static int pow(int a, int x) {
        int res = 1;
        while (x > 0) {
            if ((x & 1) == 1) {
                res = (int) ((1L * res * a) % MOD);
            }
            a = (int) ((1L * a * a) % MOD);
            x >>= 1;
        }
        return res;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        fact[0] = 1;
        for (int i = 1; i < N; i++) {
            fact[i] = (int) ((1L * fact[i - 1] * i) % MOD);
        }

        invFact[1] = 1;
        for (int i = 2; i < N; i++) {
            invFact[i] = (int) ((1L * (MOD - MOD / i) * invFact[MOD % i]) % MOD);
        }

        invFact[0] = 1;
        for (int i = 1; i < N; i++) {
            invFact[i] = (int) ((1L * invFact[i - 1] * invFact[i]) % MOD);
        }

        int q = scanner.nextInt();
        for (; q > 0; q--) {
            int x = scanner.nextInt();
            int y = scanner.nextInt();
            y--;
            int res = pow(2, y);
            for (int i = 2, j; i * i <= x; i++) {
                if (x % i == 0) {
                    for (j = 0; x % i == 0; x /= i, j++);
                    res = (int) ((1L * res * fact[j + y] % MOD * invFact[j] % MOD * invFact[y] % MOD) % MOD);
                }
            }
            if (x != 1) {
                res = (int) ((1L * res * (y + 1)) % MOD);
            }
            System.out.println(res);
        }
    }
}
