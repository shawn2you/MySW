package pretest4;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Solution_TA0037 {

	static int T, V, E, Sum, Seq, RootTree;
	static int[] SearchOrder;
	static int[] CoreCity;
	static ArrayList<Integer>[] City;
	
	public static void main(String[] args) throws Exception {
		
		FileInputStream fi = new FileInputStream(new File(Solution_TA0037.class.getResource("").getPath() + "Solution_TA0037.txt"));
		System.setIn(fi);
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));	
		
		T = Integer.parseInt(br.readLine());
		
		for (int t=1; t<=T; t++) {
			// 초기화 
			Sum = 0;
			Seq = 0;
			RootTree = 0;
			
			// 정점의 총 수 V 와 간선의 총 수 E
			StringTokenizer st = new StringTokenizer(br.readLine());
			V = Integer.parseInt(st.nextToken());
			E = Integer.parseInt(st.nextToken());
			
			City = new ArrayList[V + 1];
			SearchOrder = new int[V + 1];
			
			for(int i=0; i<=V; i++) {
				City[i] = new ArrayList<Integer>();
			}
			CoreCity = new int[V + 1]; // 단절 도시
			
			for(int i=0; i<E; i++) {
				st = new StringTokenizer(br.readLine());
				int A = Integer.parseInt(st.nextToken());
				int B = Integer.parseInt(st.nextToken());
				
				City[A].add(B);				
			}
			
			dfs(1);
			
			StringBuilder sb = new StringBuilder();
			
			for(int i=1; i<=V; i++) {
				if(CoreCity[i] == 1) {
					sb.append(" " + i);
					Sum++;
				}	
			}
			System.out.println("#"+t + " " + Sum + " " + sb.toString());
			
			
		} // end test case		
	} // end main
	
	public static int dfs(int start) {
		Seq++;
		
		SearchOrder[start] = Seq;
		int low = Seq;
		
		for(int c : City[start]) {
			if(SearchOrder[c] != 0) {
				low = Math.min(low, SearchOrder[c]);
			}else {
				int nextLow = dfs(c);
				if(start !=1 && SearchOrder[c] <= nextLow) {
					CoreCity[c] = 1;
				}
				low = Math.min(low, nextLow);
				
				// 점점노드 처리
				if(start == 1) {
					RootTree++;
					if(RootTree > 1) {
						CoreCity[start] = 1;
					}
				}				
			}
		}
//		System.out.println(start + " "+SearchOrder[start] + " " + low);
		return low;
		
	}
}
