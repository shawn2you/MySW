package pretest4;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * (중상) [연습P-0023] 동맹의 동맹은 동맹
 */
public class Solution_EP0023 {

static int T, Q, N, Sum;
static int[] parent = new int[100001];
	
	public static void main(String[] args) throws Exception {
		
		FileInputStream fi = new FileInputStream(new File(Solution_EP0023.class.getResource("").getPath() + "Solution_EP0023.txt"));
		System.setIn(fi);
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));	
		
		T = Integer.parseInt(br.readLine());
		
		for (int t=1; t<=T; t++) {
			// 초기화 
			Sum = 0;
			
			// 낙성마을의 사람의 수 N(1 ≤ N ≤ 100,000), 질의의 수 Q
			N = Integer.parseInt(br.readLine());
			Q = Integer.parseInt(br.readLine());	
			
			for(int i=0; i<N; i++) {
				parent[i] = i; // 자기자신으로 셋팅
			}
			StringTokenizer st;

			int q, a, b;
			for(int i=0; i<Q; i++) {
				st = new StringTokenizer(br.readLine());
				
				q = Integer.parseInt(st.nextToken());
				a = Integer.parseInt(st.nextToken());
				b = Integer.parseInt(st.nextToken());
				
				
				if(q == 0) {
					//   0 a b : a번 사람과 b번 사람이 동맹 관계를 맺었음을 의미하는 질의이다.
					union(a, b); // 동맹관계이므로 서로 합친다. 
				}else {
					//   1 a b : a번 사람과 b번 사람이 동맹 관계에 있는지 물어보는 질의이다.
					if(find(a) == find(b)){
						Sum++;
					}
				}				
			}
			
			System.out.println("#"+t+" "+Sum);
			
		} // end test case		
	} // end main
	
	public static int find(int a) {
		if(a == parent[a]) return a;
		else return parent[a] = find(parent[a]);
	}

	public static void union(int a, int b) {
		int ar = find(a);
		int br = find(b);
//		if(a < b) parent[b] = a; // 숫자가 낮은(순번이 빠른)쪽으로 기준 잡음
//		else parent[a] = b;
//		if(ar == br) {
//			return;
//		}
		parent[ar] = br;
	}
}
