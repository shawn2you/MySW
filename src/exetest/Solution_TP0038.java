package exetest;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution_TP0038 {

	static int T, Q, N, Sum;
	static long[] tree;
	
	public static void main(String[] args) throws Exception {
		
		FileInputStream fi = new FileInputStream(new File(Solution_TP0038.class.getResource("").getPath() + "Solution_TP0038.txt"));
		System.setIn(fi);
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));	
		
		T = Integer.parseInt(br.readLine());
		
		for (int t=1; t<=T; t++) {
			// 초기화 
			Sum = 0;

			//  사람의 수 N 은 1 이상 300,000 이하의 자연수, 질문의 수 Q 는  1 이상 300,000 이하의 자연수
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			Q = Integer.parseInt(st.nextToken());
			
			
			int S = 2;
			while(S < N) {
				S *= 2;
			}
			tree = new long[S*2 + 1];
			
			st = new StringTokenizer(br.readLine());
			for(int i=0; i<N; i++) {
				tree[S + i] = Integer.parseInt(st.nextToken());
			}
			
			// 부모 트리 초기화

			
			for(int i=0; i<Q; i++) {
				st = new StringTokenizer(br.readLine());
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
				int x = Integer.parseInt(st.nextToken());
				
				for(int k=0; k<S; i++) {
					tree[k] = 0;
				}
				
				// 찾기
			}
			
			System.out.println("#"+t+" "+Sum);
		} // end test case		
	} // end main

	static void find(int a, int b, int x) {
		
	}
}
