package exetest;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/*
 * (중) [사전A-0030] [2019년 9~10월 PRO 검정 사전 테스트] 기지국 점검 
 */
public class Solution_PA0030 {

    static int T, N, E, C, K, M;
    static List<Edge>[] edge   = new ArrayList[20001];
    static List<Edge>[] edge2 = new ArrayList[20001];
    static long[] d  = new long[20001];
    static long[] d2 = new long[20001];
    static int[] origin = new int[20001];
    static int[] origin2 = new int[20001];
    static int[] check = new int[20001];
    static long[] diff  = new long[20001];
    
    static class Edge implements Comparable<Edge>{
    	int y;
    	long z;
    	Edge(int y, long z ) {
    		this.y = y;
    		this.z = z;
    	}
		@Override
		public int compareTo(Edge o) {
			if(o.z > this.z) {
				return -1;
			}else {
				return 1;
			}
		}
    }
	
	public static void main(String[] args) throws Exception {
		
		FileInputStream fi = new FileInputStream(new File(Solution_PA0030.class.getResource("").getPath() + "Solution_PA0030.txt"));
		System.setIn(fi);
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));	
		
		T = Integer.parseInt(br.readLine());
		
		StringTokenizer st;
		int x,y,z, temp, centerCheck, mCheck, cnt;
		long ans;
		Edge tempEdge;
		
		
		for(int t=1; t<=T; t++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			E = Integer.parseInt(st.nextToken());
			C = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());

			for(int i=0; i<=N; i++) {
				edge[i] = new ArrayList<Edge>();
				edge2[i] = new ArrayList<Edge>();
			}
			for(int i=0; i<E; i++) {
				st = new StringTokenizer(br.readLine());
				x = Integer.parseInt(st.nextToken());
				y = Integer.parseInt(st.nextToken());
				z = Integer.parseInt(st.nextToken());
				edge[x].add(new Edge(y,z));
				edge[y].add(new Edge(x,z));
				edge2[x].add(new Edge(y,z));
				edge2[y].add(new Edge(x,z));
			}
			for(int i=0; i<=N; i++) {
				d[i] = Long.MAX_VALUE;
				d2[i] = Long.MAX_VALUE;
				origin[i]   = -1;
				origin2[i] = -1;
				check[i] = 0;
			}
			
			// 다익스트라 1
			for(int i =1; i<=C; i++) {
				edge[0].add(new Edge(i,0)); // 임의의 노드 정의
			}
			
			
			PriorityQueue<Edge> pq = new PriorityQueue<Edge>();

			pq.clear();
			pq.add(new Edge(0,0));
			d[0] = 0;
			while(!pq.isEmpty()) {
				tempEdge = pq.poll();
				
				if(tempEdge.z > d[tempEdge.y]) {
					continue;
				}
				for(Edge e : edge[tempEdge.y]) {
					if(d[tempEdge.y] + e.z < d[e.y]) {
						if(tempEdge.y !=0 && tempEdge.y <= C && origin[tempEdge.y] == -1) {
							origin[e.y] = tempEdge.y;
						} else {
							origin[e.y] = origin[tempEdge.y];
						}
						d[e.y] = d[tempEdge.y] + e.z;
						pq.add(new Edge(e.y, d[e.y]));
					}
				}
			}
			ans = 0;
			for(int i=C+1; i<=K; i++) {// 총비용 계산
				temp = origin[i];
				ans += d[i];
				check[temp]++;
			}
			
			centerCheck=0;
			mCheck = 0;
			for(int i =1; i<=C; i++) { // 엔지니어 수 초과 확인
				if(check[i] > M) {
					centerCheck  = i;
					mCheck = check[i];
					break;
				}
			}
			if(centerCheck == 0) {
				System.out.println("#"+t+" "+ ans);
			} else { // 2번째 다익스트라
				
				for(int i = 1; i<=C; i++) {
					if(centerCheck != i) {
						edge2[0].add(new Edge(i,0));
					}
				}
				//2번째 다익스트라
				pq.clear();
				pq.add(new Edge(0,0));
				d2[0] = 0;
//				ans = 0;
				while(!pq.isEmpty()) {
					tempEdge = pq.poll();
					
					if(tempEdge.z > d2[tempEdge.y]) {
						continue;
					}
					
					for(Edge e : edge2[tempEdge.y]) {
						if(d2[tempEdge.y] + e.z < d2[e.y]) {
							if(tempEdge.y !=0 && tempEdge.y <= C && origin2[tempEdge.y] == -1) {
								origin2[e.y] = tempEdge.y;
							} else {
								origin2[e.y] = origin2[tempEdge.y];
							}
							d2[e.y] = d2[tempEdge.y] + e.z;
							pq.add(new Edge(e.y, d2[e.y]));
						}
					}
				}
				// 초과된 센터의 최단경로 차이가 적은 결로들을 2번째 최단 경로로 선택
				cnt  = 0;
				for(int i=C+1; i<=K ; i++) {
					if(origin[i] == centerCheck) {
						diff[cnt++] = d2[i] - d[i];
					}
				}
				Arrays.sort(diff, 0, cnt);
				for(int i = 0; i< mCheck - M; i++) {
					ans +=diff[i];
				}
				System.out.println("#"+t+" "+ ans);
			}
		}
	} // end main
}