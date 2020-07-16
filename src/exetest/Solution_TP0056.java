package exetest;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Solution_TP0056 {

	static int T, M, N, Sum;
	static int[] loc;
	static ArrayList<Integer> ans = new ArrayList<>();
	
	public static void main(String[] args) throws Exception {
		
		FileInputStream fi = new FileInputStream(new File(Solution_TP0056.class.getResource("").getPath() + "Solution_TP0056.txt"));
		System.setIn(fi);
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));	
		
		T = Integer.parseInt(br.readLine());
		StringTokenizer st;
		for (int t=1; t<=T; t++) {
			// 초기화 
			Sum = 0;
			
			// 수열의 크기 N
			N = Integer.parseInt(br.readLine());
			loc = new int[N + 1];
			
			st = new StringTokenizer(br.readLine());
			int size = 0, pos = 0;
            for(int i = 0; i < N; i++) {
            	int num = Integer.parseInt(st.nextToken());
            	
            	pos = binarySearch(loc, 0, size, num);
            	loc[pos] = num;

            	if(pos == size ) {
            		size++;
            	}
            }
			
			System.out.println("#"+t+" "+size);
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
			
			if(midVal > key) {
				low = mid + 1; // 오른쪽으로 탐색하러 간다.(작은값이 있는 쪽)
			}else {
				high = mid - 1;
			}
			
				
//			if(midVal < key) { // 탐색값(key)s과 비교
//				// key 값이 크으면 (왼쪽/high으로 탐색이동)
//				low = mid + 1;
//			}else {
//				// key 값이 작으면 (왼쪽/low으로 탐색이동)
//				high = mid - 1;
//			}
		}
		// while문을 나오는 시점을 기준으로 값이 반환되므로
		// low를 반환시 나보다 한칸 오른쪽 값
		// high를 반환시 나보다 한칸 왼쪽 값
		return low;
	}
}
