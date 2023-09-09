import java.util.Scanner;

public class Main {
    static char[] s = new char[100005];
    static int curr = 1, all = 1;
    static int[][] st = new int[200005][26];
    static int[] len = new int[200005];
    static int[] par = new int[200005];
    static long[] cou = new long[200005];
    static long res;
    static int[] maxn = new int[1000005];
    static int[] id = new int[2000005];

    public static void create(int c) {
        int p = curr, np = ++all;
        cou[np]++;
        curr = all;
        len[np] = len[p] + 1;
        while (p != 0 && st[p][c] == 0) {
            st[p][c] = np;
            p = par[p];
        }
        if (p == 0) {
            par[np] = 1;
        } else {
            int q = st[p][c];
            if (len[q] == len[p] + 1) {
                par[np] = q;
            } else {
                int nq = ++all;
                System.arraycopy(st[q], 0, st[nq], 0, st[q].length);
                par[nq] = par[q];
                len[nq] = len[p] + 1;
                par[q] = par[np] = nq;
                while (p != 0 && st[p][c] == q) {
                    st[p][c] = nq;
                    p = par[p];
                }
            }
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        s = scanner.next().toCharArray();
        int sz = s.length;
        for (int i = 0; i < sz; i++) {
            create(s[i] - 'a');
        }
        for (int i = 1; i <= all; i++) {
            maxn[len[i]]++;
        }
        for (int i = 1; i <= sz; i++) {
            maxn[i] += maxn[i - 1];
        }
        for (int i = 1; i <= all; i++) {
            id[maxn[len[i]]--] = i;
        }
        for (int i = all; i >= 1; i--) {
            cou[par[id[i]]] += cou[id[i]];
        }
        for (int i = 2; i <= all; i++) {
            res += (len[i] - len[par[i]]) * cou[i] * (cou[i] + 1) / 2;
        }
        System.out.println(res);
    }
}
