package bj;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/*
 * 위상정렬/우선순위큐
 * 줄 세우기 : https://www.acmicpc.net/problem/2252
 */
public class BJ2252 {
	
	static int N, M, T;
	static int[] inDegree;

	public static void main(String[] args) throws Exception{
		FileInputStream fi = new FileInputStream(new File(BJ2252.class.getResource("").getPath() + "BJ2252.txt"));
		System.setIn(fi);
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		T = Integer.valueOf(br.readLine());
		
		for(int t=1; t<=T; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			N = Integer.valueOf(st.nextToken());
			M = Integer.valueOf(st.nextToken());
			
			int start, end;
			inDegree = new int[N+1];
			ArrayList<ArrayList<Integer>> graph = new ArrayList<>();
			ArrayList<Integer>[] graph2 = new ArrayList[N+1];
			for(int i=1; i<=N; i++){
				graph.add(new ArrayList<Integer>());
				graph2[i] = new ArrayList<Integer>();
			}
			
			for(int i=0; i<M; i++){
				st = new StringTokenizer(br.readLine());
				start = Integer.valueOf(st.nextToken());
				end   = Integer.valueOf(st.nextToken());
				
				graph.get(start).add(end);
				graph2[start].add(end);
				
				inDegree[end]++;
			}		
		} // end Test case		
		
	} // main 
	
	static void topologicalSort(){
		Queue<Integer> q = new LinkedList();
	}
	
	
	
} // class
