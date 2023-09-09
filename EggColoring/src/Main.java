import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int sA = 0, sG = 0;
        String result = "";
        for (int i = 0; i < n; i++) {
            int a = scanner.nextInt();
            int g = scanner.nextInt();
            if (Math.abs(sA + a - sG) <= 500) {
                sA += a;
                result += 'A';
            } else {
                sG += g;
                result += 'G';
            }
        }
        if (Math.abs(sA - sG) <= 500) {
            System.out.println(result);
        }
        else {
            System.out.println(-1);
        }
    }
}