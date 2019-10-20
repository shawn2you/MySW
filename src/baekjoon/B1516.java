package baekjoon;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/*
 * 게임개발 : https://www.acmicpc.net/problem/1516
 * 위상정렬
 */

class B1516_Node implements Comparable<B1516_Node>{
	int a;
	int tm;
	B1516_Node(int a, int tm){
		this.a  = a;
		this.tm = tm;
	}
	@Override
	public int compareTo(B1516_Node T) {
		return this.tm > T.tm ? 1 : -1;
	}
}


public class B1516 {
	static int N, M;
	static int[] inDegree = new int[501];
	static int[] diTime   = new int[501];
	static ArrayList<ArrayList<B1516_Node>> al = new ArrayList<ArrayList<B1516_Node>>();

	public static void main(String[] args) throws Exception {
		FileInputStream fi = new FileInputStream(new File(B1516.class.getResource("").getPath() + "B1516.txt"));
		System.setIn(fi);
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken()); // 건물의 종류
		
		al.clear();
		for(int i=0; i<=N; i++){
			al.add(new ArrayList<>());
			inDegree[i] = 0;
			diTime[i] = 0;
		}
		int a, tm, idx;
		for(int i=1; i<=N; i++){
			idx = 0;
			st = new StringTokenizer(br.readLine());
			
			tm = Integer.parseInt(st.nextToken());
			
			while(st.hasMoreElements()){				
				a  = Integer.parseInt(st.nextToken());
				if(a == -1) {
					if(idx == 0) {
						al.get(0).add(new B1516_Node(i, tm));
						inDegree[i]++;
					}
					break;
				}
				
				al.get(a).add(new B1516_Node(i, tm));
				inDegree[i]++;
				idx++;
			}
		}
		
		topologicalSort();
		
		for(int i=1; i<=N; i++){
			System.out.println(diTime[i]);
		}
	} // end main
	
	
	public static void topologicalSort(){
		PriorityQueue<B1516_Node> pq = new PriorityQueue<>();
		StringBuilder sb = new StringBuilder();
		
		B1516_Node nd, nNd;
		int curr, next, tm;
		for(int i=0; i<=N; i++){
			if(inDegree[i] == 0) {
				nd = al.get(i).get(0);
				diTime[nd.a] = nd.tm;
				pq.add(new B1516_Node(nd.a, nd.tm));
			}
		}		
		
		while(!pq.isEmpty()){
			nd = pq.poll();
			curr = nd.a;
			
			for(int i=0; i<al.get(curr).size(); i++){
				nNd = al.get(curr).get(i);
				next = nNd.a;
				tm   = nNd.tm;
				diTime[next] = Math.max(diTime[next], diTime[curr] + tm);
				
				if(--inDegree[next] == 0) {
					pq.add(new B1516_Node(next, diTime[next]));
				}
			}
		}
	}
}
