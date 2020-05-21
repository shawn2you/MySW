package pretest3;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution_P0005 {

	static int T, X, Y, Z, Sum;
	
	public static void main(String[] args) throws Exception {
		
		FileInputStream fi = new FileInputStream(new File(Solution_P0005.class.getResource("").getPath() + "Solution_P0005.txt"));
		System.setIn(fi);
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));	
		
		T = Integer.parseInt(br.readLine());
		
		for (int t=1; t<=T; t++) {
			// 초기화 
			Sum = 0;

			// 한변의 길이 N
			StringTokenizer st = new StringTokenizer(br.readLine());
			X = Integer.parseInt(st.nextToken());
			Y = Integer.parseInt(st.nextToken());
			Z = Integer.parseInt(st.nextToken());
			
			// 11X + 19Y = 1
			int gcd = gcd(X, Y);
			// 11, 19가 Z의 배수가 되어야 값이 나올수 있다
			// 배수가 된다는것은 최대공약수가 1이 아닌 수가 있다는 의미(서로소가 아니다)
			if(Z !=1 && gcd(Z, gcd) != 1) {
				System.out.print(-1);
			}
			
		} // end test case		
	} // end main
	
	static int gcd(int a, int b) {
		int r, t;
		if(a < b) {
			t = a;
			a = b;
			b = t;
		}
		
		while(b != 0) { // b가 0이 되는 순간 a가 최대 공약수
			r = a%b;
			a = b;
			b = r;
		}
		
		return a;
	}
	
	static int gcdr(int a, int b) {
		return b == 0 ? a : gcdr(b, a%b);
	}

}
