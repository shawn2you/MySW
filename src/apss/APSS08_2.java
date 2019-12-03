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
				for(int x=0; x<wc.length; x++){
					for(int y=0; y<fn.length; y++){
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
	
	public static int match(int w, int s){

		// 재귀호출 or while 처리 검토
		
		// 같은지 검토(자리수별)
		if(w < wc.length && s < fn.length){
			// ? 이거나 같으면 다음으로 이동
			if(wc[w] == '?' || wc[w] == fn[s]){
				return cache[w][s] = match(w+1, s+1);
			}
		}
		// wildcard 문자를 만나면, whildcar를... fllename을 한칸씩 이동
		if(wc[w] == '*'){
			if(match(w+1, s) == 1 || (match(w, s+1) == 1))
			return cache[w][s] = 1;
		}
		
		return cache[w][s] = 0;
	}

}
