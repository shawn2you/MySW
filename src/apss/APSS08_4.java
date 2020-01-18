package apss;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/*
 * DP : https://algospot.com/judge/problem/read/LIS
 * LIS
 */
public class APSS08_4 {
	static int T, N, Ans;
	static int[] cache = new int[100001];
	static int[] dp = new int[100001];

	public static void main(String[] args) throws Exception {
		FileInputStream fi = new FileInputStream(new File(APSS08_4.class.getResource("").getPath() + "APSS08_4.txt"));
		System.setIn(fi);

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		T = Integer.parseInt(br.readLine());
		StringTokenizer st = null;

		for (int t = 0; t < T; t++) {
			Ans = 0;
			N = Integer.parseInt(br.readLine());
			st = new StringTokenizer(br.readLine());
			Arrays.fill(dp, -1);

			for (int i = 0; i < N; i++) {				
				cache[i] = Integer.parseInt(st.nextToken());
			}
			dp[0] = 1;
			lis();
			Ans =  dp[N-1];
			System.out.println(Ans);
		} // end test case
	} // end main

	static void lis(){
		// dp[i] = i 번째 수를 마지막 원소로 가지(i번째까지의)는 LIS의 길이
		for(int i=1; i<N; i++){
//			if(i == 0) dp[i] = 1; // 최소 1
			// dp[i]가 이미 채워져 있다면 i보다 큰수의 j의 수열값
			// arr[j]도 arr[i]보다 크다면 dp[j]는 dp[i]+1이 될 수 있음
			for(int j=0; j<i; j++){
				// i번째값이 j번째보다 크다면, 
				if(cache[i] > cache[j] && dp[i] < dp[j] + 1){
					dp[i] = dp[j] + 1;
				}
			}
		}
	}
	
	static void lis2(){
		// dp[i] = i 번째 수를 마지막 원소로 가지(i번째까지의)는 LIS의 길이
		for(int i=1; i<N; i++){
//			if(i == 0) dp[i] = 1; // 최소 1
			// dp[i]가 이미 채워져 있다면 i보다 큰수의 j의 수열값
			// arr[j]도 arr[i]보다 크다면 dp[j]는 dp[i]+1이 될 수 있음
			for(int j=0; j<i; j++){
				// i번째값이 j번째보다 크다면, 
				if(cache[i] > cache[j] && dp[i] < dp[j] + 1){
					dp[i] = dp[j] + 1;
				}
			}
		}
	}
}