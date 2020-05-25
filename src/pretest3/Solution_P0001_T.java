package pretest3;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.StringTokenizer;

/*
 * (중상) [연습P-0001] 큰수만들기
 */
public class Solution_P0001_T {

static int T, K, N;
//static int[] reArr = new int[500001];
	
	public static void main(String[] args) throws Exception {
		
		FileInputStream fi = new FileInputStream(new File(Solution_P0001_T.class.getResource("").getPath() + "Solution_P0001.txt"));
		System.setIn(fi);
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));	
		
		T = Integer.parseInt(br.readLine());
		String num;
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		for (int t=1; t<=T; t++) {
			// 초기화 
			sb.setLength(0);
			sb.append("#");
			sb.append(t);
			sb.append(" ");
			// N자리 숫자에서 K개를 지운다. 
			st = new StringTokenizer(br.readLine());
			
			N = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());			

			num = br.readLine();
			
			char[] cnum = num.toCharArray();
					
			LinkedList<Character> sta = new LinkedList<>();	// statck 구조(LIFO)
			int selIdx = 0; // 지운수
			int maxIdx = 0;
			
			for(int i=0; i<N; i++) {
				// 선택할 대상중에 최대숫자를 선택한다. (다음숫자와 비교하여 크면 선택)
				while(K > 0 && !sta.isEmpty() && sta.getFirst() < cnum[i]) {
					if(selIdx == K) break; // 모두 선택 완료 함
					sta.poll(); // stack에서 제거한다. 
					selIdx++;   // 지운수 1 증가
				}
				sta.push(cnum[i]);
			}
			
			while(!sta.isEmpty()) {
				sb.append(sta.getLast());
				sta.pollLast();
				maxIdx++;
				if(maxIdx == N-K) break;
			}
			
			System.out.println(sb.toString());
			
			
		} // end test case		
	} // end main

}
