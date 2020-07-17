package exetest;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * (중상) [기출P-0018] 종이컵 전화기 
 */
public class Solution_TP0018 {

	static int T, M, N, Sum;
	static int[][] conn;
	static int[][] D;
	
	public static void main(String[] args) throws Exception {
		
		FileInputStream fi = new FileInputStream(new File(Solution_TP0018.class.getResource("").getPath() + "Solution_TP0018.txt"));
		System.setIn(fi);
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));	
		
		T = Integer.parseInt(br.readLine());
		
		for (int t=1; t<=T; t++) {
			// 초기화 
			Sum = 0;

			// 집의 개수 N 과 실의 개수 M
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			
			conn = new int[N+1][N+1];
			D    = new int[N+1][N+1];
			
			for(int i=0; i<M; i++) {
				st = new StringTokenizer(br.readLine());
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
				
				conn[a][b] = 1; // 연결된 정보
				conn[b][a] = 1; // 연결된 정보
			}
			int j;
			for(int len=1; len<=N; len++) {
				// case 1 : 1~len까지 와 len+1~N까지 연결쌍의 최대 정보
				for(int i=1; i<=N-len; i++) {
					j = len + i; // 끝점
					
					// 양끝점에 대한 케이스 부터 처리
					D[i][j] = conn[i][j] + D[i+1][j-1];
					
					for(int k=i; k<j; k++ ) {
						D[i][j] = Math.max(D[i][j], D[i][k] + D[k+1][j]);
					}					
				}
			}
			for(int i=1; i<=N; i++) {
				Sum = Math.max(Sum, D[1][i]);
			}
			System.out.println("#"+t+" "+Sum);
		} // end test case		
	} // end main

}
