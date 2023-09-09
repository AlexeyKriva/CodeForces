import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tokenizer = new StringTokenizer(reader.readLine());

        int n = Integer.parseInt(tokenizer.nextToken());
        int m = Integer.parseInt(tokenizer.nextToken());
        int k = Integer.parseInt(tokenizer.nextToken());

        int[][] p = new int[1001][1001];
        int[] row = new int[1001];
        int[] col = new int[1001];
        for (int r = 1; r <= n; ++r)
            row[r] = r;
        for (int c = 1; c <= m; ++c)
            col[c] = c;
        for (int r = 1; r <= n; ++r) {
            tokenizer = new StringTokenizer(reader.readLine());
            for (int c = 1; c <= m; ++c) {
                p[r][c] = Integer.parseInt(tokenizer.nextToken());
            }
        }

        while (k-- > 0) {
            tokenizer = new StringTokenizer(reader.readLine());
            char s = tokenizer.nextToken().charAt(0);
            int x = Integer.parseInt(tokenizer.nextToken());
            int y = Integer.parseInt(tokenizer.nextToken());

            switch (s) {
                case 'r':
                    int tempRow = row[x];
                    row[x] = row[y];
                    row[y] = tempRow;
                    break;
                case 'c':
                    int tempCol = col[x];
                    col[x] = col[y];
                    col[y] = tempCol;
                    break;
                case 'g':
                    System.out.println(p[row[x]][col[y]]);
                    break;
                default:
                    break;
            }
            }
        }
    }
}
