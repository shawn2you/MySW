package pretest3;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * 2일차 과제
 * (중상) [교육P-0016] 동굴
 */

public class Solution_P0016 {
	
	static int T, H, N;

	public static void main(String[] args)throws Exception {
		
		FileInputStream fi = new FileInputStream(new File(Solution_P0016.class.getResource("").getPath() + "Solution_P0016.txt"));
		System.setIn(fi);
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
				
		T = Integer.parseInt(br.readLine());
		
		for (int t=1; t<=T; t++) {
			
			// 동굴의 높이(구간)를 지정하고, 
			// 장애물을 각 구간에 미리 계산을 해서 둔다. 
			
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			N = Integer.parseInt(st.nextToken());
			H = Integer.parseInt(st.nextToken());
			
			int[] hi = new int[H+1];
			
			for(int i=0; i<N; i++){
				// 시작은 석순 ~ 끝은 종유석
				int len = Integer.parseInt(br.readLine()); // 길치
				if(i%2 == 0) {
					for(int h=0; h<len; h++){
						hi[h]++;
					}
				}else{
					for(int h=H-1; h>=H-len; h--){
						hi[h]++;
					}
				}
			}
			
			int sum = Integer.MAX_VALUE;
			int idx = 0;
			
			for(int h=0; h<H; h++){
				if(hi[h] <= sum){
					idx++;
					sum = hi[h];
				}
			}
			System.out.println("#"+t+" "+ sum + " " + idx);
			
		} // end test case
		
	} // end main

}
