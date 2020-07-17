package exetest;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Solution_EP0021 {

	static int T, M, N, Sum;
	static int INF = 100000007;
	static char[] badook = new char[401];
	static int[][] D ; 
	static int[][] H ; 
	
	public static void main(String[] args) throws Exception {
		
		FileInputStream fi = new FileInputStream(new File(Solution_EP0021.class.getResource("").getPath() + "Solution_EP0021.txt"));
		System.setIn(fi);
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));	
		
		T = Integer.parseInt(br.readLine());
		
		for (int t=1; t<=T; t++) {
			// 초기화 
			Sum = 0;

			// 점의 개수 N (n 은 400 이하의 짝수)
			N = Integer.parseInt(br.readLine());
			badook = br.readLine().toCharArray();

			// 순차적으로 2개씩 진행(짝수)
			// D[i][j] : i번째 바둑돌에서 j번째까지 바둑돌을 있는 최소비용
			// H[i][j] : i번째 바둑돌에서 j번째까지 최소비용으로 이을때의 높이
			
			D = new int[N+1][N+1]; 
			H = new int[N+1][N+1]; 
			for(int i=1; i<=N; i++) {
				Arrays.fill(D[i], INF);
				Arrays.fill(H[i], INF);
			}
				int j = 0;
				for(int len=1; len<=N; len+=2) {
					for(int i=1; i<=N-len; i++) {
						j = i + len; // len 길이만큼 비교
						if(len==1 && badook[i-1] != badook[j-1]) {
							H[i][j] = 1;
							D[i][j] = 3;
						}else if(badook[i-1] != badook[j-1]) {
							H[i][j] = H[i+1][j-1] + 1;
							D[i][j] = D[i+1][j-1] + len + H[i][j] + H[i][j];
						}
						
						for(int k=i+2; k<j; k+=2) {
							if(D[i][j] > D[i][k-1] + D[k][j]) {
								D[i][j] = D[i][k-1] + D[k][j];
								H[i][j] = Math.max(H[i][k-1], H[k][j]);
							}
						}
					}
				}
//			}
			
			
			
			
			System.out.println("#"+t+" "+D[1][N]);
		} // end test case		
	} // end main

}
