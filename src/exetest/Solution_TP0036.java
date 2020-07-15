package exetest;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;

public class Solution_TP0036 {

	static int T, K, OrgLen, Sum;
	static int INF = 100000000;
	static String Org;
	static String[] Dic;
	
	public static void main(String[] args) throws Exception {
		
		FileInputStream fi = new FileInputStream(new File(Solution_TP0036.class.getResource("").getPath() + "Solution_TP0036.txt"));
		System.setIn(fi);
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));	
		
		T = Integer.parseInt(br.readLine());
		
		for (int t=1; t<=T; t++) {
			// 초기화 
			Sum = INF;

			K = Integer.parseInt(br.readLine());
			Org = br.readLine(); // 복원대상 단어
			OrgLen = Org.length(); // 문자길이
			
			Dic = new String[K+1]; // 사전
			// 사전의 단어 개수 K (1 ≤ K ≤ 100)
			for(int i=1; i<=K; i++) {
				Dic[i] = br.readLine();
			}
			
			// 동일한 단어는 연속으로 구성이 안됨
			// 사전은 중복된 단어가 없음
			// D[i][j] : j 번째 단어를 마지막으로 사용하면서 보고서의 i 번 문자까지 복원했을때 가능한 최소 단어수
			int[][] D = new int[OrgLen + 1][K + 1];
			
			for(int i=1; i<=OrgLen; i++) {
				for(int j=1; j<=K; j++) {
					D[i][j] = INF; // 초기화
				}
			}
			
			String tempOrg, word;
			int minV = 0;
			for(int i=1; i<=OrgLen; i++) {
				tempOrg = Org.substring(0, i); // 앞에서부터 탐색
				
				for(int j=1; j<=K; j++) {
					// 사전과 비교
					word = Dic[j];
					if(OrgLen == 1) {
						if(word.equals(Org)) {
							Sum = 1;
						}
					}
					
					if(tempOrg.length() >= word.length() && tempOrg.endsWith(word)) {
						// 사전과 같으면 
						int start = tempOrg.length() - word.length();
						minV = INF;
						
						for(int k=1; k<=K; k++) {
							// 동일 사전(연속)은 제외하고, 
							if(k != j) {
								// 마지막 단어를 제외했을때 
								minV = Math.min(D[start][k], minV + 0);
							}
						}
						D[i][j] = Math.min(D[i][j], minV + 1);
					}					
				}
			}
			
			for(int i=1; i<=K; i++) {
				Sum = Math.min(Sum,  D[OrgLen][i]);
			}
			
			if(Sum == INF) {
				System.out.println("#"+t+" -1");
			}else {
				System.out.println("#"+t+" "+Sum);
			}
		} // end test case		
	} // end main
}
