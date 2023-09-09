import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        while (n-- > 0){
            int a = scanner.nextInt();
            if (a == 1){
                System.out.println(Math.pow(10, a));
            } else {
                System.out.println(Math.pow(10, a) - Math.pow(10, a - 1));
            }
        }
    }
}
