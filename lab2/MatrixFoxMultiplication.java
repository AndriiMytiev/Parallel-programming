package lab2;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class MatrixFoxMultiplication {

    public static Matrix foxMultiply(Matrix matrixA, Matrix matrixB, int threads, int blockSize) {
        Matrix resultMatrix = Matrix.emptyMatrix(matrixA.rowAmount(), matrixB.colAmount());

        ExecutorService executor = Executors.newFixedThreadPool(threads);

        Matrix[][] blocksA = matrixA.split(blockSize);
        Matrix[][] blocksB = matrixB.split(blockSize);
        Matrix[][] blocksResult = resultMatrix.split(blockSize);

        for (int i = 0; i < blocksResult.length; i++) {
            for (int j = 0; j < blocksResult[0].length; j++) {
                executor.execute(new BlockTask(i, j, blocksA, blocksB, blocksResult));
            }
        }

        executor.shutdown();

        try {
            executor.awaitTermination(Long.MAX_VALUE, TimeUnit.NANOSECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return Matrix.matrixFrom(resultMatrix.rowAmount(), resultMatrix.colAmount(), blocksResult);
    }

    private static class BlockTask implements Runnable {
        private final int row;
        private final int col;
        private final Matrix[][] blocksA;
        private final Matrix[][] blocksB;
        private final Matrix[][] blocksResult;

        public BlockTask(int row, int col, Matrix[][] blocksA, Matrix[][] blocksB, Matrix[][] blocksResult) {
            this.row = row;
            this.col = col;
            this.blocksA = blocksA;
            this.blocksB = blocksB;
            this.blocksResult = blocksResult;
        }

        @Override
        public void run() {
            Matrix block = Matrix.emptyMatrix(blocksResult[0][0].rowAmount(), blocksResult[0][0].colAmount());
            for (int k = 0; k < blocksResult.length; k++) {
                Matrix multiplyResult = MatrixStringMultiplication.stringMultiply(blocksA[row][k], blocksB[k][col], 1);
                block.addMatrix(multiplyResult);
            }
            blocksResult[row][col].addMatrix(block);
        }
    }
}
