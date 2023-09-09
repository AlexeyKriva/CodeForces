import java.util.Scanner;

import static java.lang.Math.min;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int x = scanner.nextInt();
        int y = scanner.nextInt();
        String str = scanner.next();
        int zero = 0;
        for (int i = 0; i < n - 1; i++){
            if (i == 0 && str.charAt(i) == '0'){
                zero++;
            }
            if (str.charAt(i) == '1' && str.charAt(i + 1) == '0'){
                zero++;
            }
        }
        if (zero == 0){
            System.out.println('0');
        }
        else {
            int amount = zero * y;
            for (int i = 1; i < zero; i++){
                int amount1 = x * i + (zero - i) * y;
                amount = min(amount, amount1);
            }
            System.out.println(amount);
        }
    }
}