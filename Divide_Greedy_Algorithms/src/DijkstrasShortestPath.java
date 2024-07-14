import java.util.*;

class Graph {
    private int vertices;
    private LinkedList<Edge>[] adjacencyList;

    class Edge {
        int dest;
        int weight;

        Edge(int dest, int weight) {
            this.dest = dest;
            this.weight = weight;
        }
    }

    Graph(int vertices) {
        this.vertices = vertices;
        adjacencyList = new LinkedList[vertices];
        for (int i = 0; i < vertices; i++) {
            adjacencyList[i] = new LinkedList<>();
        }
    }

    void addEdge(int src, int dest, int weight) {
        if (src >= vertices || dest >= vertices) {
            System.out.println("Error: Source or destination vertex out of bounds. Please enter valid vertices.");
            return;
        }
        Edge edge = new Edge(dest, weight);
        adjacencyList[src].add(edge);
    }

    void dijkstra(int src) {
        if (src >= vertices) {
            System.out.println("Error: Source vertex out of bounds. Please enter a valid source vertex.");
            return;
        }

        int[] distances = new int[vertices];
        boolean[] visited = new boolean[vertices];
        PriorityQueue<Node> pq = new PriorityQueue<>(vertices, new Node());

        Arrays.fill(distances, Integer.MAX_VALUE);
        distances[src] = 0;
        pq.add(new Node(src, 0));

        while (!pq.isEmpty()) {
            int u = pq.poll().node;

            if (visited[u]) continue;
            visited[u] = true;

            for (Edge edge : adjacencyList[u]) {
                int v = edge.dest;
                int weight = edge.weight;

                if (!visited[v] && distances[u] + weight < distances[v]) {
                    distances[v] = distances[u] + weight;
                    pq.add(new Node(v, distances[v]));
                }
            }
        }

        printSolution(distances);
    }

    void printSolution(int[] dist) {
        System.out.println("\nVertex \t Distance from Source");
        for (int i = 0; i < vertices; i++) {
            System.out.println(i + " \t\t " + dist[i]);
        }
    }

    class Node implements Comparator<Node> {
        int node;
        int cost;

        public Node() {}

        public Node(int node, int cost) {
            this.node = node;
            this.cost = cost;
        }

        @Override
        public int compare(Node node1, Node node2) {
            return Integer.compare(node1.cost, node2.cost);
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Welcome to the Dijkstra's Shortest Path Algorithm Tester!");
        
        int vertices = getPositiveIntegerInput(scanner, "Enter the number of vertices:");

        Graph graph = new Graph(vertices);

        int edges = getPositiveIntegerInput(scanner, "Enter the number of edges:");
        
        System.out.println("Enter the edges with source, destination, and weight:");
        for (int i = 0; i < edges; i++) {
            int src = getValidVertex(scanner, "Source vertex:", vertices);
            int dest = getValidVertex(scanner, "Destination vertex:", vertices);
            int weight = getPositiveIntegerInput(scanner, "Edge weight:");
            graph.addEdge(src, dest, weight);
        }

        int src = getValidVertex(scanner, "Enter the source vertex:", vertices);

        long startTime = System.nanoTime();
        graph.dijkstra(src);
        long endTime = System.nanoTime();

        System.out.println("Execution time: " + (endTime - startTime) + " nanoseconds");
    }

    private static int getPositiveIntegerInput(Scanner scanner, String prompt) {
        int input = -1;
        while (input < 0) {
            System.out.print(prompt + " ");
            try {
                input = scanner.nextInt();
                if (input < 0) {
                    System.out.println("Please enter a non-negative integer.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter an integer.");
                scanner.next(); // Clear invalid input
            }
        }
        return input;
    }

    private static int getValidVertex(Scanner scanner, String prompt, int vertices) {
        int vertex = -1;
        while (vertex < 0 || vertex >= vertices) {
            System.out.print(prompt + " ");
            try {
                vertex = scanner.nextInt();
                if (vertex < 0 || vertex >= vertices) {
                    System.out.println("Please enter a valid vertex (0 to " + (vertices - 1) + ").");
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter an integer.");
                scanner.next(); // Clear invalid input
            }
        }
        return vertex;
    }
}

