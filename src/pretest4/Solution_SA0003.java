package pretest4;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution_SA0003 {

	static int T, M, N, Sum;
	static int[][] P; // 도시의 폐지개수(입력값)
	static int[][] D; // D[i][j] : i, j까지 이동할때까지의 최대 폐지 개수
	
	public static void main(String[] args) throws Exception {
		
		FileInputStream fi = new FileInputStream(new File(Solution_SA0003.class.getResource("").getPath() + "Solution_SA0003.txt"));
		System.setIn(fi);
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));	
		
		T = Integer.parseInt(br.readLine());
		
		for (int t=1; t<=T; t++) {
			// 초기화 
			Sum = 0;

			//  도시의 크기 N(2 ≤ N ≤ 200)
			N = Integer.parseInt(br.readLine());	
			
			StringTokenizer st;
			P = new int[N + 1][N +1];
			for(int i=1; i<=N; i++) {
				st = new StringTokenizer(br.readLine());
				for(int j=1; j<=N; j++) {
					P[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			// D[i][j] = max(위에서 이동한 경우, 왼쪽에서 이동한 경우)
			// D[i][j] = max( D[i-1][j], D[i][j-1] )
			D = new int[N + 1][N +1];
			// 최대값을 구하기때문에 초기값 셋팅 불필요(객체 생성시 0으로 셋팅)
			for(int i=1; i<=N; i++) {				
				for(int j=1; j<=N; j++) {
					D[i][j] = Math.max(D[i-1][j], D[i][j-1]) + P[i][j];
				}
			}			
			
			System.out.println("#"+t+" "+D[N][N]);
		} // end test case		
	} // end main

}
