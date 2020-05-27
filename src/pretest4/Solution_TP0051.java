package pretest4;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.StringTokenizer;

/*
 * (중상) [기출P-0051] 조상이 키컸으면
 */
public class Solution_TP0051 {
	
	static int T, N, Q, Dep;
	// LCA 기본 셋팅
	static int[][] Parent;  // 2K번째 조상을 담을 공간
	static int[] Depth;     // 깊이를 담을 공간
	static ArrayList<Integer>[] Anc;
	static int[] Height;    // 구성원의 키
	static int[] MaxHeight; // 구성원의 부모중에 가장 큰키
	
	public static void main(String[] args) throws Exception {
		FileInputStream fi = new FileInputStream(new File(Solution_TP0051.class.getResource("").getPath() + "Solution_TP0051.txt"));
		System.setIn(fi);
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		T = Integer.parseInt(br.readLine());
		StringTokenizer st;
		for(int t=1; t<=T; t++){
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken()); // 구성원의 수
			Q = Integer.parseInt(st.nextToken()); // 구성원의 모임수
			
			int S = 2;
			Dep = 2;
			while(S<N){
				S *= 2;
				Dep++; // 총 깊이
			}
			
			Anc = new ArrayList[N + 1];
			Parent = new int[Dep + 1][N + 1]; // Parent[k][[v] v정점의 k번째 조상
			Depth  = new int[N + 1];
			Height  = new int[N + 1];
			MaxHeight  = new int[N + 1];
			
			for(int i=0; i<=N; i++){
				Anc[i] = new ArrayList<Integer>();
			}
			int p, h;
			
			for(int i=1; i<=N; i++){			
				st = new StringTokenizer(br.readLine());
				p = Integer.parseInt(st.nextToken());
				h = Integer.parseInt(st.nextToken());
				
				Anc[p].add(i);
				Height[i] = h;
				Parent[0][i] = p;
			}
			
			// bfs or dfs 탐색하여 각 정점의 깊이를 계산한다. 
			dfs(1);
			
			// Parent table 생성 Parent[k][v] = Parent[k-1][Parent[k-1][v]]
			for(int k=1; k<=Dep; k++){
				for(int v=1; v<=N; v++){				
					Parent[k][v] = Parent[k-1][Parent[k-1][v]];
				}
			}
			
			// LCA
			int K, P;
			StringBuilder sb = new StringBuilder();
			for(int q=0; q<Q; q++){
				st = new StringTokenizer(br.readLine());
				K = Integer.parseInt(st.nextToken());
				
				int a = 0;
				for(int ps=0; ps<K; ps++){
					P = Integer.parseInt(st.nextToken());
					if(a == 0){
						a = P;
//						if(K == 1) {
//							System.out.print("LCA =" + a + "," + P);
//							a = Parent[0][a];
//							System.out.println(" = " + a);
//						}
					}else{
//						System.out.print("LCA =" + a + "," + P);
						a = getLCA(a, P);
//						System.out.println(" = " + a);
					}
				}
				sb.append(" ");
				sb.append(MaxHeight[a]);
			}		
			
			System.out.println("#"+t+sb.toString());
		} // end test case
	} //end main

	
	static int getLCA(int a, int b){
		// 기준을 한쪽으로 맞춘다.
		if(Depth[a] < Depth[b]){
			return getLCA(b, a);
		}
		
		// 높이를 맞춘다. (a가 더 깊이있으므로 a를 b높이에 맞춘다.)
		for(int i=0; i<=Dep; i++) {
			// bit 1(1), 10(2), 100(4), 1000(8)
			if( ((1 << i) & (Depth[a] - Depth[b])) != 0 ) {
				a = Parent[i][a];
			}
		}
		// 조상이 동일하면 같은 위치이므로 a를 리턴
		if(a == b){
			return a;
		}
		// 조상이 달라지는 첫 지점의 a의 조상 찾기(같아지기 직전의 값과 동일)
		for(int i = Dep; i >=0; i--) {
			if(Parent[i][a] != Parent[i][b]) {
				a = Parent[i][a];
				b = Parent[i][b];				
			}
		}
		
		return Parent[0][a];
	}
	
	static void dfs(int start){
		LinkedList<Integer> sta = new LinkedList<>();
		
		sta.push(start);
		Depth[start] = 1;
		MaxHeight[start] = Height[start];
		int curr;
		while(!sta.isEmpty()){
			curr = sta.poll();
			for(int e : Anc[curr]){
				Depth[e] = Depth[curr] + 1;
				MaxHeight[e] = Math.max(MaxHeight[curr], Height[e]);
				sta.push(e);
			}
		}		
	}
}
