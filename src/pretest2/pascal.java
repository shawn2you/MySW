package pretest2;

public class pascal {

	public static void main(String[] args) {
		
		int[][] pascal = new int[64][64];
		
		pascal[0][0] = 1;
		pascal[1][0] = 1;
		pascal[1][1] = 1;
		// nCr = (n-1)C(r-1) + (n-1)Cr
		for(int i=2; i<64; i++){
			pascal[i][0] = 1;
			
			for(int j=1; j<=i; j++){
				pascal[i][j] = pascal[i-1][j-1] + pascal[i-1][j];
			}
		}
		
		// 출력 확인
		for(int i=0; i<64; i++){			
			for(int j=0; j<=i; j++){
				System.out.print(pascal[i][j] + " ");
			}
			System.out.println();
		}

	}

}
