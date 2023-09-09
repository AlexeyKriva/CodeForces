import java.util.*;

public class Main {
    static List<Integer>[] graph = new ArrayList[3001];
    static List<Integer> stations = new ArrayList<>();
    static int[] visited = new int[3001];
    static int[] parents = new int[3001];
    static int n;

    static boolean dfs(int node, int parent) {
        if (visited[node] == 2){
            return false;
        }
        if (visited[node] == 1) {
            stations.add(node);
            int cur = parent;
            while (cur != node) {
                stations.add(cur);
                cur = parents[cur];
            }
            return true;
        }
        visited[node] = 1;
        parents[node] = parent;
        for (int station : graph[node]) {
            if (station == parent) {
                continue;
            }
            if (dfs(station, node)){
                return true;
            }
        }
        visited[node] = 2;
        return false;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        n = scanner.nextInt();
        for (int i = 0; i < 3001; i++) {
            graph[i] = new ArrayList<>();
        }
        for (int i = 1; i <= n; i++) {
            int u = scanner.nextInt();
            int v = scanner.nextInt();
            graph[u].add(v);
            graph[v].add(u);
        }

        dfs(1, 0);

        int[] results = new int[n + 1];
        Arrays.fill(results, Integer.MAX_VALUE);
        List<Integer> que = new LinkedList<>();
        for (int c : stations) {
            results[c] = 0;
            que.add(c);
        }
        while (!que.isEmpty()) {
            int cur = que.remove(0);
            for (int next : graph[cur]) {
                int d = 1 + results[cur];
                if (d < results[next]) {
                    results[next] = d;
                    que.add(next);
                }
            }
        }
        for (int i = 1; i <= n; i++) {
            System.out.print(results[i] + " ");
        }
    }
}
