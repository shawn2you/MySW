package exetest;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

/*
 * (중상) [기출P-0034] 팀짜기 
 */
public class Solution_TP0034 {

	static int T, K, N, Sum;
	static int[] A;   // 입력받은 값	
	
	static ArrayList[] num;
	
	@SuppressWarnings("unchecked")
	public static void main(String[] args) throws Exception {
		
		FileInputStream fi = new FileInputStream(new File(Solution_TP0034.class.getResource("").getPath() + "Solution_TP0034.txt"));
		System.setIn(fi);
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));	
		
		T = Integer.parseInt(br.readLine());
		
		for (int t=1; t<=T; t++) {
			// 초기화 
			Sum = 0;

			// 사람의 수 N 과 제외할 수 있는 사람의 최대 수 K (1 ≤ K < N ≤ 100,000) 
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			
			A = new int[N + 1];
			
			num = new ArrayList[N+1];
			for(int i=0; i<=N; i++) {
				num[i] = new ArrayList<Integer>();
			}
			
			// 같은 번호끼리 인덱스 정보를 저장하고, 
			// 해당 번호에서 양쪽 끝 인덱스를 유지하면서 범위안에 포함되는 제외 사람 수의 개수를 계산
			
			st = new StringTokenizer(br.readLine());
			for(int i=1; i<=N; i++) {
				A[i] = Integer.parseInt(st.nextToken());
				num[A[i]].add(i);
			}
			
			for(int i=1; i<=N; i++) {
				// 각 같은번호 끼리의 인덱스내에 정보를 비교하여 계산
				// 팀의 최대 인원 출력
				Sum = Math.max(Sum, calcNum(i));;
			}
			
			
			System.out.println("#"+t+" "+Sum);
		} // end test case		
	} // end main

	public static int calcNum(int n) {
		int reSum = 0;
		int size = num[n].size();
		int cnt = 0;
		int left=0, right=0;
		
		while(right < size) {
			if((int)num[n].get(right) - (int)num[n].get(left) - cnt <= K) {
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
