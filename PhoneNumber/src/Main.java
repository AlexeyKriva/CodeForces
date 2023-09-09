import java.util.Scanner;

public class Main {
    static long[][] dp = new long[55][10];

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String s = scanner.next();
        int n = s.length();

        for (int j = 0; j < 10; j++)
            dp[0][j] = 1;

        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < 10; j++) {
                if (dp[i][j] == 0)
                    continue;
                int z = s.charAt(i + 1) - '0';
                dp[i + 1][(z + j) / 2] += dp[i][j];
                if ((z + j) % 2 != 0)
                    dp[i + 1][(z + j + 1) / 2] += dp[i][j];
            }
        }

        long sol = 0;
        boolean ok = true;

        for (int i = 0; i < n - 1; i++) {
            int a = s.charAt(i) - '0';
            int b = s.charAt(i + 1) - '0';
            if ((a + b) / 2 == b || (a + b + 1) / 2 == b)
                continue;
            ok = false;
            break;
        }

        if (ok)
            sol--;

        for (int j = 0; j < 10; j++)
            sol += dp[n - 1][j];

        System.out.println(sol);
        scanner.close();
    }
}
