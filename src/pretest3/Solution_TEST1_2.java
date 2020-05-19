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
public class Solution_TEST1_2 {

	static int T, X, MX, N;
	

	public static void main(String[] args) throws Exception {

		FileInputStream fi = new FileInputStream(new File(Solution_TEST1_2.class.getResource("").getPath() + "Solution_TEST1.txt"));
		System.setIn(fi);

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		T = Integer.parseInt(br.readLine());

		for (int t = 1; t <= T; t++) {
			// 초기화

			// 재료의 개수 N, 구멍 길이 X
			X = Integer.parseInt(br.readLine());
			N = Integer.parseInt(br.readLine()); // 0<=n<=1000000
			MX = X*10000000;
			ArrayList<Integer> hal = new ArrayList<>();
			ArrayList<Integer> lal = new ArrayList<>();
			
			for (int n = 0; n < N; n++) {
				int in = Integer.parseInt(br.readLine());
//				System.out.println(in);
				if(in*2 <= MX ) {
					hal.add(in); // 큰값
				}else {
					lal.add(in); // 작은값
				}
			}
			
			Collections.sort(hal);
			Collections.sort(lal);
			
			// 순차적 증가 하면서 비교
			// H[i] + L[j]를 더해서 값이 X가 되면 종료
			int isTrue = 0; // 오류
			int hIdx = hal.size()-1;
			int lIdx = 0;
			
			while(isTrue == 0) {
				int hSc = hal.get(hIdx);
				int lSc = lal.get(lIdx);
				if(lSc + hSc == X) {
					isTrue = 1;
				}				
			}			
			
			StringBuilder sb = new StringBuilder();
			sb.append("#");
			sb.append(t);
			sb.append(" ");
			if(isTrue == 1) {
				sb.append("yes");
				sb.append(" ");
				sb.append(1);
				sb.append(" ");
				sb.append(2);
			}else {
				sb.append("danger");
			}
			
			System.out.println(sb.toString());

		} // end test case
	} // end main
}