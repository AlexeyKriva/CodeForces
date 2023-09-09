import java.util.Scanner;

public class Main {
    static final int N = 100010;

    static int m;
    static int[] _max = new int[N << 2];
    static int[] add = new int[N << 2];
    static int[] a = new int[N];

    public static int max(int x, int y) {
        return x > y ? x : y;
    }

    public static void pushdown(int p) {
        if (add[p] == 0) return;
        _max[p << 1] += add[p];
        _max[p << 1 | 1] += add[p];
        add[p << 1] += add[p];
        add[p << 1 | 1] += add[p];
        add[p] = 0;
    }

    public static void pushup(int p) {
        _max[p] = max(_max[p << 1], _max[p << 1 | 1]);
    }

    public static void modify(int p, int l, int r, int L, int R, int k) {
        if (L <= l && r <= R) {
            _max[p] += k;
            add[p] += k;
            return;
        }
        pushdown(p);
        int mid = (l + r) >> 1;
        if (L <= mid) modify(p << 1, l, mid, L, R, k);
        if (R > mid) modify(p << 1 | 1, mid + 1, r, L, R, k);
        pushup(p);
    }

    public static int query(int p, int l, int r) {
        if (l == r) {
            return l;
        }
        pushdown(p);
        int mid = (l + r) >> 1;
        if (_max[p << 1 | 1] > 0) return query(p << 1 | 1, mid + 1, r);
        else return query(p << 1, l, mid);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        m = scanner.nextInt();
        for (int i = 1, p, t; i <= m; i++) {
            p = scanner.nextInt();
            t = scanner.nextInt();
            if (t == 1) {
                a[p] = scanner.nextInt();
            }
            modify(1, 1, m, 1, p, t == 1 ? 1 : -1);
            if (_max[1] <= 0) {
                System.out.println("-1");
            } else {
                System.out.println(a[query(1, 1, m)]);
            }
        }
    }
}
