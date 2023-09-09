import java.util.*;

public class Main {
    static int n, m, vertInd = 1;
    static int[] adjList = new int[200002];
    static int[] vertexQ = new int[200002];
    static int[] result = new int[200002];
    static int[] allFlow = new int[200002];
    static int[] sumFlow = new int[200002];
    static boolean[] visit = new boolean[400004];
    static Edge[] edges = new Edge[400004];

    static class Edge {
        int y, z, n;

        Edge(int y, int z, int n) {
            this.y = y;
            this.z = z;
            this.n = n;
        }
    }

    static void addEdge(int x, int y, int z) {
        vertInd++;
        edges[vertInd] = new Edge(y, z, adjList[x]);
        adjList[x] = vertInd;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        n = scanner.nextInt();
        m = scanner.nextInt();
        for (int i = 1; i <= m; i++) {
            int a = scanner.nextInt();
            int b = scanner.nextInt();
            int c = scanner.nextInt();
            addEdge(a, b, c);
            addEdge(b, a, c);
            allFlow[a] += c;
            allFlow[b] += c;
        }
        vertInd = 1;
        vertexQ[vertInd] = 1;
        for (int i = 1; i <= vertInd; i++) {
            for (int j = adjList[vertexQ[i]]; j != 0; j = edges[j].n) {
                if (!visit[j]) {
                    visit[j] = visit[j ^ 1] = true;
                    result[j / 2] = j % 2;
                    sumFlow[edges[j].y] += edges[j].z;
                    if (sumFlow[edges[j].y] * 2 == allFlow[edges[j].y] && edges[j].y != n) {
                        vertexQ[++vertInd] = edges[j].y;
                    }
                }
            }
        }
        for (int i = 1; i <= m; i++) {
            System.out.println(result[i]);
        }
    }
}
