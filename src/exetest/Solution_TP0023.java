package exetest;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * (중상) [기출P-0023] 운동하자 
 */
public class Solution_TP0023 {

	static int T, M, N, Sum;
	static int[][] Distance; // 최단경로, 인접행렬
	
	public static void main(String[] args) throws Exception {
		
		FileInputStream fi = new FileInputStream(new File(Solution_TP0023.class.getResource("").getPath() + "Solution_TP0023.txt"));
		System.setIn(fi);
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));	
		
		T = Integer.parseInt(br.readLine());
		
		for (int t=1; t<=T; t++) {
			// 초기화 
			Sum = 0;

			// 교차로(N)와 도로(M)의 개수 (2 ≤ N ≤ 500, 1 ≤ M ≤ 100,000)
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			
			Distance = new int[N+1][N+1];
			
			for(int i=1; i<=M; i++) {
				st = new StringTokenizer(br.readLine());
				for(int j=1; j<=N; j++) {
					Distance[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			
			
			// 모든 정점을 기준으로 최단 경로를 찾아서 표시 한다. 
			
			// D[i][j] : i 번 이동했을때 j 위치에 도착하는 경우의 수
			// j 위치와 인접한 교차로들을 i-1 번 이동했을때 도착하는 경우 수의 합
			
			
			// D[1][2] ~ D[N-1][2] 값들의 합을 구할것
			
			
			
			
			System.out.println("#"+t+" "+Sum);
		} // end test case		
	} // end main

}
