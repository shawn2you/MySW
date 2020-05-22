package pretest3;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution_P0053 {

static int T, N, W, B, Sum;
static int maxV = 2001;
static long[][] C = new long[maxV][maxV];
	
	public static void main(String[] args) throws Exception {
		
		FileInputStream fi = new FileInputStream(new File(Solution_P0053.class.getResource("").getPath() + "Solution_P0053.txt"));
		System.setIn(fi);
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));	
		
		T = Integer.parseInt(br.readLine());
		
		C[0][0] = 1;
		
		for (int t=1; t<=T; t++) {
			// 초기화 
			Sum = 0;

			// W, B 기사의 수 (0 ≤ W, B ≤1,000, 단, W+B>0)
			StringTokenizer st = new StringTokenizer(br.readLine());
			W = Integer.parseInt(st.nextToken());
			B = Integer.parseInt(st.nextToken());
			N = W+B;
			
			// W+B 는 전체 수 N
			double D[][] = new double[W + 2][B + 2];
			
			
			
			
			
			
		} // end test case		
	} // end main

}
