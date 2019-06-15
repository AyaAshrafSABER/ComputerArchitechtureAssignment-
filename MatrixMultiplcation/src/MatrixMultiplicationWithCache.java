
public class MatrixMultiplicationWithCache {
    private  int BLOCK_SIZE = 32;
	private long timeWithBlocking;
	private long timeWithoutBlocking;
	void setBLOCK_SIZE(int value) {
		BLOCK_SIZE = value;
	}

	void do_block(int n, int si, int sj, int sk, double[][] A, double[][] B, double[][] C) {
		for (int i = si; i < si + BLOCK_SIZE && i < n; ++i)
			for (int j = sj; j < sj + BLOCK_SIZE && j < n; ++j) {
				double cij = C[i][j];/* cij = C[i][j] */
				for (int k = sk; k < sk + BLOCK_SIZE && k < n; k++)
					cij += A[i][k] * B[k][j];/* cij+=A[i][k]*B[k][j] */
				C[i][j] = cij;/* C[i][j] = cij */

			}
	}

	void matrixMul(int n, double[][] A, double[][] B, double[][] C) {
		long startTime = System.nanoTime();
		for (int sj = 0; sj < n; sj += BLOCK_SIZE)
			for (int si = 0; si < n; si += BLOCK_SIZE)
				for (int sk = 0; sk < n; sk += BLOCK_SIZE)
					do_block(n, si, sj, sk, A, B, C);
		long endTime   = System.nanoTime();
	    setTimeWithBlocking(endTime - startTime);
	}

	void matrixMulWithoutBlocking(int n, double[][] A, double[][] B, double[][] C) {
		long startTime = System.nanoTime();
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				for (int k = 0; k < n; k++) {
					C[i][j] += A[i][k] * B[k][j];
				}
			}
		}
		long endTime   = System.nanoTime();
		setTimeWithoutBlocking(endTime - startTime);
	}

	public long getTimeWithBlocking() {
		return timeWithBlocking;
	}

	public void setTimeWithBlocking(long timeWithBlocking) {
		this.timeWithBlocking = timeWithBlocking;
	}

	public long getTimeWithoutBlocking() {
		return timeWithoutBlocking;
	}

	public void setTimeWithoutBlocking(long timeWithoutBlocking) {
		this.timeWithoutBlocking = timeWithoutBlocking;
	}

}
