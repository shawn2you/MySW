package baekjoon;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

/*
 * LCA : https://www.acmicpc.net/problem/11437
 */
public class B11437 {
	static int N, M, Max;
	static int[] depth    = new int[50001];
	static int[] visited  = new int[50001];
	static int[][] parents;
	static ArrayList<Integer>[] ad = new ArrayList[50001];
	
	public static void main(String[] args) throws Exception {
		FileInputStream fi = new FileInputStream(new File(B11437.class.getResource("").getPath() + "B11437.txt"));
		System.setIn(fi);
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());  // 노드 개수
		Max = 0;
		for(int i=1; i<N; i*=2){
			Max++;
		}
		// 부모의 조상을 담는다.
		parents = new int[Max+1][N+1];
	
		for(int i=0; i<=N; i++){
			visited[i] = 0;
			ad[i] = new ArrayList<>();
			ad[i].clear();
			depth[i] = -1;
		}		
		
		StringTokenizer st;
		int a, b;
		for(int i=1; i<N; i++){
			st = new StringTokenizer(br.readLine());
			a  = Integer.parseInt(st.nextToken());
			b  = Integer.parseInt(st.nextToken());
			
			// 양방향 노드로 셋팅
			ad[a].add(b);
			ad[b].add(a);
		}
		
		// node의 각 depth를 계산하여 셋팅(dfs, bfs 등)
		makeGraph(1);
		
		// parents 체우기
		for(int i=1; i<=Max; i++){
			for(int j=1; j<=N; j++){
				// j node의 i번째 부모는 j node의 
				parents[i][j] = parents[i-1][parents[i-1][j]];
			}
		}
		
		M = Integer.parseInt(br.readLine());  // 질의 개수
		
		for(int i=1; i<=M; i++){
			st = new StringTokenizer(br.readLine());
			a  = Integer.parseInt(st.nextToken());
			b  = Integer.parseInt(st.nextToken());
			
			System.out.println(LCA(a, b));			
		}
		
		
	} // end main
	
	static int LCA(int a, int b){
		
		if(depth[a] > depth[b]){
			// a가 더 깊으면 Root에서 내려오면서 b와 만나는 depth 리턴
			for(int i=Max; i>=0; i--){
				if(depth[parents[i][a]] >= depth[b]) a = parents[i][a];
			}			
		}else if(depth[a] < depth[b]){
			for(int i=Max; i>=0; i--){
				if(depth[parents[i][b]] >= depth[a]) b = parents[i][b];
			}
		}
		
		if(a == b) return a;
		// Root에서부터 두노드의 조상이 달라지면 종료(LCA에서 종료)
		for(int i=Max; i>=0 && a!=b; i--){
			if(parents[i][a] != parents[i][b]){
				a = parents[i][a];
				b = parents[i][b];
			}
		}		
		
		return parents[0][a];
	}
		
	static void makeGraph(int node){
		visited[node] = 1;
		
		int next;
		for(int i=0; i<ad[node].size(); i++){
			next = ad[node].get(i);
			if(visited[next] == 0){
				depth[next] = depth[node] + 1;
				// 다음노드의 부모 셋팅
				parents[0][next] = node;
 				makeGraph(next);
			}
		}
	}

}
