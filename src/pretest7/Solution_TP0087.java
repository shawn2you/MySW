package pretest7;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

/*
 * (중상) [기출P-0087] 그룹의 수 
 * 알고리즘 : DFS(단절점)
 * https://blog.naver.com/lastingchild/140187693114
 * https://justicehui.github.io/hard-algorithm/2019/01/06/ArticulationPoint/
 */
public class Solution_TP0087 {
	
	static int T, N, M;
	static int[][] map;
	
	static class Grp{
		int no;
		int gno;
		Grp(int no, int gno){
			this.no  = no;
			this.gno = gno;
		}
	}
	
	// 인접리스트 생성 
	static ArrayList<Grp>[] al = new ArrayList[25001];
	static int[] gcnt = new int[25001];
	// 단절점 기본 변수
	static int[][] cntCut = new int[501][501]; // 단절점 개수 셋팅
	static int[] visited = new int[501]; // 방문여부
	static int[] order = new int[501]; // 방문순서
	static int[] lowOrder = new int[501]; // 정점 방문 이후(자식) 정점을 거치지 않고 방문 가능한 정점중 가장 낮은 순서
	static int[] child = new int[501]; // 정점의 자식 트리수

	public static void main(String[] args) throws Exception {
		FileInputStream fi = new FileInputStream(new File(Solution_TP0087.class.getResource("").getPath() + "Solution_TP0087"));
		System.setIn(fi);
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		T = Integer.parseInt(br.readLine());
		StringTokenizer st;
		
		for(int t=1; t<=T; t++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			
			Arrays.fill(al, null);
			Arrays.fill(gcnt, 0);
			Arrays.fill(visited, 0);
			
			// 상하좌우를 인접리스트로 구성한다. ((1≤N≤500, 1≤M≤ 500))
			map = new int[N+1][M+1];
			int idx = 1;
			for(int n=1; n<=N; n++) {
				st = new StringTokenizer(br.readLine());
				
				for(int m=1; m<=M; m++) {
					map[n][m] = Integer.parseInt(st.nextToken());
					cntCut[n][m] = 0; // 초기화
					al[idx] = new ArrayList<Grp>();
					idx++;
				}
			}
			// 무방향이며 같은 번호일때 연결한다. (전체 탐색하면 양방향 연결이 된다.)
			idx = 1;
			int currV;
			for(int n=1; n<=N; n++) {
				for(int m=1; m<=M; m++) {
					currV = map[n][m];
					// 좌
					if(m>1) {
						if(currV == map[n][m-1]){
							al[idx].add(new Grp(idx - 1, map[n][m-1]));
//							System.out.println(n + ", " + (m-1));
						}
					}
					// 우
					if(m<M) {
						if(currV == map[n][m+1]){
							al[idx].add(new Grp(idx + 1, map[n][m+1]));
//							System.out.println(n + ", " + (m+1));							
						}
					}					
					// 상
					if(n>1) {
						if(currV == map[n-1][m]){
							al[idx].add(new Grp(idx - M, map[n-1][m]));
//							System.out.println((n-1) + ", " + (m));							
						}
					}
					// 하
					if(n<N) {
						if(currV == map[n+1][m]){
							al[idx].add(new Grp(idx + M, map[n+1][m]));
//							System.out.println((n+1) + ", " + (m));		
						}
					}
					idx++;
				}
			}
			
			// 정점을 순회하면서 각 정점을 시작점으로 DFS 탐색을 진행한다. 
			// 시작점은 방문한 자식이 2개 이상이면 단절점이 된다. 
			// 정점의 방문순서를 기록해야 한다. 
			// 현재 방문순서와 탐색된 방문 순서중 min 값으로 찾는다.
			idx = 1;
			for(int n=1; n<=N; n++) {
				for(int m=1; m<=M; m++) {
					// dfs (idx, 0)
				}
			}
			
			
			System.out.println("#" + t + " " + "");
			
		} // end case
	} // end main

	static void dfs(int startNo, int parentNo) {
		int vOrder = 0;
		if(visited[startNo] == 1) {
			return; // 이미 방문했다면 종료
		}else {
			// 방문처리
			visited[startNo] = 1; // 방문 처리
			// 부모가 없으므로 root
			if(parentNo == 0) {
				vOrder = 1;
			}
		}		
		
		// 인접 리스트를 방문한다.
		int nextNo;
		for(int i=0; i<al[startNo].size(); i++) {
			nextNo = al[startNo].get(i).no;
			if(visited[nextNo] == 0) {
				dfs(nextNo, startNo);
				visited[nextNo] = 1; // 방문 처리		
			}
		}
		// 시작점이면 자식의 수가 2개 이상이면 단절점이며, 노드의 개수만큼 단절점 개수가 된다. 
		if(parentNo == 0) {			
			
		}
		
	}
}
