import java.util.Scanner;

public class Main {
    static final int N = 7010;
    static int n, a[][] = new int[2][N], k[] = new int[2];
    static int res[][] = new int[2][N], vis[][] = new int[2][N], cou[][] = new int[2][N];
    public static void dfs(int u, int c) {
        if (vis[u][c] == 1) {
            return;
        }
        vis[u][c] = 1;
        for (int i = 1; i <= k[1 - u]; i++) {
            int r = (c - a[1 - u][i] + n) % n;
            if (r == 0){
                continue;
            }
            if (res[u][c] == -1){
                res[1 - u][r] = 1;
            }
            else if (++cou[1 - u][r] == k[1 - u]){
                res[1 - u][r] = -1;
            }
            if (res[1 - u][r] != 0){
                dfs(1 - u, r);
            }
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        n = scanner.nextInt();
        for (int i = 0; i < 2; i++) {
            k[i] = scanner.nextInt();
            for (int j = 1; j <= k[i]; j++){
                a[i][j] = scanner.nextInt();
            }
        }

        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < N; j++) {
                res[i][j] = 0;
                vis[i][j] = 0;
                cou[i][j] = 0;
            }
        }

        res[0][0] = res[1][0] = -1;
        dfs(0, 0);
        dfs(1, 0);

        for (int i = 0; i < 2; i++) {
            for (int j = 1; j < n; j++) {
                if (res[i][j] == -1){
                    System.out.print("Lose ");
                }
                else if (res[i][j] == 0){
                    System.out.print("Loop ");
                }
                else{
                    System.out.print("Win ");
                }
            }
            System.out.println();
        }
    }
}
