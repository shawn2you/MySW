package baekjoon;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/*
 * 작업 : https://www.acmicpc.net/problem/2056
 * 위상정렬
 */

class Node_B2056 implements Comparable<Node_B2056>{
	int a;
	int tm;
	public Node_B2056(int a, int tm){
		super();
		this.a  = a;
		this.tm = tm;
	}
	@Override
	public int compareTo(Node_B2056 T) {
		// TODO Auto-generated method stub
		return this.tm > T.tm ? 1:-1;
	}
}

public class B2056 {
	static int N, M, ans;
	static int[] inDegree = new int[32001];
	static int[] diTime   = new int[32001];
	static ArrayList<ArrayList<Node_B2056>> al = new ArrayList<ArrayList<Node_B2056>>();

	public static void main(String[] args) throws Exception {
		FileInputStream fi = new FileInputStream(new File(B2056.class.getResource("").getPath() + "B2056.txt"));
		System.setIn(fi);
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		
		ans = 0;		
		al.clear();
		for(int i=0; i<=N; i++){
			al.add(new ArrayList<>());
			inDegree[i] = 0;
			diTime[i] = 0;
		}
		int a, c, tm;
		for(int i=1; i<=N; i++){
			st = new StringTokenizer(br.readLine());
			
			tm = Integer.parseInt(st.nextToken()); // 소요시간
			c = Integer.parseInt(st.nextToken());  // 선행작업개수
			
			if(c == 0){
				// 최초 진입(가상의 시작점[0]에서 출발하는것으로 설정)
				al.get(0).add(new Node_B2056(i, tm));
				inDegree[i]++;
			}
			
			for(int j=0; j<c; j++){
				a = Integer.parseInt(st.nextToken());
				
				al.get(a).add(new Node_B2056(i, tm));
				inDegree[i]++;
			}
		}
		
		topologicalSort();
		
	} // end main
	
	
	public static void topologicalSort(){
		PriorityQueue<Node_B2056> pq = new PriorityQueue<>();
		// 가상의 시작점에서 무조건 시작
		pq.add(new Node_B2056(0, al.get(0).get(0).tm));
		
		Node_B2056 cNd, nNd;
		int curr, next;
		
		while(!pq.isEmpty()){
			cNd = pq.poll();
			curr = cNd.a;
			
			for(int i=0; i<al.get(curr).size(); i++){
				nNd = al.get(curr).get(i);
				next = nNd.a;
				
				diTime[next] = Math.max(diTime[next], diTime[curr] + nNd.tm);
				ans = Math.max(ans, diTime[next]);
				if(--inDegree[next] == 0) {
					pq.add(new Node_B2056(next, diTime[next]));
				}
			}
		}		
		System.out.println(ans);
	}
}
