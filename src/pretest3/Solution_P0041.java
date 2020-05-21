package pretest3;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Solution_P0041 {

	static int T, M, N, S, Sum;
	static int[] tree;
	
	public static void main(String[] args) throws Exception {
		
		FileInputStream fi = new FileInputStream(new File(Solution_P0041.class.getResource("").getPath() + "Solution_P0041.txt"));
		System.setIn(fi);
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));	
		
		T = Integer.parseInt(br.readLine());
		
		S = 2;
		while(S < 100001){ //  카드의 숫자를 고려
			S *= 2;
		}			
		// 시작점보다 2배 크게 배열 정의해야 모두 담을 수 있음
		tree = new int[S * 2];
		StringBuilder sb = new StringBuilder();
		
		for (int t=1; t<=T; t++) {
			// 초기화 
			sb.setLength(0);
			Arrays.fill(tree, 0);
			sb.append("#");
			sb.append(t);

			// 질의 수 N
			N = Integer.parseInt(br.readLine());		
						
			StringTokenizer st;
			for(int i=0; i<N; i++){
				st = new StringTokenizer(br.readLine());
				int q = Integer.parseInt(st.nextToken());
				int k = Integer.parseInt(st.nextToken());
				
				if(q == 1){
					add(k);
				}else{
					sb.append(" ");
					sb.append(search(k));					
				}				
			}
			System.out.println(sb.toString());
		} // end test case		
	} // end main
	
	static void add(int a) {
		int idx;
		idx = a + S -1;
		// 노드까지의 카드 숫자합을 구성하면, 카드 숫자만큼 순서가 된다. 
		while(idx >= 1) {
			tree[idx]++;
			idx /= 2;
		}
	}	
	
	static int search(int k) {
		int idx = 1; // root node에서 시작
		
		while(idx < S) {
			tree[idx]--;
			
			if(tree[idx * 2] >= k) { 
				// 왼쪽 자식 탐색하여 순번이 작거나 같으면 계속 아래로 탐색해 나간다. (왼쪽 자식에 포함)
				idx *= 2;
			}else {
				// 왼쪽 자식 보다 순번이 크면(오른쪽 자식에 포함)
				k -= tree[idx * 2]; // 오른쪽 자식기준으로 k 재계산(부모는 각 구간의 개수(순서)의 합이므로 이전 개수를 빼준다)
				idx = idx * 2 + 1;  // 다음 이동할 Node 지정(오른쪽 갈 위치)
			}
		}
		tree[idx]--;
		
		return idx - S + 1; // Tree의 위치에 해당하는 위치가 카드값
	}
}