import java.util.*;

public class Main {
    static class Sa {
        int amountVert;
        int[][] tbv = new int[200005][26];
        int[] links = new int[200005];
        long[] maxLength = new long[200005];
        long[] amountPosition = new long[200005];
        long[] degrees = new long[200005];

        void create() {
            for (int i = 1; i <= amountVert; i++) {
                Arrays.fill(tbv[i], 0);
                links[i] = 0;
                maxLength[i] = amountPosition[i] = 0;
            }
            amountVert = 1;
        }

        int add(int last, int symbol) {
            int index = ++amountVert;
            int lst = last;
            maxLength[index] = maxLength[lst] + 1;
            amountPosition[index] = 1;
            for (; lst != 0 && tbv[lst][symbol] == 0; lst = links[lst]) {
                tbv[lst][symbol] = index;
            }
            int q = tbv[lst][symbol];
            if (q == 0) {
                links[index] = 1;
            }
            else if (maxLength[q] == maxLength[lst] + 1) {
                links[index] = q;
            }
            else {
                int clone = ++amountVert;
                maxLength[clone] = maxLength[lst] + 1;
                System.arraycopy(tbv[q], 0, tbv[clone], 0, tbv[q].length);
                for (int x = lst; x != 0 && tbv[x][symbol] == q; x = links[x]) {
                    tbv[x][symbol] = clone;
                }
                links[clone] = links[q];
                links[q] = links[index] = clone;
            }
            return index;
        }

        void topSort() {
            LinkedList<Integer> vertex = new LinkedList<>();
            for (int i = 2; i <= amountVert; i++) {
                degrees[links[i]]++;
            }
            for (int i = 1; i <= amountVert; i++) {
                if (degrees[i] == 0) {
                    vertex.add(i);
                }
            }
            while (!vertex.isEmpty()) {
                int a = vertex.remove(0);
                amountPosition[links[a]] += amountPosition[a];
                if (--degrees[links[a]] == 0) {
                    vertex.add(links[a]);
                }
            }
        }

        long Result() {
            long result = 0;
            for (int i = 1; i <= amountVert; i++) {
                result += (maxLength[i] - maxLength[links[i]]) * amountPosition[i] * amountPosition[i];
            }
            return result;
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int t = scanner.nextInt();
        while (t-- > 0) {
            StringBuilder s = new StringBuilder(scanner.next());
            Sa suffixAutomaton = new Sa();
            suffixAutomaton.create();
            int last = 1;
            for (int i = 0; i < s.length(); i++) {
                last = suffixAutomaton.add(last, s.charAt(i) - 'a');
            }
            suffixAutomaton.topSort();
            System.out.println(suffixAutomaton.Result());
        }
    }
}
