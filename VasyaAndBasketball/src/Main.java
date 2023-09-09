import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int n = scanner.nextInt();
        long[] dist1 = new long[n + 1];
        for (int i = 1; i <= n; i++) {
            dist1[i] = scanner.nextLong();
        }
        Arrays.sort(dist1, 1, n + 1);

        int m = scanner.nextInt();
        long[] dist2 = new long[m + 1];
        for (int i = 1; i <= m; i++) {
            dist2[i] = scanner.nextLong();
        }
        Arrays.sort(dist2, 1, m + 1);

        long points1, temp1, points2, temp2;
        points1 = temp1 = n * 2;
        points2 = temp2 = m * 2;

        for (int i = n, j = m; i >= 1; i--) {
            temp1++;
            for (; j >= 1 && dist2[j] >= dist1[i]; j--) {
                temp2++;
            }
            if (points1 - points2 <= temp1 - temp2) {
                points1 = temp1;
                points2 = temp2;
            }
        }

        System.out.println(points1 + ":" + points2);
    }
}
