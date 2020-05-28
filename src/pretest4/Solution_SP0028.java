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
			str1 = br.readLine();
			str2 = br.readLine();
			
			S1 = new char[str1.length() + 1];
			S2 = new char[str2.length() + 1];
			
			for(int i=0; i<str1.length(); i++) {
				S1[i] = str1.charAt(i);
			}
			
			for(int i=0; i<str2.length(); i++) {
				S1[i] = str2.charAt(i);
			}
			
			
			System.out.println("#"+t+" "+Sum);
		} // end test case		
	} // end main

}
