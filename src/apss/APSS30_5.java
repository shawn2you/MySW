package apss;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/*
 * 소방차 : https://algospot.com/judge/problem/read/FIRETRUCKS#
 * 다익스트라
 */

class Node implements Comparable<Node>{
	int x; // 도착점
	int time; // 소요시간
	int gubun; // 1 장소, 2 화재장소, 3 소방서
	public Node(int x, int time){
		super();
		this.x = x;
		this.time = time;
	}
	@Override
	public int compareTo(Node o) {
		// TODO Auto-generated method stub
		return this.time > o.time ? 1:-1 ;
	}
}
public class APSS30_5 {

	static int T, V, E, n, m, sum;
	// 방향없는 간선으로 양방향 셋팅 필요
	static ArrayList<Node>[] map1;
	static int[] fireOffice = new int[1001];
	static int[] fireHouse;
	static int INF = Integer.MAX_VALUE-10000;
	
	public static void main(String[] args) throws Exception {
		FileInputStream fi = new FileInputStream(new File(APSS30_5.class.getResource("").getPath() + "APSS30_5.txt"));
		System.setIn(fi);
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		T = Integer.parseInt(br.readLine());		 
		
		for(int t=0; t<T; t++){
			sum = 0;
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			V = Integer.parseInt(st.nextToken()); // 장소 V(2≤V≤1000)
			E = Integer.parseInt(st.nextToken()); // 도로(간선) E(0≤E≤V*(V-1)/2) , 1000*999/2 
			n = Integer.parseInt(st.nextToken()); // 화재장소 수
			m = Integer.parseInt(st.nextToken()); // 소방서 수
			
			int s, e, d;
			int[] time = new int[V+1]; // 해당 장소의 최소값
			map1 = new ArrayList[V + 1];
			
			for (int k = 0; k <= V; k++){
				map1[k] = new ArrayList<Node>();
				fireOffice[k] = INF;
			}
			
			for(int i=0; i<E; i++){
				st = new StringTokenizer(br.readLine());
				s = Integer.parseInt(st.nextToken()); // 시작
				e = Integer.parseInt(st.nextToken()); // 종료
				d = Integer.parseInt(st.nextToken()); // 시간
										
				map1[s].add(new Node(e, d));
				map1[e].add(new Node(s, d));
			}
			// 화재발생장소
			
			st = new StringTokenizer(br.readLine());
			fireHouse  = new int[n];
			for(int i=0; i<n; i++){
				fireHouse[i] = Integer.parseInt(st.nextToken());
			}
			// 소방서(가상의 위치[0]에서 각 소방서로 가중치가 0인 간선을 연결한다. 
			st = new StringTokenizer(br.readLine());
			for(int i=0; i<m; i++){
				map1[0].add(new Node(Integer.parseInt(st.nextToken()), 0));
			}
			
			Dijkstra(0);
			
			for(int i=0; i<n; i++){
				sum = sum + fireOffice[fireHouse[i]];
			}
			System.out.println(sum);
			
		} // end test case
				

	} // end mian
	
	static void Dijkstra(int start){
		PriorityQueue<Node> pq = new PriorityQueue<>();
		
		fireOffice[0] = 0;
		pq.add(new Node(start, fireOffice[0]));
		
		Node cNd, nNd;
		int curr, next, time;
		while(!pq.isEmpty()){
			
			cNd = pq.poll();
			curr = cNd.x;

			// 인접 노드 방문을 한다. 
			for(int i=0; i<map1[curr].size(); i++){
				nNd = map1[curr].get(i);
				next = nNd.x;
				time = nNd.time;
				
				if(fireOffice[next] > fireOffice[curr] + time){
					fireOffice[next] = fireOffice[curr] + time;
					pq.add(new Node(next, fireOffice[next]));
				}
			}			
		}
	}
}
