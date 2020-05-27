package pretest4;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/*
 * (중상) [교육P-0007] 군사 도로망 
 * 기건설된 도로는 음수처리 하는 아이디어 필요
 */
public class Solution_EP0007 {

	static int T, M, N, K, Sum;
	
	static class City implements Comparable<City>{
		int x, y, c;
		City(int x, int y, int c){
			this.x = x;
			this.y = y;
			this.c = c;
		}
		@Override
		public int compareTo(City o) {
			if(o.c >= this.c) {
				return -1;
			}else {
				return 1;
			}
		}
	}
	
	static int[] parent = new int[100001];
//	static City[] road;
    static ArrayList<City> road = new ArrayList<City>();
    static ArrayList<City>[] roadList = new ArrayList[100001];


	
	public static void main(String[] args) throws Exception {
		
		FileInputStream fi = new FileInputStream(new File(Solution_EP0007.class.getResource("").getPath() + "Solution_EP0007.txt"));
		System.setIn(fi);
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));	
		
		T = Integer.parseInt(br.readLine());
		
		for (int t=1; t<=T; t++) {
			// 초기화 
			Sum = 0;

			// 첫 수는 N(1≤N≤100,000), 둘째 수는 M(1≤M≤250,000), 셋째 수는 K(1≤K≤250,000)이다.
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken()); // 기존 도로
			K = Integer.parseInt(st.nextToken());// 신규 도로
			
			for(int i=0; i<=N; i++) {
				parent[i] = i;
				roadList[i] = new ArrayList<City>();
			}
			
			int x, y, c;
			for(int i=0; i<M+K; i++) {
				st = new StringTokenizer(br.readLine());
				x = Integer.parseInt(st.nextToken());
				y = Integer.parseInt(st.nextToken());
				c = Integer.parseInt(st.nextToken());
				
				if(i<M) {
					// 간선 List 형태(간선수 만큼 담는다)
					road.add(new City(x, y, -c));
					roadList[i].add(new City(x, y, -c));
				}else {
					road.add(new City(x, y, c));
					roadList[i].add(new City(x, y, c));
				}
			}
			// 음수(건살한), 양수(건설할) 순으로 정렬
			Collections.sort(road, new Comparator<City>() {
				@Override
				public int compare(City o1, City o2) {
					// TODO Auto-generated method stub
					return o1.c - o2.c;
				}
			});
			
			PriorityQueue<Integer> pq = new PriorityQueue<>();
			
			
		} // end test case		
	} // end main
	
	static int find(int a) {
		if(a == parent[a]) return a;
		else return parent[a] = find(parent[a]);
	}
	
	static void union(int a, int b) {
		int ra = find(a);
		int rb = find(b);
		if(ra == rb) return;
		else parent[rb] = ra;
	}
}
