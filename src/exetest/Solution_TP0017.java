package exetest;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

/*
 * (중상) [기출P-0017] 직사각형 개수세기 
 */
public class Solution_TP0017 {

	static int T, M, N;
	static long Sum;
	
	static int[][] map;
	static int[][] Ht;
	static long[][] Dc;
	
	public static void main(String[] args) throws Exception {
		
		FileInputStream fi = new FileInputStream(new File(Solution_TP0017.class.getResource("").getPath() + "Solution_TP0017.txt"));
		System.setIn(fi);
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));	
		
		T = Integer.parseInt(br.readLine());
		
		for (int t=1; t<=T; t++) {
			// 초기화 
			Sum = 0;

			// 한변의 길이 N
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			
			map = new int[N+1][M+1];
			Ht = new int[N+1][M+1];
			Dc = new long[N+1][M+1];
			
			String line  = null;
			for(int i=1; i<=N; i++) {
//				st = new StringTokenizer(br.readLine());
				line = br.readLine();
//			    System.out.println((int)line.charAt(1) - 48);
				for(int j=1; j<=M; j++) {
					map[i][j] = (int)line.charAt(j-1) - 48;
//					// 해당 위취의 높이를 구한다. 
//					if(map[i][j] == 1) {
//						Ht[i][j] = Ht[i-1][j] + 1;
//					}else {
//						Ht[i][j] = 0;
//					}					 
				}
			}
			// 강사 설명 기준
	        Deque<Integer> list = new ArrayDeque<>(); // stack
			int temps=0, temp;
			for(int i=0; i<=N; i++) {
				for(int j=0; j<=M; j++) {
					if(map[i][j] == 0) {
						Dc[i][j] = 0;
						list.clear();
						temps = j + 1;
						Ht[i][j] = 0;
					}else {
						Ht[i][j] = Ht[i-1][j] + 1;
						temp = -1;
						
						while(!list.isEmpty() && Ht[i][list.peek()] > Ht[i][j]) {
							temp = list.pop();
						}
						
						if(list.isEmpty()) {
							if(temp == -1) {
								Dc[i][j] = Ht[i][j];
							}else {
								Dc[i][j] = (j - temps + 1)*Ht[i][j];
							}
						}else {
							Dc[i][j] = Dc[i][list.peek()] + (j - list.peek() -1)*Ht[i][j] + Ht[i][j];
						}
						list.push(j);
						Sum = Sum + Dc[i][j];
					}
				}				
			}	
			
			
			
			// 직사각형 개수(넓이) 구하기 (값이 안나옴)
//			for(int i=1; i<=N; i++) {
//				for(int j=1; j<=M; j++) {
//					calcCnt(i, j);
//				}				
//			}		
			
			
			System.out.println("#"+t+" "+Sum);
		} // end test case		
	} // end main

	static void calcCnt(int i, int j) {
		// Dc[i][j] : i,j 위치까지의 넚이
		int cnt = 0;
		if(Ht[i][j] < Ht[i][j-1]) {
			// 이전 나랑 같거나 작은 높이 찾기
			for(int k=j-1; k>0; k--) {
				cnt++;
				if(Ht[i][j] >=  Ht[i][k]) {					
					break;
				}
			}
			if(cnt == 0) {
				// 나보다 같거나 작은 높이가 없는 경우
				Dc[i][j] = Ht[i][j]*j;
			}else {
				// 나보다 같거나 작은 높이가 있는 경우
				Dc[i][j] = Dc[i][j-cnt] + cnt*Ht[i][j];
			}
		}else {
			Dc[i][j] = map[i][j]*Ht[i][j] + (j-1)*Ht[i][j-1];
		}
		Sum = Sum + Dc[i][j];
	}	
}