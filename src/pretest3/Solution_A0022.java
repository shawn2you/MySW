package pretest3;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Solution_A0022 {

	static int T, A, B, Sum;
	static int minV = 1000;
	static int maxV = 9999;
	static boolean[] isPrime = new boolean[maxV+1]; // 소수
	static boolean[] visited = new boolean[maxV+1]; // 방문
	
	public static void main(String[] args) throws Exception {
		
		FileInputStream fi = new FileInputStream(new File(Solution_A0022.class.getResource("").getPath() + "Solution_A0022.txt"));
		System.setIn(fi);
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));	
		
		T = Integer.parseInt(br.readLine());
		// 1000 이상, 9999 이하의 소수 미리 만들기
		
		makePrime(9999);		
		
		for (int t=1; t<=T; t++) {
			// 초기화 
			Sum = 0;

			// 시작 A, 종료 B
			StringTokenizer st = new StringTokenizer(br.readLine());
			A = Integer.parseInt(st.nextToken());
			B = Integer.parseInt(st.nextToken());
			
			int[] curr = {A/1000, A%1000/100, A%100/10,A%10};
			while(true) {
				if(A == B) break;
				
				
				
				
			}
			
			
		} // end test case		
	} // end main

	// 에라토스테네스 체(배수처리)
	static void makePrime(int n) {
		Arrays.fill(isPrime, true); // 전체를 소수로 셋팅
		isPrime[0] = false; // 제외 처리
		isPrime[1] = false; // 제외 처리
//		for(int i=2; i*i<=n; i++) {  // n의 제곱근까지만 처리
		for(int i=2; i<=n; i++) {  // n의 제곱근까지만 처리
			if(isPrime[i]) {
//				for(int j=i*i; j<=n; j+=i) { // i배수시작부터(i*i 판별
				for(int j=i*2; j<=n; j+=i) { // i배수시작부터(i*i 판별
					isPrime[j] = false;
				}
			}
		}
	}
}
