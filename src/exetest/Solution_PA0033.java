package exetest;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution_PA0033 {

	static int T, M, N, K, Sum;
	
	static int[] eng = new int[5]; // 기술자
	static int[][] engCnt; // 각 시간대별로 엔지니어가 처리할 수 있는 부품 수
	static int[][] item;
	
	static int[][] D;
	
	
	
	public static void main(String[] args) throws Exception {
		
		FileInputStream fi = new FileInputStream(new File(Solution_PA0033.class.getResource("").getPath() + "Solution_PA0033.txt"));
		System.setIn(fi);
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));	
		
		T = Integer.parseInt(br.readLine());
		
		for (int t=1; t<=T; t++) {
			// 초기화 
			Sum = 0;

			// 부품의 수 N, 전체 라인의 수 M, 투입되는 엔지니어의 수 K
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			
/*
[제한사항]
 
1. N 은 1 이상 5000 이하의 자연수, M 은 1 이상 100 이하의 자연수이다. 
2. K 는 1 이상 5 이하의 자연수이다. 
3. K 명의 엔지니어를 모두 투입 시킬 필요는 없다. 
4. K 명의 엔지니어 중 서로 다른 두 담당자의 담당 부품이 동일 할 수도 있다.

 */
//			st = new StringTokenizer(br.readLine());
			String temp = br.readLine();
			for(int i=1; i<=K; i++) {
				eng[i] = (int)(temp.charAt(i-1) - 64);  // A -> 1
			}
			
			engCnt = new int[N+1][26]; // 각 시간대별로 엔지니어가 처리할 수 있는 부품 수
			for(int i=0; i<M; i++) {
				temp = br.readLine();
				for(int k=1; k<=N; k++) {
					int engI = (int)(temp.charAt(k-1) - 64);
					engCnt[k][engI] += 1;
				}
			}
			
			// K 엔지니어라 최대 5명이기때문에 5명의 엔지니어 투입 경우의 수별로 진행
			// D[i][j] : i번째(경우의 수) 엔지니어가 출근해있고, j번째 시간의 부품까지 만들었을때 최대 부품 수
			reOrder(eng, 1, K);
			
			System.out.println("#"+t+" "+Sum);
		} // end test case		
	} // end main

	static void reOrder(int[] arr, int depth, int n) {
		if(depth == K) {
			for (int i = 1; i <= K; i++) { // 위치
				System.out.print(arr[i] + ",");
				// 로직 구현 필요
				for (int j = 1; j <= N; j++) {
//					D[i][j] = engCnt[i][j-1] + engCnt[]
				}
					
				
			}
			System.out.println();
			
			
			return;
		}
		
		
		for(int i = depth; i <= K; i++) {
			swap(arr, i, depth);
			reOrder(arr, depth+1, n);
			swap(arr, i, depth);
		}		
	}
	
	static void swap(int[] arr, int x, int y) {
		int temp = arr[x];
		arr[x] = arr[y];
		arr[y] = temp;
	}
}
