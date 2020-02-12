/*
 * To test the MatrixMultiplication class that consists of three different approaches.
 */
public class Test 
{ 	
	/*
	 * The main program that runs an infinite loops until the computer crashes. The size of the matrix
	 * that it generates is of power of 2 and is of size n x n. I am running each algorithm 10 times
	 * to find the average time spent by each algorithm of distinct sizes.
	 */
	public static void main(String[] args) 
	{
		MatrixMultiplication MM = new MatrixMultiplication();
	
		final int REPEAT_TIMES = 8;
		int size;
		long startTime, endTime;
		long totalTimeClassic = 0, totalTimeDivideConquer = 0, totalTimeStrassen = 0;
		int[][] matrixA, matrixB;
		
		for (int i = 1; i > 0; i++) 
		{
			size = (int) Math.pow(2, i);

			matrixA = MM.generateMatrix(size);
			matrixB = MM.generateMatrix(size);

			for (int j = 0; j < REPEAT_TIMES; j++) 
			{
				startTime = System.nanoTime();
				MM.classicMatrixMultiplication(matrixA, matrixB, matrixA.length);
				endTime = System.nanoTime();
				totalTimeClassic += endTime - startTime;

				startTime = System.nanoTime();
				MM.divideConquerMatrixMultiplication(matrixA, matrixB, matrixA.length);
				endTime = System.nanoTime();
				totalTimeDivideConquer += endTime - startTime;

				startTime = System.nanoTime();
				MM.strassenMatrixMultiplication(matrixA, matrixB, matrixA.length);
				endTime = System.nanoTime();
				totalTimeStrassen += endTime - startTime;
			}

			totalTimeClassic = totalTimeClassic / REPEAT_TIMES;
			totalTimeDivideConquer = totalTimeDivideConquer / REPEAT_TIMES;
			totalTimeStrassen = totalTimeStrassen / REPEAT_TIMES;

			System.out.println("For n = " + size + ": " + 
							"\n\tClassic Matrix Multiplication time: " + totalTimeClassic + " nanoseconds." + 
							"\n\tDivide and Conquer Matrix Multiplication time: " + totalTimeDivideConquer + " nanoseconds." +
							"\n\tStrassen's Matrix Multiplication time: " + totalTimeStrassen + " nanoseconds.\n");			
		}

		
	}

	
	
}
