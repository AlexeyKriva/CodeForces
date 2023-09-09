import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in, StandardCharsets.UTF_8);
        int k = scanner.nextInt();
        int x = scanner.nextInt();
        int n = scanner.nextInt();
        int m = scanner.nextInt();
        boolean flag = false;
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 2 && i + j <= n; j++) {
                for (int i2 = 0; i2 < 2; i2++) {
                    for (int j2 = 0; j2 < 2 && i2 + j2 <= m; j2++) {
                        for (int ij1 = 0; ij1 <= (n - i - j) / 2; ij1++) {
                            for (int ij2 = 0; ij2 <= (m - i2 - j2) / 2; ij2++) {
                                int c1 = i, c2 = i2, a1 = j, ac1 = ij1, ac2 = ij2;
                                for (int l = 2; l < k && ac2 <= x; l++) {
                                    int nc1 = c1, nc2 = c2, na1 = a1, nac1 = ac1, nac2 = ac2;
                                    c1 = nc2;
                                    c2 = nc1;
                                    a1 = j2;
                                    ac1 = nac2;
                                    ac2 = nac1 + (na1 & nc2) + nac2;
                                }
                                if (ac2 == x) {
                                    flag = true;
                                    System.out.println("C".repeat(i) + "AC".repeat(ij1) + "B".repeat(n - i - ij1 * 2 - j) + "A".repeat(j) + System.lineSeparator()
                                            + "C".repeat(i2) + "AC".repeat(ij2) + "B".repeat(m - i2 - ij2 * 2 - j2) + "A".repeat(j2));
                                    break;
                                }
                            }
                            if (flag){
                                break;
                            }
                        }
                        if (flag){
                            break;
                        }
                    }
                    if (flag){
                        break;
                    }
                }
                if (flag){
                    break;
                }
            }
            if (flag){
                break;
            }
        }
        if (!flag) {
            System.out.println("Happy new year!");
        }
    }
}