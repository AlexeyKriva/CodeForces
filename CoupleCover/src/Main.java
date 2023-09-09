import java.util.*;

public class Main {


    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int[] numberOfBalls = new int[3000100];
        long[] area = new long[3000100];
        for (int i = 0; i < n; i++) {
            int a = scanner.nextInt();
            numberOfBalls[a]++;
        }
        for (int i = 1; i < 3000100; i++) {
            if (numberOfBalls[i] != 0) {
                for (int j = i; j < 3000100; j += i) {
                    if (j / i != i) {
                        area[j] += (long) numberOfBalls[i] * numberOfBalls[j / i];
                    }
                    else {
                        area[j] += (long) numberOfBalls[i] * (numberOfBalls[i] - 1);
                    }
                }
            }
        }
        for (int i = 1; i < 3000100; i++) {
            area[i] += area[i - 1];
        }
        int m = scanner.nextInt();
        while (m > 0) {
            int p = scanner.nextInt();
            System.out.println((long) n * (n - 1) - area[p - 1]);
            m--;
        }
        scanner.close();
    }
}
