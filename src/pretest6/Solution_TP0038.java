package pretest6;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.StringTokenizer;

/*
 * (중상) [기출P-0038] 키컸으면 
 */
public class Solution_TP0038 {

	static int T, Q, N, Sum;
	static long[] tree;
	
	public static void main(String[] args) throws Exception {
		
		FileInputStream fi = new FileInputStream(new File(Solution_TP0038.class.getResource("").getPath() + "Solution_TP0038"));
		System.setIn(fi);
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));	
		
		T = Integer.parseInt(br.readLine());
		
		for (int t=1; t<=T; t++) {
			// 초기화 
			Sum = 0;

			//  사람의 수 N 은 1 이상 300,000 이하의 자연수, 질문의 수 Q 는  1 이상 300,000 이하의 자연수
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			Q = Integer.parseInt(st.nextToken());
			
			
			int S = 2;
			while(S < N) {
				S *= 2;
			}
			tree = new long[S*2 + 1];
			
/*
[입력]  
입력 파일의 제일 첫째 줄에는 파일에 포함된 케이스의 수 T가 주어진다. 
각 케이스의 첫째 줄에 줄 서 있는 사람의 수 N 과 철수가 스스로에게 하는 질문의 수 Q 가 주어진다. 
둘째 줄에는 N 개의 자연수가 공백으로 구분되어 주어지는데, 줄 서 있는 사람들의 키가 차례대로 주어진다. 
셋째 줄부터 Q 개의 줄에 질문에 대한 정보가 주어진다. 
각 줄에는 “a b x” 세 개의 수가 공백으로 구분되어 주어지는데, 
이는 “a번째 사람부터 b번째 사람까지 해당하는 구간에서 키가 x보다 큰 사람이 몇명이나 있지?”라는 질문을 의미한다. 
여기서, a ≤ b를 항상 만족한다.			
 */
			
			st = new StringTokenizer(br.readLine());
			
			// 175 182 178 179 170 179 171 185 185 181 
			// 위치정보와 키정보를 가지고 있고, 키순서대로 정렬한다.			
			ArrayList<int[]> al = new ArrayList<>();
			
			for(int i=1; i<=N; i++) {
//				tree[S + i] = Integer.parseInt(st.nextToken());
				al.add(new int[] {i, Integer.parseInt(st.nextToken())});
			}

			for(int i=0; i<al.size(); i++) {
				System.out.println(al.get(i)[0] + ", " + al.get(i)[1]);
			}
			Collections.sort(al, new Comparator<int[]>() {

				@Override
				public int compare(int[] o1, int[] o2) {
					if(o1[1] > o2[1]) {
						return 1;
					}else {
						return -1;
					}
				}
			});
			
			for(int i=0; i<al.size(); i++) {
				System.out.println(al.get(i)[0] + ", " + al.get(i)[1]);
			}
			
			// 부모 트리 초기화
			// 구간내 X보다 큰사람을 찾는것은 구간내 X보다 큰 사람만 Tree를 구성한다. (각 위치에는 사람이 1명만 있으니 구간합으로 구성)
			// 질의의 X를 큰사람순으로 정렬하여, Tree를 삽입연산하면서 구간내 합을 구한다. 

			
			for(int i=0; i<Q; i++) {
				st = new StringTokenizer(br.readLine());
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
				int x = Integer.parseInt(st.nextToken());
				
				for(int k=0; k<S; i++) {
					tree[k] = 0;
				}
				
				// 찾기
				
				
				
				
			}
			
			System.out.println("#"+t+" "+Sum);
		} // end test case		
	} // end main

	static void find(int a, int b, int x) {
		
	}
}
