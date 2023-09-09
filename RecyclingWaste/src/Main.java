import java.util.*;

public class Main {
    static class Point implements Comparable<Point> {
        double dist = 0;
        int id = 0;

        public Point(double first, int second) {
            this.dist = first;
            this.id = second;
        }

        @Override
        public int compareTo(Point other) {
            if (this.dist < other.dist)
                return -1;
            else if (this.dist > other.dist)
                return 1;
            else
                return 0;
        }
    }

    static double evklidDist(int x1, int y1, int x2, int y2) {
        return Math.sqrt(1.0 * (x1 - x2) * (x1 - x2) + 1.0 * (y1 - y2) * (y1 - y2));
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int ax = scanner.nextInt();
        int ay = scanner.nextInt();
        int bx = scanner.nextInt();
        int by = scanner.nextInt();
        int tx = scanner.nextInt();
        int ty = scanner.nextInt();
        int n = scanner.nextInt();
        int[] x = new int[100005];
        int[] y = new int[100005];
        Point[] distAdil = new Point[100005];
        Point[] distBera = new Point[100005];
        Point[] distUrn = new Point[100005];
        double result = 0;
        double addDist = 0;
        for (int i = 1; i <= n; i++) {
            x[i] = scanner.nextInt();
            y[i] = scanner.nextInt();
            double dis = evklidDist(x[i], y[i], tx, ty);
            distAdil[i] = new Point(evklidDist(ax, ay, x[i], y[i]) - dis, i);
            distBera[i] = new Point(evklidDist(bx, by, x[i], y[i]) - dis, i);
            distUrn[i] = new Point(dis * 2, i);
            result += distUrn[i].dist;
        }
        Arrays.sort(distAdil, 1, n + 1);
        Arrays.sort(distBera, 1, n + 1);
        if (n == 1){
            distAdil[2] = new Point(0, 0);
            distBera[2] = new Point(0, 0);
        }
        addDist = Math.min(distAdil[1].dist, distBera[1].dist);
        if (distAdil[1].id == distBera[1].id) {
            addDist = Math.min(addDist, Math.min(distAdil[1].dist + distBera[2].dist, distAdil[2].dist + distBera[1].dist));
        }
        else {
            addDist = Math.min(addDist, distAdil[1].dist + distBera[1].dist);
        }
        System.out.println(result + addDist);
    }
}
