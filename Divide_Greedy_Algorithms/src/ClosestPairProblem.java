
import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

public class ClosestPairProblem {
    public static class Point {
        public double x, y;

        public Point(double x, double y) {
            this.x = x;
            this.y = y;
        }
    }

    public static void run() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the number of points:");
        int n = scanner.nextInt();
        Point[] points = new Point[n];

        System.out.println("Enter the points (x y): There should be space inbetween x and y");
        for (int i = 0; i < n; i++) {
            double x = scanner.nextDouble();
            double y = scanner.nextDouble();
            points[i] = new Point(x, y);
        }

        double result = closestPair(points);
        System.out.println("The smallest distance is " + result);
    }

    private static double closestPair(Point[] points) {
        Point[] px = points.clone();
        Point[] py = points.clone();
        Arrays.sort(px, Comparator.comparingDouble(p -> p.x));
        Arrays.sort(py, Comparator.comparingDouble(p -> p.y));
        return closestPair(px, py, 0, points.length - 1);
    }

    private static double closestPair(Point[] px, Point[] py, int low, int high) {
        if (high - low <= 3) {
            return bruteForce(px, low, high);
        }

        int mid = (low + high) / 2;
        Point midPoint = px[mid];

        Point[] pyl = Arrays.copyOfRange(py, low, mid + 1);
        Point[] pyr = Arrays.copyOfRange(py, mid + 1, high + 1);

        double d1 = closestPair(px, pyl, low, mid);
        double d2 = closestPair(px, pyr, mid + 1, high);

        double d = Math.min(d1, d2);

        return Math.min(d, stripClosest(py, low, high, midPoint, d));
    }

    private static double stripClosest(Point[] py, int low, int high, Point midPoint, double d) {
        Point[] strip = new Point[high - low + 1];
        int j = 0;
        for (int i = low; i <= high; i++) {
            if (Math.abs(py[i].x - midPoint.x) < d) {
                strip[j++] = py[i];
            }
        }

        double min = d;
        Arrays.sort(strip, 0, j, Comparator.comparingDouble(p -> p.y));
        for (int i = 0; i < j; ++i) {
            for (int k = i + 1; k < j && (strip[k].y - strip[i].y) < min; ++k) {
                min = Math.min(min, distance(strip[i], strip[k]));
            }
        }

        return min;
    }

    private static double bruteForce(Point[] points, int low, int high) {
        double min = Double.MAX_VALUE;
        for (int i = low; i <= high; ++i) {
            for (int j = i + 1; j <= high; ++j) {
                double dist = distance(points[i], points[j]);
                if (dist < min) {
                    min = dist;
                }
            }
        }
        return min;
    }

    private static double distance(Point p1, Point p2) {
        return Math.sqrt((p1.x - p2.x) * (p1.x - p2.x) + (p1.y - p2.y) * (p1.y - p2.y));
    }
}
