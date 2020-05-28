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
	static int[][] S; // 입력 정보
	static int[][] D; // D[i][j] : S[i][j] 위치를 사용한 최대 정사각형 개수
	
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
			
			
/*
가장 큰 정사각형
- S[i][j] = 최초 입력 받은 배열
- D[i][j] = S[i][j] 까지 사용해서 가장 큰 정사각형
- S[i][j] 가 0이 아닌 경우엔 A[i][j] 를 이용해서 정사각형을 절대 못만듬으로,
  D[i][j] 는 0이다.
- S[i][j] 가 0인 경우에는 나의 왼쪽이나, 윗쪽, 대각선 왼쪽위 3지점의 D 값 중 가장 작은 값을 찾아서 + 1 한다.
  (내 왼쪽이나 윗쪽에 있는 모든 D 값들중에 하나라도 0이라면, D[i][j]는 1이 될수 밖에 없음
  D[i][j] = min (D[i-1][j], D[i][j-1] , D[i-1][j-1]) + 1 (S[i][j] 가 0 일때)			
 */
			S = new int[N+1][M+1];
			for(int i=1; i<=N; i++){
				st = new StringTokenizer(br.readLine());
				for(int j=1; j<=M; j++){
					S[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			D = new int[N+1][M+1];
			for(int i=1; i<=N; i++){				
				for(int j=1; j<=M; j++){
					if(S[i][j] == 1){
						D[i][j] = 0;
					}else{
						if(S[i-1][j] == 0 && S[i][j-1] == 0 && S[i-1][j-1] == 0){
							D[i][j] = Math.min(Math.min(D[i-1][j], D[i][j-1]), D[i-1][j-1]) + 1;
						}else{
							D[i][j] = 1; // 자기자신을 포함해야 하므로 1개 셋팅
						}
					}
					Sum = Math.max(Sum, D[i][j]);
				}
			}
			
			System.out.println("#"+t+" "+Sum);
		} // end test case		
	} // end main

}
