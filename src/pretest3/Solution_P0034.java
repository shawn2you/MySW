package pretest3;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/*
 * (중상) [교육P-0034] 당황한 암호학자
 */
public class Solution_P0034 {

	static int T, L, pcnt;
	static String K;
	static boolean[] isPrime = new boolean[1000001]; // 소수
	static int[] prime = new int[1000001]; // 소수
	
	public static void main(String[] args) throws Exception {
		
		FileInputStream fi = new FileInputStream(new File(Solution_P0034.class.getResource("").getPath() + "Solution_P0034.txt"));
		System.setIn(fi);
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));	
		
		T = Integer.parseInt(br.readLine());
		
		
		StringTokenizer st;
		
		for (int t=1; t<=T; t++) {
			st = new StringTokenizer(br.readLine());
			
			K = st.nextToken();
			L = Integer.parseInt(st.nextToken());
			int q = 0;
			
			eratos(L); // L보다 작은 암호(소수) 찾기
			
			for(int i=0; i<pcnt; i++) {
				if(div(prime[i])) {
					q = prime[i];
					break;
				}
			}
			
			if(q>0) {
				System.out.println("#" + t + " BAD " + q);
			}else {
				System.out.println("#" + t + " GOOD");
			}

		}		
	}
	// 큰수처리를 위해 String 분할 처리
	static boolean div(int num) {
		int d = 0;
		int len = K.length();
		
		for(int i=0; i<len; i++) {
			// 각 자리수를 증가하면서 소수로 나눠본다.
			// 소수 2로 나눌때 143  : 100자리 1은 나머지가 1이므로 넘긴다. 
			//                        10자리는 100자리 남은수를 넘겨서 14은 나머지가 0이므로 제거
			//                        1자리 3은 나머지가 1이므로 나누어지지 않는다.
			d = d*10 + K.charAt(i) - '0'; // 숫자문자의 경우 'N' - '0'
			if(d >= num) {
				d %= num;
			}
		}
		return d == 0;
	}
	
	public static void eratos(int n){
		Arrays.fill(isPrime, true); // 초기값셋팅(모두 소수 처리)
		isPrime[0] = false;
		isPrime[1] = false;
		pcnt = 0;
		for(int i=2; i<n; i++){ // 자기 자신을 제외 
//		for(int i=2; i<=n; i++){
			if(isPrime[i]){
				prime[pcnt] = i;
				pcnt++; // 소수판별시 해당 소수(만)를 순서대로 담아놓는다.

				// 배수는 모두 소수가 아니다. 
				for(int j=i*2; j<=n; j+=i){
					isPrime[j] = false;
				}
			}
		}
	}
	
	public static void eratosOrg(int n){
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
