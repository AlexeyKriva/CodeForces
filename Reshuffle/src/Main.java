import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        long m = scanner.nextLong();
        int[] position = new int[n];
        int s = 0;
        int e = n - 1;
        for (int i = 1; i <= n; i++) {
            long weight = 1L << (n - i - 1);
            if (m <= weight) {
                position[s++] = i;
            } else {
                position[e--] = i;
                m -= weight;
            }
        }
        for (int i = 0; i < n; i++) {
            System.out.print(position[i] + " ");
        }
        System.out.println();
    }
}
