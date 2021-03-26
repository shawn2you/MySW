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
 * https://m.blog.naver.com/PostView.nhn?blogId=zoqdlekt&logNo=221589199600&proxyReferer=https:%2F%2Fwww.google.com%2F
 */
public class Solution_TP0087 {
	
	static int T, N, M, od;
	static int[][] map;
	
	static class Grp{
		int no;
		int gno;
		int n;
		int m;
		
		Grp(int no, int gno){
			this.no  = no;
			this.gno = gno;
		}
		
		
		Grp(int no, int gno, int n, int m){
			this.no  = no;
			this.gno = gno;
			this.n = n;
			this.m = m;
		}
	}
	
	// 인접리스트 생성 
	static ArrayList<Grp>[] al = new ArrayList[25001];
	static int[] gcnt = new int[25001];
	// 단절점 기본 변수
//	static int[][] cntCut = new int[501][501]; // 단절점 개수 셋팅
	static int[] cntCut = new int[25001]; // 단절점 개수 셋팅
	static int[] visited = new int[25001]; // 방문여부
	static int[] order = new int[25001]; // 방문순서
//	static int[] lowOrder = new int[25001]; // 정점 방문 이후(자식) 정점을 거치지 않고 방문 가능한 정점중 가장 낮은 순서
	static int[] orderNo = new int[25001]; // 정점의 번호
//	static int[] child = new int[25001]; // 정점의 자식 트리수

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
			Arrays.fill(cntCut, 0);
			Arrays.fill(orderNo, 0);
			
			// 상하좌우를 인접리스트로 구성한다. ((1≤N≤500, 1≤M≤ 500))
			map = new int[N+1][M+1];
			int idx = 1;
			int maxIdx = 0;
			for(int n=1; n<=N; n++) {
				st = new StringTokenizer(br.readLine());
				
				for(int m=1; m<=M; m++) {
					map[n][m] = Integer.parseInt(st.nextToken());
					orderNo[idx] = map[n][m]; // 정점의 번호 (Map이 필요 없어질수도 있네)
					al[idx] = new ArrayList<Grp>();
					idx++;
				}
			}
			maxIdx = idx; // 최대 개수
			// 무방향이며 같은 번호일때 연결한다. (전체 탐색하면 양방향 연결이 된다.)
			idx = 1;
			
			int currV;
			for(int n=1; n<=N; n++) {
				for(int m=1; m<=M; m++) {
					currV = map[n][m];
					// 좌
					if(m>1) {
						if(currV == map[n][m-1]){
							al[idx].add(new Grp(idx - 1, map[n][m-1], n, m-1));
//							System.out.println(n + ", " + (m-1));
						}
					}
					// 우
					if(m<M) {
						if(currV == map[n][m+1]){
							al[idx].add(new Grp(idx + 1, map[n][m+1], n, m+1));
//							System.out.println(n + ", " + (m+1));							
						}
					}					
					// 상
					if(n>1) {
						if(currV == map[n-1][m]){
							al[idx].add(new Grp(idx - M, map[n-1][m], n-1, m));
//							System.out.println((n-1) + ", " + (m));							
						}
					}
					// 하
					if(n<N) {
						if(currV == map[n+1][m]){
							al[idx].add(new Grp(idx + M, map[n+1][m], n+1, m));
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
			od = 0;
			for(int n=1; n<=N; n++) {
				for(int m=1; m<=M; m++) {
					// 방문 안했다면~~ 단절점 탐색
					// 단절점이면~~ 해당 위치에서 그룹이 나눠지는 개수를 확인
					if(visited[idx] == 0) {
						od = 0;
						dfs (4, od);
					}
					idx++;
				}
			}
			int cnt2 = 0;
			int cnt3 = 0;
			int cnt4 = 0;
			for(int i=1; i<=maxIdx; i++) {
				if(cntCut[i] == 2) {
					cnt2++;
				}else if(cntCut[i] == 3) {
					cnt3++;
				}else if(cntCut[i] == 4){
					cnt4++;
				}
			}
			
			System.out.println("#" + t + " " + cnt2 + " " + cnt3 + " " + cnt4);
			
		} // end case
	} // end main

/*
단절점을 구하는 방식
- dfs 를 구현한다.
- 방문하는 순서를 정한다 . (order)
- 인접하는 경로들을 돌면서 아래의 활동을한다.
  . 이미 방문한 정점이면 
    > 해당 정점의 order를 내 low 와 비교해서 처리 low 값 리턴
  . 방문하지 않은 정점이라면
    > 해당 정점에 대해서 dfs 를 실행시켜서 리턴되는 low 값 저장
       리턴된 low 값을 내 order랑 비교해서 단절점인지 체크(*내 order가 크면 단절점 : 검증필요)
       리턴된 low 값을 내 low 랑 비교해서 갱신
       (시작점이라면 dfs 를 호출한 횟수를 체크해서 단절점인지 파악)
- 해당 dfs 가 종료되면서 low 값 리턴	
 */
	static int dfs(int startNo, int parentNo) {		
		od++;
		order[startNo] = od;
		visited[startNo] = 1;
		int lowOd = od;
		
		int child = 0;		
		// 인접 리스트를 방문한다.
		int nextNo, n, m;
		for(Grp grp:al[startNo]) {
			nextNo = grp.no;
			n = grp.n;
			m = grp.m;
			
			// 방문하지 않았다면 계속 진행
			if(visited[nextNo] == 0) {
				child++;
				// 자식의 최저순서 
				int relowOd = dfs(nextNo, startNo);
				
				// 자식의 최저순서(relowOd)가 부모의 순서랑 비교하여 자식이 더 낮은 순서가 있다면 부모를 경유하지 않고 탐색이 가능하다. (단절점이 아니다)
				if(relowOd >= lowOd) {
					// 부모가 더 앞이니 단절점이다.(자식이 순위가 낮다, 값은 크다)
					System.out.println("단절점==" + n + ", " + m  + " :: 자식 " + nextNo + "==> 부모 " + startNo + " :: " + relowOd+ " :: " +lowOd );
					cntCut[startNo] = 1;					
				}
				// 최저순서를 갱신한다.				
				lowOd =  Math.min(lowOd, relowOd);
				
			}else {
				// 이미 방문했다면 해당 노드의 최소값을 리턴한다.
				lowOd = Math.min(lowOd, order[startNo]);
			}
		}
		
		// 최초(root) 처리를 한다. 
		if(parentNo == 0 && child > 1) {
			// 자식이 2개 이상이면 단절점이다.
			cntCut[startNo] = 1;
//			for(int i=0; i<al[startNo].size(); i++) {
//				
//				if(al[startNo].get(i).gno == orderNo[startNo]) {
//					cntCut[startNo] ++;
//				}
//			}	

		}
		
		
		return lowOd;
	}
}
