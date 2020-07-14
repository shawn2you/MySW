package exetest;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

/*
 * (중상) [기출P-0054] 자동차여행
 */
public class Solution_TP0054 {

	static int T, M, N, B;
	static long Sum;
	
	static class City implements Comparable<City>{
		int s, e, c;
		City(int s, int e, int c){
			this.s = s;
			this.e = e;
			this.c = c;
		}
		@Override
		public int compareTo(City o) {
			if(o.c > this.c) {
				return -1;
			}else {
				return 1;
			}
		}
	}
	
	static ArrayList<City>[] road;
	
	public static void main(String[] args) throws Exception {
		
		FileInputStream fi = new FileInputStream(new File(Solution_TP0054.class.getResource("").getPath() + "Solution_TP0054.txt"));
		System.setIn(fi);
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));	
		
		T = Integer.parseInt(br.readLine());
		
		for (int t=1; t<=T; t++) {
			// 초기화 
			Sum = 0;

			// 도시의 수 N, 도로의 수 M, 동수씨가 가지고 있는 돈 B
			// (2 ≤ N ≤ 5,000, 1 ≤ M ≤ 10,000, 1,000 ≤ B ≤ 1,000,000)
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			B = Integer.parseInt(st.nextToken());
			
			
			
			// 2개의 서로 다른 도시를 일방통행으로 이동
			// 같은 도로를 여러 번 지나더라도 각각을 도로 통행 횟수에 포함
			// 항상 1번 도시에서 출발한다
			
			road = new ArrayList[N + 1];
			
			for(int i=0; i<=N; i++) {
				road[i] = new ArrayList<>();
			}
			
			for(int i=1; i<=M; i++) {
				st = new StringTokenizer(br.readLine());
				int s = Integer.parseInt(st.nextToken());
				int e = Integer.parseInt(st.nextToken());
				int c = Integer.parseInt(st.nextToken());
				
				road[s].add(new City(s, e, c));
			}
			
			// D[i][j] : 도로를 i번 사용해서 j도시에 도착했을때 최소비용
			int move = B/1000 + 1;
			long[][] D = new long[move+1][N + 1];
			// 초기값 셋팅
			for(int i=0; i<=move; i++) {
				for(int j=0; j<=N; j++) {
					D[i][j] = B;
				}
			}
			
			D[0][1] = 0; // 초기값(최초위치)
			
			for(int i=1; i<move; i++) { 
				// 최대 이동할 간선수 만큼 수행하다가 금액이 초과되면 종료 시켜야 한다. 
				// 이동할 모든 정점을 비교한다. 
				for(int k=1; k<=N; k++) {
					int len = road[k].size();					
					if(len == 0) continue;
					
					for(int s=0; s<len; s++) {
						int ts = road[k].get(s).s;
						int te = road[k].get(s).e;
						int tc = road[k].get(s).c;
						
//	if(t == 5) {
//		System.out.println();
//	}
						if(D[i-1][ts] + tc <= D[i][te]) {
							D[i][te] = D[i-1][ts] + tc;
							if(D[i][te] <= B) {
								Sum = i;
							}
						}
					}
				}
			}
			
			
			System.out.println("#"+t+" "+Sum);
		} // end test case		
	} // end main
}
