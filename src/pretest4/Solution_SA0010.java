package pretest4;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * (중) [교육A-0010] 가장 큰 정사각형
 */
public class Solution_SA0010 {

	static int T, M, N, Sum;
	
	public static void main(String[] args) throws Exception {
		
		FileInputStream fi = new FileInputStream(new File(Solution_SA0010.class.getResource("").getPath() + "Solution_SA0010.txt"));
		System.setIn(fi);
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));	
		
		T = Integer.parseInt(br.readLine());
		
		for (int t=1; t<=T; t++) {
			// 초기화 
			Sum = 0;

			// 격자판의 크기 N, M  (1 ≤ N, M ≤ 1,000)
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			
			
			System.out.println("#"+t+" "+Sum);
		} // end test case		
	} // end main

}
