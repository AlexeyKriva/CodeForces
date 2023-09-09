import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n, m;
        n = scanner.nextInt();
        m = scanner.nextInt();
        int left = 1;
        int right = n;
        for (int i = 0; i < m; ++i) {
            String s = "";
            int t = 0;
            for (int j = 0; j < 5; ++j) {
                s = scanner.next();
                if (s.equals("left"))
                    t = 0;
                if (s.equals("right"))
                    t = 1;
            }
            int x = Integer.parseInt(s);
            if (t == 1)
                left = Math.max(left, x + 1);
            else
                right = Math.min(right, x - 1);
        }
        if (left > right)
            System.out.println(-1);
        else
            System.out.println(right - left + 1);
    }
}
