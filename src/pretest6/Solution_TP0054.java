package pretest6;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Solution_TP0054 {

	static int T, M, N, B, Sum;
	
	static public class Road{
		int s;
		int e;
		long c;
		
		Road(int s, int e, long c){
			this.s = s;
			this.e = e;
			this.c = c;			
		}
	}
	static ArrayList<Road>[] roadList;
	static long[][] dp;
	
	public static void main(String[] args) throws Exception {
		
		FileInputStream fi = new FileInputStream(new File(Solution_TP0054.class.getResource("").getPath() + "Solution_TP0054"));
		System.setIn(fi);
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));	
		
		T = Integer.parseInt(br.readLine());
		
		for (int t=1; t<=T; t++) {
			// 초기화 
			Sum = 0;

			// 도시의 수 N, 도로의 수 M, 동수씨가 가지고 있는 돈 B
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			B = Integer.parseInt(st.nextToken());
			
			roadList = new ArrayList[N + 1];
			
			for(int i=0; i<=N; i++) {
				roadList[i] = new ArrayList<>();
			}			
			
			for(int i=1; i<=N; i++) {
				st = new StringTokenizer(br.readLine());
				int s = Integer.parseInt(st.nextToken());
				int e = Integer.parseInt(st.nextToken());
				long c = Long.parseLong(st.nextToken());
				
				roadList[s].add(new Road(s, e, c));
			}
			// 수행회수 계산
			int move = B/1000 + 1;
			
			dp = new long[move][N + 1]; // dp[i][j] 도로를 i번 사용해서 j도시에 도착했을때 최소비용
			for(int i=0; i<move; i++) {
				for(int j=0; j<=N; j++) {
					dp[i][j] = B; // 최대값 셋팅(가지고 있는 돈)
				}
			}
						
			dp[0][1] = 0; // 초기값 (1번 도시에서 시작)
			
			// priority queue 사용시 정렬 수행이 되어 cost 더 높음
			
			for(int i=1; i<move; i++) {
				
				
				
				
			}
			
			System.out.println("#"+t+" "+Sum);
		} // end test case		
	} // end main

}
