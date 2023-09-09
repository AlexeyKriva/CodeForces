import java.util.*;

public class Main {
    static int results(List<Integer> x, int k, int len) {
        if (len == 0) {
            return k;
        }
        int r;
        if (k == x.size() - 1) {
            r = k;
        } else {
            r = upperBound(x, x.get(k) + len) - 1;
        }
        len -= x.get(r) - x.get(k);
        int l = lowerBound(x, x.get(r) - len);
        if (l == r) {
            return l;
        }
        len -= x.get(r) - x.get(l);
        if (l > k) {
            return results(x, l, len);
        }
        int dist = x.get(r) - x.get(l);
        int times = len / dist;
        len = len % dist;
        if (times % 2 == 0) {
            return results(x, l, len);
        }
        l = lowerBound(x, x.get(r) - len);
        if (l == r) {
            return l;
        }
        len -= x.get(r) - x.get(l);
        return results(x, l, len);
    }

    static int upperBound(List<Integer> arr, int target) {
        int left = 0;
        int right = arr.size() - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (arr.get(mid) <= target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return left;
    }

    static int lowerBound(List<Integer> arr, int target) {
        int left = 0;
        int right = arr.size() - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (arr.get(mid) < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return left;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int m = scanner.nextInt();
        List<Integer> x = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            x.add(scanner.nextInt());
        }
        Map<Integer, Integer> dict1 = new HashMap<>();
        List<Integer> v1 = new ArrayList<>(x);
        for (int i = 0; i < n; i++) {
            v1.set(i, x.get(i));
        }
        for (int i = 0; i < n; i++) {
            dict1.put(x.get(i), i);
        }
        Collections.sort(x);
        List<Integer> v2 = new ArrayList<>(Collections.nCopies(n, 0));
        Map<Integer, Integer> dict2 = new HashMap<>();
        for (int i = 0; i < n; i++) {
            v2.set(i, dict1.get(x.get(i)));
        }
        for (int i = 0; i < n; i++) {
            dict2.put(x.get(i), i);
        }
        while (m-- > 0) {
            int a = scanner.nextInt();
            int l = scanner.nextInt();
            a--;
            a = dict2.get(v1.get(a));
            System.out.println(v2.get(results(x, a, l)) + 1);
        }
    }
}
