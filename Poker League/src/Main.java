import java.util.*;

public class Main {
    static final int N = 50007; // Increased a bit to handle the indices in Java
    static final int E = 100007; // Increased a bit to handle the indices in Java

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();

        int n = scanner.nextInt();
        int e = scanner.nextInt();
        int T = 0;

        int[][] t = new int[N][25];
        int[] bel = new int[N];
        int[] c = new int[N];

        for (int i = 0; i < E; i++) {
            int X = scanner.nextInt();
            int Y = scanner.nextInt();
            t[X][0]++;
            t[Y][0]++;
            t[X][t[X][0]] = Y;
            t[Y][t[Y][0]] = X;
        }

        for (int i = 1; i <= n; i++) {
            T = Math.max(T, t[i][0]);
            bel[i] = t[i][random.nextInt(t[i][0]) + 1]; // Рандомный выбор игрока
        }

        for (int i = 1; i <= T; i++) {
            c[i] = random.nextInt(2); // Рандомный выбор команды
        }

        while (!work(t, bel, c, e));

        for (int i = 1; i <= n; i++) {
            System.out.print(bel[i] + " ");
        }
        System.out.println();

        for (int i = 1; i <= T; i++) {
            System.out.print(c[i] + 1 + " ");
        }
    }

    static boolean work(int[][] t, int[] bel, int[] c, int e) {
        int res = 0;
        for (int i = 1; i <= e; i++) {
            int X = t[i][0];
            int Y = t[i][1];
            res += (c[bel[X]] != c[bel[Y]]) ? 1 : 0;
        }
        return res * 2 >= e;
    }
}
