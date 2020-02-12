import java.util.Random;

/*
 * The purpose is to demonstrate matrix multiplication in three different way:
 * Classical, Divide and Conquer, and Strassen's approach.
 */

public class MatrixMultiplication 
{
	/*
	 * Perform classical matrix multiplication using three loops.
	 * @param A - A matrix to multiply
	 * @param B - A matrix to multiply
	 * @param n - the size of the matrix. Assumes no ragged one, it's a square.
	 * @return a new matrix, C, that's result of A times B
	 */
	public int[][] classicMatrixMultiplication(int[][] A, int[][] B, int n) 
	{
        int[][] C = new int[n][n];
		
		for(int i = 0; i < n; i++) 
		{
			for (int j = 0; j < n; j++) 
			{
				for (int k = 0; k < n; k++) 
				{
					C[i][j] += A[i][k] * B[k][j];
				}
			}
		}
		
		return C;
	}

	/*
	 * Perform divide and conquer matrix multiplication.
	 * @param A - A matrix to multiply
	 * @param B - A matrix to multiply
	 * @param n - the size of the matrix. Assumes no ragged one, it's a square.
	 * @return a new matrix, C, that's result of A times B
	 */
	public int[][] divideConquerMatrixMultiplication(int[][] A, int[][]B, int n) 
	{        
        int[][] C = new int[n][n];
		
        if (n == 1) 
        {
			C[0][0] = A[0][0] * B[0][0];
			
			return C;
			
		} 
        else 
		{
			int[][] A11 = new int[n / 2][n / 2];
			int[][] A12 = new int[n / 2][n / 2];
			int[][] A21 = new int[n / 2][n / 2];
			int[][] A22 = new int[n / 2][n / 2];
			int[][] B11 = new int[n / 2][n / 2];
			int[][] B12 = new int[n / 2][n / 2];
			int[][] B21 = new int[n / 2][n / 2];
			int[][] B22 = new int[n / 2][n / 2];

			deconstructMatrix(A, A11, 0, 0);
			deconstructMatrix(A, A12, 0, n / 2);
			deconstructMatrix(A, A21, n / 2, 0);
			deconstructMatrix(A, A22, n / 2, n / 2);
			deconstructMatrix(B, B11, 0, 0);
			deconstructMatrix(B, B12, 0, n / 2);
			deconstructMatrix(B, B21, n / 2, 0);
			deconstructMatrix(B, B22, n / 2, n / 2);

			int[][] C11 = addMatrix(divideConquerMatrixMultiplication(A11, B11, n / 2), 
								   	divideConquerMatrixMultiplication(A12, B21, n / 2), n / 2);
			int[][] C12 = addMatrix(divideConquerMatrixMultiplication(A11, B12, n / 2), 
								   	divideConquerMatrixMultiplication(A12, B22, n / 2), n / 2);
			int[][] C21 = addMatrix(divideConquerMatrixMultiplication(A21, B11, n / 2), 
								   	divideConquerMatrixMultiplication(A22, B21, n / 2), n / 2);
			int[][] C22 = addMatrix(divideConquerMatrixMultiplication(A21, B12, n / 2), 
								   	divideConquerMatrixMultiplication(A22, B22, n / 2), n / 2);

			constructMatrix(C11, C, 0, 0);
			constructMatrix(C12, C, 0, n / 2);
			constructMatrix(C21, C, n / 2, 0);
			constructMatrix(C22, C, n / 2, n / 2);
		}
        
		return C;
	}
	
	/*
	 * Perform Strassen's matrix multiplication.
	 * @param A - A matrix to multiply
	 * @param B - A matrix to multiply
	 * @param n - the size of the matrix. Assumes no ragged one, it's a square.
	 * @return a new matrix, C, that's result of A times B
	 */
	public int[][] strassenMatrixMultiplication(int[][] A, int[][] B, int n) 
	{
		int[][] C = new int[n][n];
		strassenHelper(A, B, C, n);
		
		return C;
	}
	
	/*
	 * Actually perform the multiplication according to the Strassen's approach.
	 * @param A - A matrix to multiply
	 * @param B - A matrix to multiply
	 * @param C – The matrix that is A x B
	 * @param n - the size of the matrix. Assumes no ragged one, it's a square.
	 */
	private void strassenHelper(int[][] A, int[][] B, int[][] C, int n) 
	{
		if (n == 2) 
		{
			C[0][0] = (A[0][0] * B[0][0]) + (A[0][1] * B[1][0]);
			C[0][1] = (A[0][0] * B[0][1]) + (A[0][1] * B[1][1]);
			C[1][0] = (A[1][0] * B[0][0]) + (A[1][1] * B[1][0]);
			C[1][1] = (A[1][0] * B[0][1]) + (A[1][1] * B[1][1]);
		} 
		else 
		{
			int[][] A11 = new int[n / 2][n / 2];
			int[][] A12 = new int[n / 2][n / 2];
			int[][] A21 = new int[n / 2][n / 2];
			int[][] A22 = new int[n / 2][n / 2];
			int[][] B11 = new int[n / 2][n / 2];
			int[][] B12 = new int[n / 2][n / 2];
			int[][] B21 = new int[n / 2][n / 2];
			int[][] B22 = new int[n / 2][n / 2];

			int[][] P = new int[n / 2][n / 2];
			int[][] Q = new int[n / 2][n / 2];
			int[][] R = new int[n / 2][n / 2];
			int[][] S = new int[n / 2][n / 2];
			int[][] T = new int[n / 2][n / 2];
			int[][] U = new int[n / 2][n / 2];
			int[][] V = new int[n / 2][n / 2];

			deconstructMatrix(A, A11, 0, 0);
			deconstructMatrix(A, A12, 0, n / 2);
			deconstructMatrix(A, A21, n / 2, 0);
			deconstructMatrix(A, A22, n / 2, n / 2);
			deconstructMatrix(B, B11, 0, 0);
			deconstructMatrix(B, B12, 0, n / 2);
			deconstructMatrix(B, B21, n / 2, 0);
			deconstructMatrix(B, B22, n / 2, n / 2);

			strassenHelper(addMatrix(A11, A22, n / 2), addMatrix(B11, B22, n / 2), P, n / 2);
			strassenHelper(addMatrix(A21, A22, n / 2), B11, Q, n / 2); 
			strassenHelper(A11, subtractMatrix(B12, B22, n / 2), R, n / 2);
			strassenHelper(A22, subtractMatrix(B21, B11, n / 2), S, n / 2);
			strassenHelper(addMatrix(A11, A12, n / 2), B22, T, n / 2);
			strassenHelper(subtractMatrix(A21, A11, n / 2), addMatrix(B11, B12, n / 2), U, n / 2);
			strassenHelper(subtractMatrix(A12, A22, n / 2), addMatrix(B21, B22, n / 2), V, n / 2);

			int[][] C11 = addMatrix(subtractMatrix(addMatrix(P, S, P.length), T, T.length), V, V.length);
			int[][] C12 = addMatrix(R, T, R.length);
			int[][] C21 = addMatrix(Q, S, Q.length);
			int[][] C22 = addMatrix(subtractMatrix(addMatrix(P, R, P.length), Q, Q.length), U, U.length);

			constructMatrix(C11, C, 0, 0);
			constructMatrix(C12, C, 0, n / 2);
			constructMatrix(C21, C, n / 2, 0);
			constructMatrix(C22, C, n / 2, n / 2);
		}
		
		
	}
	
	/*
	 * Piece back a matrix provided the given matrix.
	 * @param givenMatrix - the matrix to add to the newMatrix
	 * @param newMatrix - the matrix that the givenMatrix adds itself to.
	 * @param givenMatrixInitialRowPosition - begin from the row position
	 * @param givenMatrixInitialColumnPosition - until the column position
	 */
	private void constructMatrix(int[][] givenMatrix, int[][] newMatrix, 
								 int givenMatrixInitialRowPosition, int givenMatrixInitialColumnPosition) 
	{

		int temp = givenMatrixInitialColumnPosition;
		for (int i = 0; i < givenMatrix.length; i++) 
		{
			for (int j = 0; j < givenMatrix.length; j++) 
			{
				newMatrix[givenMatrixInitialRowPosition][temp++] = givenMatrix[i][j];
			}
			temp = givenMatrixInitialColumnPosition;
			givenMatrixInitialRowPosition++;
		}
		
		
	}
	
	/*
	 * Break the givenMatrix and fill up the newMatrix.
	 * @param givenMatrix - the matrix to add to the newMatrix
	 * @param newMatrix - the matrix that the givenMatrix adds itself to.
	 * @param givenMatrixInitialRowPosition - begin from the row position
	 * @param givenMatrixInitialColumnPosition - until the column position
	 */
	private void deconstructMatrix(int[][] givenMatrix, int[][] newMatrix, 
						  		   int givenMatrixInitialRowPosition, int givenMatrixInitialColumnPosition) 
	{

		int temp = givenMatrixInitialColumnPosition;
		for (int i = 0; i < newMatrix.length; i++) 
		{
			for (int j = 0; j < newMatrix.length; j++) 
			{
				newMatrix[i][j] = givenMatrix[givenMatrixInitialRowPosition][temp++];
			}
			temp = givenMatrixInitialColumnPosition;
			givenMatrixInitialRowPosition++;
		}
		
		
	}

	/*
	 * Add the two matrix: A and B
	 * @param A - A matrix to add
	 * @param B - A matrix to add
	 * @param n - the size of the matrix. Assumes no ragged one, it's a square.
	 * @return – A resultant matrix C of the operation
	 */
	private int[][] addMatrix(int[][] A, int[][] B, int n) 
	{

		int[][] C = new int[n][n];

		for (int i = 0; i < n; i++) 
		{
			for (int j = 0; j < n; j++) 
			{
				C[i][j] = A[i][j] + B[i][j];
			}
		}
		
		return C;
	}
	
	/*
	 * Subtract the two matrix: A and B
	 * @param A - A matrix to subtract
	 * @param B - A matrix to subtract
	 * @param n - the size of the matrix. Assumes no ragged one, it's a square.
	 * @return – A resultant matrix C of the operation
	 */
	private int[][] subtractMatrix(int[][] A, int[][] B, int n) 
	{

		int[][] C = new int[n][n];

		for (int i = 0; i < n; i++) 
		{
			for (int j = 0; j < n; j++) 
			{
				C[i][j] = A[i][j] - B[i][j];
			}
		}
		
		return C;
	}
	
	/*
	 * Actually generates a new matrix with randomly generated ints from 0 to 100 of size n.
	 * @param n - the size of the matrix. Assumes no ragged one, it's a square.
	 * @return the matrix which is generated of n by n
	 */
	public int[][] generateMatrix(int size) 
	{
		Random integer = new Random();
		int[][] matrix = new int[size][size];

		for (int i = 0; i < size; i++) 
		{
			for (int j = 0; j < size; j++) 
			{
				matrix[i][j] = integer.nextInt(100);
			}
		}
		
		return matrix;
	}
	
	/*
	 * For testing purposes to display the any givenMatrix
	 * @param givenMatrix - input a matrix to display
	 * @param size - the size of the matrix
	 */
	public void displayMatrix(int[][] givenMatrix, int size) 
	{
		for (int i = 0; i < size; i++) 
		{
			for (int j = 0; j < size; j++) 
			{
				System.out.printf("%10d", givenMatrix[i][j]);
			}
			System.out.println();
		}
		
		
	}

	
	
}
