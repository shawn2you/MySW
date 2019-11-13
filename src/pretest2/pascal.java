package pretest2;

import java.util.Arrays;

public class pascal {

	public static void main(String[] args) {
		
		long[][] pascal = new long[200][200];
		
		pascal[0][0] = 1;
		pascal[1][0] = 1;
		pascal[1][1] = 1;
		// nCr = (n-1)C(r-1) + (n-1)Cr
		for(int i=2; i<200; i++){
			pascal[i][0] = 1;
			
			for(int j=1; j<=i; j++){
				pascal[i][j] = pascal[i-1][j-1] + pascal[i-1][j];
			}
		}
		
		// 출력 확인
//		for(int i=0; i<200; i++){			
//			for(int j=0; j<=i; j++){
//				System.out.print(pascal[i][j] + " ");
//			}
//			System.out.println();
//		}
		
		System.out.println(gcd(4000001, 4000002));
		System.out.println(lcm(100, 40));
		System.out.println(Arrays.toString(prime(4)));
	}
	
	
	// 소인수 분해
	static public int[] prime(long a){
		int k=2; // 1을 제외하고 가장작은 소수
		int[] map = new int[200];
		while(a != 1){
			if(a%k == 0){
				map[k]++;
				a /= k;
			}else{
				k++;
			}
		}
		
		return map;
	}
	
	// 최대공약수
	// 유클리드 알고리즘 : 임의의 두 수에서 큰수에서 작은수를 나눈 나머지를 다시 
	static public long gcd(long a, long b){
		long rest, temp;
		if(a < b){
			temp = a;
			a = b;
			b = temp;
		}
		// 작은값이 0이 될때가지(나누어 떨어질때까지)
		while(b != 0){
			rest = a%b;
			a = b;
			b = rest;
		}
		return a;
	}
	
	
	// 최소공배수
	// 최대공약수 * 최소공배수 = a * b 와 같다는 성질 이용
	static public long lcm(long a, long b){
		return a*b/gcd(a, b);
	}

}
