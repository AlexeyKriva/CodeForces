import java.util.Scanner;

public class Main {
    static long[] numbers = new long[5];
    static long result = 1000000000000000000l;
    static int[] use = new int[5];
    static char[] signs = new char[4];

    public static long sumOrProd(long a, long b, char x) {
        if (x == '+'){
            return a + b;
        }
        return a * b;
    }

    public static void findMinResult(int x) {
        if (x == 4) {
            for (int i = 1; i <= 4; ++i)
                if (use[i] == 0) {
                    result = Math.min(result, numbers[i]);
                }
            return;
        }
        for (int i = 1; i <= 4; ++i)
            for (int j = 1; j <= 4; ++j) {
                if (i == j || use[i] != 0 || use[j] != 0){
                    continue;
                }
                long t = numbers[i];
                numbers[i] = sumOrProd(numbers[i], numbers[j], signs[x]);
                use[j] = 1;
                findMinResult(x + 1);
                use[j] = 0;
                numbers[i] = t;
            }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        numbers[1] = scanner.nextLong();
        numbers[2] = scanner.nextLong();
        numbers[3] = scanner.nextLong();
        numbers[4] = scanner.nextLong();
        signs[1] = scanner.next().charAt(0);
        signs[2] = scanner.next().charAt(0);
        signs[3] = scanner.next().charAt(0);
        findMinResult(1);
        System.out.println(result);
    }
}
