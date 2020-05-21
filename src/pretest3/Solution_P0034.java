package pretest3;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Solution_P0034 {

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
		
		eratos(100000);
		
		for (int t=1; t<=T; t++) {

		}		
	}
	
	
	public static void eratos(int n){
		Arrays.fill(isPrime, true); // 초기값셋팅(모두 소수 처리)
		isPrime[0] = false;
		isPrime[1] = false;
		
		for(int i=2; i<=n; i++){
			if(isPrime[i]){
				// 배수는 모두 소수가 아니다. 
				for(int j=i*2; j<=n; j+=i){
					isPrime[j] = false;
				}
			}
		}
	}
}
