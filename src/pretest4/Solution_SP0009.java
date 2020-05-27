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
 * (중상) [교육P-0009] 최단 경로 
 * 다익스트라
 */
public class Solution_SP0009 {

	static int T, M, N, Sum;
	static int inf = Integer.MAX_VALUE;
	
	static class City implements Comparable<City>{
		int e, t;
		City(int end, int time){			
			this.e = end;
			this.t = time;
		}
		@Override
		public int compareTo(City o) {
			if(o.t > this.t) {
				return -1;
			}else {
				return 1;
			}
		}		
	}
	
	static ArrayList<City>[] CityList;
	static int[] Distance;
	
	public static void main(String[] args) throws Exception {
		
		FileInputStream fi = new FileInputStream(new File(Solution_SP0009.class.getResource("").getPath() + "Solution_SP0009.txt"));
		System.setIn(fi);
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));	
		
		T = Integer.parseInt(br.readLine());
		
		for (int t=1; t<=T; t++) {
			// 초기화 
			Sum = 0;

			// 도시의 수 N과 도로의 수 M (1 ≤ N ≤ 100,000, 1 ≤ M ≤ 300,000)
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			
			Distance = new int[N + 1];
			Arrays.fill(Distance, inf);
			
			CityList = new ArrayList[N + 1];
			for(int i=0; i<=N; i++) {
				CityList[i] = new ArrayList<>();
			}
			int a, b, c; // (1 ≤ a, b ≤ N, 1 ≤ c ≤ 10,000, a ≠ b)
			for(int i=0; i< M; i++) {
				st = new StringTokenizer(br.readLine());
				a = Integer.parseInt(st.nextToken());
				b = Integer.parseInt(st.nextToken());
				c = Integer.parseInt(st.nextToken());
				
				CityList[a].add(new City(b, c));
				CityList[b].add(new City(a, c));
			}
			
			// 최단 거리 탐색
			Dijkstra(1);
			
			// 1번 도시에서 N번 도시로 가는 최소 시간을 출력한다. 
			// 만약 1번 도시에서 N번 도시로 갈 수 없다면 -1을 출력한다.
			if(Distance[N] == inf) {
				System.out.println("#" + t + " -1");
			} else {
				System.out.println("#" + t + " " + Distance[N]);
			}
			
		} // end test case		
	} // end main
	
	static void Dijkstra(int start) {
		PriorityQueue<City> pq = new PriorityQueue<>();
		
		pq.add(new City(start, 0));
		Distance[start] = 0;
		
		City curr;
		while(!pq.isEmpty()) {
			curr = pq.poll();
			int next = curr.e;
			// 연결된 다음 도시 탐색
			for(int i=0; i<CityList[next].size(); i++) {
				int nextTime = CityList[next].get(i).t;
				int nextEnd = CityList[next].get(i).e;
				// 거리 비교하여 이동거리가 짧으면 갱신처리
				if(Distance[nextEnd] > curr.t +  nextTime) {
					Distance[nextEnd] = curr.t +  nextTime;
					pq.add(new City(nextEnd, Distance[nextEnd]));
				}			
			}			
		}		
	}

}
