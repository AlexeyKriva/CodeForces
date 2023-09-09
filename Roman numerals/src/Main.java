import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        long[] am = {0,4,10,20,35,56,83,116,155,198,244,292};
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        if (n <= 11){
            System.out.println(am[n]);
        } else {
            System.out.println(am[11] + (n - 11) * 49);
        }
    }
}