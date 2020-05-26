package pretest4;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.StringTokenizer;

/*
 * (중) [연습A-0002] 키순서(BFS, DFS)
 */
public class Solution_EA0002 {

	static int T, M, N, Sum;
	static int[][] visited = new int[501][501];
	static ArrayList<Integer>[] emp = new ArrayList[501];
	
	public static void main(String[] args) throws Exception {
		
		FileInputStream fi = new FileInputStream(new File(Solution_EA0002.class.getResource("").getPath() + "Solution_EA0002.txt"));
		System.setIn(fi);
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));	
		
		T = Integer.parseInt(br.readLine());
		
		for (int t=1; t<=T; t++) {
			// 초기화 
			Sum = 0;
			
			//  사원들의 수 N(2 ≤ N ≤ 500), 두 사원 키를 비교한 횟수 (0 ≤ M ≤ N(N-1)/2)
			N = Integer.parseInt(br.readLine());	
			M = Integer.parseInt(br.readLine());
			
			for(int i=1; i<=N; i++){
				Arrays.fill(visited[i], 0);
				emp[i] = new ArrayList<Integer>();
			}
			
			StringTokenizer st;
			int s, e;
			// 키가 몇번째인지 알수 있는 방법은 해당 Node를 모두 경유하는지 확인 필요
			for(int i=0; i<M; i++){
				st = new StringTokenizer(br.readLine());
				s = Integer.parseInt(st.nextToken());
				e = Integer.parseInt(st.nextToken());
				emp[s].add(e); // 방향 그래프
			}		
			for(int i=1; i<=N; i++){
				bfs(i);
//				dfs(i); // 각 직원기준으로 이동 경로여부를 체크
			}
			
			int check;
			for(int i=1; i<=N; i++){
				check = 1;
				for(int j=1; j<=N; j++){
					// 방문한 곳은 순서가 존재 한다.
					if(visited[i][j] == 1 || visited[j][i] == 1){
						check++;
					}
				}
				if(check == N){
					Sum++;
				}
			}

			System.out.println("#"+t+" "+ Sum);
			
		} // end test case		
	} // end main
	
	public static void dfs(int start){
		LinkedList<Integer> sta = new LinkedList<Integer>();	
		sta.push(start);
		
		int curr, next;
		while(!sta.isEmpty()){
			curr = sta.poll();
//			System.out.print(curr+", ");
			for(int i=0; i<emp[curr].size(); i++){
				next = emp[curr].get(i);
				if(visited[start][next] == 0){
					visited[start][next] = 1; // 방문처리
					sta.push(next);
				}
			}
		}
//		System.out.println();
	}
	
	public static void bfs(int start){
		ArrayDeque<Integer> q = new ArrayDeque<Integer>();
		
		q.addLast(start);
		
		while(!q.isEmpty()){
			int curr = q.pollFirst();
//			System.out.print(curr+", ");
			for(int i=0; i<emp[curr].size(); i++){
				int next = emp[curr].get(i);
				if(visited[start][next] == 0){
					visited[start][next] = 1; // 방문처리
					q.addLast(next);
				}
			}
		}
//		System.out.println();
	}
}
