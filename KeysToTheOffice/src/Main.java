import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n, k, p;
        n = scanner.nextInt();
        k = scanner.nextInt();
        p = scanner.nextInt();
        List<Integer> peoplePos = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            peoplePos.add(scanner.nextInt());
        }
        List<Integer> keysPos = new ArrayList<>();
        for (int i = 0; i < k; i++) {
            keysPos.add(scanner.nextInt());
        }
        Collections.sort(peoplePos);
        Collections.sort(keysPos);
        long result = Long.MAX_VALUE;
        for (int i = 0; i + n - 1 < k; i++) {
            long tempRes = 0;
            for (int j = 0; j < n; j++) {
                tempRes = Math.max(tempRes, Math.abs(keysPos.get(i + j) - peoplePos.get(j)) + Math.abs(p - keysPos.get(i + j)));
            }
            result = Math.min(result, tempRes);
        }
        System.out.println(result);
    }
}
