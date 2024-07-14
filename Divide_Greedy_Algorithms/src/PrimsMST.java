import java.util.Scanner;
import java.util.Arrays;

public class PrimsMST {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the number of vertices: ");
        int numberOfVertices = scanner.nextInt();

        int[][] graph = new int[numberOfVertices][numberOfVertices];
        System.out.println("Enter the adjacency matrix:");
        for (int i = 0; i < numberOfVertices; i++) {
            for (int j = 0; j < numberOfVertices; j++) {
                graph[i][j] = scanner.nextInt();
            }
        }

        long startTime = System.nanoTime();

        primsMST(graph, numberOfVertices);

        long endTime = System.nanoTime();
        long duration = (endTime - startTime); // Time in nanoseconds

        System.out.println("Prim's MST algorithm took " + duration + " nanoseconds (" + (duration / 1000000) + " milliseconds).");
    }

    public static void primsMST(int[][] graph, int numberOfVertices) {
        int[] parent = new int[numberOfVertices]; // Array to store constructed MST
        int[] key = new int[numberOfVertices]; // Key values used to pick minimum weight edge in cut
        boolean[] mstSet = new boolean[numberOfVertices]; // To represent set of vertices not yet included in MST

        // Initialize all keys as INFINITE
        Arrays.fill(key, Integer.MAX_VALUE);

        // Always include first 1st vertex in MST.
        key[0] = 0; // Make key 0 so that this vertex is picked as first vertex
        parent[0] = -1; // First node is always root of MST

        // The MST will have numberOfVertices vertices
        for (int count = 0; count < numberOfVertices - 1; count++) {
            // Pick the minimum key vertex from the set of vertices not yet included in MST
            int u = minKey(key, mstSet, numberOfVertices);

            // Add the picked vertex to the MST Set
            mstSet[u] = true;

            // Update key value and parent index of the adjacent vertices of the picked vertex.
            for (int v = 0; v < numberOfVertices; v++) {
                // graph[u][v] is non zero only for adjacent vertices of m
                // mstSet[v] is false for vertices not yet included in MST
                // Update the key only if graph[u][v] is smaller than key[v]
                if (graph[u][v] != 0 && !mstSet[v] && graph[u][v] < key[v]) {
                    parent[v] = u;
                    key[v] = graph[u][v];
                }
            }
        }

        // Print the constructed MST
        printMST(parent, graph, numberOfVertices);
    }

    public static int minKey(int[] key, boolean[] mstSet, int numberOfVertices) {
        // Initialize min value
        int min = Integer.MAX_VALUE, minIndex = -1;

        for (int v = 0; v < numberOfVertices; v++) {
            if (!mstSet[v] && key[v] < min) {
                min = key[v];
                minIndex = v;
            }
        }

        return minIndex;
    }

    public static void printMST(int[] parent, int[][] graph, int numberOfVertices) {
        System.out.println("Edge \tWeight");
        for (int i = 1; i < numberOfVertices; i++) {
            System.out.println(parent[i] + " - " + i + "\t" + graph[i][parent[i]]);
        }
    }
}

