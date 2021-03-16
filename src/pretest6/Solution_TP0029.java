package pretest6;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.StringTokenizer;

public class Solutioni_TP0029 {

	static int T, M, N, Sum;
	
	static ArrayList<int[]> al;
	public static void main(String[] args) throws Exception {
		
		FileInputStream fi = new FileInputStream(new File(Solutioni_TP0029.class.getResource("").getPath() + "Solutioni_TP0029"));
		System.setIn(fi);
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));	
		
		T = Integer.parseInt(br.readLine());
		
		StringTokenizer st;
		
		for (int t=1; t<=T; t++) {
			// 초기화 
			Sum = 0;
/*
입력 파일의 제일 첫째 줄에는 파일에 포함된 케이스의 수 T가 주어진다. 
각 케이스의 첫째 줄에는 선수들의 인원 N이 주어진다. (1 ≤ N ≤ 100,000) 
다음 N개의 줄에 각 선수들의 실력 쌍이 자연수 두개로 주어진다. 
 */
			//  선수들의 인원 N이 주어진다. (1 ≤ N ≤ 100,000)
			N = Integer.parseInt(br.readLine());
			
			al = new ArrayList<>();
			
			for(int i=0; i<N; i++) {
				// 실력 값(슈팅, 드리블 능력) 각각의 범위는 감독과 선수 모두 최소 1 최대 1,000,000,000 인 자연수이다. 
				st = new StringTokenizer(br.readLine());
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
				
				al.add(new int[] {a, b});
			}
			// a(슈팅) 오름차순으로 정렬
			
			al.add(new int[] {1, 1000000001});
			al.add(new int[] {1000000001, 1});
			
			Collections.sort(al, new Comparator<int[]>(){
				@Override
				public int compare(int[] o1, int[] o2) {
					if(o1[0] - o2[0] > 0) {
						return 1;
					}else {
						return -1;
					}					
				}				
			});
			
			for(int i=0; i<=N+1; i++) {
				System.out.println(al.get(i)[0]);
			}
			
			
			System.out.println("#"+t+" "+Sum);
		} // end test case		
	} // end main

}
