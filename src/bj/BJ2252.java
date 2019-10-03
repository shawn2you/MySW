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
	static ArrayList<ArrayList<Integer>> graph;
	static ArrayList<Integer>[] graph2;
	static Queue<Integer> q = new LinkedList<Integer>();

	public static void main(String[] args) throws Exception{
		FileInputStream fi = new FileInputStream(new File(BJ2252.class.getResource("").getPath() + "BJ2252.txt"));
		System.setIn(fi);
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

//		T = Integer.valueOf(br.readLine());
		
//		for(int t=1; t<=T; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			N = Integer.valueOf(st.nextToken()); // 학생수
			M = Integer.valueOf(st.nextToken()); // 비교 횟수
			
			int start, end;
			inDegree = new int[N+1];
			graph = new ArrayList<>();
			graph2 = new ArrayList[N+1];
			
			for(int i=0; i<=N; i++){
				graph.add(new ArrayList<Integer>());
				graph2[i] = new ArrayList<Integer>();
			}
			
			for(int i=0; i<M; i++){
				st = new StringTokenizer(br.readLine());
				start = Integer.valueOf(st.nextToken());
				end   = Integer.valueOf(st.nextToken());
				
				graph.get(start).add(end);
				graph2[start].add(end);
				// 도착점 기준으로 셋팅
				inDegree[end]++;
			}
			
			for(int i=1; i<=N; i++){
				// 최초 진입점들을 queue에 담는다. 
				if(inDegree[i] == 0) q.add(i);
			}
			
			topologicalSort();
//		} // end Test case		
		
	} // main 
	
	static void topologicalSort(){		
		StringBuilder sb = new StringBuilder();
//		sb.append("#");
//		sb.append(T);
//		sb.append(" ");
		
		while(!q.isEmpty()){
			int i = q.peek();
			sb.append(i);
			sb.append(" ");
			q.poll(); // queue 에서 제거 한다. 
			
//			for(int j=0; j<graph.get(i).size(); j++){
//				if(--inDegree[graph.get(i).get(j)] == 0)
//					// 해당 위치를 탐색 후 차수를 -1 처리 한다.  
//					// 해당 차수가0 이면 최초 진입점이므로 queue에 담는다. 
//					q.add(graph.get(i).get(j));				
//			}
			
			for(int j=0; j<graph2[i].size(); j++){
				if(--inDegree[graph2[i].get(j)] == 0)
					// 해당 위치를 탐색 후 차수를 -1 처리 한다.  
					// 해당 차수가0 이면 최초 진입점이므로 queue에 담는다. 
					q.add(graph2[i].get(j));				
			}	
			
		}
		
		System.out.println(sb.toString());
	}	
	
} // class
