package pretest4;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution_SA0012 {

	static int T, M, N, Sum;
	static long[][] Jewel;
	static long[] C; // 가치
	static int[] W; // 무게
	
	public static void main(String[] args) throws Exception {
		
		FileInputStream fi = new FileInputStream(new File(Solution_SA0012.class.getResource("").getPath() + "Solution_SA0012.txt"));
		System.setIn(fi);
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));	
		
		T = Integer.parseInt(br.readLine());
		
		for (int t=1; t<=T; t++) {
			// 초기화 
			Sum = 0;

			// 보석의 개수 N과 무게 제한 M  (1 ≤ N ≤ 1,000, 1 ≤ M ≤ 10,000)
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			
			Jewel = new long[N+1][M+1];
			C = new long[N+1];
			W = new int[N+1];
			
			// 각 보석에 대한 정보가 주어진다. 
			// i+1번째 줄에 C[i]와 W[i]가 공백으로 구분되어 주어진다. (1 ≤ C[i] ≤ 109, 1 ≤ W[i] ≤ 10,000)
			for(int i=1; i<=N; i++) {
				st = new StringTokenizer(br.readLine());
				
				C[i] = Long.parseLong(st.nextToken()); // 가치
				W[i] = Integer.parseInt(st.nextToken()); // 무게
			}
			
			// Jewel[i][j] : i는 보석, j는 무게제한
			// 1번부터 i번까지 보석을 가져갔을때 최대값(구하고자 하는 값)
			// Jewel[i][j] = i보석을 가져가는 경우, i보석을 가져가지 않는 경우중 최대값을 취한다.
			// 아무것도 선택하지 않는 경우, 무게제한이 0인경우 모두 0으로 초기화 수행 
			for(int i=0; i<=N; i++) {
				for(int j=0; j<=M; j++) {
					if(i == 0 || j == 0) {
						Jewel[i][j] = 0;
					}else {
						if(j-W[i] >= 0) {
							Jewel[i][j] = Math.max(Jewel[i-1][j], Jewel[i-1][j-W[i]] + C[i] );	
						}else {
							Jewel[i][j] = Jewel[i-1][j];	
						}						
					}
				}				
			}
			
			System.out.println("#" + t + " " + Jewel[N][M]);
			
			
			
		} // end test case		
	} // end main

}
