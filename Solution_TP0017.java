package pretest6;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

/*
 * (중상) [기출P-0017] 직사각형 개수세기
 */

public class Solution_TP0017 {
	static int T, N, M;
	static long SUM;
	
	// 변수설정
	static int[][] map;   // 입력값
	static int[][] ht;    // 높이
	static int[][] dp;    // 직사각형 개수

	public static void main(String[] args) throws Exception {
		FileInputStream fi = new FileInputStream(new File(Solution_TP0017.class.getResource("").getPath() + "Solution_TP0017.txt"));
		System.setIn(fi); 
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		T = Integer.parseInt(br.readLine());
		
		StringTokenizer st;
		for(int t=1; t<=T; t++) {
			SUM = 0;
			
			// 두 자연수 N, M (1 ≤ N, M ≤ 3,000)
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			
			
			// 입력값을 우선 받는다.(초기화 고려하여 new 선언) 
			map  = new int[N+1][M+1];
			ht = new int[N+1][M+1];
			dp   = new int[N+1][M+1];
			
			String line;
			for(int n=1; n<=N; n++) {
				line = br.readLine();
				for(int m=1; m<=M; m++){
					map[n][m] = line.charAt(m-1)-48;
				}
			}
			
			// 순차적으로 탐색해가며, 직사각형의 수는 직사각형의 넓이와 동일하다는 것에 착안하여 진행
			// case1 직전과 비교시 높이가 같을 경우   : 직전까지의 수 + 현재위치의 수  
			// case2 직전과 비교시 높이가 높아질 경우 : 직전까지의 수 + 현재위치의 수 
			// case3 직전과 비교시 높이가 낮아질 경우 : 높이가 같아지는 지점(S)까지의 수 + (S+1)에서  현재 위치까지의 수
			
			Deque<Integer> list = new ArrayDeque<>(); // stack(이전 위치)
			int left = 0;
			int temp;
			
			for(int i=0; i<=N; i++) {
				for(int j=0; j<=M; j++) {
					if(map[i][j] == 0) {
						// 격자칸이 0이므로 대상에서 제외 처리
						list.clear();
						dp[i][j] = 0;
						ht[i][j] = 0;
						
						left =  j + 1;						
					}else {						
						// 높이 구하기(이전 높이 + 1)
						ht[i][j] = ht[i-1][j] + 1;
						temp = -1; // 직전위치 정보
						
						// 이전 높이와 비교하여 높이가 낮아지면 같아질때까지 위치 찾기
						while(!list.isEmpty() && ht[i][list.peek()] > ht[i][j]) {
							temp = list.pop(); // 직전 위치 찾기
						}
						
						if(list.isEmpty()) { // 직전위치가 최초 시작점인 경우 처리
							dp[i][j] = (j - left + 1)*ht[i][j];
						}else {
							// case1,2,3
							dp[i][j] = dp[i][list.peek()] + (j - list.peek())*ht[i][j];
						}
						
						list.push(j); // 현재 위치 정보 저장
						
						SUM = SUM + dp[i][j];
						
					}
				}
			}
			
			System.out.println("#"+t+" "+SUM);
			
		} // end test case
	}
}
