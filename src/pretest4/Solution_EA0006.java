package pretest4;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/*
 * (중) [연습A-0006] 집으로 
 * 다익스트라(우선순위)
 */
public class Solution_EA0006 {

	static int T, R, N, Sum;
	
	static class Farm implements Comparable<Farm>{
		int e, d;
		Farm(int end, int dist){
			this.e = end;
			this.d = dist;
		}
		@Override
		public int compareTo(Farm o) {
			if(o.d > this.d) {
				return -1;
			}else {
				return 1;
			}
		}		
	}
	
	static ArrayList<Farm>[] farmList; // 인접 리스트
	static int[] Distance; // 최단경로
	
	public static void main(String[] args) throws Exception {
		
		FileInputStream fi = new FileInputStream(new File(Solution_EA0006.class.getResource("").getPath() + "Solution_EA0006.txt"));
		System.setIn(fi);
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));	
		
		T = Integer.parseInt(br.readLine());
		
		for (int t=1; t<=T; t++) {
			// 초기화 
			Sum = 0;

			// 양방향 길의 수 R와 농장의 개수 N (1 ≤ R ≤ 10,000, 1 ≤ N ≤ 1,000)
			StringTokenizer st = new StringTokenizer(br.readLine());
			R = Integer.parseInt(st.nextToken());
			N = Integer.parseInt(st.nextToken());
			
			Distance = new int[N + 1];
			Arrays.fill(Distance, Integer.MAX_VALUE);
			
			farmList = new ArrayList[N + 1];
			for(int i=0; i<=N; i++) {
				farmList[i] = new ArrayList<Farm>();
			}
			
			for(int i=1; i<=R; i++) {
				st = new StringTokenizer(br.readLine());
				int s = Integer.parseInt(st.nextToken());
				int e = Integer.parseInt(st.nextToken());
				int d = Integer.parseInt(st.nextToken());
				
				farmList[s].add(new Farm(e, d));
				farmList[e].add(new Farm(s, d));
			}
			
			search(1);
			
			// N번 농장에서 1번 농장인 집으로 이동하는 최단거리
			System.out.println("#"+t+" " + Distance[N]);
			
		} // end test case		
	} // end main

	// 우선순위(거리가 짧은) Queue로 탐색(DFS)
	public static void search(int start) {
		PriorityQueue<Farm> pq = new PriorityQueue<>();
		
		pq.add(new Farm(start, 0));
		Distance[start] = 0;
		
		Farm curr;
		while(!pq.isEmpty()) {
			curr = pq.poll();
			int next = curr.e;
//			System.out.print(next +", " +curr.d +" -> ");
			// 이동과정에 최소 거리를 저장한다.  
			if(curr.d > Distance[next]) {				
				continue;
			}
			
			for(int i=0; i<farmList[next].size(); i++) {
				int e = farmList[next].get(i).e;
				int d = farmList[next].get(i).d;
//				System.out.print(e +", " +d +", Min=");
				if(Distance[e] > curr.d + d)
				{					
					Distance[e] = curr.d + d;
//					System.out.println(Distance[e]);
					pq.add(new Farm(e, Distance[e]));
				}
//				else {
//					System.out.println(Distance[e]);
//				}
			}			
		}		
	}
}
