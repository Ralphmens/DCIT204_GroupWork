import java.util.Scanner;

public class QuickSort {

    public static void quickSort(int[] array, int lowIndex, int highIndex) {
        if (lowIndex < highIndex) {
            int partitionIndex = partition(array, lowIndex, highIndex);
            quickSort(array, lowIndex, partitionIndex - 1);
            quickSort(array, partitionIndex + 1, highIndex);
        }
    }

    public static int partition(int[] array, int lowIndex, int highIndex) {
        int pivot = array[highIndex];
        int i = (lowIndex - 1); // Index of smaller element

        for (int j = lowIndex; j < highIndex; j++) {
            if (array[j] <= pivot) {
                i++;

                // Swap array[i] and array[j]
                int temp = array[i];
                array[i] = array[j];
                array[j] = temp;
            }
        }

        // Swap array[i+1] and array[highIndex] (or pivot)
        int temp = array[i + 1];
        array[i + 1] = array[highIndex];
        array[highIndex] = temp;

        return i + 1;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the number of elements in the array: ");
        int numberOfElements = scanner.nextInt();

        int[] arrayToSort = new int[numberOfElements];
        System.out.println("Enter the elements of the array:");
        for (int i = 0; i < numberOfElements; i++) {
            arrayToSort[i] = scanner.nextInt();
        }

        long startTime = System.nanoTime();

        quickSort(arrayToSort, 0, numberOfElements - 1);

        long endTime = System.nanoTime();
        long duration = (endTime - startTime); // Time in nanoseconds

        System.out.println("Sorted array:");
        for (int element : arrayToSort) {
            System.out.print(element + " ");
        }
        System.out.println();
        System.out.println("Quick Sort algorithm took " + duration + " nanoseconds (" + (duration / 1000000) + " milliseconds).");
    }
}
