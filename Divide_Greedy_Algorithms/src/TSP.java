import java.util.Scanner;

public class TSP {
    static int numberOfCities; // Number of cities
    static int[][] distanceMatrix; // Distance matrix

    public static int tsp(int visitedCitiesMask, int currentPosition, int[][] dp) {
        if (visitedCitiesMask == ((1 << numberOfCities) - 1)) {
            return distanceMatrix[currentPosition][0];
        }

        if (dp[visitedCitiesMask][currentPosition] != -1) {
            return dp[visitedCitiesMask][currentPosition];
        }

        int minimumCost = Integer.MAX_VALUE;

        for (int city = 0; city < numberOfCities; city++) {
            if ((visitedCitiesMask & (1 << city)) == 0) {
                int newCost = distanceMatrix[currentPosition][city] + tsp(visitedCitiesMask | (1 << city), city, dp);
                minimumCost = Math.min(minimumCost, newCost);
            }
        }

        return dp[visitedCitiesMask][currentPosition] = minimumCost;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the number of cities: ");
        numberOfCities = scanner.nextInt();

        distanceMatrix = new int[numberOfCities][numberOfCities];
        System.out.println("Enter the distance matrix:");
        for (int i = 0; i < numberOfCities; i++) {
            for (int j = 0; j < numberOfCities; j++) {
                distanceMatrix[i][j] = scanner.nextInt();
            }
        }

        long startTime = System.nanoTime();

        int[][] dp = new int[1 << numberOfCities][numberOfCities];
        for (int i = 0; i < (1 << numberOfCities); i++) {
            for (int j = 0; j < numberOfCities; j++) {
                dp[i][j] = -1;
            }
        }

        int result = tsp(1, 0, dp);

        long endTime = System.nanoTime();
        long duration = (endTime - startTime) / 1000000; // Convert to milliseconds

        System.out.println("The minimum cost of the TSP tour is: " + result);
        System.out.println("TSP algorithm took " + duration + " milliseconds.");
    }
}
