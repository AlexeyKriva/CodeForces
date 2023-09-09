import java.util.Scanner;
import java.util.ArrayList;

class Main {
    static int n;
    static int m;
    static int oldRoute[];
    static int edgeNumber;
    static int nowEdge[];
    static int nextEdge[];
    static int numVert[];
    static int result[];
    static int visit[];
    static boolean findSol;

    static class Edge implements Comparable<Edge> {
        int vertNumber, id;

        public Edge(int vertNumber, int id) {
            this.vertNumber = vertNumber;
            this.id = id;
        }

        public int compareTo(Edge other) {
            return Integer.compare(this.vertNumber, other.vertNumber);
        }
    }

    static void addEdge(int u, int v) {
        numVert[++edgeNumber] = v;
        nextEdge[edgeNumber] = nowEdge[u];
        nowEdge[u] = edgeNumber;
    }

    static void findResult(int vert, int roomNumber, boolean findRes) {
        result[roomNumber] = vert;
        if (roomNumber == m + 1) {
            if (findRes)
                findSol = true;
            return;
        }
        ArrayList<Edge> q = new ArrayList<>();
        for (int i = nowEdge[vert]; i != 0; i = nextEdge[i]) {
            q.add(new Edge(numVert[i], i));
        }
        while (!q.isEmpty() && !findSol) {
            int v = q.get(0).vertNumber;
            int i = q.get(0).id;
            q.remove(0);
            boolean flag = false;
            if (visit[i % 2 == 1 ? i : i - 1] == 1){
                flag = true;
            }
            if (flag) {
                continue;
            }
            if (v < oldRoute[roomNumber + 1] && !findRes) {
                continue;
            }
            visit[i % 2 == 1 ? i : i - 1] = 1;
            if (v > oldRoute[roomNumber + 1]) {
                findResult(v, roomNumber + 1, true);
            }
            else {
                findResult(v, roomNumber + 1, findRes);
            }
            visit[i % 2 == 1 ? i : i - 1] = 0;
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        n = scanner.nextInt();
        m = scanner.nextInt();
        oldRoute = new int[m + 2];
        nowEdge = new int[n + 1];
        nextEdge = new int[2 * m + 1];
        numVert = new int[2 * m + 1];
        result = new int[m + 2];
        visit = new int[2 * m + 1];
        for (int i = 1; i <= m + 1; i++) {
            oldRoute[i] = scanner.nextInt();
        }
        for (int i = 1; i <= m; i++) {
            addEdge(oldRoute[i], oldRoute[i + 1]);
            addEdge(oldRoute[i + 1], oldRoute[i]);
        }
        findResult(oldRoute[1], 1, false);
        if (findSol) {
            for (int i = 1; i <= m + 1; i++) {
                System.out.print(result[i] + " ");
            }
        } else {
            System.out.print("No solution");
        }
    }
}
