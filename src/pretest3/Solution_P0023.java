package pretest3;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution_P0023 {

	static int T, K, N, Sum;

	static int[] word = new int[1000001];
	public static void main(String[] args) throws Exception {

		FileInputStream fi = new FileInputStream(
				new File(Solution_P0023.class.getResource("").getPath() + "Solution_P0023.txt"));
		System.setIn(fi);

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		T = Integer.parseInt(br.readLine());

		for (int t = 1; t <= T; t++) {
			// 초기화
			Sum = 0;

			StringTokenizer st = new StringTokenizer(br.readLine());
			
			// 단어길이 N, 단어종류 K
			N = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			
			st = new StringTokenizer(br.readLine());
			for(int i=0; i<N; i++) {
				word[i] = Integer.parseInt(st.nextToken());;
			}
			
			for(int i=0; i<N; i++) {
				find(i);
			}

		} // end test case

	} // end main
	
	
	static void find(int start) {
		
	}
	
}