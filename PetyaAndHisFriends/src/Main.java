import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        if (n == 2){
            System.out.println(-1);
        }
        else {
            int[] numbers = new int[n];
            numbers[0] = 6;
            numbers[1] = 10;
            numbers[2] = 15;
            int comp = 12;
            for (int i = 3; i < n; i++){
                numbers[i] = comp;
                comp += 6;
            }
            for (int i = 0; i < n; i++){
                System.out.println(numbers[i]);
            }
        }
    }
}