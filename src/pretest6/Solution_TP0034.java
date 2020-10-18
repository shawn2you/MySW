package pretest6;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Solution_TP0034 {
	static int T, N, K;
	static long SUM;
	
	static int[] num;
	static ArrayList[] numList;

	public static void main(String[] args) throws Exception {
		FileInputStream fi = new FileInputStream(new File(Solution_TP0034.class.getResource("").getPath() + "Solution_TP0034.txt"));
		System.setIn(fi); 
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		T = Integer.parseInt(br.readLine());
		
		StringTokenizer st;
		for(int t=1; t<=T; t++) {
			SUM = 0;
			// 사람의 수 N 과 제외할 수 있는 사람의 수 K 가 주어진다. (1 ≤ K < N ≤ 100,000) 
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			
			
			// 동일숫자를 배열에 담아 놓는다.(위치정보)
			numList = new ArrayList[N + 1];
			for(int i=0; i<=N; i++) {
				numList[i] = new ArrayList<Integer>();
			}
			
			st = new StringTokenizer(br.readLine());
			
			num = new int[N + 1];
			for(int i=0; i<N; i++) {
				num[i] = Integer.parseInt(st.nextToken());
				numList[num[i]].add(i);
			}
			
			
			// 동일번호내에서 탐색
			for(int i=0; i<=N; i++) {
				SUM = Math.max(SUM, calcNum(i));;
			}
			
			System.out.println("#"+t+" "+SUM);
			
		} // end test case
	}
	
	public static int calcNum(int n) {
		int reSum = 0;
		int size = numList[n].size();
		int cnt = 0;
		int left=0, right=0;
		// 최대길이까지 순차적으로 탐색하면서, 같은 번호를 선택한(cnt 값) 수의 최대값을 저장한다. 
		
		while(right < size) {
			if((int)numList[n].get(right) - (int)numList[n].get(left) - cnt <= K) {
				cnt++;
				right++;
			}else {
				cnt--;
				left++;
			}
			reSum = Math.max(reSum, cnt);
		}
		
		return reSum;
	}

}