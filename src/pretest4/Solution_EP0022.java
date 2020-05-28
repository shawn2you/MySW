package pretest4;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * (중상) [연습P-0022] LIS 2
 */
public class Solution_EP0022 {

	static int T, M, N, Sum;
	static int[] D;   // DP[i] : i번째까지의 최대 부분수열 사이즈
	static int[] A;   // 입력받은 값
	static int[] LIS; // 증가수열
	
	public static void main(String[] args) throws Exception {
		
		FileInputStream fi = new FileInputStream(new File(Solution_EP0022.class.getResource("").getPath() + "Solution_EP0022.txt"));
		System.setIn(fi);
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));	
		
		T = Integer.parseInt(br.readLine());
		
		for (int t=1; t<=T; t++) {
			// 초기화 
			Sum = 0;

			// 수열의 길이 N (1 ≤ N ≤ 300,000)
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			
			A = new int[N + 1];
			D = new int[N + 1];
			LIS = new int[N + 1];
			
			// 32비트 정수형 이내의 숫자(1부터 N까지 순서대로)
			st = new StringTokenizer(br.readLine());
			for(int i=1; i<=N; i++) {
				A[i] = Integer.parseInt(st.nextToken());
			}
			
			// D[i] : i를 포함한 LIS의 길이
			// 증가수열 인 경우 가장 오른쪽에 해당 수열 삽입
			// 증가수열이 아닌 경우 나보다 한칸 큰 위치에 값을 교체
			int len = 1; // LIS의 길이
			D[1] = len;
			LIS[len] = A[1];
			
			for(int i=2; i<=N; i++) {
				// 증가수열 인 경우 가장 오른쪽에 해당 수열 삽입
				if(LIS[len] < A[i]) {
					len++;
					D[i] = len;
					LIS[len] = A[i];
				}else {
					// 증가수열이 아닌 경우 나보다 한칸 큰 위치에 값을 교체
					int idx = binarySearch(LIS, 1, len + 1, A[i]);
					System.out.println(idx + ", " + A[i]);
					D[i] = idx;
					LIS[idx] = A[i];
				}
			}
			
			System.out.println("#"+t+" " + len);
			
		} // end test case		
	} // end main

	static int binarySearch(int[] a, int leftIdex, int rightIdex, int key) {
		int low = leftIdex;
		int high = rightIdex;
		
		int mid, midVal;
		while(low <= high) {
			mid = (high + low)/2; // 중간위치
			midVal = a[mid];
			
			if(midVal == key) return mid;
				
			if(midVal < key) { // 탐색값(key)s과 비교
				// key 값이 크으면 (오른쪽/high으로 탐색이동)
				low = mid + 1;
			}else {
				// key 값이 작으면 (왼쪽/low으로 탐색이동)
				high = mid - 1;
			}
		}
		// while문을 나오는 시점을 기준으로 값이 반환되므로
		// low를 반환시 나보다 한칸 오른쪽 값
		// high를 반환시 나보다 한칸 왼쪽 값
		return low;
	}
}
