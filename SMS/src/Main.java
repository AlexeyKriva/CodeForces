import java.util.PriorityQueue;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int a = scanner.nextInt();
        int b = scanner.nextInt();
        int tm = a;
        int max = b;
        int now = b;
        for (int i = 1; i < n; i++){
            a = scanner.nextInt();
            b = scanner.nextInt();
            now = now + b - (a - tm);
            now = Math.max(now, b);
            max = Math.max(max, now);
            tm = a;
        }
        System.out.println((tm + now) + " " + max);
    }
}
