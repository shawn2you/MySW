package pretest4;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * (중상) [교육P-0006] 가장 먼 두 도시 
 */
public class Solution_SP0006 {

	static int T, M, N, Sum;
	static int inf = Integer.MAX_VALUE;
	
	static int[][] Distance; // 최단경로, 인접행렬
	
	public static void main(String[] args) throws Exception {
		
		FileInputStream fi = new FileInputStream(new File(Solution_SP0006.class.getResource("").getPath() + "Solution_SP0006.txt"));
		System.setIn(fi);
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));	
		
		T = Integer.parseInt(br.readLine());
		
		for (int t=1; t<=T; t++) {
			// 초기화 
			Sum = 0;

			// 도시의 수 N (1 ≤ N ≤ 300)
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			
			Distance = new int[N+1][N+1];
			
			for(int i=1; i<=N; i++) {
				st = new StringTokenizer(br.readLine());
				for(int j=1; j<=N; j++) {
					Distance[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			doFloyd();
			
			// 거리가 가장 긴 곳의 거리를 출력
			for(int i=1; i<=N; i++) {
				for(int j=1; j<=N; j++) {
					Sum = Math.max(Sum, Distance[i][j]);
				}
			}
			
			System.out.println("#"+t+" " + Sum);
			
		} // end test case		
	} // end main

	static void doFloyd() {
		for(int a=1; a<=N; a++) {
			for(int b=1; b<=N; b++) {
				for(int c=1; c<=N; c++) {
					if(b != c && Distance[b][a] != inf && Distance[a][c] != inf ) {
						Distance[b][c] = Math.min(Distance[b][c], Distance[b][a] + Distance[a][c]);
					}
				}
			}
		}
	}
}
