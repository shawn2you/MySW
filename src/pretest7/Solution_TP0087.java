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
			
			// 상하좌우를 인접리스트로 구성한다. ((1≤N≤500, 1≤M≤ 500))
			map = new int[N+1][M+1];
			
			for(int n=1; n<=N; n++) {
				st = new StringTokenizer(br.readLine());
				
				for(int m=1; m<=M; m++) {
					map[n][m] = Integer.parseInt(st.nextToken());
				}
			}
			int idx = 1;
			for(int n=1; n<=N; n++) {
				for(int m=1; m<=M; m++) {
					al[idx] = new ArrayList<Grp>();
					// 좌
					if(m>1) {
						al[idx].add(new Grp(idx - 1, map[n][m-1]));
//						System.out.println(n + ", " + (m-1));
					}
					// 우
					if(m<M) {
						al[idx].add(new Grp(idx + 1, map[n][m+1]));
//						System.out.println(n + ", " + (m+1));
					}					
					// 상
					if(n>1) {
						al[idx].add(new Grp(idx - M, map[n-1][m]));
//						System.out.println((n-1) + ", " + (m));
					}
					// 하
					if(n<N) {
						al[idx].add(new Grp(idx + M, map[n+1][m]));
//						System.out.println((n+1) + ", " + (m));
					}
					idx++;
				}
			}
			
			
			System.out.println("#" + t + " " + "");
			
		} // end case
	} // end main

}
