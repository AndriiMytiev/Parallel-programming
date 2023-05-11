package lab2;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class MatrixStringMultiplication {
    public static Matrix stringMultiply(Matrix matrixA, Matrix matrixB, int threads) {
        Matrix resultMatrix = Matrix.emptyMatrix(matrixA.rowAmount(), matrixB.colAmount());

        ExecutorService executor = Executors.newFixedThreadPool(threads);

        for (int row = 0; row < matrixA.colAmount(); row++) {
            for (int col = 0; col < matrixB.rowAmount(); col++) {
                executor.execute(new Multiplier(row, col, matrixA, matrixB, resultMatrix));
            }
        }

        executor.shutdown();

        try {
            executor.awaitTermination(Long.MAX_VALUE, TimeUnit.NANOSECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return resultMatrix;
    }

    private static class Multiplier implements Runnable {
        private final int row;
        private final int col;
        private final Matrix matrixA;
        private final Matrix matrixB;
        private final Matrix resultMatrix;

        public Multiplier(int row, int col, Matrix matrixA, Matrix matrixB, Matrix resultMatrix) {
            this.row = row;
            this.col = col;
            this.matrixA = matrixA;
            this.matrixB = matrixB;
            this.resultMatrix = resultMatrix;
        }

        @Override
        public void run() {
            int sum = 0;

            for (int i = 0; i < matrixA.colAmount(); i++) {
                sum += matrixA.get(row, i) * matrixB.get(i, col);
            }

            resultMatrix.set(row, col, sum);
        }
    }
}
