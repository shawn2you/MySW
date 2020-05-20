package pretest3;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Solution_P0041 {

	static int T, M, N, Sum;
	static int[] C;
	static int[] tree;
	static PriorityQueue<Integer> pq = new PriorityQueue<Integer>();
	
	public static void main(String[] args) throws Exception {
		
		FileInputStream fi = new FileInputStream(new File(Solution_P0041.class.getResource("").getPath() + "Solution_P0041.txt"));
		System.setIn(fi);
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));	
		
		T = Integer.parseInt(br.readLine());
		
		for (int t=1; t<=T; t++) {
			// 초기화 
			Sum = 0;

			// 질의 수 N
			N = Integer.parseInt(br.readLine());
			
			int S = 2;
			while(S < 300001){
				S *= 2;
			}			
			// 시작점보다 2배 크게 배열 정의해야 모두 담을 수 있음
			tree = new int[S * 2];
			C = new int[N];
			
			StringTokenizer st;
			for(int i=0; i<N; i++){
				st = new StringTokenizer(br.readLine());
				int q = Integer.parseInt(st.nextToken());
				int k = Integer.parseInt(st.nextToken());
				
				if(q == 1){
					pq.add(k);
				}else{
					// k번째 순서 값꺼내기
				}
				
			}
			
		} // end test case		
	} // end main

}
