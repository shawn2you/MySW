package pretest4;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/*
 * (중) [교육A-0011] 동전 교환 
 */
public class Solution_SA0011 {

	static int T, W, N, Sum;
	static int INF = Integer.MAX_VALUE;
	
	static int[] C; // 동전
	static int[][] D; // D[i][j]  : C[i]까지의 동전을 사용하고, J원을 채우기 위한 최소 동전갯수
	
	// 재귀함수에서 활용(case 2)
	static long LINF = Long.MAX_VALUE;
	static long[] Ans; 
	
	public static void main(String[] args) throws Exception {
		
		FileInputStream fi = new FileInputStream(new File(Solution_SA0011.class.getResource("").getPath() + "Solution_SA0011.txt"));
		System.setIn(fi);
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));	
		
		T = Integer.parseInt(br.readLine());
		
		for (int t=1; t<=T; t++) {
			// 초기화 
			Sum = 0;

			// 동전 단위의 수 N (1 ≤ N ≤ 10)
			N = Integer.parseInt(br.readLine());
			
			// 동전 C (1 ≤ C ≤ 64,000)
			C = new int[N + 1];
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int i=1; i<=N; i++) {
				C[i] = Integer.parseInt(st.nextToken());
			}
			// 잔돈 W (1 ≤ W ≤ 64,000)
			W = Integer.parseInt(br.readLine());			
			
			// 동전 1(C[1]), 4(C[2]), 6(C[3])원
			// D[i][j] : C[1] ~ C[i]까지의 동전을 사용하고, 잔돈 J원을 채우기 위한 최소 동전갯수
			// min ( D[i-1][j],  D[i][j - C[i]] + 1 )
			// D[i-1][j] : 이전(i-1)동전까지 사용해서 J원을 채운 최소값
			// D[i][j - C[i]] + 1 : 동전(i)까지 사용해서 J원을 채운 최소값
			D = new int[N+1][W+1];
			for(int i=1; i<=N; i++){
				for(int j=1; j<=W; j++){
					int bfMC = D[i-1][j]; // 이전 동전사용까지 j잔돈을 채운 최대 개수
					
					// 초기값을 고려하여 첫번째 코인은 이전코인 사용값만 체크 한다.
					if(i == 1){
						// 잔돈을 채우지 못하는 경우 -1로 처리 (j%C[i], J%C[i-1] > 0)
						if(j - C[i] >= 0) {
							if(j%C[i] > 0) {
								D[i][j] = INF;
							}else {
								D[i][j] =  D[i][j - C[i]] + 1;
							}
						}else {
							if(j%C[i] > 0) {
								D[i][j] = INF;
							}else {
								D[i][j] =  j/C[i]; // 첫번째 라인만 처리 하면 된다.(이후는 항상 최소값 또는 불가 처리)
							}
						}
					}else{
						// 잔돈을 채우지 못한 경우는 계산 Skip 처리 필요(max 값으로 셋팅해 놓으면 구분이 가능할듯함)
						// 이전 동전까지 채우지 못한 경우는 그냥 하위를 그대로 적용하면 되나, 
						// 해당 동전까지 채우지 못한 경우(D[i][j - C[i]])가 있다면 무조건 이전동전으로 셋팅해야 한다. 
						if(j - C[i] >= 0){
							if(D[i][j - C[i]] == INF) {
								D[i][j] = bfMC;
							}else {								
								D[i][j] = Math.min(bfMC, D[i][j - C[i]] + 1);
							}							
						}else{
							D[i][j] = bfMC;
						}					
					}				
				}
			}
			
			
			Ans = new long[W + 1];
			Arrays.fill(Ans, LINF);
			
			System.out.println("#"+t+" "+D[N][W]);
			
			// 재귀호출
//			System.out.println("#"+t+" "+getCoinCount(W));
		} // end test case		
	} // end main
	
	// backtracking 방식
	static long getCoinCount(int money) {
		if(Ans[money] != LINF) {
			return Ans[money];
		}else if(money == 0) {
			return 0;
		}
		
		for(int i=1; i<=N; i++) {
			if(money >= C[i]) {
				Ans[money] = Math.min(Ans[money], getCoinCount(money - C[i]) + 1);
			}
		}
		return Ans[money];
	}

}
