import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        long[] x = new long[100007];
        long[] m = new long[100007];
        for (int i = 1; i <= n; i++) {
            x[i] = scanner.nextLong();
            m[i] = scanner.nextLong();
        }
        long res = 0;
        for (int i = 1; i <= n; i++) {
            res ^= xor(x[i] + m[i] - 1) ^ xor(x[i] - 1);
        }
        System.out.println(res != 0 ? "tolik" : "bolik");
    }
    public static long xor(long n) {
        if (n % 4 == 0) return n;
        if (n % 4 == 1) return 1;
        if (n % 4 == 2) return n + 1;
        if (n % 4 == 3) return 0;
        return -1;
    }
}
