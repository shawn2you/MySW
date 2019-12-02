package apss;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * DP : https://algospot.com/judge/problem/read/JUMPGAME
 */
public class APSS08_1 {
	static int C, N, T, Ans;
	static int[][] map = new int[101][101];
	static int[][] dp = new int[101][101];
	
	public static void main(String[] args) throws Exception{
		FileInputStream fi = new FileInputStream(new File(APSS08_1.class.getResource("").getPath() + "APSS08_1.txt"));
		System.setIn(fi);
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		T = Integer.parseInt(br.readLine());
		StringTokenizer st = null;
		
		for(int t=1; t<=T; t++){
			Ans = -1;
			N = Integer.parseInt(br.readLine());
			for(int i=0; i<N; i++){
				st = new StringTokenizer(br.readLine());
				
				for(int j=0; j<N; j++){
					map[i][j] = Integer.parseInt(st.nextToken());
					dp[i][j] = -1; // 초기화
				}
			}
			// 입력값 검증
//			for(int i=0; i<N; i++){
//				for(int j=0; j<N; j++){
//					System.out.print(map[i][j] + " ");
//				}
//				System.out.println();
//			}
			
			// find
			Ans = find(0, 0);
			StringBuilder sb = new StringBuilder();
//			sb.append("#").append(t).append(" ");
			if(Ans == 1){
				sb.append("YES");
			}else{
				sb.append("NO");
			}
			System.out.println(sb.toString());
		} // end test case
	} // end main
	
	public static int find(int x, int y){
		if(x >= N || y >= N ){
			// 땅에서 벋어나는 경우 실패
			return 0;
		}
		// 도착
		if(x == N-1 && y == N-1) return 1;
		
		int ret = dp[x][y];
		if(ret != -1) return ret;
		
		int next = map[x][y];
		// 오른쪽/아래쪽으로 가는 경우 (비트연산자)
		dp[x][y] = find(x + next, y) | find(x, y + next);
		
		return dp[x][y];
	}
}
