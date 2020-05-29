package pretest4;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * (중상) [기출P-0007] 팀 구성
 */
public class Solution_TP0007 {

	static int T, M, N, Sum;
	
	public static void main(String[] args) throws Exception {
		
		FileInputStream fi = new FileInputStream(new File(Solution_TP0007.class.getResource("").getPath() + "Solution_TP0007.txt"));
		System.setIn(fi);
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));	
		
		T = Integer.parseInt(br.readLine());
		
		for (int t=1; t<=T; t++) {
			// 초기화 
			Sum = 0;

			// 한변의 길이 N
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			N = Integer.parseInt(br.readLine());	
			
			
			System.out.println("#"+t+" "+Sum);
		} // end test case		
	} // end main

}
