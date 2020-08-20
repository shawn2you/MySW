package basic;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Solution_basic {
	// 변수 처리 
	static int T, N;
	static long Res;

	// union-find by rank
	static int[] parent;
	static int[] rank;
	
	// 그래프 구조 설계
	static class Node{
		int x, y;
		Node(){
			
		}
		Node(int x, int y){
			this.x = x;
			this.y = y;
		}
	}
	
	static int[] visited;
	
	public static void main(String[] args) throws Exception {
		// 파일 처리 
		FileInputStream fi = new FileInputStream(new File(Solution_basic.class.getResource("").getPath() + "basic"));
		System.setIn(fi);
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		T = Integer.parseInt(br.readLine());
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		for(int t=1; t<=T; t++){
			Res = 0;
			sb.setLength(0);
			sb.append("#").append(t).append(" ");
			
			// 자료구조
			// array
			ArrayList<Integer> al = new ArrayList<>();
			al.add(1);
			al.add(2);
			al.add(4);
			al.add(5);
			al.add(3);
			al.add(1);
			// stack
			LinkedList<Integer> ll = new LinkedList<>();
			ll.addFirst(1);
			ll.addFirst(2);
			ll.addFirst(4);
			ll.addFirst(5);
			ll.addFirst(3);
			ll.addFirst(1);
			ll.add(2, 3);			
			ll.push(9);
			System.out.println(ll.pop()); // LIFO
			// queue
			Queue<Integer> aq = new LinkedList<Integer>();
			aq.add(1);
			aq.add(2);
			aq.add(4);
			aq.add(3);
			aq.add(5);
			System.out.println(aq.poll()); // FIFO
						
			// Priority Queue
			PriorityQueue<Integer> pq = new PriorityQueue<>();
			pq.add(1);
			pq.add(3);
			pq.add(8);
			pq.add(4);
			while(!pq.isEmpty()){
				System.out.println(pq.poll());		
			}
			
			// Indexed tree
			N = 7;
			int S = 2;
			while(S <= N){
				S *= 2;
			}
			System.out.println("배열크기 S=" + S);
			int[] tree = new int[S+N+1];
			for(int i=S; i<=S+N; i++){
				tree[i] = i;
			}
			int i = S-1;
			while(i>0){
				tree[i] = Math.min(tree[2*i], tree[2*i+1]);
				i--;				
			}
			// 데이터 갱신 수행 (8번째를 11로 업데이트)
			int idx = 8 + S-1, update = 11;
			tree[idx] = update;
			while(idx != 0){
				idx /= 2;
				tree[idx] = Math.min(tree[2*idx], tree[2*idx+1]);
			}
			
			// 쿼리 수행 (5~8 최소 숫자)
			int x = 5 + S-1, y = 8 + S-1;
			int minValue = Integer.MAX_VALUE;
			while(x<y){
				if(x%2 == 0){
					x = x/2;
					minValue = Math.min(minValue, tree[x]);
				}else{
					minValue = Math.min(minValue, tree[x]);
					x++;
				}
				if(y%2 == 1){
					y = y/2;
					minValue = Math.min(minValue, tree[y]);
				}else{
					minValue = Math.min(minValue, tree[y]);
					y--;
				}
			}
			System.out.println(minValue);
			
						
						
			// 경우의 수 구성하기 (순열)
			int[] arr = {1, 2, 3};		
//			order(arr, 0, arr.length);
			
			// Union-find 함수
			N=10;
			rank   = new int[N];
			parent = new int[N];
			for(i=0; i<N; i++){
				parent[i] = i;
				rank[i] = 0; // 높이, 순위 등 우선순위
			}
			
			// 이분 탐색 (오름차순으로 정렬된 수열에서 특정수를 찾는 경우)
			int[] arr2 = {1, 2, 3, 4, 5, 6, 7, 8};
			int[] arr3 = {1, 4, 3, 5, 9, 2, 7, 6};
			int[] arr4 = {8, 7, 6, 5, 4, 3, 2, 1};
			
//			System.out.println(binarySearch(arr2, 0, arr4.length, 8));
//			Arrays.sort(arr3, 0, 8);
//			System.out.println(Arrays.binarySearch(arr3, 0, 8, 9));
			
			
			// 최대공약수 (유클리드 호제법)
			int a = 8;
			int b = 15;
			System.out.println(a + ", " + b + "=최대공약수" + GCD(a, b));
			// 기약분수 형태로 출력시 두수의 최대공약수로 각각 나누면 됨
			
			// 최소공배수 
			System.out.println("최소공배수="+a*b/GCD(a, b));
			
			// 확장유클리드(해결 못함)
			EEA(a, b);
			
			// 소수판별
			a = 100;
			int[] isPrime = new int[a+1]; // 캐싱
			isPrime[2] = 1;
			isPrime[3] = 1;
			
			System.out.println(Math.sqrt(a));
			double sqrtA = Math.sqrt(a);
			// root a = x * y : a가 소수가 아니면 x, y로 나누어 떨어진다. x, y중 더 큰 수는 무조건 a의 제곱근보다 크다 . 
			loops : while(true){
				if(isPrime[a] == 1) {
					System.out.println(a + " is prime");
					break loops;
				}
				for(i = 2; i<sqrtA; i++){
					if(a%i == 0){
						System.out.println(a + " is not prime");
						break loops;
					}
				}
				System.out.println(a + " is prime");
				break loops;
			}
			System.out.println("=================================================");
			// 소수판별(에라토네스의 체, N까지의 소수합이 더 적합함)
			a = 100;
			isPrime = new int[a+1]; // 캐싱
			while(true){
				for(i=2; i<=a; i++){
					if(isPrime[i] == 0){
						System.out.println(i + " is prime");
					}
					for(int j=i; j<=a; j+=i){
						isPrime[j] = 1;
					}
				}
				break;
			}
			
			
			
			
			
			
			// BFS/DFS 구현해보기 
			int V = 7;
			LinkedList<Node> nl[] = new LinkedList[V+1];
			visited = new int[V+1];
			for(i=0; i<=V; i++){
				nl[i] = new LinkedList<Node>();
				visited[i] = 0;
			}
			nl[1].add(new Node(1, 2));
			nl[1].add(new Node(1, 3));
			nl[2].add(new Node(2, 4));
			nl[4].add(new Node(4, 7));
			nl[3].add(new Node(3, 5));
			nl[3].add(new Node(3, 6));
			nl[5].add(new Node(5, 7));
			
//			BFS(nl, 1);
			DFS(nl, 1);
			
			
			
			sb.append(Res);
			System.out.println(sb.toString());
		}		
	} // end main
	
	// 경우의 수 구성하기 (순열)
	static void order(int[] arr, int depth, int size){
		if(depth == size){
			for(int i=0; i<arr.length; i++){
				System.out.print(arr[i] + ",");
			}
			System.out.println();
			return;
		}
		
		for(int i=depth; i<size; i++){
			swap(arr, i, depth);
			order(arr, depth + 1, size);
			swap(arr, i, depth);
		}
	}
	static void swap(int[] arr, int x, int y){
		int temp = arr[x];
		arr[x] = arr[y];
		arr[y]= temp;
	}
	
	static int find(int a){
		if(parent[a] == a) return a;
		return find(parent[a]);
	}
	static void union(int a, int b){
		int mA = find(a);
		int mB = find(b);
		
		if(mA == mB) return;
		
		// parent[mB] = mA;
		// 항상 순위(높이)가 높은쪽으로 합친다.
		if(rank[mA] < rank[mB]){
			parent[mA] = mB;
		}else{
			parent[mB] = mA;
			
			// 순위(높이)가 같다면 합친 후 한단계 올린다. 
			if(rank[mA] == rank[mB]) rank[mA]++;
		}
	}
	
	
	// 이분 탐색
	static int binarySearch(int[] arr, int leftidx, int rigthidx, int key){
		int left  = leftidx;  // low
		int right = rigthidx - 1; // high
		int mid, midValue;

		while(left <= right){
//			mid = (right + left)/2;
			mid = (right + left) >>> 1;
			midValue = arr[mid];
			if(midValue == key) return mid;
			
			if(midValue > key){ // 오름 차순일 경우 (반대로 하면 내림 차순)
				right = mid - 1;
			}else{
				left = mid + 1;
			}
		}
		return -(left + 1);
	}
	
	
	// 최대공약수
	static int GCD(int a, int b){
		int temp;
		if(a < b){
			temp = a;
			a = b;
			b = temp;
		}
		// GCD(a, b) = GCD(b, r)
		while(b != 0){
			temp = a%b; // a를 b로 나눈 나머지
			a = b;
			b = temp;
		}
		return a;
	}
	
	// 확장유클리드 
	static void EEA(int a, int b){
		int r0 = a, r1 = b;
		int s0 = 1, s1 = 0;
		int t0 = 0, t1 = 1;
		int temp = 0, q = 0;
		
		while(r1 != 0){
			q = r0/r1;
			temp = r0;
			r0 = r1;
			r1 = temp - r1*q;
			temp = s0;
			s0 = s1;
			s1 = temp = s1*q;
			temp = t0;
			t0 = t1;
			t1 = temp - t1*q;
		}
		
		System.out.println("s1 = " + s1 + ", t1 = " + t1);
	}
	
	
	// BFS - Queue
	static void BFS(LinkedList<Node>[] nl, int start){
		Queue <Node> que = new LinkedList<Node>();
		que.add(new Node(0, start)); // 시작점 
		visited[start] = 1;
		System.out.println("방문=" + start);
		Node curr, next;
		int nextY;
		while(!que.isEmpty()){
			curr = que.poll();
			nextY = curr.y;
			// 인접 노드로 이동
			for(int i=0; i<nl[nextY].size(); i++){
				next = nl[nextY].get(i);
				if(visited[next.y] == 0){
					que.add(nl[nextY].get(i));
					visited[next.y] = 1; // 방문처리
					// 로직 구현
					System.out.println("방문=" + next.y);
				}
			}
		}			
	}
	
	// DFS - Stack
	static void DFS(LinkedList<Node>[] nl, int start){
		LinkedList<Node> stack = new LinkedList<>();

		stack.push(new Node(0, start)); // 시작점 
//		visited[start] = 1;
//		System.out.println("방문=" + start);
		Node curr, next;
		int nextY;
		while(!stack.isEmpty()){
			curr = stack.pop();
			nextY = curr.y;
			
			if(visited[nextY] == 0){
				visited[nextY] = 1; // 방문처리
				System.out.println("방문=" + nextY);
			}
			// 인접 노드로 이동		
			for(int i=0; i<nl[nextY].size(); i++){
				next = nl[nextY].get(i);
				if(visited[next.y] == 0){
					stack.push(nl[nextY].get(i));
				}
			}
		}			
	}
	
	public static long factorial(long value) {
		if(value == 0) {
			return 1;
		}
		return value * factorial(value -1);
	}
	
	public static long factorial2(int n) {
		long rval = 1;
		for(int i=1; i<=n; i++) {
			rval *= i;
		}
		return rval;
	}
	
}