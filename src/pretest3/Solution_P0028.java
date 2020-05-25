package pretest3;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/*
 *  (중) [기출A-0028] 제품의 일련번호 2 
 */
public class Solution_P0028 {

static int T, N, Sum;
static String S, E;
static int[] visited = new int[19];
	
	public static void main(String[] args) throws Exception {
		
		FileInputStream fi = new FileInputStream(new File(Solution_P0028.class.getResource("").getPath() + "Solution_P0028.txt"));
		System.setIn(fi);
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));	
		
		T = Integer.parseInt(br.readLine());
		
		
		for (int t=1; t<=T; t++) {
			// 초기화 
			Sum = 0;
			Arrays.fill(visited, 0);
			
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken()); // 알파벳의 개수  N
			S = st.nextToken();  // 일련번호 시작
			E = st.nextToken();  // 일련번호 끝
			
			// abcd 기준 총 건수는 4! (24개)
			// 첫번째 a문자가 b로 변경될려면 a뒤의 남은 문자열만큼 이동된다. 
			// abcd -> bacd까지 이동개수는 1*3! (6개)
			// abcd -> dabc까지 이동개수는 3*3!
			
			
			System.out.println("#"+t+" "+ (Math.abs(getIndex(E) - getIndex(S)) - 1));
					
			
		} // end test case		
	} // end main

	
	public static long getIndex(String num){
		long result = 1;
		
		for(int i=1; i<=N; i++) {
			visited[i] = 0;
		}
		
		int seq, cnt;
		
		for(int i=0; i<N; i++) {
			seq = (int)(num.charAt(i) - 'a' + 1);
			visited[seq] = 1;
			cnt = checkDiff(seq); 
			result += cnt*factorial(N-i-1);
		}
		return result;
	}
	
	public static int checkDiff(int seq) {
		int diff = 0;
		
		// 순서상 몇번째 인지 확인(차이만큼 이동한 것으로 봄)
		for(int i=1; i<=seq; i++) {
			if(visited[i] == 0) {
				diff++;
			}
		}
		return diff;
	}
	
	public static long factorial(long value) {
		if(value == 0) {
			return 1;
		}
		return value * factorial(value -1);
	}
	
	public static long factorial2(int n) {
		long rval = 1;
		for(int i=1; i<=n; i++) {
			rval *= i;
		}
		return rval;
	}
}
