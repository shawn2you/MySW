package pretest6;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Solution_TP0040 {

	static int T, N, M;
	static long SUM;
	
	static long[] dp;
	static int[] indegree;
	static int[] salary;
	
	public class Job{
		int s;
		int e;
		int c;
		Job(int s, int e, int c){
			this.s = s;
			this.e = e;
			this.c = c;
		}
		
	}
	
	static ArrayList<Integer>[] al;
	
	public static void main(String[] args) throws Exception {
		FileInputStream fi = new FileInputStream(new File(Solution_TP0040.class.getResource("").getPath() + "Solution_TP0040"));
		System.setIn(fi); 
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		T = Integer.parseInt(br.readLine());
		
		StringTokenizer st;
		for(int t=1; t<=T; t++) {
			SUM = 0;
			// 사람의 수 N 과 제외할 수 있는 사람의 수 K 가 주어진다. (1 ≤ K < N ≤ 100,000) 
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken()); // 직장(노드)
			M = Integer.parseInt(st.nextToken()); // 상담소(간선)
			
			indegree = new int[N + 1];  // 차수
			salary   = new int[N + 1];  // 월급
			dp       = new long[N + 1];
			
			st = new StringTokenizer(br.readLine());
			for(int i=1; i<=N; i++) {
				salary[i] = Integer.parseInt(st.nextToken());	
			}
			
			// 간선의 월급 기준으로 위상 정렬 구성
			// dp[i] i 직장에서 시작되는 경력 개수(가능)
			// 월급이 가장 높은 직장은 이동이 불가 (초기값 구성이 됨)
			
			al = new ArrayList[N + 1];
			for(int i=0; i<=N; i++) {
				al[i] = new ArrayList<Integer>();
			}
			
			for(int i=1; i<=M; i++) {
				st = new StringTokenizer(br.readLine());
				int s = Integer.parseInt(st.nextToken());
				int e = Integer.parseInt(st.nextToken());
				// 방향 설정(월급이 낮은데서 높은곳으로만 이동 가능)
//				if(salary[e] > salary[s]) {
				if(salary[e] < salary[s]) { // 반대로 구성하여 그래프 생성
					al[s].add(e);
					indegree[e]++;
				}else {
					if(salary[s] != salary[e]) {
						al[e].add(s);
						indegree[s]++;
					}					
				}				
			}
			
			for(int i=1; i<=N; i++) {
				if(indegree[i] == 0) {
					// 시작점(salary가 제일 높은 위치)
					findPath(i);
				}
			}
			
			for(int i=1; i<=N; i++) {
				SUM += dp[i];
			}
			
			System.out.println("#"+t+" "+SUM);
			
		} // end test case
	}
	
	static void findPath(int start) {
		PriorityQueue<Integer> pq = new PriorityQueue<Integer>();
		pq.add(start);
		dp[start] = 0; // 최초 시작점(가장 높은 salary)
		
		int curr, next;
		while(!pq.isEmpty()) {
			curr = pq.poll();
			
			for(int i=0; i<al[curr].size(); i++) {
				next = al[curr].get(i);
				if(indegree[next] > 0) {
					dp[next] = dp[next] + dp[curr] + 1;
					pq.add(next);
					indegree[next]--;
				}
			}
			
		}
	}

}
