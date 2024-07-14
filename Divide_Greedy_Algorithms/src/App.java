import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("Select an algorithm to run:");
            System.out.println("1. Traveling Salesman Problem");
            System.out.println("2. Closest Pair Problem");
            System.out.println("3. Dijkstra's Shortest Path");
            System.out.println("4. Huffman Codes");
            System.out.println("5. Kruskal's MST");
            System.out.println("6. Merge Sort");
            System.out.println("7. Prim's MST");
            System.out.println("8. Quickhull");
            System.out.println("9. Quick Sort");
            System.out.println("10. Strassen's Matrix Multiplication");
            System.out.println("11. Exit");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    TSP.main(null);
                    break;
//                case 2:
//                    ClosestPairProblem.main(null);
//                    break;
                case 3:
                    Graph.main(null);
                    break;
                case 4:
                    HuffmanCodes.main(null);
                    break;
                case 5:
                    KruskalMST.main(null);
                    break;
                case 6:
                    MergeSort.main(null);
                    break;
//                case 7:
//                    PrimsMST.main(null);
//                    break;
//                case 8:
//                    Quickhull.main(null);
//                    break;
                case 9:
                    QuickSort.main(null);
                    break;
                case 10:
                    StrassenMatrixMultiplication.main(null);
                    break;
                case 11:
                    System.out.println("Exiting...");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }

            System.out.println();
        }
    }
}
