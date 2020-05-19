package pretest3;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution_E0013 {

	static int T, M, N, Sum;
	
	public static void main(String[] args) throws Exception {
		
		FileInputStream fi = new FileInputStream(new File(Solution_E0013.class.getResource("").getPath() + "Solution_E0013.txt"));
		System.setIn(fi);
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));	
		
		T = Integer.parseInt(br.readLine());
		
		for (int t=1; t<=T; t++) {
			Sum = Integer.MAX_VALUE;
			
			// 나무 N, 구간 M
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			
			// 구간내에 존재하지 않는 나무 높이의 최소값
			for(int m=0; m<M; m++) {
				st = new StringTokenizer(br.readLine());
				int start = Integer.parseInt(st.nextToken());
				int end   = Integer.parseInt(st.nextToken());
				int size  = end - start + 1; // 구간 길이(나무가 0부터 시작시 최대 길이는 size-1)
				Sum = Math.min(Sum, size);
			}
			
			System.out.println("#" + t);
			System.out.println(Sum);
			
			for(int n=0; n<N; n++) {
				if(n == N-1) {
					System.out.println(n%Sum);

				}else {
					System.out.print(n%Sum + " ");
				}
			}
			
		} // end test case		
	} // end main
}
