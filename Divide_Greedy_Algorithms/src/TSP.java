import java.util.*;

public class TSP {

    private int V;
    private List<Integer> path;

    public TSP(int V) {
        this.V = V;
        this.path = new ArrayList<>();
    }

    public int tsp(int[][] graph) {
        boolean[] visited = new boolean[V];
        path.clear();
        visited[0] = true;
        path.add(0);
        return tspUtil(graph, visited, 0, V, 1, 0, Integer.MAX_VALUE);
    }

    private int tspUtil(int[][] graph, boolean[] visited, int currPos, int n, int count, int cost, int ans) {
        if (count == n && graph[currPos][0] > 0) {
            path.add(0);
            return Math.min(ans, cost + graph[currPos][0]);
        }

        for (int i = 0; i < n; i++) {
            if (!visited[i] && graph[currPos][i] > 0) {
                visited[i] = true;
                path.add(i);
                ans = tspUtil(graph, visited, i, n, count + 1, cost + graph[currPos][i], ans);
                path.remove(path.size() - 1);
                visited[i] = false;
            }
        }
        return ans;
    }

    public List<Integer> getPath() {
        return path;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the number of vertices:");
        int V = scanner.nextInt();
        int[][] graph = new int[V][V];
        System.out.println("Enter the adjacency matrix:");
        for (int i = 0; i < V; i++) {
            for (int j = 0; j < V; j++) {
                graph[i][j] = scanner.nextInt();
            }
        }

        TSP tsp = new TSP(V);
        long startTime = System.nanoTime();
        int result = tsp.tsp(graph);
        long endTime = System.nanoTime();
        System.out.println("Minimum cost: " + result);
        System.out.println("Path: " + tsp.getPath());
        System.out.println("Execution time: " + (endTime - startTime) + " nanoseconds");
    }
}
