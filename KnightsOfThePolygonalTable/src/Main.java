import java.util.*;

class Knight implements Comparable<Knight>{
    int power;
    int money;
    int id;
    Knight(int power){
        this.power = power;
    }

    @Override
    public int compareTo(Knight knight){
        return this.power - knight.power;
    }
}

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int k = scanner.nextInt();
        List<Knight> knights = new ArrayList<>();
        for (int i = 0; i < n; i++){
            knights.add(new Knight(scanner.nextInt()));
            knights.get(i).id = i;
        }
        for (int i = 0; i < n; i++){
            knights.get(i).money = scanner.nextInt();
        }
        Collections.sort(knights);
        Long[] results = new Long[n];
        Arrays.fill(results, -1l);
        for (int i = n - 1; i >= 0; i--){
            long m = knights.get(i).money;
            List<Long> money = new ArrayList<>();
            for (int j = i - 1; j >= 0; j--){
                money.add((long) knights.get(j).money);
            }
            Collections.sort(money);
            for (int j = money.size() - 1; j > money.size() - 1 - k && j >= 0; j--){
                m += money.get(j);
            }
            results[knights.get(i).id] = m;
        }
        for (Long result : results) {
            System.out.print(result + " ");
        }
    }
}