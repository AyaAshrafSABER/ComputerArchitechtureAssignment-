import java.util.Random;

public class Main {
	 public static Random rand = new Random();

	public static void main(String[] args) {
		int low = 100;
		int high = 1000;
	     int size = rand.nextInt(high-low) + low;
		//int size = 100;
		 double[][] matA = matrixGeneration(size);
		 //System.out.println("matrix A :");
		 //matrixDisplay(size, matA);
		 double[][] matB = matrixGeneration(size);
		// System.out.println("matrix B :");
		 //matrixDisplay(size, matB);
		 double[][] matC = new double[size][size];
		 MatrixMultiplicationWithCache c = new MatrixMultiplicationWithCache();
		 c.setBLOCK_SIZE(32);
		 c.matrixMul(size,matA,matB,matC);
		 //System.out.println("matrix C :");
		// matrixDisplay(size, matC);
		// System.out.println( c.getTimeWithBlocking());
		 c.matrixMulWithoutBlocking(size,matA,matB,matC);
		 System.out.println( size+"\t "+c.getTimeWithBlocking() +"\t "+ c.getTimeWithoutBlocking());
		 
	}
	public static double[][] matrixGeneration(int size ) {
		double[][] A = new double[size][size];
		for ( int i = 0; i < size;  i++ ) 
			for ( int j = 0; j < size ; j++) {
				int low = 10;
				int high = 20;
				A[i][j] = rand.nextInt(high-low) + low;
			} 
		return A;
		
	}
	public static void matrixDisplay(int size ,double[][] A ) {
		
		for ( int i = 0; i < size;  i++ ) {
			for ( int j = 0; j < size ; j++) {
				System.out.print(A[i][j] +  " ");
			} 
		System.out.println(" ");
		}
	}
}
