package pretest3;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

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
				if(in*2 > MX ) {
					hal.add(in); // 큰값
				}else if(in*2 < MX ) {
					lal.add(in); // 작은값
				}else {
					hal.add(in); // 큰값
					lal.add(in); // 작은값
				}
			}
			
			Collections.sort(hal, new Comparator<Integer>() {
				@Override
				public int compare(Integer o1, Integer o2) {
					if(o1 - o2 > 0) return -1;
					else if(o1 - o2 < 0) return 1;
					else return 0;
				}
			});
			Collections.sort(lal);
			
			// 순차적 증가 하면서 비교
			// H[i] + L[j]를 더해서 값이 X가 되면 종료
			int isTrue = 0; // 오류
			int hIdx = 0;
			int lIdx = 0;
			int hSc = 0;
			int lSc = 0;
			while(true) {
				if(lIdx == lal.size() || hIdx == hal.size()) break;
				
				hSc = hal.get(hIdx);
				lSc = lal.get(lIdx);
				
				if(lSc + hSc == MX) {
					isTrue = 1;
					break;
				}
				
				// 두값의 합이 작으면 작은값을 증가 시킨다. 
				if(hSc + lSc < MX && lIdx < lal.size()) lIdx++;
				// 두값의 합이 크면 큰값을 증가 시킨다.
				if(hSc + lSc > MX && hIdx < hal.size()) hIdx++;
								
			}
			
			StringBuilder sb = new StringBuilder();
			sb.append("#");
			sb.append(t);
			sb.append(" ");
			if(isTrue == 1) {
				sb.append("yes");
				sb.append(" ");
				sb.append(lSc);
				sb.append(" ");
				sb.append(hSc);
			}else {
				sb.append("danger");
			}
			
			System.out.println(sb.toString());

		} // end test case
	} // end main
}