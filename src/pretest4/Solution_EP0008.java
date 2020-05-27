package pretest4;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/*
 * (중상) [연습P-0008] 웜홀
 * 플로이드-워셜 알고리즘
 */
public class Solution_EP0008 {

	static int T, M, N, W, Sum;
	static int INF = Integer.MAX_VALUE;
	static class Farm{
		int s, e, t;
		Farm(int start, int end, int time){
			this.s = start;
			this.e = end;
			this.t = time;
		}
	}
	static int[] minDistanc; // 최소거리
	static Farm[] roads; // 간선리스트
	
	public static void main(String[] args) throws Exception {
		
		FileInputStream fi = new FileInputStream(new File(Solution_EP0008.class.getResource("").getPath() + "Solution_EP0008.txt"));
		System.setIn(fi);
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));	
		
		T = Integer.parseInt(br.readLine());
		
		for (int t=1; t<=T; t++) {
			// 초기화 
			Sum = 0;

			// 농장의수 N(1≤N≤500), 도로의 수 M(1≤M≤2500), 웜홀의 수 W(1≤W≤200)
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			W = Integer.parseInt(st.nextToken());
			
			minDistanc = new int[N+1]; // 점정내 최소 거리
			roads = new Farm[2*M + W]; // 간선리스트
			
			int s, e, tm;
			int idx = 0;
			// 도로의 정보가 세개의 정수 s, e, tm
			for(int i=1; i<=M; i++) {
				st = new StringTokenizer(br.readLine());
				s = Integer.parseInt(st.nextToken());
				e = Integer.parseInt(st.nextToken());
				tm = Integer.parseInt(st.nextToken());
				
				roads[idx++] = new Farm(s, e, tm);
				roads[idx++] = new Farm(e, s, tm);
			}
			
			// 웜홀의 정보가 s, e, tm
			for(int i=1; i<=W; i++) {
				st = new StringTokenizer(br.readLine());
				s = Integer.parseInt(st.nextToken());
				e = Integer.parseInt(st.nextToken());
				tm = Integer.parseInt(st.nextToken());
				
				roads[idx++] = new Farm(s, e, tm*-1);
			}
			
			StringBuilder sb = new StringBuilder();
			sb.append("#").append(t).append(" ");
			if(bellmanFord()) {
				sb.append("YES");
			}else {
				sb.append("NO");
			}
			System.out.println(sb.toString());
			
		} // end test case		
	} // end main

	static boolean bellmanFord() {
		Arrays.fill(minDistanc, INF);
		minDistanc[1] = 0;
		
		// 간선수 N-1 번만큼 수행(최단거리 찾는 알고리즘)
		for(int i=1; i<N; i++) { 
			for(int j=0; j<roads.length; j++) {
				Farm farm = roads[j];
				// 다음 도착지까지 거리를 찾아 기존 거리가 갱신되면 반영
				// 출발지가 초기값이면 갱신 불가
				if(minDistanc[farm.s] != INF) {
					minDistanc[farm.e] = Math.min(minDistanc[farm.e], minDistanc[farm.s] + farm.t);
				}
			}
		}
		// 1번더 탐색하여 음수 사이클 여부 판단(갱신되면)
		for(int j=0; j<roads.length; j++) {
			Farm farm = roads[j];
			// 다음 도착지까지 거리를 찾아 기존 거리가 갱신되면 반영
			// 출발지가 초기값이면 갱신 불가
			if(minDistanc[farm.s] != INF && minDistanc[farm.e] > minDistanc[farm.s] + farm.t) {
				return true;
			}
		}
		
		return false;
	}
}
