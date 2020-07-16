package exetest;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution_TP0057 {

	static int T, N, K;
	static int cnt, len;
	static long res;
	static long MOD =  1000000007L;
	
	public static void main(String[] args) throws Exception {
		
		FileInputStream fi = new FileInputStream(new File(Solution_TP0057.class.getResource("").getPath() + "Solution_TP0057.txt"));
		System.setIn(fi);
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));	
		
		T = Integer.parseInt(br.readLine());
		StringTokenizer st ;
		
		for (int t=1; t<=T; t++) {
			 st = new StringTokenizer(br.readLine());
			  N = Integer.parseInt(st.nextToken());
			  K = Integer.parseInt(st.nextToken());
			   
			  res = 0;

			  for(int i =1; i<=N; i++) {
				  for(int j =1; j<=N; j++) {
					  cnt = 0;
					  len = K;
					  cnt = (j+K) > N ? N-j : K; // 기준점의 오른쪽 라인
					  for(int m=i+1; m<=i+K && m<=N; m++) { // 기준점을 내리면 오른쪽, 왼쪽 라인을 더한다.
						  len--;
						  cnt += (j + len) >N ? N-j : len; // 오른쪽
						  cnt += (j -  len) >0  ? len : j-1; // 왼쪽
						  cnt++; // 기준점 아래
					  }
					  res = (res + cnt * pow(2, N*N-2)) % MOD; // 위치를 고정하고 경우의 수를 계산
				  }
			  }
			  System.out.println("#" + t + " " + res);
		} // end test case		
	} // end main
	
	static long pow(long a, long b) {
		if(b ==0) return 1;
		if(b ==1) return a;
		if(b%2 == 0) {
			return pow((a*a)%MOD, b/2)%MOD; // 짝수 a = (a^2)^b/2
		} else {
			return  a * pow((a*a)%MOD, b/2)%MOD; // 홀수 a = a*(a^2)^b/2
		}
		
	}
}
