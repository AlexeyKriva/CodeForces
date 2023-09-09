import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int[] left = new int[n + 1];
        int[] right = new int[n + 1];
        int[] number = new int[n + 1];

        for (int i = 1; i <= n; ++i) {
            int x = scanner.nextInt();
            int w = scanner.nextInt();
            left[i] = x - w;
            right[i] = x + w;
            number[i] = i;
        }

        Integer[] newId = new Integer[n + 1];
        for (int i = 1; i <= n; i++) {
            newId[i] = number[i];
        }

        Arrays.sort(newId, 1, n + 1, new Comparator<Integer>() {
            @Override
            public int compare(Integer x, Integer y) {
                return right[x] == right[y] ? left[x] - left[y] : right[x] - right[y];
            }
        });

        int result = 0;
        int rightPointCoord = -2 * 1000000000;
        for (int i = 1; i <= n; ++i) {
            int nowId = newId[i];
            if (rightPointCoord <= left[nowId]) {
                result++;
                rightPointCoord = right[nowId];
            }
        }

        System.out.println(result);
    }
}
