public class Main {
    static final int N = 1005;
    static final int[] mvx = {1, 0, -1, 0};
    static final int[] mvy = {0, 1, 0, -1};
    static int n, m, ans, all, p;
    static int[][] room = new int[N][N];
    static boolean[][][] vis = new boolean[N][N][4];

    static int scan() {
        int w = 0;
        char ch;
        try {
            ch = (char) System.in.read();
            while (ch >= '0' && ch <= '9') {
                w = w * 10 + ch - '0';
                ch = (char) System.in.read();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return w;
    }

    static int GCD(int a, int b) {
        return b == 0 ? a : GCD(b, a % b);
    }

    static void dfs(int x, int y, int lst, int len) {
        boolean flag = false;
        for (int i = 0; i < 4; i++) {
            if (room[x + mvx[i]][y + mvy[i]] != 0 && !vis[x][y][i]) {
                int nx = x + mvx[i];
                int ny = y + mvy[i];
                vis[x][y][i] = vis[nx][ny][(i + 2) % 4] = true;
                all -= 2;
                flag = true;
                if (lst == i) dfs(nx, ny, i, len + 1);
                else {
                    ans = GCD(ans, len);
                    dfs(nx, ny, i, 1);
                }
            }
        }
        if (!flag) ans = GCD(ans, len);
    }

    public static void main(String[] args) {
        n = scan();
        m = scan();
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                room[i][j] = scan();
            }
        }
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                if (room[i][j] != 0) {
                    int am = 0;
                    for (int k = 0; k < 4; k++) {
                        am += room[i + mvx[k]][j + mvy[k]];
                    }
                    if (am == 0) {
                        System.out.println("-1");
                        return;
                    }
                    all += am;
                    p += am & 1;
                }
            }
        }
        if (p != 0 && p != 2) {
            System.out.println("-1");
            return;
        }
        for (int i = 1; i <= n * m; i++) {
            if (room[(i - 1) / m + 1][(i - 1) % m + 1] != 0) {
                dfs((i - 1) / m + 1, (i - 1) % m + 1, -1, 0);
                break;
            }
        }
        if (all != 0 || ans < 2) {
            System.out.println("-1");
            return;
        }
        for (int i = 2; i <= ans; i++) {
            if (ans % i == 0) {
                System.out.print(i + " ");
            }
        }
    }
}
