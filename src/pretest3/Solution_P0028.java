package pretest3;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 *  (중) [기출A-0028] 제품의 일련번호 2 
 */
public class Solution_P0028 {

static int T, N, Sum;
static String S, E;
	
	public static void main(String[] args) throws Exception {
		
		FileInputStream fi = new FileInputStream(new File(Solution_P0028.class.getResource("").getPath() + "Solution_P0028"));
		System.setIn(fi);
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));	
		
		T = Integer.parseInt(br.readLine());
		
		for (int t=1; t<=T; t++) {
			// 초기화 
			Sum = 0;

			// 한변의 길이 N
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			S = st.nextToken();
			E = st.nextToken();
			
			
			
			
			
			
		} // end test case		
	} // end main

}
