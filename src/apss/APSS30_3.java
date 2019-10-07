package apss;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/*
 * 신호 라우팅 : https://algospot.com/judge/problem/read/ROUTING
 * Dikjstra
 */

class Computer implements Comparable<Computer>{
	int x;
	double noise;
	Computer(int x, double n){
		this.x = x;
		this.noise = n;
	}
	@Override
	public int compareTo(Computer o) {		
		return this.noise > o.noise ? 1 : -1;
	}
}

public class APSS30_3 {
	
	static int T, N, M;
	static double sum;
	static ArrayList<Computer>[] cp = new ArrayList[100001];
	static double[] lCost = new double[100001];
	static int INF = Integer.MAX_VALUE - 100000;

	public static void main(String[] args) throws Exception {
		FileInputStream fi = new FileInputStream(new File( APSS30_3.class.getResource("").getPath() + "APSS30_3.txt"));
		System.setIn(fi);
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		T = Integer.parseInt(br.readLine());
		StringTokenizer st;
		
		for(int t=1; t<=T; t++) {
			sum = 0;
			st = new StringTokenizer(br.readLine());
			
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			
			for(int i=0; i<N; i++) {
				cp[i] = new ArrayList<>();
				lCost[i] = INF;
			}
			
			int a, b;
			double c;
			for(int i=0; i<M; i++) {
				
				st = new StringTokenizer(br.readLine());
				a = Integer.parseInt(st.nextToken());
				b = Integer.parseInt(st.nextToken());
				c = Double.parseDouble(st.nextToken());
				
				cp[a].add(new Computer(b, c));
				cp[b].add(new Computer(a, c));				
			}
			
			Dijkstra(0);

			System.out.printf("%.10f", lCost[N-1]);
			System.out.println();
			
		} // end test case
	} // end main
	
	public static void Dijkstra(int start) {
		// 우선순위 큐에 정점과 정점까지의 누적노이즈 포함하여 저장
		PriorityQueue<Computer> pq = new PriorityQueue<Computer>();
		
		lCost[start] = 1; // 최초는 1로 셋팅
		pq.add(new Computer(start, lCost[start]));
		
		Computer cCp, nCp;
		int nextCp, currCp;
		double nextCost;
		while(!pq.isEmpty()) {
			
			cCp = pq.poll();
			currCp   = cCp.x;
			
			// 인접한 노드를 방문한다. 
			for(int i=0; i<cp[currCp].size(); i++) {
				nCp = cp[currCp].get(i);
				nextCp   = nCp.x;
				nextCost = nCp.noise;
				if(lCost[nextCp] > lCost[currCp]*nextCost) {
					lCost[nextCp] = lCost[currCp]*nextCost;
					pq.add(new Computer(nextCp, lCost[nextCp]));
				}
			}			
		}
	}
	
}
