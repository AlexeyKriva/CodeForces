import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int N = 10010;
        int n = scanner.nextInt();
        int T = scanner.nextInt();
        int[] a = new int[N];
        int[] entr = new int[N];
        int[] seq = new int[N];
        int maxa = 0;
        int len = 0;
        for (int i = 1; i <= n; i++) {
            a[i] = scanner.nextInt();
            entr[a[i]]++;
            maxa = Math.max(maxa, entr[a[i]]);
        }
        for (int i = n + 1; i <= n * Math.min(n, T); i++) {
            a[i] = a[i - n];
        }
        for (int i = 1; i <= n * Math.min(n, T); i++) {
            for (int j = 0; j < i; j++) {
                if (a[j] <= a[i]) {
                    seq[i] = Math.max(seq[i], seq[j] + 1);
                }
            }
            len = Math.max(len, seq[i]);
        }
        System.out.println(len + maxa * Math.max(0, T - n));
        for (int i = 1; i <= 100; i++){
            System.out.print(i + " ");
        }
    }
}