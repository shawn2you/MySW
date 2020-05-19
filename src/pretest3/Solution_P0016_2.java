package pretest3;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/*
 * 2일차 과제
 * (중상) [교육P-0016] 동굴
 */

public class Solution_P0016_2 {
	
	static int T, H, N;
	static int[] hi;
	
	public static void main(String[] args)throws Exception {
		
		FileInputStream fi = new FileInputStream(new File(Solution_P0016_2.class.getResource("").getPath() + "Solution_P0016.txt"));
		System.setIn(fi);
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
				
		T = Integer.parseInt(br.readLine());
		
		for (int t=1; t<=T; t++) {
			
			// 동굴의 높이(구간)를 지정하고, 
			// 장애물을 각 구간에 미리 계산을 해서 둔다. 
			
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			N = Integer.parseInt(st.nextToken());
			H = Integer.parseInt(st.nextToken());
			
			hi = new int[H+1]; // 높이별 장애물 개수
            
			int len;
              
            for(int i = 1; i <= N; i++) { 
            	// 높이별 장애물 누적합 구하기 위해, 시작에 +1, 끝 다음에-1
            	len = Integer.parseInt(br.readLine());
                  
                if(i % 2 == 1) { // 홀수는 석순
                	hi[1]++; // 가장 아래에서 장애물 시작
                	hi[len + 1]--; // 높이 len까지 장애물 있으며, +1부터 장애물 사라짐
                }
                else { // 짝수는 종유석
                	hi[H - len + 1]++; // 위에서 부터 생기니 시작점은 (동굴높이- len + 1)에서 장애물 생성
                	// 끝다음이 없으므로 사라지는점은 불필요
                }
            }
             
            for(int i = 2; i <= H; i++) { // 높이별 장애물개수 계산(1번째는 계산 불필요하며, 누적으로 합을 계산)
            	hi[i] += hi[i - 1];
            }
			
			
			Arrays.sort(hi);
			
			int min = hi[1]; // 정렬 후 최소값
            int cnt = 1;
              
            for(int i = 2; i <= H; i++) { // 제일 적은 장애물 구간 카운트
                if(hi[i] == min) {
                    cnt++;
                }
                else {
                    break;
                }
            }
						
			System.out.println("#"+t+" "+ min + " " + cnt);
			
		} // end test case
		
	} // end main

}
