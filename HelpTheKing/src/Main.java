import java.util.Scanner;

public class Main {

    static long gcd(long a, long b) {
        while (b > 0) {
            long temp = b;
            b = a % b;
            a = temp;
        }
        return a;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int step = 0;
        while (n % 2 == 0) {
            step++;
            n /= 2;
        }
        if (n == 1) {
            System.out.printf("%d/1%n", step);
            return;
        }
        long s = 2, cou = 1;
        while (s % n != 1) {
            s *= 2;
            cou++;
        }
        long tm = cou, npo = s - 1, temp = step + 1;
        long sum = s * n, remaind = s;
        while (remaind > 1) {
            sum /= 2;
            if (remaind > sum) {
                tm += temp * sum;
                remaind -= sum;
            }
            temp++;
        }
        long res = gcd(tm, npo);
        tm /= res;
        npo /= res;
        System.out.printf("%d/%d%n", tm, npo);
    }
}