package pretest6;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution_EP0014 {

static int T, M, N, Sum;

static int[] num;
static int[] isPrime;
	
	public static void main(String[] args) throws Exception {
		
		FileInputStream fi = new FileInputStream(new File(Solution_EP0014.class.getResource("").getPath() + "Solution_EP0014"));
		System.setIn(fi);
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));	
		
		T = Integer.parseInt(br.readLine());
		isPrime = new int[101];
		checkPrime(100);
		System.out.println(GCD(4, 2));
		
		for (int t=1; t<=T; t++) {
			// 초기화 
			Sum = 0;
			// 수열의 크기 (1 ≤ N ≤ 100)
			N = Integer.parseInt(br.readLine());	
			num     = new int[N + 1];
//			isPrime = new int[N + 1];
			
			// N개의  원소 Si가 공백으로 구분 (1 ≤ Si ≤ 100,000)
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			for(int i=1; i<=N; i++) {
				num[i] = Integer.parseInt(st.nextToken());
			}
			
			
			// 최대공약수가 1이 되는 개수 구하기
			// dp[i][j] i번째 원소까지 활용한 GCD=j 인 조합의 수			
			// 현재까지 GCD가 x인 조합이 n개 있을때, 
			// A라는 원소를 추가한 경우 GCD(x, A)인 조합이 n개
			// 추가하지 않은 GCD = x 인 조합이 n개 생성
			// 끝으로 원소A가 단독인 GCD=A인 조합(1) 추가
			

			
			System.out.println("#"+t+" "+Sum);
		} // end test case		
	} // end main

	// 에라토네스의 체(소수사전 판별)
	static void checkPrime(int n) {
		isPrime[0] = 0;
		isPrime[1] = 0;
		isPrime[2] = 0;
		
		for(int i=2; i<=N; i++) {
			// 배수판별
			for(int j=i; j<=N; j+=i) {
				isPrime[j] = 1;
			}
		}	
	}
	
	static int GCD(int a, int b) {
		int tmp;
		if(a < b) {
			tmp = a;
			a   = b;
			b   = tmp;
		}
		
		while(b > 0) {
			tmp = a%b;
			a = b;
			b = tmp;						
		}
		return a;
	}	
}
