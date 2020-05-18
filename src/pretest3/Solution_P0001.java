package pretest3;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/*
 * (중상) [연습P-0001] 큰수만들기
 */
public class Solution_P0001 {

static int T, K, N, Sum;
	
	public static void main(String[] args) throws Exception {
		
		FileInputStream fi = new FileInputStream(new File(Solution_P0001.class.getResource("").getPath() + "Solution_P0001.txt"));
		System.setIn(fi);
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));	
		
		T = Integer.parseInt(br.readLine());
		
		for (int t=1; t<=T; t++) {
			// 초기화 
			Sum = 0;

			// N자리 숫자에서 K개를 지운다. 
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			N = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());

			// N자리 숫자에서 N-K 자리를 최대값 순서로 넣는다.
			String num = br.readLine();
			int[] arr = new int[N];
			for(int i = 0; i< N; i++) {
				arr[i] = Integer.parseInt(num.substring(i, i+1));				
			}
			
			Arrays.sort(arr);
			
			StringBuilder sb = new StringBuilder();
			
			for(int i = N-1; i >= N-K; i--) {
//				System.out.print(arr[i]);
				sb.append(arr[i]);
			}
			
			System.out.println("#" +  t + " " + sb.toString());
			
			
		} // end test case		
	} // end main

}
