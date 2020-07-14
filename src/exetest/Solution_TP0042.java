package exetest;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/*
 * (중상) [기출P-0042] 배제쌍 
 */
public class Solution_TP0042 {

	static int T, M, N, K;
	static long Sum;
	static long INF = Long.MAX_VALUE - 1;
	
	static class Node implements Comparable<Node>{
		int x, y, c, l;
		Node(int x, int y, int c, int l){
			this.x = x;
			this.y = y;
			this.c = c;
			this.l = l;
		}
		@Override
		public int compareTo(Node o) {
			if(o.c > this.c) {
				return -1;
			}else {
				// 기존께 크면 유지
				return 1;
			}
		}
		
	}
	
	static Node[] nodeList;
	static int[] parent = new int[51];
	static int[][] exNode = new int[17][2];
	static int[] arr = new int[17];
	static int[] chk = new int[201];
	
	public static void main(String[] args) throws Exception {
		
		FileInputStream fi = new FileInputStream(new File(Solution_TP0042.class.getResource("").getPath() + "Solution_TP0042.txt"));
		System.setIn(fi);
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));	
		
		T = Integer.parseInt(br.readLine());
		
		for (int t=1; t<=T; t++) {
			// 초기화 
			Sum = -1;

			// 정점의 N, 간선의 수 M, 배제쌍의 수 K (3 ≤ N ≤ 50, N-1 ≤ M ≤200, 1 ≤ K ≤ 16) 
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			
			for(int i=1; i<=N; i++) {
				parent[i] = i; // 자기자신을 셋팅
			}
			
			// 간선 List
			nodeList = new Node[M+1];
			
			// 우선 MST를 먼저 구해보자.
			for(int i=1; i<=M; i++) {
				st = new StringTokenizer(br.readLine());
				int x, y, c;
				x = Integer.parseInt(st.nextToken());
				y = Integer.parseInt(st.nextToken());
				c = Integer.parseInt(st.nextToken());
				
				nodeList[i] = new Node(x, y, c, i);				
			}
			// 정렬 수행(오름차순으로)
			Arrays.sort(nodeList, 1, M+1);
			
			// 배제쌍을 적용하여 구성 (16개 이하)
			// 모든 경우의 수를 고려하여 구성
			
			for(int i=0; i<K; i++) {
				st = new StringTokenizer(br.readLine());
				int x, y;
				x = Integer.parseInt(st.nextToken());
				y = Integer.parseInt(st.nextToken());
				
				exNode[i][0] = x;
				exNode[i][1] = y;
			}
			
			dfs(0);
			
			
			System.out.println("#"+t+" "+Sum);
		} // end test case		
	} // end main

	
	static void dfs(int n) {
		if(n == K) {
			Arrays.fill(chk, 0); // 정상간선
			for(int i=0; i<K; i++) {
				chk[arr[i]] = 1; // 배제쌍
//				System.out.print(arr[i] + ",");
			}
//			System.out.println();
			
			long tempSum = MST();
//			System.out.println("SUM = " + tempSum);
			if(tempSum != -1) {
				if(Sum == -1) {
					Sum = tempSum;
				}else {
					Sum = Math.min(Sum, tempSum);
				}				
			}
			
			return;
		}
		arr[n] = exNode[n][0];
		dfs(n + 1);
		arr[n] = exNode[n][1];
		dfs(n + 1);
	}

	static long MST() {
		long sum = 0;
		int cnt = 0;
		
		for(int i=1; i<=N; i++) {
			parent[i] = i; // 자기자신을 셋팅
		}
		
		for(int i=1; i<=M; i++) {
			if(find(nodeList[i].x) != find(nodeList[i].y) && chk[nodeList[i].l] == 0) {
				union(nodeList[i].x, nodeList[i].y);				
				sum += (long)nodeList[i].c;
				cnt++;
//				System.out.println(nodeList[i].x + " " + nodeList[i].y + " " + (long)nodeList[i].c);
			}
		}
		if(cnt != N-1) { // 점점의 수보다 1 작음(MST)
			return -1; // MST가 없음
		}else {
			return sum;
		}
	}
	static int find(int a) {
		if(a == parent[a]) return a;
		else return parent[a] = find(parent[a]);
	}
	
	static void union(int a, int b) {
		int ra = find(a);
		int rb = find(b);
		if(ra == rb) return;
		else parent[rb] = ra;
	}
}
