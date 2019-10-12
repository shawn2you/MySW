package apss;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * 에디터 전쟁 : https://algospot.com/judge/problem/read/EDITORWARS
 */

/*
1. 전형적인 Union Find 자료구조처럼 해당 노드의 루트를 찾는 find 함수와 집합들을 합치는 merge 함수를 정의해줍니다.

2. 두 노드 사이가 적인지 판별하는 dis 함수와 아군인지 판별하는 ack 함수를 정의하는데 모순 발생 시 false를 반환하도록 반환형을 boolean으로 선언합니다.

i) dis 함수에서 같은 집합에 속해 있으면 false를 반환하고, 적의 적은 아군이기 때문에 u와 enemy[v]를 합치고 v와 enemy[u]를 합친 뒤 서로를 적이라고 표시해줍니다.

ii) ack 함수에서도 비슷한 맥락으로 서로가 적대 관계라면 false를 반환하고, 아군의 적은 나의 적이므로 u와 v를 합치고 enemy[u]와 enemy[v]를 합친 뒤 서로를 적이라고 표시해줍니다.

3. 설명들을 입력할 때 2번에서 false를 반환하는 설명이 있다면 해당 설명의 인덱스를 표시하고 숫자들을 다 입력받은 뒤 해당 인덱스와 함께 모순이라고 출력해줍니다.

4. 모순인 설명이 없을 경우 한 파티에 올 가능성이 있는 최대 인원을 구해줍니다.

i) 같은 모임 쌍을 두 번 세지 않기 위해, enemy < node인 경우만 세줍니다.

ii) 적이 없는 노드들도 한번씩 세줍니다.

iii) 아군과 적군 중 규모가 큰 쪽을 출력해줍니다.
 */
public class APSS25_2 {
	static int T, N, M;
//	static ArrayList<ArrayList> nl = new ArrayList<>();
	static int[] parents = new int[100001];
	static int[] others  = new int[100001];
	static int[] size  = new int[100001];
	public static void main(String[] args) throws Exception{
		FileInputStream fi = new FileInputStream(new File( APSS25_2.class.getResource("").getPath() + "APSS25_2.txt"));
		System.setIn(fi);
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		T = Integer.parseInt(br.readLine());
		for(int t=1; t<=T; t++){
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			N = Integer.parseInt(st.nextToken()); // 회원수
			M = Integer.parseInt(st.nextToken()); // 댓글수
			for(int i=0; i<N; i++){
//				nl.add(new ArrayList<>());
				parents[i] = i; // 자기자신을 부모로 초기화 처리
				others[i] = -1; // 초기값 확인 필요
			}
			String order;
			int start, end;
			for(int i=0; i<M; i++){
				st = new StringTokenizer(br.readLine());
				order = st.nextToken();
				start = Integer.parseInt(st.nextToken()); // 시작
				end   = Integer.parseInt(st.nextToken()); // 종료
								
//				nl.get(start).add(end);
				
				if("ACK".equals(order)){
					checkAck(start, end);
				}else{
					checkDis(end, start);
				}
			}
			
			System.out.println("#");
		}// end test case
	} // end main
	
	static int findParent(int a){
		if(parents[a] == a) return a;
		return parents[a] = findParent(parents[a]);
	}

	static int unionParent(int a, int b){
		int pA = findParent(a);
		int pB = findParent(b);
		// 큰쪽 기준으로 셋팅
		if(pA > pB) {
			parents[b] = pA;
//			size[b] = size[b] + size[a];
			return pA;
		}else if(pA < pB) {
			parents[a] = pB;
//			size[b] = size[b] + size[a];
			return pA;
		}
		return a;
	}
	// Ack 인경우 검증
	static boolean checkAck(int a, int b){
		int pA = findParent(a);
		int pB = findParent(b);
		// 두 최상위 부모가 적대 관계이면 오류 
		if(others[pA] == pB) return false;
		// 상위 부모가 적이 아니면 합치기
		pA = unionParent(a, b);
		// 나의 적의 적은 나의 동지 
		// 나의 적의 동지는 적
		pB = unionParent(others[a], others[b]);
		others[a] = pB;
		others[b] = pB;
		
		return true;
	}
	
	// Dis 인경우 검증
		static boolean checkDis(int a, int b){
			int pA = findParent(a);
			int pB = findParent(b);
			// 두 최상위 부모가 같으면 오류 
			if(pA == pB) return false;
			// 나의 적의 적은 나의 동지 
			// 나의 적의 동지는 적
			pA = unionParent(a, others[b]);
			pB = unionParent(b, others[a]);
			others[a] = pB;
			others[b] = pB;
			
			return true;
		}	
}
