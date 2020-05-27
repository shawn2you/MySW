package pretest4;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/*
 * (중상) [교육P-0007] 군사 도로망 
 * 기건설된 도로는 음수처리 하는 아이디어 필요
 */
public class Solution_EP0007 {

	static int T, M, N, K;
	static long Sum;
	
	static class City implements Comparable<City>{
		int x, y, c;
		City(int x, int y, int c){
			this.x = x;
			this.y = y;
			this.c = c;
		}
		@Override
		public int compareTo(City o) {
			if(o.c >= this.c) {
				return -1;
			}else {
				return 1;
			}
		}
	}
	
	static int[] parent = new int[100001];
    static City[] roadList;
	
	public static void main(String[] args) throws Exception {
		
		FileInputStream fi = new FileInputStream(new File(Solution_EP0007.class.getResource("").getPath() + "Solution_EP0007.txt"));
		System.setIn(fi);
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));	
		
		T = Integer.parseInt(br.readLine());
		
		for (int t=1; t<=T; t++) {
			// 초기화 
			Sum = 0;

			// 첫 수는 N(1≤N≤100,000), 둘째 수는 M(1≤M≤250,000), 셋째 수는 K(1≤K≤250,000)이다.
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken()); // 기존 도로
			K = Integer.parseInt(st.nextToken());// 신규 도로
			
			for(int i=0; i<=N; i++) {
				parent[i] = i;
			}
			
			roadList = new City[M+K];
			
			int x, y, c;
			for(int i=0; i<M+K; i++) {
				st = new StringTokenizer(br.readLine());
				x = Integer.parseInt(st.nextToken());
				y = Integer.parseInt(st.nextToken());
				c = Integer.parseInt(st.nextToken());
				
				if(i<M) {
					// 간선 List 형태(간선수 만큼 담는다)
					roadList[i] = new City(x, y, -c);
				}else {
					roadList[i] = new City(x, y, c);
				}
			}
			// 음수(건설한), 양수(건설할) 순으로 정렬
			Arrays.sort(roadList, 0, M + K);
			
			Sum = findLowCost();
			
			System.out.println("#"+t+" "+Sum);
			
		} // end test case		
	} // end main
	
	static long findLowCost() {
		long rtn = 0;
		int roadCnt = 0;
		// 건설한 다리 처리
		for(int i=0; i<M; i++) {
			if( find(roadList[i].x) != find(roadList[i].y) ){
				union(roadList[i].x, roadList[i].y);
				roadCnt++;
			}else {
				rtn += (-1) * roadList[i].c;
			}
		}
		
		if(roadCnt == N - 1) {
			return rtn;
		}
		
		for(int i=M; i<=M + K ; i++) {
			if( find(roadList[i].x) != find(roadList[i].y) ){
				union(roadList[i].x, roadList[i].y);
				roadCnt++;
				rtn += roadList[i].c;
			}
			if(roadCnt == N - 1) 
				return rtn;
		}
		return rtn;
	}
	
	static int find(int a) {
		if(a == parent[a]) return a;
		else return parent[a] = find(parent[a]);
	}
	
	static void union(int a, int b) {
		int ra = find(a);
		int rb = find(b);
		if(ra == rb) return;
		else parent[rb] = ra;
	}
}
