package apss;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

/*
 * DP : https://algospot.com/judge/problem/read/WILDCARD
 * https://redscreen.tistory.com/170
 */
public class APSS08_2 {
	static int T, N;
	static int[][] cache = new int[101][101];
	static char[] wc, fn;
	public static void main(String[] args) throws Exception{
		FileInputStream fi = new FileInputStream(new File(APSS08_2.class.getResource("").getPath() + "APSS08_2.txt"));
		System.setIn(fi);
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		T = Integer.parseInt(br.readLine());
		StringTokenizer st = null;
		String wildcard = null;
		String filename = null;
		for(int t=1; t<=T; t++){
			wildcard = br.readLine();
			N = Integer.parseInt(br.readLine());
			wc = wildcard.toCharArray();
			ArrayList<String> fnlist = new ArrayList<>();
			for(int i=0; i<N; i++){
				filename = br.readLine();
				fn = filename.toCharArray();
				
				// 초기화
				for(int x=0; x<=wc.length; x++){
					for(int y=0; y<=fn.length; y++){
						cache[x][y] = -1;
					}
				}
				
				// * 카드 기준으로 문자를 탐색한다. 
				if(match(0,0) == 1){
					fnlist.add(filename);
				}
			}
			
			Collections.sort(fnlist);
			for(int i=0; i<fnlist.size(); i++){
				System.out.println(fnlist.get(i));
			}
			
		} // end test case

	} // end main
	
	
	// 첫문자부터 와이드카드 기준으로 순차적으로 탐색한다. 
	public static int match(int w, int s){

		// 재귀호출 or while 처리 검토
		// 해당 위치가 이미 검증되었다면 바로 종료
		if(cache[w][s] != -1) return cache[w][s];
		

		
		// 같은지 검토(자리수별)
		if(w < wc.length && s < fn.length){
			// ? 이거나 같으면 다음으로 이동
			if(wc[w] == '?' || wc[w] == fn[s]){
				return cache[w][s] = match(w+1, s+1);
			}
		}
		// wildcard 기준으로 전체 탐색 후 filename 길이 비교하여 같으면 문자가 동일하다고 판단
		if(w == wc.length) return cache[w][s] = (s == fn.length)?1:0;
		
		// wildcard 문자를 만나면 문자 비교가 필요 없으므로, 
		// whildcard를... fllename을 한칸씩 이동하고 탐색
		if(wc[w] == '*'){
			if(match(w+1, s) == 1 || (match(w, s+1) == 1))
			return cache[w][s] = 1;
		}
		
		// 비정상인 경우 0으로 종료
		return cache[w][s] = 0;
	}

}
