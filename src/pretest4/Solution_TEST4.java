package pretest4;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * [기출P-0018] 종이컵 전화기
 * 4차 모의 테스트 
 * http://182.193.11.65/common/practice/problem/view.do?problemId=AXJamPdwwOWojUHh&_menuId=AVUU732mAAHBC0c9&_menuF=true
 */
public class Solution_TEST4 {

	static int T, M, N, Sum;
	
	static int[][] D; // DP 배열
	static int[][] H; // 집연결선 정보
	
	public static void main(String[] args) throws Exception {
		
		FileInputStream fi = new FileInputStream(new File(Solution_TEST4.class.getResource("").getPath() + "Solution_TEST4.txt"));
		System.setIn(fi);
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));	
		
		T = Integer.parseInt(br.readLine());
		
		for (int t=1; t<=T; t++) {
			// 초기화 
			Sum = 0;

			// 집의 개수 N 과 실의 개수 M (2 ≤ N ≤ 200, 1 ≤ M ≤ 500, N, M 은 정수)
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			
			H = new int[N + 1][N + 1];
			// 연결된 경우 표시
			for(int i=1; i>=M; i++) {
				st = new StringTokenizer(br.readLine());
				H[Integer.parseInt(st.nextToken())][Integer.parseInt(st.nextToken())] = 1;
			}
			
			
			D = new int[N + 2][N + 2];
						
			// D[i][j] = i, j집을 연결할때 1~i번째까지 집에서 1~j번째 집까지 연결 가능한 최대 수 
			// D[i-1][j] : 1~i-1 번째까지 집에서 1~j번째 집까지 연결 가능한 최대 수
			// D[i][j-1] : 1~i번째까지 집에서 1~j-1번째 집까지 연결 가능한 최대 수
			// 연결 가능하다는 판단을 어떻게 할것인가? 
			for(int i=1; i<=N; i++) {
				for(int j=1; i+j <=N; j++) {
					for(int k=j; k<i+j; k++) {
						System.out.println();
					}
				}
			}
			
			
			System.out.println("#"+t+" "+Sum);
		} // end test case		
	} // end main

}
