import java.util.Arrays;
import java.util.Scanner;

public class MergeSort {

    public void sort(int[] arr) {
        if (arr.length > 1) {
            int mid = arr.length / 2;

            int[] left = Arrays.copyOfRange(arr, 0, mid);
            int[] right = Arrays.copyOfRange(arr, mid, arr.length);

            sort(left);
            sort(right);

            merge(arr, left, right);
        }
    }

    private void merge(int[] arr, int[] left, int[] right) {
        int i = 0, j = 0, k = 0;

        while (i < left.length && j < right.length) {
            if (left[i] <= right[j]) {
                arr[k++] = left[i++];
            } else {
                arr[k++] = right[j++];
            }
        }

        while (i < left.length) {
            arr[k++] = left[i++];
        }

        while (j < right.length) {
            arr[k++] = right[j++];
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the number of elements:");
        int n = scanner.nextInt();
        int[] arr = new int[n];
        System.out.println("Enter the elements:");
        for (int i = 0; i < n; i++) {
            arr[i] = scanner.nextInt();
        }

        MergeSort mergeSort = new MergeSort();
        long startTime = System.nanoTime();
        mergeSort.sort(arr);
        long endTime = System.nanoTime();
        System.out.println("Sorted array: " + Arrays.toString(arr));
        System.out.println("Execution time: " + (endTime - startTime) + " nanoseconds");
    }
}
