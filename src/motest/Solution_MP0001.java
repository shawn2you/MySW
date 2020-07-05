package motest;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution_MP0001 {
	static int T, M, N, Sum;
	static int[] parent = new int[30001];

	public static void main(String[] args) throws Exception {

		FileInputStream fi = new FileInputStream(new File(Solution_MP0001.class.getResource("").getPath() + "Solution_MP0001.txt"));
		System.setIn(fi);

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		T = Integer.parseInt(br.readLine());

		for (int t = 1; t <= T; t++) {
			// 초기화
			StringBuilder sb = new StringBuilder();
			sb.append("#").append(t).append(" ");
			N = Integer.parseInt(br.readLine());
			
			for(int i=1; i<=N; i++){
				parent[i] = i;			
			}
			
			StringTokenizer st;
			for(int i=1; i<=N; i++){				
				st = new StringTokenizer(br.readLine());
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
				
				// a -> b
				if(union(a, b)){
					sb.append(a).append(" ").append(b);
					System.out.println(sb.toString());
				}				
			}
			
		} // END TEST CASE
	}
	
	static int find(int a) {
		if(a == parent[a]) return a;
		else return parent[a] = find(parent[a]);
	}
	
	static boolean union(int a, int b) {
		int ra = find(a);
		int rb = find(b);
		if(ra == rb) {
			return true;
		} else {
			parent[ra] = rb;
			return false;
		}
	}
}
