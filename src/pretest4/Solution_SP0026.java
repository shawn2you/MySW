package pretest4;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.StringTokenizer;

/*
 * (중상) [교육P-0026] 최저 공통 조상 
 * LCA
 */
public class Solution_SP0026 {

	static int T, Q, N, S, dep, Sum;
	static ArrayList<Integer>[] tree = new ArrayList[100001];
	static int[][] parent = new int[18][100001]; 
	static int[] depth = new int[100001];
	
	
	public static void main(String[] args) throws Exception {
		
		FileInputStream fi = new FileInputStream(new File(Solution_SP0026.class.getResource("").getPath() + "Solution_SP0026.txt"));
		System.setIn(fi);
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));	
		
		T = Integer.parseInt(br.readLine());
		
		for (int t=1; t<=T; t++) {
			// 초기화 
			Sum = 0;

			// 정점의 개수 N과 질의의 수 Q (1 ≤ N, Q ≤ 100,000)
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			Q = Integer.parseInt(st.nextToken());
			S =2;
			dep = 1;
			while(S < N) {
				S *= 2;
				dep++;
			}
//			System.out.println(depth);
			
			for(int i=0; i<=N; i++) {
				tree[i] = new ArrayList<Integer>();
			}
			
			// root
			tree[0].add(1);
			parent[0][1] = 0; 
			
			st = new StringTokenizer(br.readLine());
			// 1 1 2 2 5 3
			int e;
			for(int i=2; i<=N; i++) {
				e = Integer.parseInt(st.nextToken());
				parent[0][i] = e; // 첫번째 부모 셋팅
				tree[e].add(i);
			}
			
			// 1. Parent table 구성 parent[k][v] = parent[k-1][ parent[k-1][v] ]			
			for(int k=1; k<=dep; k++) {
				for(int v=1; v<=N; v++) {
					parent[k][v] = parent[k-1][parent[k-1][v]];
				}
			}
			
			// 2. 각 정점별 Depth 계산하기
			dfs(1);
			
			StringBuilder sb = new StringBuilder();
			sb.append("#").append(t);
			// 3. LCA
			for(int i=0; i<Q; i++) {
				st = new StringTokenizer(br.readLine());
				sb.append(" ");
				sb.append(getLCA(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
			}
			
			System.out.println(sb.toString());
		} // end test case		
	} // end main

	static int getLCA(int a, int b) {
		// 두개의 Depth를 맞춘다.
		if(depth[a] < depth[b]) {
			return getLCA(b, a);
		}
		
		// case 1 : 깊이의 차를 작은값에서 큰값으로 가는 구조
		for(int i=0; i<=dep; i++) {
			// bit 1(1), 10(2), 100(4), 1000(8)
			if( ((1 << i) & (depth[a] - depth[b])) != 0 ) {
				a = parent[i][a];
			}
		}
		
		// case 2 : 깊이의 차를 큰값에서 작은값으로 가는 구조
		/*
		int[] pows = new int[dep + 1];
		for(int i=0; i>=dep; i++) {
			pows[i] = (int)Math.pow(2, i);
		}
		int diff = depth[a] - depth[b];
		if(diff > 0) {
			for(int k=dep; k>=0; k--) {
				if(diff > pows[k]) {
					a = parent[k][a];
					diff = diff - pows[k];
					if(diff == 0) break;
				}
			}
		}		
		*/
		
		if(a == b) return a;
		
		for(int i = dep; i >=0; i--) {
			if(parent[i][a] != parent[i][b]) {
				a = parent[i][a];
				b = parent[i][b];				
			}
		}
		
		return parent[0][a];
	}
	
	
	static void dfs(int start) {
		LinkedList<Integer> sta = new LinkedList<>();
		depth[start] = 1;
		sta.push(start);
		int curr, next;
		while(!sta.isEmpty()) {
			curr = sta.poll();
			System.out.print(curr + ",");
			for(int i=0; i<tree[curr].size();i++) {
				next = tree[curr].get(i);
				depth[next] = depth[curr] + 1;
				sta.push(next);
			}
		}
	}
	// 재귀로 구현해보기
	static void dfsR(int start) {
//		LinkedList<Integer> sta = new LinkedList<>();
//		depth[start] = 1;
//		sta.push(start);
//		int curr, next;
//		while(!sta.isEmpty()) {
//			curr = sta.poll();
//			System.out.print(curr + ",");
//			for(int i=0; i<tree[curr].size();i++) {
//				next = tree[curr].get(i);
//				depth[next] = depth[curr] + 1;
//				sta.push(next);
//			}
//		}
	}
	

	public static void bfs(int start){
		ArrayDeque<Integer> q = new ArrayDeque<Integer>();
		depth[start] = 1;
		q.addLast(start);
		int curr, next;
		while(!q.isEmpty()){
			curr = q.pollFirst();
			System.out.print(curr+", ");
			for(int i=0; i<tree[curr].size(); i++){
				next = tree[curr].get(i);
				depth[next] = depth[curr] + 1;
				q.addLast(next);				
			}
		}
//		System.out.println();
	}
}
