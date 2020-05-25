package pretest4;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Solution_EA0002 {

	static int T, M, N, Sum;
	static int[] inDegree = new int[501];
	static int[] visited = new int[501];
	
	static ArrayList[] stu = new ArrayList[501];
	
	public static void main(String[] args) throws Exception {
		
		FileInputStream fi = new FileInputStream(new File(Solution_EA0002.class.getResource("").getPath() + "Solution_EA0002.txt"));
		System.setIn(fi);
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));	
		
		T = Integer.parseInt(br.readLine());
		
		for (int t=1; t<=T; t++) {
			// 초기화 
			Sum = 0;
			Arrays.fill(inDegree, 0);
			Arrays.fill(visited, 0);
			
			//  사원들의 수 N(2 ≤ N ≤ 500), 두 사원 키를 비교한 횟수 (0 ≤ M ≤ N(N-1)/2)
			N = Integer.parseInt(br.readLine());	
			M = Integer.parseInt(br.readLine());
			
			
			for(int i=0; i<N; i++) {
				stu[i] = new ArrayList<Integer>();
			}
			
			
			StringTokenizer st;		
			
			int a, b;
			for(int i=0; i<M; i++) {
				st = new StringTokenizer(br.readLine());
				// 번호가 a인 사원이 번호가 b인 사원보다 키가 작은 것을 의미한다
				a = Integer.parseInt(st.nextToken());
				b = Integer.parseInt(st.nextToken());
				inDegree[b]++;
				stu[a].add(b);
			}
			// 최초(가장 작은 키로 판단되는 사람은 위상이 0인 경우)
	        Queue <Integer> que = new LinkedList<Integer>();

			for(int i=0; i<N; i++) {
				if(inDegree[i] == 0) {
					que.add(i);
					visited[i] = 1;
					
				}else{
					
				}
			}
			
		} // end test case		
	} // end main
	
	
	
}
