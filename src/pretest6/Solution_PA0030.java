package pretest6;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * 기지국 점검
 */
public class Solution_PA0030 {
	static int T, N, E, C, K, M;

	public static void main(String[] args) throws Exception{
		FileInputStream fi = new FileInputStream(new File(Solution_PA0030.class.getResource("").getPath() + "Solution_PA0030"));
		System.setIn(fi);
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		T = Integer.parseInt(br.readLine());
		
		for(int t=1; t<=T; t++) {
			
			// 기지국 
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken()); // 정점 개수
			E = Integer.parseInt(st.nextToken()); // 간선의 개수
			C = Integer.parseInt(st.nextToken()); // 기술센터 개수
			K = Integer.parseInt(st.nextToken()); // K - C 기지국 개수 (C+1, C+2 ~~ K)
			M = Integer.parseInt(st.nextToken()); // 엔지니어 수
			
			// 각 기술센터를 시작으로하는 최단거리 계산 (0번째 임의의 노드를 시작으로 각 기술센터를 연결)
			// 엔지니어수가 넘지 않는 최단 거리 계산
			
			
		} // end test case
		
		
	}

}
