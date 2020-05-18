package pretest3;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.StringTokenizer;

/*
 * 1일차 3번 문제
 * (중) [연습A-0013] 피크닉 
 */
public class Solution_A0013 {
	static int T, K, M, N, Sum;
	static ArrayList<Integer>[] road;
	
	static int[] place = new int[1001];
	static int[] visited = new int[1001];

	public static void main(String[] args) throws Exception {
		
		FileInputStream fi = new FileInputStream(new File(Solution_A0013.class.getResource("").getPath() + "Solution_A0013.txt"));
		System.setIn(fi);
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));	
		
		T = Integer.parseInt(br.readLine());
		
		for (int t=1; t<=T; t++) {
			// 초기화 
			Sum = 0;

			// K(1 <= K <= 100),N(1 <= N <= 1,000),M(1 <= M <= 10,000)
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			K = Integer.parseInt(st.nextToken()); // 소
			N = Integer.parseInt(st.nextToken()); // 목초지
			M = Integer.parseInt(st.nextToken()); // 길
						
			
			// 처음 소들이 풀을 뜯어 먹는 위치
			int[] cow = new int[K + 1];
			for(int i = 0; i < K; i++) {
				cow[i] = Integer.parseInt(br.readLine()); // 소의 시작위치
			}
			
			road = new ArrayList[N + 1];
			
			for(int i = 0; i<=N; i++) {
				place[i] = 0; // 초기화(목초지 소 숫자)
				road[i] = new ArrayList<>();
			}
			// 목초장 이동정보(단방향) road(start).add(end)			
			for(int i = 0; i<M; i++) {
				st = new StringTokenizer(br.readLine());
				int start = Integer.parseInt(st.nextToken());
				int end   = Integer.parseInt(st.nextToken());
				road[start].add(end);
			}
			
			for(int i = 0; i<K; i++) {
				for(int n=0; n<=N; n++) {
					visited[n] = 0; //초기화
				}
//				move(cow[i]); // i번째 소가 출발한 위치
				move2(cow[i]); // i번째 소가 출발한 위치
			}

			for(int i=0; i<=N; i++) {
				if(place[i] == K) {
					Sum++;
				}
			}
			System.out.println("#"+t+" " + Sum);

			
		} // end test case		
	} // end main
	
	// 소들이 이동하는 위치를 표시(K소가 방문한 목초장 표시)
	// 해당 목초지에 방문시 방문 count 증가(소의 숫자와 같으면 모두 모인 장소가 됨
	static void move(int start) {
		if(visited[start] == 0) {
			visited[start] = 1;
			place[start]++;			
		}
		int next;
		// 방문했다면 다음 위치로 이동
		for(int i=0; i < road[start].size(); i++ ) {
			next = road[start].get(i);
			if(visited[next] == 0) {
				// 방문하지 않았다면
				visited[next] = 1;
				place[next]++;
				
				move(next);
			} else {
			    // 이미 방문했다면 종료
				return;
			}
			
		}
		return;
	}
	
	static void move2(int start) {
		LinkedList<Integer> lu = new LinkedList<>();
		lu.add(start);
		visited[start] = 1;
		place[start]++;
		
		while(!lu.isEmpty()) {
			int curr = lu.pop();
			for(int i=0; i < road[curr].size(); i++ ) {
				int next = road[curr].get(i);
				if(visited[next] == 0) {
					lu.add(next);
					visited[next] = 1;
					place[next]++;
				}
			}			
		}		
		return;
	}
}
