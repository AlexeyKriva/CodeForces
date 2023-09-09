import java.util.*;

public class Main {
    static final int MAXN = 100005;
    static int n;
    static int[] cou = new int[MAXN];
    static int[] dep = new int[MAXN];
    static int[][] par = new int[MAXN][18];
    static Pair[] info = new Pair[MAXN];
    static ArrayList<Integer>[] conn = new ArrayList[MAXN];

    static class Pair {
        long first, second;
        Pair(long first, long second) {
            this.first = first;
            this.second = second;
        }
    }

    static Pair diff(Pair i, Pair j) {
        return new Pair(j.first - i.first, j.second - i.second);
    }

    static long prod(Pair i, Pair j) {
        return i.first * j.second - i.second * j.first;
    }

    static void dfs(int i, int par) {
        dep[i] = dep[par] + 1;
        Main.par[i][0] = par;
        for (int j = 1; j < 17; ++j)
            Main.par[i][j] = Main.par[Main.par[i][j - 1]][j - 1];
        for (int x : conn[i]) dfs(x, i);
    }

    static int lca(int x, int y) {
        if (dep[x] < dep[y]) {
            int temp = x;
            x = y;
            y = temp;
        }
        for (int i = 16; i >= 0 && dep[x] != dep[y]; --i) {
            if (dep[x] - (1 << i) >= dep[y])
                x = par[x][i];
        }
        if (x == y)
            return x;
        for (int i = 16; i >= 0; --i) {
            if (par[x][i] != par[y][i]) {
                x = par[x][i];
                y = par[y][i];
            }
        }
        return par[x][0];
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        n = scanner.nextInt();
        for (int i = 1; i <= n; ++i)
            info[i] = new Pair(scanner.nextLong(), scanner.nextLong());
        for (int i = 0; i < MAXN; ++i)
            conn[i] = new ArrayList<>();
        for (int i = n, cnt = 0; i > 0; --i) {
            cou[++cnt] = i;
            while (cnt >= 3) {
                int a = cou[cnt - 2], b = cou[cnt - 1], c = cou[cnt];
                Pair x = diff(info[b], info[c]), y = diff(info[b], info[a]);
                if (prod(x, y) >= 0)
                    break;
                cou[cnt - 1] = c;
                --cnt;
            }
            conn[cou[cnt - 1]].add(i);
        }
        dfs(n, 0);
        int m, a, b;
        m = scanner.nextInt();
        while (m-- > 0) {
            a = scanner.nextInt();
            b = scanner.nextInt();
            System.out.print(lca(a, b) + " ");
        }
    }
}
