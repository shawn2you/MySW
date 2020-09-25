package pretest6;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * (중상) [기출P-0065] Shields Up! 
 */
public class Solution_TP0065 {

	static int T, M, N, Sum;
	
	public static void main(String[] args) throws Exception {
		
		FileInputStream fi = new FileInputStream(new File(Solution_TP0065.class.getResource("").getPath() + "Solution_TP0065"));
		System.setIn(fi);
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));	
		
		T = Integer.parseInt(br.readLine());
		
		for (int t=1; t<=T; t++) {
			// 초기화 
			Sum = 0;
			
/*
입력 파일의 제일 첫째 줄에는 파일에 포함된 케이스의 수 T가 주어진다. 
각 케이스의 첫째 줄에는 격자칸의 세로 크기 N과 가로 크기 M이 주어진다. (1 ≤ N, M ≤ 1,000) 
다음 N개의 줄 각각에 길이 M인 문자열이 하나씩 주어진다. 이 문자열들은 (위에서 아래로 순서대로) 격자 칸의 한 줄을 표시한 것이다. 
문자 하나가 격자칸 하나에 해당하며, 그 값은 0, 1, 2, …, 9 중 하나이다. 
본문에서 설명한 바와 같이, 입력으로 주어진 격자칸들은 그 전체가 0들로 둘러 쌓여 있는 것으로 생각해야 한다.
 */
			// 세로 크기 N과 가로 크기 M (1 ≤ N, M ≤ 1,000) 
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			
			
			System.out.println("#"+t+" "+Sum);
		} // end test case		
	} // end main

}
