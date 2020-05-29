package pretest4;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;

/*
 * (중상) [교육P-0028] LCS
 */
public class Solution_SP0028 {

	static int T, M, N, Sum;
	static char[] S1, S2;
	static int[][] D;  // D[i][j] : 문자열 S1(1~i), S2(1~j)의 LCS의 길이
	static char[] LCS; // 최장공통수열
	
	public static void main(String[] args) throws Exception {
		
		FileInputStream fi = new FileInputStream(new File(Solution_SP0028.class.getResource("").getPath() + "Solution_SP0028.txt"));
		System.setIn(fi);
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));	
		
		T = Integer.parseInt(br.readLine());
		
		for (int t=1; t<=T; t++) {
			// 초기화 
			Sum = 0;

			// 첫째 줄과 둘째 줄에 두 문자열
			String str1, str2;
			int lenStr1, lenStr2;
			str1 = br.readLine();
			str2 = br.readLine();
			lenStr1 = str1.length();
			lenStr2 = str2.length();
			
			S1 = new char[lenStr1 + 1];
			S2 = new char[lenStr2 + 1];
			
			for(int i=0; i<lenStr1; i++) {
				S1[i+1] = str1.charAt(i);
			}
			
			for(int i=0; i<lenStr2; i++) {
				S2[i+1] = str2.charAt(i);
			}
			
			
			// D[i][j] : 문자열 S1(1~i), S2(1~j)의 LCS의 길이
			D = new int[S1.length][S2.length];
			for(int i=1; i<=lenStr1; i++) {
				for(int j=1; j<=lenStr2; j++) {
					if(S1[i] == S2[j]) {
//						D[i][j] = Math.max(Math.max(D[i-1][j], D[i][j-1]), D[i-1][j-1] + 1);
						D[i][j] = D[i-1][j-1] + 1;
					} else {
						D[i][j] = Math.max(D[i-1][j], D[i][j-1]);
					}
				}				
			}
			int lenLCS = D[lenStr1][lenStr2];
			LCS = new char[lenLCS + 1];
			
			int ix = lenStr1;
			int jx = lenStr2;
			while(lenLCS > 0) {
				if(S1[ix] == S2[jx]) {
					// 대각선으로 이동
					LCS[lenLCS] = S1[ix];
					ix--; jx--;
					lenLCS--;
				}else {
					// 최대개수가 같은쪽으로 이동한다. (LCS 생성된 위치)
					if(D[ix][jx] == D[ix-1][jx]) {
						ix--;
					}
					if(D[ix][jx] == D[ix][jx-1]) {
						jx--;
					}
				}
			}
			StringBuilder sb = new StringBuilder();
			sb.append("#").append(t).append(" ");
			for(int i=1; i<LCS.length; i++) {
				sb.append(LCS[i]);
			}
			
			System.out.println(sb.toString());
		} // end test case		
	} // end main

}
