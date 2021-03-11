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
	static int T, N, S;
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
					return 1;
				}else {
					return -1;
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
			
			S = 2;
			while(S<N) {
				S *= 2;
			}
			
			tree = new int[2*S];
			power = new int[N+1];
			point = new int[N+1];
			build = new BD[N+1];
			
			st1 = new StringTokenizer(br.readLine());
			st2 = new StringTokenizer(br.readLine());
			
			for(int i=1; i<=N; i++) {
				build[i] = new BD(i, Integer.parseInt(st1.nextToken()));				
			}
			
			for(int i=1; i<=N; i++) {
				power[i] =  Integer.parseInt(st2.nextToken());
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
				
				int no = build[i].no;
				int he = build[i].he;
				
				// 건물 찾기
				// 첫번째는 가장 높으므로 무조건 0이다. 
				if(i == 1) {
					point[no] = 0;
					update(no, 1);
				}else {
					int posi = query(1, no-1) + power[no] + 1;
					int sno  = search(posi);
					if(sno == 0) {
						point[no] = 0;
					}else {
						// 해당 위치에 화살이 꽃이는 위치의 건물의 번호를 넣는다. 
						point[no] = sno - S + 1;
					}
					
					update(no, 1);
				}
			}
			
			for(int i=1; i<=N; i++) {
				Sum += point[i];
			}

			System.out.println("#"+t+" " + Sum);
			
		} // end case
	} // end main
	
	static int search(int x) {
		// top-down 방식으로 찾기		
		int start = 1; // root
		// 찾는 위치가 크면 종료
		if(x > tree[start]) return 0;
			
		while(start <= S - 1) {
			if(x > tree[2*start]) {
				// 오른쪽으로 이동
				x -= tree[2*start];
				start = 2*start + 1;
			}else {
				// 왼쪽으로 이동
				start *= 2;
			}			
		}
		return start;
	}
	
	/*
	 * x에서 y까지의 개수 구하기
	 */
	static int query(int x, int y) {
		int sum = 0;
		x = S-1 + x;
		y = S-1 + y;
		while(x <= y) {
			if(x%2 == 1) {
				sum += tree[x];
				x += 1;
			}
			
			if(y%2 == 0) {
				sum += tree[y];
				y -= 1;
			}		
			x = x/2;
			y = y/2;
		}
		return sum;
	}
	
	/*
	 * x번째 값 입력 후 갱신하기
	 */
	static void update(int x, int value) {
		x = S-1 + x;
		tree[x] = value;
		while(x != 0) {
			x = x/2;
		    tree[x] = tree[2*x] + tree[2*x + 1];			
		}
	}

}
