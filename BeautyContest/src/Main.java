import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int[] a = new int[55];

        int n = scanner.nextInt();
        int k = scanner.nextInt();

        for (int i = 1; i <= n; i++) {
            a[i] = scanner.nextInt();
        }

        Arrays.sort(a, 1, n + 1);
        reverse(a, 1, n);

        for (int i = 1; k > 0; i++) {
            for (int j = i; j <= n && k > 0; j++, k--) {
                System.out.print(i + " ");
                for (int l = 1; l < i; l++) {
                    System.out.print(a[l] + " ");
                }
                System.out.println(a[j]);
            }
        }
    }

    private static void reverse(int[] a, int left, int right) {
        while (left < right) {
            int b = a[left];
            a[left] = a[right];
            a[right] = b;
            left++;
            right--;
        }
    }
}
