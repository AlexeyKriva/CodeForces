import java.util.Scanner;

public class Main {
    static final int maxn = 5005;
    static int[] cnt = new int[26];
    static int[] tmp = new int[26];
    static int ls, lt;
    static char[] s = new char[maxn];
    static char[] t = new char[maxn];
    static char[] ans = new char[maxn];

    static int Solve(int pos) {
        System.arraycopy(cnt, 0, tmp, 0, cnt.length);
        int c = 25;
        for (int i = pos; i <= lt; ++i) {
            while (tmp[c] == 0)
                --c;
            if (c > t[i] - 'a')
                return 1;
            if (c < t[i] - 'a')
                return 0;
            --tmp[c];
        }
        return ls > lt ? 1 : 0;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String sStr = scanner.next();
        String tStr = scanner.next();
        s = (" " + sStr).toCharArray();
        t = (" " + tStr).toCharArray();

        ls = sStr.length();
        lt = tStr.length();

        for (int i = 1; i <= ls; ++i)
            ++cnt[s[i] - 'a'];

        if (Solve(1) == 0) {
            System.out.println("-1");
            return;
        }

        for (int i = 1; i <= ls; ++i) {
            int c = -1;
            if (i <= lt) {
                c = t[i] - 'a';
                if (cnt[c] > 0) {
                    ans[i] = (char) (c + 'a');
                    --cnt[c];
                    if (Solve(i + 1) == 1)
                        continue;
                    ++cnt[c];
                }
            }
            int p = -1;
            for (int j = c + 1; j < 26; ++j) {
                if (cnt[j] > 0) {
                    p = j;
                    break;
                }
            }
            if (p == -1) {
                System.out.println("-1");
                return;
            }
            ans[i] = (char) (p + 'a');
            --cnt[p];
            int now = 0;
            for (int j = i + 1; j <= ls; ++j) {
                while (cnt[now] == 0)
                    ++now;
                ans[j] = (char) (now + 'a');
                --cnt[now];
            }
            break;
        }
        System.out.println(new String(ans, 1, ans.length - 1));
    }
}
