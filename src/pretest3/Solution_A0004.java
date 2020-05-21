package pretest3;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution_A0004 {

	static int T, M, N, Sum;
	
	public static void main(String[] args) throws Exception {
		
		FileInputStream fi = new FileInputStream(new File(Solution_A0004.class.getResource("").getPath() + "Solution_A0004.txt"));
		System.setIn(fi);
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));	
		
		T = Integer.parseInt(br.readLine());
		
		for (int t=1; t<=T; t++) {
			// 초기화 
			Sum = 0;

			// 금괴의 수 N과 사람의 수 M (1<=N,M<=100)
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
//			if(N>=M) {
				System.out.println("#"+t+" "+ (M-gcd(N, M)));
//			}else {
//				System.out.println("#"+t+" "+ (N-gcd(N, M)));
//			}
			
			
		} // end test case		
	} // end main

	static int gcd(int a, int b) {
		int tmp, r;
		if(a < b)
		{
			tmp = a;
			a = b;
			b = tmp;
		}
		
		while(b != 0) { // 나머지가 0이 되면 그때 a가 GCD(최대 공약수)
			r = a%b;
			a = b;
			b = r;
		}
		return a;
	}
}
