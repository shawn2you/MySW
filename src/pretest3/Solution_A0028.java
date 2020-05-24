package pretest3;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * 1일차 1번 문제
 * (중) [연습A-0028] 나누기
 * 알고리즘 : 재귀호출
 */
public class Solution_A0028 {
	
	static int T, M, N, Sum_0, Sum_1;
	
	static int[][] map = new int[129][129];  // 각 종이값 (2 ~ 128중 하나이므로)

	public static void main(String[] args) throws Exception {
		
		FileInputStream fi = new FileInputStream(new File(Solution_A0028.class.getResource("").getPath() + "Solution_A0028.txt"));
		System.setIn(fi);
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
				
		T = Integer.parseInt(br.readLine());
		
		for (int t=1; t<=T; t++) {
			// 초기화 
			Sum_0 = 0;
			Sum_1 = 0;
			// 한변의 길이 N
			N = Integer.parseInt(br.readLine());
			
			for(int n=0; n<N; n++) {
//				String line = br.readLine();
				StringTokenizer st = new StringTokenizer(br.readLine());
				for(int i=0; i<N; i++) {
					map[n][i] = Integer.parseInt(st.nextToken());
				}
			}
			
			// 나눌지 판단 여부 (전체에서 부분으로 나누면서 진행)
			div_check(0, 0, N);
//			div_check_exercise(0, 0, N);
			
			System.out.println("#"+t+" " + Sum_0+" " + Sum_1);
			
			
		} // end test case T		
		
	} // end main 
	
	static void div_check(int x, int y, int n) {
		
		int cntCheck = 0;
		
		for(int i=x; i<x+n; i++) {
			for(int j=y; j<y+n; j++) {
				if(map[i][j] == 1) {
					cntCheck++;
				}
			}
		}
		if(cntCheck == 0) { // 1의 개수가 없을 경우 0으로 색칠한 종이로 판단
			Sum_0++;
		}else if(cntCheck == n*n) { // 정사각형 개수와 동일한 경우 1로 색칠한 종이로 판단
			Sum_1++;
		}else {
			// 4등분 처리
			div_check(x, y, n/2);          // 1사분면 (0, 0, 2)
			div_check(x, y+n/2, n/2);      // 2사분면 (0, 2, 2)
			div_check(x+n/2, y, n/2);      // 3사분면 (2, 0, 2)
			div_check(x+n/2, y+n/2, n/2);  // 4사분면 (2, 2, 2)	
		}
	}
	// 재귀 호출을 이용한 탐색
	static void div_check_exercise(int x, int y, int n){
		int cnt_0 = 0;
		for(int i=x; i<x+n; i++){
			for(int j=y; j<y+n; j++){
				if(map[i][j] == 0){
					cnt_0++; // n 정사각형내 0의 개수
				}
			}
		}
		
		if(cnt_0 == 0){
			Sum_1++;
		}else if(cnt_0 == n*n){
			Sum_0++;
		}else{
			// 색종이가 아니면 정사각형을 나눈다.
			div_check_exercise(x, y, n/2);
			div_check_exercise(x+n/2, y, n/2);
			div_check_exercise(x, y+n/2, n/2);
			div_check_exercise(x+n/2, y+n/2, n/2);
		}
		
	}
	
}