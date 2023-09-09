import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int N = 200000;
        int[] a = new int[N];
        int[] am = new int[N];
        int n = scanner.nextInt();
        int s = scanner.nextInt();
        s--;
        for (int i = 0; i < n; i++)
            a[i] = scanner.nextInt();
        int k = 0;
        for (int i = 0; i < n; i++) {
            int num = a[i];
            if (i != s) {
                if (num == 0)
                    k++;
                else
                    am[num]++;
            }
        }
        int res = n;
        for (int i = 1; i < n; i++)
            k += am[i];
        for (int i = 0, j = 1; j <= n; j++) {
            if (j > 1 && am[j - 1] == 0)
                i++;
            res = Math.min(res, k + Math.max(i - k, 0));
            if (j < n)
                k -= am[j];
        }
        if (a[s] != 0)
            res++;
        System.out.println(res);
    }
}
