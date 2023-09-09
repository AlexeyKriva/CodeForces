import java.util.Scanner;

public class Main {
    static final int mod = 1000000007;
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int k = scanner.nextInt();
        int pa = scanner.nextInt();
        int pb = scanner.nextInt();
        long Pa = (((pa * degree(pa + pb, mod - 2)) % mod));
        long Pb = (((pb * degree(pa + pb, mod - 2)) % mod));
        long[][] ab = new long[1010][1010];
        for (int i = k; i >= 1; i--) {
            for (int j = k; j >= 0; j--) {
                if (i + j >= k)
                    ab[i][j] = ((i + j + pa * degree(pb, mod - 2)) % mod);
                else
                    ab[i][j] = ((Pa * ab[i + 1][j] % mod + Pb * ab[i][j + i] % mod) % mod);
            }
        }
        System.out.println(ab[1][0]);
    }
    static long degree(long a, long b) {
        long res = 1;
        while (b > 0) {
            if ((b & 1) == 1)
                res = ((res * (long) a) % mod);
            a = ((a * (long) a) % mod);
            b >>= 1;
        }
        return res;
    }
}