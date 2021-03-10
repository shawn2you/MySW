package pretest7;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

/*
 * (중상) [기출P-0088] 화살
 * 알고리즘 : Indexed tree
 */
public class Solution_TP0008 {
	static int T, N;
	static long Sum;
	
	static int[] tree;
	static int[] power;
	static int[] point;
	
	public static class BD implements Comparable<BD>{
		int no;
		int he;
		BD(int x, int y){
			this.no = x;
			this.he = y;
		}
		@Override
		public int compareTo(BD o) {
			if(this.he == o.he) {
				if(this.no > o.no) {
					return -1;
				}else {
					return 1;
				}				
			}else if(this.he > o.he) {
				return -1;
			}else{
				return 1;
			}
		}
	}
	
	static BD[] build; 
	
	public static void main(String[] args) throws Exception {
		
		FileInputStream fi = new FileInputStream(new File(Solution_TP0008.class.getResource("").getPath() + "Solution_TP0008"));
		System.setIn(fi);
			
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));	
		
		T = Integer.parseInt(br.readLine());
		StringTokenizer st1, st2;
		
		for (int t=1; t<=T; t++) {
			Sum = 0;
			N = Integer.parseInt(br.readLine());
			
			int S = 2;
			while(S<N) {
				S = 2*S;
			}
			
			tree = new int[S+1];
			power = new int[N+1];
			point = new int[N+1];
			build = new BD[N+1];
			
			st1 = new StringTokenizer(br.readLine());
			st2 = new StringTokenizer(br.readLine());
			
			for(int i=1; i<=N; i++) {
				build[i] = new BD(i, Integer.parseInt(st1.nextToken()));				
			}
			
//			for(int i=1; i<=N; i++) {
//				System.out.print(build[i].no + " : ");
//				System.out.println(build[i].he);				
//			}
						
			Arrays.sort(build, 1, N+1);
			
			for(int i=1; i<=N; i++) {
//				System.out.print(build[i].no + " : ");
//				System.out.println(build[i].he);				
				// 높은 빌딩 순서로 탐색하면서 전체 통과점수를 계산한다. 
				// 탐색빌딩 기준으로 ( 낮은 건물 수 + 힘 + 1(자기자신))로 위치를 계산하면 점수가 되는 빌딩이 된다.
				// 낮은 건물수를 검색은 높은 건물기준으로 정렬하였고, 해당 정렬기준으로 indexed tree에 셋팅하면, 
				// 1번 건물에서 찾는 위치이전까지의 수를 구간합으로 구한다. 
			}
			
			for(int i=0; i<N; i++) {
				power[i] =  Integer.parseInt(st2.nextToken());
			}
			
			System.out.println("#"+t+" " + Sum);
			
		} // end case
	}

}
