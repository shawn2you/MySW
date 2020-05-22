package pretest3;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution_A0005 {

	static int T, K, N, Sum;
	static int pscCnt = 5001;
	static int mod = 1000000007;
	static long[][] psc = new long[pscCnt][pscCnt];
	
	public static void main(String[] args) throws Exception {
		
		FileInputStream fi = new FileInputStream(new File(Solution_A0005.class.getResource("").getPath() + "Solution_A0005.txt"));
		System.setIn(fi);
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));	
		
		T = Integer.parseInt(br.readLine());
		
		// 파스칼의 함수로 미리 정의
		psc[0][0] = 1;
		// 0,0 -> 1,0 / 1,1
		for(int i=1; i<pscCnt; i++) {
			for(int j=0; j<=i; j++) {
				if(j==0) {
					// 첫번째는 이전이 없으므로 상위 같은위치값을 상속받아 넣는다.
//					psc[i][j] = psc[i-1][j];
					psc[i][j] = psc[i-1][j]%mod;
				}else {
//					psc[i][j] = (psc[i-1][j-1] + psc[i-1][j]);
					psc[i][j] = (psc[i-1][j-1] + psc[i-1][j])%mod;
				}				
			}
		}
		
		
		for (int t=1; t<=T; t++) {
			// 초기화 
			Sum = 0;

			// 한변의 길이 N
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			
			System.out.println("#"+t + " " + psc[N][K]);
			
		} // end test case		
	} // end main

}
