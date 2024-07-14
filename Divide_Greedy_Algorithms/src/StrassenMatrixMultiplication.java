import java.util.Arrays;
import java.util.Scanner;

public class StrassenMatrixMultiplication {

    public int[][] multiply(int[][] A, int[][] B) {
        int n = A.length;
        int[][] C = new int[n][n];
        if (n == 1) {
            C[0][0] = A[0][0] * B[0][0];
        } else {
            int newSize = n / 2;
            int[][][] subMatrices = new int[8][newSize][newSize];
            splitMatrix(A, subMatrices, 0);
            splitMatrix(B, subMatrices, 4);
            int[][][] M = new int[7][newSize][newSize];
            int[][] A11 = subMatrices[0];
            int[][] A12 = subMatrices[1];
            int[][] A21 = subMatrices[2];
            int[][] A22 = subMatrices[3];
            int[][] B11 = subMatrices[4];
            int[][] B12 = subMatrices[5];
            int[][] B21 = subMatrices[6];
            int[][] B22 = subMatrices[7];
            M[0] = multiply(add(A11, A22), add(B11, B22));
            M[1] = multiply(add(A21, A22), B11);
            M[2] = multiply(A11, subtract(B12, B22));
            M[3] = multiply(A22, subtract(B21, B11));
            M[4] = multiply(add(A11, A12), B22);
            M[5] = multiply(subtract(A21, A11), add(B11, B12));
            M[6] = multiply(subtract(A12, A22), add(B21, B22));
            int[][] C11 = add(subtract(add(M[0], M[3]), M[4]), M[6]);
            int[][] C12 = add(M[2], M[4]);
            int[][] C21 = add(M[1], M[3]);
            int[][] C22 = add(subtract(add(M[0], M[2]), M[1]), M[5]);
            joinMatrix(C, C11, 0, 0);
            joinMatrix(C, C12, 0, newSize);
            joinMatrix(C, C21, newSize, 0);
            joinMatrix(C, C22, newSize, newSize);
        }
        return C;
    }

    private void splitMatrix(int[][] P, int[][][] C, int startIndex) {
        int n = P.length / 2;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                C[startIndex][i][j] = P[i][j];
                C[startIndex + 1][i][j] = P[i][j + n];
                C[startIndex + 2][i][j] = P[i + n][j];
                C[startIndex + 3][i][j] = P[i + n][j + n];
            }
        }
    }

    private void joinMatrix(int[][] C, int[][] P, int iB, int jB) {
        int n = P.length;
        for (int i = 0, iC = iB; i < n; i++, iC++) {
            for (int j = 0, jC = jB; j < n; j++, jC++) {
                C[iC][jC] = P[i][j];
            }
        }
    }

    private int[][] add(int[][] A, int[][] B) {
        int n = A.length;
        int[][] C = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                C[i][j] = A[i][j] + B[i][j];
            }
        }
        return C;
    }

    private int[][] subtract(int[][] A, int[][] B) {
        int n = A.length;
        int[][] C = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                C[i][j] = A[i][j] - B[i][j];
            }
        }
        return C;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the size of the matrices (must be a power of 2):");
        int size = scanner.nextInt();
        int[][] A = new int[size][size];
        int[][] B = new int[size][size];
        System.out.println("Enter the first matrix:");
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                A[i][j] = scanner.nextInt();
            }
        }
        System.out.println("Enter the second matrix:");
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                B[i][j] = scanner.nextInt();
            }
        }

        StrassenMatrixMultiplication smm = new StrassenMatrixMultiplication();
        long startTime = System.nanoTime();
        int[][] resultMatrix = smm.multiply(A, B);
        long endTime = System.nanoTime();
        System.out.println("Result matrix: " + Arrays.deepToString(resultMatrix));
        System.out.println("Execution time: " + (endTime - startTime) + " nanoseconds");
    }
}
