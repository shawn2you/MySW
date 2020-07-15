package exetest;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Solution_TP0019 {

	static int T, M, N;
	static long Sum;
	static char[] colStr = new char[100001];;
	static long INF = Long.MAX_VALUE;
	
	static class Node implements Comparable<Node>{
		int c, e;
		long d;
		Node(int c, int e, long d){
			this.c = c;
			this.e = e;
			this.d = d;
		}
		@Override
		public int compareTo(Node o) {
			if(o.d > this.d) {
				return -1;
			}else {
				return 1;
			}
		}
	}
	static ArrayList<Node>[] ndList = new ArrayList[100001];;
	static long[][] D = new long[4][100001];;
	public static void main(String[] args) throws Exception {
		
		FileInputStream fi = new FileInputStream(new File(Solution_TP0019.class.getResource("").getPath() + "Solution_TP0019.txt"));
		System.setIn(fi);
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));	
		
		T = Integer.parseInt(br.readLine());
		
		for (int t=1; t<=T; t++) {
			// 초기화 
			Sum = INF;

			// 지점의 개수 N 과 일방통행 도로의 개수 M
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			
			
			// R은 적색지점, G는 녹색지점, B는 청색지점, W는 백색지점
			String col = br.readLine();
//			colStr = new char[N+1];
			for(int i=0; i<N; i++) {
				colStr[i+1] = col.substring(i, i+1).charAt(0);
			}
			
//			ndList = new ArrayList[N + 1];
			for(int i=0; i<=N; i++) {
				ndList[i] = new ArrayList<Node>();
			}
			for(int i=0; i<M; i++) {
				st = new StringTokenizer(br.readLine());
				int s = Integer.parseInt(st.nextToken());
				int e = Integer.parseInt(st.nextToken());
				long d = Long.parseLong(st.nextToken());
				ndList[s].add(new Node(0, e, d));
			}			
			
			// 다익스트라
			// D[i][j] : i통행증을 가지고 있을때 j 정점에서의 최단 거리
			// i : 통행증(3:적색/청색, 2:적색, 1:청색, 0:없음), j : 정점
//			D = new long[4][N+1];
			for(int j=1; j<=N; j++) {
				D[0][j] = INF;
				D[1][j] = INF;
				D[2][j] = INF;
				D[3][j] = INF;
			}
			
			search();
			
			for(int i=0; i<4; i++) {
				Sum = Math.min(Sum, D[i][N]);
			}
			
			if(Sum == INF) {
				System.out.println("#"+t+" -1");
			}else {
				System.out.println("#"+t+" "+Sum);
			}			
			
		} // end test case		
	} // end main

	static void search() {
		PriorityQueue<Node> pq = new PriorityQueue<>();
		
		// 출발점이 1이므로 초기 셋팅
		// 적색/청색 모두 가지고 있음
		pq.add(new Node(3, 1, 0));
		D[3][1] = 0;
		
		Node curr;
		int nNode, cPass; // 다음 방문 지점, 통행권
		int pass;
		while(!pq.isEmpty()) {
			curr = pq.poll();
			nNode = curr.e;
			cPass = curr.c;
			
			if(curr.d > D[cPass][nNode]) {
				continue;
			}
			
			// 통행량 처리
			if(colStr[nNode] == 'R') {
				// 통과여부 체크 (비트 & 연산자로 변경 해보기)
				if(cPass == 3) {
					pass = 1;
				}else if(cPass == 2) {
					pass = 0;
				}else {
					continue;
				}				
			}else if(colStr[nNode] == 'B') {
				// 통과여부 체크 (비트 & 연산자로 변경 해보기)
				if(cPass == 3) {
					pass = 2;
				}else if(cPass == 1) {
					pass = 0;
				}else {
					continue;
				}				
			}else if(colStr[nNode] == 'W') {
				pass = 3; // 카드 보충
			}else { // 'G'
				pass = cPass; // 유지
			}
			// 다음 방문점을 순차적으로 방문하여 처리 한다. 
			for(Node nd : ndList[curr.e]) {
				if(D[pass][nd.e] > D[curr.c][curr.e] + nd.d) {
					D[pass][nd.e] = D[curr.c][curr.e] + nd.d;
					pq.add(new Node(pass, nd.e, D[pass][nd.e]));
				}
			}			
		}  // end while
		
	}
}
