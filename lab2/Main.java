package lab2;

public class Main {
    public static void main(String[] args) {
        Matrix testMatrix1 = Matrix.randomMatrix(3,3);
        Matrix testMatrix2 = Matrix.randomMatrix(3,3);
        long time = System.nanoTime();
        Matrix resultMatrix = MatrixStringMultiplication.stringMultiply(testMatrix1, testMatrix2, 2);
        time = System.nanoTime() - time;
        System.out.println("Test matrix A:");
        System.out.println(testMatrix1);
        System.out.println("Test matrix B:");
        System.out.println(testMatrix2);
        System.out.println("Result matrix C:");
        System.out.println(resultMatrix);
        System.out.println("Time: " + time / 1000000 + " ms.");

//        System.out.println("\n########## Test 'String' matrix multiply: ##########\n");
//        for (int i = 2; i <= 256; i*=2) {
//            testStringMultiply(i);
//            System.out.println("\n------------------------------------------\n");
//        }
//        System.out.println("########## Test 'Fox's' matrix multiply: ##########\n");
//        for (int i = 2; i <= 256; i*=2) {
//            testFoxMultiply(i);
//            System.out.println("\n------------------------------------------\n");
//        }
    }

    private static void testStringMultiply(int threads) {
        for (int size = 1000; size <= 3000; size += 500) {

            Matrix m1 = Matrix.randomMatrix(size, size);
            Matrix m2 = Matrix.randomMatrix(size, size);

            long time = System.nanoTime();

            MatrixStringMultiplication.stringMultiply(m1, m2, threads);

            time = System.nanoTime() - time;

            System.out.println("[" + size + "x" + size + "]: threads: " + threads + " --> " + time / 1000000 + " ms.");
        }
    }

    private static void testFoxMultiply(int threads) {
        for (int size = 1000; size <= 3000; size += 500) {

            Matrix m1 = Matrix.randomMatrix(size, size);
            Matrix m2 = Matrix.randomMatrix(size, size);

            long time = System.nanoTime();

            MatrixFoxMultiplication.foxMultiply(m1, m2, threads, (int) Math.sqrt(size));

            time = System.nanoTime() - time;

            System.out.println("[" + size + "x" + size + "]: threads: " + threads + " --> " + time / 1000000 + " ms.");
        }
    }
}
