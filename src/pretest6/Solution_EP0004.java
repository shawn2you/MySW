package pretest6;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;

/*
 * (중상) [연습P-0004] 중앙값 
 */
public class Solution_EP0004 {

	static int T, M, N, Sum;
	static int I7 = 1000000007;
	
	public static void main(String[] args) throws Exception {
		
		FileInputStream fi = new FileInputStream(new File(Solution_EP0004.class.getResource("").getPath() + "Solution_EP0004"));
		System.setIn(fi);
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));	
		
		T = Integer.parseInt(br.readLine());
		
		for (int t=1; t<=T; t++) {
			// 초기화 
			Sum = 0;

			// 중앙값 포함하여 작은값
			PriorityQueue<Integer> pq1 = new PriorityQueue<>(new Comparator<Integer>() {
				@Override
				public int compare(Integer o1, Integer o2) {
					
					return (o2 - o1);
				}
			});			
			
			PriorityQueue<Integer> pq2 = new PriorityQueue<>(); // 중앙값보다 큰 값
			
//			pq1.add(1);
//			pq1.add(3);
//			pq1.add(8);
//			pq1.add(4);
//			while(!pq1.isEmpty()){
//				System.out.println(pq1.poll());		
//			}
			
			// binary search 로 삽입시 정렬
			// 삽입이 편해야 함 (linkedlist > Arralylist > int)
			// priority queue 를 활용하여 정렬/삽입
			
			
			// 정수의 개수 N이 주어진다. (1 ≤ N ≤ 99,999, N은 홀수)
			N = Integer.parseInt(br.readLine());
			
			long midValue = 1000000001;
			int num;
			for(int i=1; i<=N; i++) {
				num = Integer.parseInt(br.readLine());
				if(midValue >= num ) {
					pq1.add(num);
				}else {
					pq2.add(num);
				}
				
				// pq1, 2 사이즈를 비교하여 최대, 최소값 이관(항상 pq1이 pq2보다 1 크거나 같아야 함)
//				while(pq1.size() - pq2.size() > 1) {
				if(pq1.size() - pq2.size() > 1) {
					pq2.add(pq1.poll());
				}else if(pq1.size() - pq2.size() < 0) {
					pq1.add(pq2.poll());
				}
				midValue = pq1.peek(); // 중앙값
				
				// 홀수일때만 계산
				if(i%2 == 1) {
//					System.out.println(midValue);
					Sum += midValue%I7;
				}
			}
			

			
			System.out.println("#"+t+" "+Sum);
		} // end test case		
	} // end main
}
