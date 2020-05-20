package pretest3;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution_P0019 {

	static int T, Q, N;
	static long Sum;
	static long[] tree;

	public static void main(String[] args) throws Exception {

		FileInputStream fi = new FileInputStream(
				new File(Solution_P0019.class.getResource("").getPath() + "Solution_P0019.txt"));
		System.setIn(fi);

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		T = Integer.parseInt(br.readLine());

		for (int t = 1; t <= T; t++) {
			// 초기화
			Sum = 0;

			// 정수의 개수 N
			N = Integer.parseInt(br.readLine());
			// 질의 개수 Q
			Q = Integer.parseInt(br.readLine());

			// 포화이진트리로 구간 합을 구성해서 처리 필요
			// 이진트리 구성을 위한 배열 정의
			int S = 2; // root 2(0), 1자리 2(1)으로 시작점을 첫번째부터 처리
			while (S < N) { // 배열의 시작시점 계산
				S *= 2;
			}
			// 시작점보다 2배 크게 배열 정의해야 모두 담을 수 있음
			tree = new long[S * 2];
			// Tree에 담기
			for (int i = S; i <= S + N -1; i++) {
				tree[i] = i - S + 1; // 1부터 ~ N까지 담기
			}

			// 구간합 계산을 위해 부모에 담기
			for (int i = S - 1; i >= 1; i--) {
				tree[i] = tree[i * 2] + tree[i * 2 + 1]; // 좌, 우 자식도의 합을 셋팅
			}
			StringTokenizer st;
			for (int i = 0; i < Q; i++) {
				st = new StringTokenizer(br.readLine());
				int q = Integer.parseInt(st.nextToken());
				int x = Integer.parseInt(st.nextToken());
				int y = Integer.parseInt(st.nextToken());

				if (q == 1) {
					// 1 x y : x번째 수부터 y번째 수까지의 합을 구한다. (1 ≤ x ≤ y ≤ N)
					// x번째 : x + S - 1 번째
					int l = x + S - 1;
					int r = y + S - 1;
					
					// 부모까지 계속 탐색하면서 연산한다. 
					while(l <= r) {

						// 내가 완전트리에 포함되면 부모로 이동 아니면 계산하고 다음으로 이동
						if(l%2 == 1) {
							Sum += tree[l];
							l++;
						}						
						if(r%2 == 0) {
							Sum += tree[r];
							r--;
						}
						l /= 2; // 부모로 이동 
						r /= 2; // 부모로 이동 
					}
					Sum %= 1000000007;
					
				} else {
					// 0 x y : x번째 수를 y로 변경한다. (1 ≤ x ≤ N, -100,000 ≤ y ≤ 100,000)
					// x번째 : x + S - 1 번째
					int tmp = x + S - 1;
					long gap = tree[tmp] - y; // 부모노드에 gap만큼만 빼주면 된다.
					//tree[tmp] = y;
					
					while (tmp >= 1) {
						
						tree[tmp] -= gap;
						tmp = tmp / 2;
					}
				}
			}
			Sum %= 1000000007;
			if (Sum > 0) {
				System.out.println("#"+t+" "+ Sum);
			} else {
				System.out.println("#"+t+" "+(Sum + 1000000007));
			}

		} // end test case
	} // end main

}
