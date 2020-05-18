package pretest3;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * 1일차 1번 문제
 * (중) [연습A-0028] 나누기
 */
public class Solution_A0028 {
	
	static int T, M, N, Sum_0, Sum_1;
	
	static int[][] map = new int[129][129];  // 각 종이값

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
			
			// 나눌지 판단 여부 
			div_check(0, 0, N);			
			
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
		if(cntCheck == 0) {
			Sum_0++;
		}else if(cntCheck == n*n) {
			Sum_1++;
		}else {
			// 4등분 처리
			div_check(x, y, n/2);          // 1사분면 (0, 0, 2)
			div_check(x, y+n/2, n/2);      // 2사분면 (0, 2, 2)
			div_check(x+n/2, y, n/2);      // 3사분면 (2, 0, 2)
			div_check(x+n/2, y+n/2, n/2);  // 4사분면 (2, 2, 2)	
		}
	}
}