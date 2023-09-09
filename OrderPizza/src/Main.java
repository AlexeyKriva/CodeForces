import java.util.*;
import java.io.*;

public class Main {
    static class FastScanner {
        BufferedReader br;
        StringTokenizer st;
        public FastScanner() {
            br = new BufferedReader(new InputStreamReader(System.in));
        }
        String next() {
            while (st == null || !st.hasMoreElements()) {
                try {
                    st = new StringTokenizer(br.readLine());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return st.nextToken();
        }
        long nextLong() {
            return Long.parseLong(next());
        }
    }

    public static void main(String[] args) {
        FastScanner scanner = new FastScanner();
        long n, k, sm, s, a, b, nw, ans, z;
        z = 0;
        ArrayList<long[]> x = new ArrayList<>();
        n = scanner.nextLong();
        k = scanner.nextLong();
        nw = 0;
        sm = 0;
        for (int i = 1; i <= n; ++i) {
            s = scanner.nextLong();
            a = scanner.nextLong();
            b = scanner.nextLong();
            nw += s * a;
            sm += s;
            x.add(new long[]{b - a, s});
        }
        a = k;
        k = (k - sm % k) % k;
        x.sort((a1, a2) -> Long.compare(a2[0], a1[0]));
        ans = nw;
        for (long[] i : x) {
            if (i[0] > 0) {
                if (z - k <= i[1]) {
                    b = Math.min(i[1], z);
                    nw += i[0] * b;
                    i[1] -= b;
                    z -= b;
                    ans = Math.max(ans, nw);
                    if (i[1] == 0) continue;
                    nw += i[0] * (i[1] / a * a);
                    ans = Math.max(ans, nw);
                    i[1] %= a;
                    if (i[1] != 0) {
                        z = a - i[1];
                        nw += i[0] * i[1];
                        if (z <= k) ans = Math.max(ans, nw);
                    }
                } else {
                    z -= i[1];
                    nw += i[0] * i[1];
                }
            } else {
                if (z - k <= i[1]) {
                    nw += i[0] * Math.max(0, z - k);
                    ans = Math.max(ans, nw);
                    break;
                } else {
                    z -= i[1];
                    nw += i[0] * i[1];
                }
            }
        }
        System.out.println(ans);
    }
}
