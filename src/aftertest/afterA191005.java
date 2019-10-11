package aftertest;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/*
 * Dijkstra(우선순위큐사용) 알고리즘 
 */

class Node implements Comparable<Node>{
	int end;
	int cost;
	
	Node(int end, int cost){
		this.end  = end;
		this.cost = cost;
	}

	@Override
	public int compareTo(Node o) {
		return this.cost > o.cost ? 1 : -1;
	}
	
}
public class afterA191005 {
	
	static int T, E, V, S, sum;
	static int INF = Integer.MAX_VALUE - 100000;
	static int[] lDist = new int[100];
	static int[] cDist = new int[100];
	static int[] visited = new int[100];
//	static int[] parents = new int[100];
	
	static ArrayList<Node>[] map = new ArrayList[1000];
	
	public static void main(String[] args) throws Exception {
        FileInputStream fi = new FileInputStream(new File(afterA191005.class.getResource("").getPath() + "191005.txt"));
        System.setIn(fi);
         
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        T = Integer.valueOf(br.readLine());
        
        StringTokenizer st;
        for(int t=1; t<=T; t++){
        	sum = 0;
        	st = new StringTokenizer(br.readLine());
        	
        	E = Integer.valueOf(st.nextToken()); // 점정의 갯수
        	V = Integer.valueOf(st.nextToken()); // 간선의 갯수
        	S = Integer.valueOf(st.nextToken()); // 시작점
        	
        	for(int i=0; i<E; i++){
        		map[i] = new ArrayList<Node>();
        		lDist[i] = INF; // 누적 방문거리
        		cDist[i] = 0;   // 정점 사용방문거리
        		visited[i] = 0;
//        		parents[i] = i;
        	}
        	
        	int a, b, c;
        	for(int i=0; i<V; i++){
        		st = new StringTokenizer(br.readLine());
            	a = Integer.valueOf(st.nextToken()); // 시작점
            	b = Integer.valueOf(st.nextToken()); // 끝점
            	c = Integer.valueOf(st.nextToken()); // 비용
            	
            	map[a].add(new Node(b, c));
            	map[b].add(new Node(a, c));
        	}
        	Dijkstra2(S);
        	
        	for(int i=0; i<E; i++){
//        		sum = Math.max(lDist[i], lDist[i+1]);
        		sum = sum + cDist[i];
        	}
        	System.out.println("# " + sum);
        	
        } // end test case        
	} // end main

	// 우선순위큐를 활용한 기법
	public static void Dijkstra(int start){
		// bfs는 각 정점을 발견한 순서대로 방문하기때문에, 최단거리를 찾지 못하는(별도 갱신을 해야하는) 문제가 있음
		// Dijkstra는 이 문제를 해결하기 위해 큐에서 우선순위 큐로 사용하여 해결한다. 
		// 우선순위큐를 사용할때 정점에 해당 거리까지의 가중치를 쌍으로 넣어 처리 
//		PriorityQueue<Integer> pq1 = new PriorityQueue<>();
		
		PriorityQueue<Node> pq = new PriorityQueue<>();
		
		lDist[start] = 0;
		pq.add(new Node(start, lDist[start])); // 시작점을 담는다. 
		
		
		Node curNd, nextNd;
		int cur, next, dist;
		
		while(!pq.isEmpty()){
			curNd = pq.poll(); // 큐에서 정점을 꺼낸다. 
			cur = curNd.end;
			// 현재 방문할 점정보다 더 짧은 경로가 있다면 무시한다. (Skip)
			if(curNd.cost > lDist[cur]) {
//				System.out.println(lDist[cur] + ", " + curNd.cost);
				continue;
			}
			
			for(int i=0; i<map[cur].size(); i++){
				nextNd = map[cur].get(i);
				
				next = nextNd.end;
				dist = nextNd.cost;
				
				// 다음 방문할 경로가 더 짧을 경우 경로를 갱신하고, 큐에 담는다.(해당 경로로 탐색)
				if(lDist[next] > lDist[cur] + dist ){
					lDist[next] = lDist[cur] + dist;
					cDist[next] = dist;
					pq.add(new Node(next, lDist[next]));
				}
			}
		}		
	}
	
	// for문을 활용(간선의 개수가 매우 많을 경우) 
	public static void Dijkstra2(int start){
		int curr, next, dist;
		
		lDist[start] = 0;
//		visited[start] = 1;
		curr = start;		
		Node nextNd;
		
		while(true){
			int closest = INF; // 연결된 노드중 짧은 노드의 거리
			// 방문하지 않은 정점중에 가장 거리가 짧은 점을 찾는다. 			
			for(int j=0; j<E; j++){
				if(visited[j] == 0 && closest > lDist[j]){
					curr = j;
					closest = lDist[j];
					visited[j] = 1;
				}
			}
			if(closest == INF) break;
			
			// 찾은 정점에서 인접한 거리를 갱신한다. 
			for(int i=0; i<map[curr].size(); i++){
			
				nextNd = map[curr].get(i);
				next = nextNd.end;
				dist = nextNd.cost;			
				
				if(visited[next] == 0 && lDist[next] >= lDist[curr] + dist ){
					lDist[next] = lDist[curr] + dist;
					cDist[next] = dist;
				}
//				else if(visited[next] == 0 && lDist[next] == lDist[curr] + dist){
//					if(cDist[next] > dist){
//						lDist[next] = lDist[curr] + dist;
//						cDist[next] = dist;
//					}
//				}
			} // end for
		}
		
	}
	
//	public static int findParent(int a){
//		if(parents[a] == a) return a;
//		return parents[a] = findParent(parents[a]);
//	}
//	
//	public static void unionParent(int a, int b){
//		int rA = findParent(a);
//		int rB = findParent(b);
//		
//		if(rA != rB) parents[b] = a;
//	}
	
}