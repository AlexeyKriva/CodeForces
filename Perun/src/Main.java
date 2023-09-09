import java.util.*;

class SuffixArray {
    int n;
    String s;
    ArrayList<Integer> sa, rank, lcp;
    static final int LG = 20;
    ArrayList<ArrayList<Integer>> t;
    ArrayList<Integer> lg;

    SuffixArray() {}

    SuffixArray(String _s) {
        n = _s.length();
        s = _s;
        sa = new ArrayList<>();
        rank = new ArrayList<>();
        lcp = new ArrayList<>();
        for (int i = 0; i < n; i++) sa.add(0);
        for (int i = 0; i < n; i++) rank.add(0);
        for (int i = 0; i < n - 1; i++) lcp.add(0);
        t = new ArrayList<>();
        lg = new ArrayList<>();
        for (int i = 0; i < n; i++) lg.add(0);

        for (int i = 0; i < n; i++) {
            sa.set(i, i);
        }
        Collections.sort(sa, (a, b) -> Character.compare(s.charAt(a), s.charAt(b)));

        rank = new ArrayList<>(Collections.nCopies(n, 0));
        for (int i = 0; i < n; i++) {
            rank.set(sa.get(i), i);
        }
        constructLCP();

        // prec();
        // build();
    }

    void constructLCP() {
        int k = 0;
        for (int i = 0; i < n; i++) {
            if (rank.get(i) == n - 1) {
                k = 0;
                continue;
            }
            int j = sa.get(rank.get(i) + 1);
            while (i + k < n && j + k < n && s.charAt(i + k) == s.charAt(j + k)) {
                k++;
                lcp.set(rank.get(i), k);
            }
            if (k > 0) {
                k--;
            }
        }
    }
}

public class Main {
    static final int MAX = 500009;

    public static void add(ArrayList<Integer>[] adj, int x, int i) {
        x += MAX;
        adj[x].add(i);
    }

    public static int query(ArrayList<Integer>[] adj, int x, int l, int r) {
        if (r < l) return 0;
        x += MAX;
        int count = 0;
        for (int val : adj[x]) {
            if (val >= l && val <= r) {
                count++;
            }
        }
        return count;
    }

    public static void solve() {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        scanner.nextLine();
        String s = scanner.nextLine();
        SuffixArray sa = new SuffixArray(s);

        int[] cum = new int[n + 1];
        int[] nxt = new int[n + 1];
        cum[0] = 0;
        for (int i = 0; i < n; i++) {
            cum[i + 1] = cum[i] + (s.charAt(i) == '(' ? 1 : -1);
        }
        Stack<Integer> stk = new Stack<>();
        ArrayList<Integer>[] adj = new ArrayList[2 * MAX];
        for (int i = 0; i < 2 * MAX; i++) {
            adj[i] = new ArrayList<>();
        }

        for (int i = n; i >= 0; i--) {
            add(adj, cum[i], i);
            while (!stk.isEmpty() && cum[stk.peek()] >= cum[i]) {
                stk.pop();
            }
            nxt[i] = stk.isEmpty() ? n + 1 : stk.peek();
            stk.push(i);
        }

        long ans = 0;
        for (int i = 0; i < n; i++) {
            int lc = (i == 0) ? 0 : sa.lcp.get(i - 1);
            int idx = sa.sa.get(i) + 1;
            int r = nxt[idx - 1];
            ans += query(adj, cum[idx - 1], idx + lc, r);
        }
        System.out.println(ans);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int t = 1;
        // int t = scanner.nextInt();
        while (t-- > 0) {
            solve();
        }
    }
}