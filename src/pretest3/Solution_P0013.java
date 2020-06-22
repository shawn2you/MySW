package pretest3;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * 1일차 2번 문제
 * (중) [기출A-0013] 숫자 배치하기 
 * 알고리즘 : 수열
 */
public class Solution_P0013 {
	
	static int T, M, N, Sum;
	
	static int[][] map = new int[11][11]; // N(2 ≤ N ≤ 10)
	static int[] visited = new int[11];
	static int[] order;
	
	public static void main(String[] args) throws Exception {
		
		FileInputStream fi = new FileInputStream(new File(Solution_P0013.class.getResource("").getPath() + "Solution_P0013.txt"));
		System.setIn(fi);
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
				
		T = Integer.parseInt(br.readLine());
		
		for (int t=1; t<=T; t++) {
			
			// 초기화 
			Sum = 0;

			// 순열 N
			N = Integer.parseInt(br.readLine());
			order = new int[N+1];
			int[] arr = new int[N+1];
			for(int i=1; i<=N; i++) {
				visited[i] = 0; // 초기
				arr[i] = i; // 수열 생성을 위한 값 셋팅
				StringTokenizer st = new StringTokenizer(br.readLine());
				
				for(int j=1; j<=N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
//			find(arr, 1, N);
			find2(arr, 1, N);
//			backtrack(0, N);
			System.out.println("#"+t+" " + Sum);
			
			// dfs 로 구현해보기(backtrack)
			
		} // end test case		
	} // end main
	
	
	static void backtrack(int pos, int depth) {	
		if(pos == depth) {
			int Sum_sub = 0;
			for (int i = 0; i < N; i++) { // 위치
				Sum_sub += map[i+1][order[i]+1];	
//				System.out.print(order[i] + ","); 
			}
//			System.out.println(Sum_sub);
			Sum = Math.max(Sum, Sum_sub);
			return;
		}
		
		for(int i = 0; i < depth; i++) {
			if(visited[i] == 0){
				order[pos] = i;
				visited[i] = 1;
				backtrack(pos + 1, depth);
				visited[i] = 0;
			}		
		}		
	}	
	
	// 순열로직(재귀)
	static void find(int[] arr, int depth, int n) {		
		if(depth == N) {
			// 순열 생성완료
			int Sum_sub = 0;
			for (int i = 1; i <= N; i++) { // 위치
//				if (i == N - 1) System.out.print(arr[i]); 
//				else System.out.print(arr[i] + ","); 
				System.out.print(arr[i] + ","); 
				Sum_sub += map[i][arr[i]];				
			}
			System.out.println();
//			System.out.println(Sum_sub); 
			Sum = Math.max(Sum, Sum_sub);
			return;
		}
		
		for(int i = depth; i <= N; i++) {
			swap(arr, i, depth);
			find(arr, depth+1, n);
			swap(arr, i, depth);
		}		
	}
	
	static void swap(int[] arr, int i, int j) {
		int temp = arr[i];
		arr[i] = arr[j];
		arr[j] = temp;
	}
	
	
	
	
	// 순열 만들기 연습
	static void find2(int[] arr, int start, int depth){
		
		if(N == start) {
//			for(int e : arr){
//				System.out.print(e + ", ");
//			}
//			System.out.println("");
			
			return;
		}
		
		for(int i=start; i<=N; i++){
			swap(arr, i, start);
			find2(arr, start+1, depth);
			swap(arr, i, start);
		}
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}