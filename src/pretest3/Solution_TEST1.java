package pretest3;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;

/*
 * http://182.193.11.65/common/practice/problem/view.do?problemId=AXImkqEQ0mqojUHh&_menuId=AVUU732mAAHBC0c9&_menuF=true
 */
public class Solution_TEST1 {

	static int T, X, MX, N;
	

	public static void main(String[] args) throws Exception {

		FileInputStream fi = new FileInputStream(new File(Solution_TEST1.class.getResource("").getPath() + "Solution_TEST1.txt"));
		System.setIn(fi);

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		T = Integer.parseInt(br.readLine());

		for (int t = 1; t <= T; t++) {
			// 초기화

			// 재료의 개수 N, 구멍 길이 X
			X = Integer.parseInt(br.readLine());
			N = Integer.parseInt(br.readLine()); // 0<=n<=1000000
			MX = X*10000000;
			ArrayList<Integer> al = new ArrayList<>();
			
			
			for (int n = 0; n < N; n++) {
				int in = Integer.parseInt(br.readLine());
				al.add(in);
			}
			
			Collections.sort(al);
			
			int lIdx = 0;
			int hIdx = N -1;
			
			StringBuilder sb = new StringBuilder();
			sb.append("#");
			sb.append(t);
			sb.append(" ");
			
			int isTrue = 0;
			int hSc = 0;
			int lSc = 0;
			
			while(lIdx < hIdx) { // 배열의 앞뒤 값을 불러서 비교한다. n번 수행
				hSc = al.get(hIdx);
				lSc = al.get(lIdx);
				
	             if(hSc + lSc == MX) {
	            	isTrue = 1;
	 				sb.append("yes");
					sb.append(" ");
					sb.append(lSc);
					sb.append(" ");
					sb.append(hSc);	                
	                break;
	             }
	             if(hSc + lSc > MX) hIdx--;
	             if(hSc + lSc < MX) lIdx++;
	         }
			
			if(isTrue == 0) {
				sb.append("danger");
			}
			
			System.out.println(sb.toString());

		} // end test case
	} // end main
}