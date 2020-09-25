package pretest6;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.StringTokenizer;

/*
 * (중상) [기출P-0070] 조약돌 게임 
 */
public class Solution_TP0058 {

	static int T, M, N;
	static long Sum;
	
	static int[] score;
	static long[] tree;
	
	public static void main(String[] args) throws Exception {
		
		FileInputStream fi = new FileInputStream(new File(Solution_TP0058.class.getResource("").getPath() + "Solution_TP0058"));
		System.setIn(fi);
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));	
		
		T = Integer.parseInt(br.readLine());
		StringTokenizer st;
		
		int cnt = 1000000;
		// 트리 초기화(X좌표값으로 구성)
		int S = 2;
		while(S < cnt) {
			S *= 2;
		}
//		tree = new int[2*S + 1];		
		
		for (int t=1; t<=T; t++) {
			// 초기화 
			Sum = 0;
/*
입력 파일의 제일 첫째 줄에는 파일에 포함된 케이스의 수 T가 주어진다.각 케이스의 첫째 줄에 탱크의 개수 N (1 ≤ N ≤ 100,000) 이 주어진다. 
다음 N 개의 줄 각각에 탱크의 위치와 점수가 주어진다. 이들 중 첫 두 자연수는 탱크의 x와 y좌표이며, 세번째 자연수는 탱크에 부여된 점수이다. 
모든 좌표 값은 1 이상 1,000,000이하이다. 탱크에 부여된 점수는 1 이상 200,000 이하인 자연수이다. 
두 탱크가 동일한 x좌표에 위치하는 경우는 없으며, 두 탱크가 동일한 y좌표에 위치하는 경우도 없다. 또한, 탱크에 부여된 점수는 모두 다르다. 

 */
			// 탱크의 개수 N (1 ≤ N ≤ 100,000)
			N = Integer.parseInt(br.readLine());
			
			// 트리 초기화			
//			int S = 2;
//			while(S < N) {
//				S *= 2;
//			}
			tree = new long[2*S + 1];
			
			ArrayList<int[]> al = new ArrayList<>();
			
			for(int i=0; i<N; i++) {
				st = new StringTokenizer(br.readLine());
				int x = Integer.parseInt(st.nextToken());
				int y = Integer.parseInt(st.nextToken());
				int s = Integer.parseInt(st.nextToken());
				
				al.add(new int[] {x, y, s});
			}
			
//			for(int i=0; i<N; i++) {
//				System.out.println(al.get(i)[0] + ", " + al.get(i)[1]);				
//			}
			
			// y좌표를 기준으로 내림차순(높은값부터 추출)으로 정렬한다.
			Collections.sort(al, new Comparator<int[]>() {
				@Override
				public int compare(int[] o1, int[] o2) {
					if(o1[1] > o2[1]) {
						return -1;
					}else {
						return 1;
					}					
				}
			});
			
			// x좌표의 위치에 해당하는 점수로 tree를 구성하고, 입려된 위치의 우측 (x보다 큰)의 부분합을 구한다.
			for(int i=0; i<N; i++) {
				// y좌표가 큰 값부터 tree 구성
				int x = al.get(i)[0];
				int y = al.get(i)[1];
				int s = al.get(i)[2];
				
				int idx = x + S - 1; // 갱신할 tree 위치 (좌표의 시작점은 S - 1에서 더하기)
				tree[idx] = s;
				
				// 데이터 갱신하기
				while(idx != 0) {
					idx /= 2;
					tree[idx] = tree[2*idx] + tree[2*idx+1];
				}
				// 구간합 구하기(x 다음 위치부터 끝까지 구간, 미리 계산하여 넣기)
				int maxX = 2*S - 1; // 좌표의 끝점
				Sum += findSum(x + S, maxX);
			}			
			
			System.out.println("#"+t+" "+Sum);
		} // end test case		
	} // end main
	
	static long findSum(int x, int y) {	
		long reSum = 0;
		while(x<=y) {
			// 왼쪽탐색
			if(x%2 == 0) {
				// 왼쪽노드이므로 상위로 진행
				x = x/2;
			} else {
				// 오른쪽노드이므로 계산하고 우측으로 한칸 이동				
				reSum += tree[x];
				x++;
				x = x/2;
			}
			
			// 오른쪽 탐색
			if(y%2 == 1) {
				//오른쪽 노드이므로 상위로 진행
				y = y/2;
			}else {
				// 왼쪽노드이므로 계산하고 좌측으로 한칸 이동				
				reSum += tree[y];
				y--;
				y = y/2;
			}
		}
		return reSum;		
	}
}
