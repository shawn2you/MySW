package pretest;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

class Pair implements Comparable<Pair>{
	int v; // 정점(도착지)
	int d; // 가중치
	// 생성자
	Pair(int v, int d){
		this.v = v;
		this.d = d;
	}
	
	int getDist(){
		return this.d;
	}
	
	int getNext(){
		return this.v;
	}
	
//	public Pair() {
//		// TODO Auto-generated constructor stub
//	}
	@Override
	public int compareTo(Pair o) {
		// TODO Auto-generated method stub
		return (int) (this.d - o.d);
	}
}

public class preA0030 {
	
	static int T, N, E, C, K, M, Sum;
	
	static int[] dist;
//	static int[][] ad;
	
	static ArrayList<Long>[] map;
	static boolean[] visited;
	static final int inf = 1000000;

//	static Pair p[] = new Pair[20001];
	
	
//	public static void dijkstra(int index){
//		
//		PriorityQueue<Pair> pq = new PriorityQueue<Pair>();
//		
//		dist[index] = 0; // 자기자신이 되므로 0
//		pq.offer(new Pair(index, dist[index])); // 자기자신을 큐에 담는다. 
//		
//		while(!pq.isEmpty()){
//			int cost = pq.peek().getDist();
//			int next = pq.peek().getNext(); // 도착점 찾기
//			
//			pq.poll();
//			
//			if(cost > dist[next]){
//				continue;
//			}
//			
////			System.out.println(next);
//			
//			// 모든 정점 방문
//			for(int i=1; i<=N; i++){
//				// 다음정점이 있어야 하고(0보다 커야 인접정점이다), 
//				// 기존에 있던 dist[i]와 큐에서 가져온 dist와 비교
////				int zeroAd = ad[next][i] - 1; 
//				if(ad[next][i] != 0 && dist[i] > dist[next] + ad[next][i] - 1){
//					dist[i] = dist[next] + ad[next][i] - 1; // 최단거리 갱신
//					pq.offer(new Pair(i, dist[i]));
//				}
//			}
//			
////			System.out.println();
////			for(int i=1; i<=N; i++){
////				System.out.println(dist[i]);
////			}
//			
//			
//		} // end while
//		
//	}
	
	public static void dijkstra2(int index){
		
		PriorityQueue<Pair> pq = new PriorityQueue<Pair>();
		
		dist[index] = 0; // 자기자신이 되므로 0
		pq.offer(new Pair(index, dist[index])); // 자기자신을 큐에 담는다. 
		
		while(!pq.isEmpty()){
			int cost = pq.peek().getDist();
			int here = pq.peek().getNext(); // 도착점 찾기
			
			pq.poll();
			
			if(cost > dist[here]){
				continue;
			}
			
//			System.out.println(next);
			
			// 모든 정점 방문
			ArrayList<Long> li = map[here];
			
			for(double x: li){
				int nextNode = (int)x/1000000;
				int nextCost = (int)x%1000000;
//				System.out.println(x);
//				System.out.println(nextNode);
				if(dist[nextNode] > dist[here] + nextCost){
					dist[nextNode] = dist[here] + nextCost; // 최단거리 갱신
					pq.offer(new Pair(nextNode, dist[nextNode]));
				}				
			}
			
		} // end while
		
	}
	
//	PriorityQueue<Pair> pa = new PriorityQueue<preA0030.Pair>(arg0, arg1)
	
/*
[제한사항] 
1. G 의 정점의 개수 N 은 3 이상 20000 이하의 정수이다.
2. G 의 간선의 개수 E 는 2 이상 50000 이하의 정수이다.
3. 각 간선의 비용 (이동시간) 은 0 이상 1000000(백만) 이하의 정수이다.
4. 각 기술센터 당 엔지니어의 명수 M 은 1000000(백만) 이하의 자연수이다. 
   또한, M의 값은 기지국의 개수의 절반 이상임이 보장된다.
5. 하나의 정점에 2개 이상의 기술센터, 혹은 2개 이상의 기지국이 있는 경우는 없으며, 
   기술센터와 기지국이 동시에 위치한 정점도 존재하지 않는다.
6. 임의의 두 정점을 직접 연결하는 간선은 최대 1개이다.
 */

	public static void main(String[] args) throws Exception {
		FileInputStream fi = new FileInputStream(new File(preA0030.class.getResource("").getPath() + "preA0030.txt"));
		System.setIn(fi);
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		
		T = Integer.parseInt(br.readLine());
		StringTokenizer st = null;
		
		for(int t=1; t<=T; t++){		
/*
[입력] 
맨 처음 테스트 케이스의 개수 T 가 주어지며 그 다음 T개의 테스트 케이스가 주어진다.
각 테스트 케이스는 여러 줄로 구성되며 첫 줄에 N, E, C, K, M 의 값이 공백으로 구분되어 주어진다. 
여기서 C 는 기술센터의 개수를 뜻하며 K 는 기술센터와 기지국의 개수의 합을 뜻한다. 즉 기지국의 개수는 K-C 가 될 것이다 (1 < C < K ≤ N).
정점의 번호는 1번부터 N번까지 매겨지며, 이들 중 1 ~ C번 정점은 기술센터가 위치한 곳, C+1 ~ K 번 정점은 기지국이 위치한 곳이다. 
그 다음 E 줄에 걸쳐 간선의 정보가 한 줄에 하나씩 주어진다. 
간선의 정보는 정수 X, Y, Z 가 공백으로 구분되어 주어지며, X, Y 는 간선의 양 끝 정점번호를 뜻하고 Z는 간선의 비용(이동시간)을 뜻한다.	
 */
			Sum = 0;
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken()); // 정점개수
			E = Integer.parseInt(st.nextToken()); // 간선개수
			C = Integer.parseInt(st.nextToken()); // 기술센터 수
			K = Integer.parseInt(st.nextToken()); // 기술센터 + 기지국 수
			M = Integer.parseInt(st.nextToken()); // 엔지니어 수
			
			
			visited = new boolean[N+1];
			dist    = new int[N+1];
//			ad      = new int[N+1][N+1];
			
			
//			ArrayList<ArrayList<Integer>> map = new ArrayList<>();
			map = new ArrayList[N+1];
			
			for(int x=1; x<=N; x++){
				map[x] = new ArrayList<Long>();
			}
			
			
			int start, end;
			Long pt;
			
			for(int i=1; i<=E; i++){
				st = new StringTokenizer(br.readLine());
				start = Integer.parseInt(st.nextToken()); // X 20000
				end   = Integer.parseInt(st.nextToken()); // Y 20000
				pt    = Long.parseLong(st.nextToken()); // Z 가중치(0가중치 처리 필요), 1000000
				
				map[start].add((long)end*1000000 + pt);
				map[end].add((long)start*1000000 + pt);
				
				
				
//				ad[start][end] = pt;
//				ad[end][start] = pt;
			}
			
			// 기술센터(시작은 1번 부터)에서 기지국까지의 최소 거리 구하기
			for(int i=1; i<=C; i++){
				// 초기화
				for(int x=1; x<=N; x++){
					dist[x] = inf;
				}
				dijkstra2(i);
				
				for(int j=C+1; j<=K; j++){
//					System.out.print(dist[j]);
				}
//				System.out.println();
			}
			
			System.out.println("#"+t + " " + Sum);

		} // end T
		
		
/*
[출력]
각 테스트 케이스에 대해 #x (x는 테스트 케이스 번호, 1부터 시작) 을 출력하고 공백을 하나 둔 다음, 위에서 설명한 총비용의 최솟값을 출력한다.	
 */
	} // main

}
