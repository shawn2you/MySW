package baekjoon;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/*
 * 문제집 : https://www.acmicpc.net/problem/1766
 * 위상정렬
 */
public class B1766 {
	static int N, M;
	static int[] inDegree = new int[32001];
	static ArrayList<ArrayList<Integer>> al = new ArrayList<ArrayList<Integer>>();

	public static void main(String[] args) throws Exception {
		FileInputStream fi = new FileInputStream(new File(B1766.class.getResource("").getPath() + "B1766.txt"));
		System.setIn(fi);
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		al.clear();
		for(int i=0; i<=N; i++){
			al.add(new ArrayList<>());
			inDegree[i] = 0;
		}
		int a, b;
		for(int i=0; i<M; i++){
			st = new StringTokenizer(br.readLine());
			a = Integer.parseInt(st.nextToken());
			b = Integer.parseInt(st.nextToken());
			
			al.get(a).add(b);
			inDegree[b]++;
		}
		
		topologicalSort();
		
	} // end main
	
	
	public static void topologicalSort(){
		PriorityQueue<Integer> pq = new PriorityQueue<>();
		StringBuilder sb = new StringBuilder();
		
		for(int i=1; i<=N; i++){
			if(inDegree[i] == 0) pq.add(i);
		}
		
		int curr, next;
		while(!pq.isEmpty()){
			curr = pq.poll();
			sb.append(curr);
			sb.append(" ");
			
			for(int i=0; i<al.get(curr).size(); i++){
				next = al.get(curr).get(i);
				if(--inDegree[next] == 0) {
					pq.add(next);
				}
			}
		}
		System.out.println(sb.toString());		
	}
}
