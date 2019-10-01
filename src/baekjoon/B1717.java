package baekjoon;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * Union & Find
 * 집합의 표현 : https://www.acmicpc.net/problem/1717
 */
public class B1717 {

	static int parent[];
	static int N, M;
	
	public static void main(String[] args) throws Exception {
		FileInputStream fi = new FileInputStream(new File(B1717.class.getResource("").getPath() + "B1717.txt"));
		System.setIn(fi);
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.valueOf(st.nextToken());
		M = Integer.valueOf(st.nextToken());	
		
		int order, s, e;
		
		parent = new int[N+1];
		for(int i=0; i<=N; i++) {
			parent[i] = i; // 자기 자신을 셋팅
		}		
		
		for(int i=0; i<M; i++) {			
			st = new StringTokenizer(br.readLine());
			order = Integer.valueOf(st.nextToken());
			s     = Integer.valueOf(st.nextToken());
			e     = Integer.valueOf(st.nextToken());
			
			// 명령어 처리
			if(order == 0) {
				// 합집합 처리
				unionParent(s, e);
			}else {
				// 합집합 확인
				if(1 == findParent(s, e)) {
					System.out.println("YES");
				}else {
					System.out.println("NO");
				}
			}
		}

	} // main 

	// 부모를 찾는 함숙
	public static int getParent(int a) {
		if(parent[a] == a) return a;
		
		return parent[a] = getParent(parent[a]);		
	}
	
	// node를 합치는 합치는 함수
	public static void unionParent(int a, int b) {
		a = getParent(a);
		b = getParent(b);
		
		if(a > b) parent[b] = a;
		else if(a < b) parent[a] = b;
	}
	// 합집합인지 확인하는 함수
	public static int findParent(int a, int b) {
		a = getParent(a);
		b = getParent(b);
		
		if(a == b) return 1;
		else return 0;
	}
	
	
	
} // class