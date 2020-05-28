package pretest4;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution_SA0011 {

	static int T, W, N, Sum;
	static int[] C; // 동전
	static int[][] D; // D[i][j]  : C[i]까지의 동전을 사용하고, J원을 채우기 위한 최소 동전갯수
	
	
	public static void main(String[] args) throws Exception {
		
		FileInputStream fi = new FileInputStream(new File(Solution_SA0011.class.getResource("").getPath() + "Solution_SA0011.txt"));
		System.setIn(fi);
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));	
		
		T = Integer.parseInt(br.readLine());
		
		for (int t=1; t<=T; t++) {
			// 초기화 
			Sum = 0;

			// 동전 단위의 수 N (1 ≤ N ≤ 10)
			N = Integer.parseInt(br.readLine());
			
			// 동전 C (1 ≤ C ≤ 64,000)
			C = new int[N + 1];
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int i=1; i<=N; i++) {
				C[i] = Integer.parseInt(st.nextToken());
			}
			// 잔돈 W (1 ≤ W ≤ 64,000)
			W = Integer.parseInt(br.readLine());
			
			
			// 1(1), 4(2), 6(3)원
			// D[1][1] = 1;
			
			System.out.println("#"+t+" "+Sum);
		} // end test case		
	} // end main

}
