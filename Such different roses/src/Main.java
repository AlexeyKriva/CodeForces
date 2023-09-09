import java.io.*;
import java.util.*;

public class Main {
    static class Pair {
        int first, second;

        Pair(int first, int second) {
            this.first = first;
            this.second = second;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = 200003;
        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        Pair[] a = new Pair[N];
        int[] cnt = new int[3];
        ArrayList<Pair> red = new ArrayList<>();
        ArrayList<Pair> white = new ArrayList<>();
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= n; i++) {
            a[i] = new Pair(Integer.parseInt(st.nextToken()), 0);
        }
        for (int i = 1; i <= n; i++) {
            char x = br.readLine().charAt(0);
            if (x == 'W') a[i].second = 2;
            if (x == 'R') a[i].second = 1;
            if (x == 'O') a[i].second = 0;
            cnt[a[i].second]++;
            if (x != 'W') red.add(a[i]);
            if (x != 'R') white.add(a[i]);
        }
        Collections.sort(red, (x, y) -> Integer.compare(y.first, x.first));
        Collections.sort(white, (x, y) -> Integer.compare(y.first, x.first));
        boolean ok = false;
        long res = 0, sum = 0;
        if (cnt[0] == 0 || (cnt[1] == 0 && cnt[2] == 0)) {
            System.out.println(-1);
            return;
        }
        cnt[0] = 0;
        cnt[1] = 0;
        for (int i = red.size() - 1; i >= 0; i--) {
            Pair top = red.get(i);
            if (cnt[0] + cnt[1] < k - 1) {
                res += top.first;
                cnt[top.second]++;
                continue;
            }
            if (cnt[0] == k - 1 && cnt[1] == 0 && top.second == 0) continue;
            if (cnt[1] == k - 1 && cnt[0] == 0 && top.second == 1) continue;
            if (cnt[0] + cnt[1] == k - 1) {
                res += top.first;
                cnt[top.second]++;
                break;
            }
        }
        if (cnt[0] + cnt[1] < k) res = -1;
        cnt[0] = 0;
        cnt[2] = 0;
        for (int i = white.size() - 1; i >= 0; i--) {
            Pair top = white.get(i);
            if (cnt[0] + cnt[2] < k - 1) {
                sum += top.first;
                cnt[top.second]++;
                continue;
            }
            if (cnt[0] == k - 1 && cnt[2] == 0 && top.second == 0) continue;
            if (cnt[2] == k - 1 && cnt[0] == 0 && top.second == 2) continue;
            if (cnt[0] + cnt[2] == k - 1) {
                sum += top.first;
                cnt[top.second]++;
                break;
            }
        }
        if (cnt[0] + cnt[2] < k) sum = -1;
        System.out.println(Math.max(res, sum));
    }
}
