package pretest4;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/*
 * (중상) [연습P-0012] 파티 참석하기
 * 모의고사 3번째
 * http://182.193.11.65/common/practice/problem/view.do?problemId=AXJTgwDwIbCojUHh&_menuId=AVUU732mAAHBC0c9&_menuF=true
 */
public class Solution_TEST3 {

	static int T, M, N, X, Sum;
	static int INF = Integer.MAX_VALUE;
	
	static int[][] Distance; // 각 정점별 최단 거리
	
	static class Room implements Comparable<Room>{
		int e, t;
		Room(int end, int time){
			this.e = end;
			this.t = time;
		}
		@Override
		public int compareTo(Room o) {
			if(o.t > this.t) {
				return -1;
			}else {
				return 1;
			}
		}
	}
	
	static ArrayList<Room>[] CityList0; // 인접리스트
	static ArrayList<Room>[] CityList1;
	
	public static void main(String[] args) throws Exception {
		
		FileInputStream fi = new FileInputStream(new File(Solution_TEST3.class.getResource("").getPath() + "Solution_TEST3.txt"));
		System.setIn(fi);
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));	
		
		T = Integer.parseInt(br.readLine());
		
		for (int t=1; t<=T; t++) {
			// 초기화 
			Sum = 0;

			// 학생의 수 N, 길의 수 M, 학생의 번호 X (1 ≤ N ≤ 50,000, 1 ≤ M ≤ 500,000)
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			X = Integer.parseInt(st.nextToken());
			
			Distance = new int[2][N + 1]; // [0][] : 첫번째, [1][] : 두번재
			for(int i=0; i<2; i++) {
				Arrays.fill(Distance[i], INF);
			}
			
			CityList0 = new ArrayList[N + 1];
			CityList1 = new ArrayList[N + 1];
			
			for(int i=0; i<=N; i++) {
				CityList0[i] = new ArrayList<>();
				CityList1[i] = new ArrayList<>();
			}
			
			int s, e, tm;
			for(int i=0; i<M; i++) {
				st = new StringTokenizer(br.readLine());
				s  = Integer.parseInt(st.nextToken());
				e  = Integer.parseInt(st.nextToken());
				tm = Integer.parseInt(st.nextToken());
				// 일방통행(원길)
				CityList0[s].add(new Room(e, tm));
				// 반대길
				CityList1[e].add(new Room(s, tm));
			}			
			
			// (단일출발점) X번 방에서 각 방까지의 경로 구하고, 
			// (단일도착점) X번을 출발로 반대로 경로 구성
			// 두번의 다익스트라 수행하여 두개의 경로 합이 가장큰 값이 대상
			Dikstra0();
			Dikstra1();
			
			for(int i=1; i<=N; i++) {
				Sum = Math.max(Sum, Distance[0][i] + Distance[1][i]);
			}
			
			System.out.println("#"+t+" "+ Sum);
		} // end test case		
	} // end main
	
	static void Dikstra0() {
		PriorityQueue<Room> pq = new PriorityQueue<>();
		
		pq.add(new Room(X, 0));
		Distance[0][X] = 0;
		
		Room curr;
		while(!pq.isEmpty()) {
			curr = pq.poll();
			int next = curr.e;
			for(int i=0; i<CityList0[next].size(); i++) {
				int nextCity = CityList0[next].get(i).e;
				int nextTime = CityList0[next].get(i).t;
				
				if(Distance[0][nextCity] > curr.t + nextTime) {
					Distance[0][nextCity] = curr.t + nextTime;
					pq.add(new Room(nextCity, Distance[0][nextCity]));
				}				
			}			
		}
	}
	
	static void Dikstra1() {
		PriorityQueue<Room> pq = new PriorityQueue<>();
		
		pq.add(new Room(X, 0));
		Distance[1][X] = 0;
		
		Room curr;
		while(!pq.isEmpty()) {
			curr = pq.poll();
			int next = curr.e;
			for(int i=0; i<CityList1[next].size(); i++) {
				int nextCity = CityList1[next].get(i).e;
				int nextTime = CityList1[next].get(i).t;
				
				if(Distance[1][nextCity] > curr.t + nextTime) {
					Distance[1][nextCity] = curr.t + nextTime;
					pq.add(new Room(nextCity, Distance[1][nextCity]));
				}				
			}			
		}
	}

}
