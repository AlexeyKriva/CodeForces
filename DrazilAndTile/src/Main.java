import java.util.Scanner;

public class Main {
    static char[][] field = new char[2005][2005];
    static int[][] move = {{-1, 0, 1, 0}, {0, 1, 0, -1}};
    static char[] signs = {'v', '<', '^', '>'};
    static int n, m;

    static class Coord {
        int x, y;

        Coord(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    static boolean isPoint(int x, int y) {
        return (x >= 0 && x < n && y >= 0 && y < m && field[x][y] == '.');
    }

    static int emptyCell(int x, int y) {
        int ans = 0;
        for (int i = 0; i < 4; i++) {
            int fx = x + move[0][i];
            int fy = y + move[1][i];
            if (isPoint(fx, fy))
                ans++;
        }
        return ans;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
            n = scanner.nextInt();
            m = scanner.nextInt();
            for (int i = 0; i < n; i++)
                field[i] = scanner.next().toCharArray();

            int sum = 0;
            Coord[] points = new Coord[2005 * 2005];
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    if (isPoint(i, j) && emptyCell(i, j) == 1) {
                        points[sum++] = new Coord(i, j);
                    }
                }
            }

            int index = 0;
            for (; index < sum; index++) {
                int x = points[index].x, y = points[index].y;
                for (int i = 0; i < 4; i++) {
                    int fx = x + move[0][i];
                    int fy = y + move[1][i];
                    if (isPoint(fx, fy)) {
                        field[x][y] = signs[i];
                        field[fx][fy] = signs[i ^ 2];
                        for (int j = 0; j < 4; j++) {
                            int ffx = fx + move[0][j];
                            int ffy = fy + move[1][j];
                            if (isPoint(ffx, ffy) && emptyCell(ffx, ffy) == 1) {
                                points[sum++] = new Coord(ffx, ffy);
                            }
                        }
                        break;
                    }
                }
            }

            boolean flag = false;
            for (int i = 0; i < n && !flag; i++) {
                for (int j = 0; j < m && !flag; j++) {
                    if (field[i][j] == '.') {
                        flag = true;
                    }
                }
            }

            if (flag) {
                System.out.println("Not unique");
            } else {
                for (int i = 0; i < n; i++) {
                    for (int j = 0; j < m; j++) {
                        System.out.print(field[i][j]);
                    }
                    System.out.println();
                }
            }
    }
}
