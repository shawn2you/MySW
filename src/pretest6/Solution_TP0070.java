package pretest6;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Solution_TP0070 {
	
	static int T, M, N, K;
	static long Sum;
	
//	static public class Badook{
//		int score;
//		long end;
//		Badook(int s, long e){
//			this.score = s;
//			this.end   = e;
//		}
//	}
//	
	static ArrayList<Integer>[] al;
	static long[] score;
	static int[] indegree;

	public static void main(String[] args) throws Exception {
		
		FileInputStream fi = new FileInputStream(new File(Solution_TP0070.class.getResource("").getPath() + "Solution_TP0070"));
		System.setIn(fi);
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));	
		
		T = Integer.parseInt(br.readLine());
		StringTokenizer st;
		
		for (int t=1; t<=T; t++) {
			Sum = 0;
			
			st = new StringTokenizer(br.readLine());
			// 조약돌 개수 N, 연결선의 개수 M, 출력을 위한 순서 K
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			
			// 최대 점수를 취하기 위해서는 두 점수에서 높은 점수를 먼저 취하면 된다. 
			// 즉, 그래프로 표현하면 높은 점수에서 낮은 점수로 간선 방향을 구성
			// 탐색을 위해 인접리스트 형태로 구성한다. 
			al = new ArrayList[N + 1];
			score = new long[N + 1];
			indegree = new int[N + 1];
			
			for(int i=0; i<=N; i++) {
				al[i] = new ArrayList<>();
			}
			
			for(int i=0; i<M; i++) {
				st = new StringTokenizer(br.readLine());
				int a  = Integer.parseInt(st.nextToken());
				int b  = Integer.parseInt(st.nextToken());
				long p = Long.parseLong(st.nextToken());
				long q = Long.parseLong(st.nextToken());
				
				score[a] += p;
				score[b] += q;
				
				if(p > q) {
					// a가 b보다 점수가 크므로 간선의 방향은 a -> b
					al[a].add(b);
					indegree[b]++;
				}else {
					if(p != q) {
						al[b].add(a);
						indegree[a]++;
					}
				}				
			}
			
			// 탐색 시작
			find();
			
			System.out.println("#"+t+" "+Sum);
		} // end test case

	}  // end main
	
	static void find() {
		PriorityQueue<Integer> pq = new PriorityQueue<Integer>();
		for(int i=1; i<=N; i++) {
			// 시작위치(사전순서이면 pq 구성시 작은노드값 부터 시작하도록 처리)
			if(indegree[i] == 0) pq.add(i);
		}
		int curr, next;
		while(!pq.isEmpty()) {
			curr = pq.poll();
			// 다음 이동 점이 있으면 바둑을 취한다. 
			if(al[curr].size() > 0) Sum += score[curr];
			
			for(int i=0; i<al[curr].size(); i++) {
				next = al[curr].get(i);
				indegree[next]--;
				
				if(indegree[next] == 0) pq.add(next);
			}
		}
		
	}

}
