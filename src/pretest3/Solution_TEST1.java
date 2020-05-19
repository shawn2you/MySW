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
			
			
			int idx = 0;
			for (int n = 0; n < N; n++) {
				int in = Integer.parseInt(br.readLine());
//				System.out.println(in);
				if(in < MX ) {
					al.add(in);
					idx++;
				}
			}
			
			Collections.sort(al);
			
			// L1-L2가 가장 큰 것(최대값에서 최소값 선택한 경우)
			int L2=0, L1=0;
			int isTrue = 0;
			for (int n = idx-1; n >= 0; n--) {
//				System.out.println(al.get(n));
				if(isTrue == 1) break;

				L2 = al.get(n); // 최대값에서 순차적으로 선택
				if(L2*2 < MX) {
					break; // 최대값이 X/2 보다 작으면 진행 불필요
				}
				for (int i = 0; i < idx; i++) {
					L1 = al.get(i);
					if(L2 + L1 == MX) { // 구멍이 일치할 경우 종료
						isTrue = 1;
//						System.out.println(L1);
//						System.out.println(L2);
						break;
					}
					if(L2 + L1 > MX) {
//						isTrue = 0;
						break;
					}
//					else {
//						isTrue = 0;
//					}
				}
			}
			StringBuilder sb = new StringBuilder();
			sb.append("#");
			sb.append(t);
			sb.append(" ");
			if(isTrue == 1) {
				sb.append("yes");
				sb.append(" ");
				sb.append(L1);
				sb.append(" ");
				sb.append(L2);
			}else {
				sb.append("danger");
			}
			
			System.out.println(sb.toString());

		} // end test case
	} // end main
}