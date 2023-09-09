import java.util.Scanner;

public class Main {
    static int[][] zero;
    static int[][][][] goodRect;
    static int[][][][] goodRect2;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int n = scanner.nextInt();
        int m = scanner.nextInt();
        int q = scanner.nextInt();
        scanner.nextLine();

        String[] strTable = new String[n + 1];
        zero = new int[n + 1][m + 1];

        for (int i = 1; i <= n; i++) {
            strTable[i] = "0" + scanner.nextLine();
        }

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                zero[i][j] = zero[i][j - 1] + zero[i - 1][j] - zero[i - 1][j - 1] + (strTable[i].charAt(j) == '1' ? 1 : 0);
            }
        }

        goodRect = new int[n + 1][m + 1][n + 1][m + 1];
        goodRect2 = new int[n + 1][m + 1][n + 1][m + 1];

        for (int c = n; c > 0; c--) {
            for (int d = m; d > 0; d--) {
                for (int a = c; a > 0; a--) {
                    for (int b = d; b > 0; b--) {
                        if (isGood(a, b, c, d)) {
                            goodRect2[a][b][c][d]++;
                        }
                        if (a < c) goodRect2[a][b][c][d] += goodRect2[a + 1][b][c][d];
                        if (b < d) goodRect2[a][b][c][d] += goodRect2[a][b + 1][c][d];
                        if (a < c && b < d) goodRect2[a][b][c][d] -= goodRect2[a + 1][b + 1][c][d];
                    }
                }
            }
        }

        for (int a = 1; a <= n; a++) {
            for (int b = 1; b <= m; b++) {
                for (int c = a; c <= n; c++) {
                    for (int d = b; d <= m; d++) {
                        goodRect[a][b][c][d] = goodRect2[a][b][c][d];
                        if (a < c) goodRect[a][b][c][d] += goodRect[a][b][c - 1][d];
                        if (b < d) goodRect[a][b][c][d] += goodRect[a][b][c][d - 1];
                        if (a < c && b < d) goodRect[a][b][c][d] -= goodRect[a][b][c - 1][d - 1];
                    }
                }
            }
        }

        while (q-- > 0) {
            int a = scanner.nextInt();
            int b = scanner.nextInt();
            int c = scanner.nextInt();
            int d = scanner.nextInt();
            System.out.println(goodRect[a][b][c][d]);
        }

        scanner.close();
    }

    static boolean isGood(int a, int b, int c, int d) {
        int in = zero[c][d] - zero[c][b - 1] - zero[a - 1][d] + zero[a - 1][b - 1];
        return in == 0;
    }
}
