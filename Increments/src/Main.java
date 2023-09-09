import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

class Main {
    static class Node {
        int x, y, z;

        Node(int x, int y, int z) {
            this.x = x;
            this.y = y;
            this.z = z;
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int m = scanner.nextInt();
        Node[] a = new Node[m + 1];
        for (int i = 1; i <= m; ++i) {
            int x = scanner.nextInt();
            int y = scanner.nextInt();
            int z = scanner.nextInt();
            a[i] = new Node(x, y, z);
        }
        Arrays.sort(a, 1, m + 1, new Comparator<Node>() {
            @Override
            public int compare(Node p, Node q) {
                return Integer.compare(p.x, q.x);
            }
        });
        int[] f = new int[n + 1];
        Arrays.fill(f, Integer.MAX_VALUE);
        f[0] = 1;
        for (int i = m; i >= 1; --i) {
            for (int j = n; j >= a[i].z; --j) {
                if (f[j - a[i].z] <= a[i].y) {
                    f[j] = Math.min(f[j], Math.max(f[j - a[i].z], a[i].x));
                }
            }
        }
        List<Integer> vc = new ArrayList<>();
        for (int i = 1; i <= n; ++i) {
            if (f[i] <= n) {
                vc.add(i);
            }
        }
        System.out.println(vc.size());
        for (int i : vc) {
            System.out.print(i + " ");
        }
    }
}
