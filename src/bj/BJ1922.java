package bj;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.StringTokenizer;

/*
 * 최소 스패닝 트리(MST)
 * 네트워크 연결 : https://www.acmicpc.net/problem/1922
 */

class Computer{
	int a;
	int b;
	int c;
	public Computer(int a, int b, int c){
		this.a = a;
		this.b = b;
		this.c = c;
	}
}

public class BJ1922 {
	
	static int N, M;
	int[] parent;
	// find
	public int find(int x){
		if(parent[x] == x) return x;
		parent[x] = find(x);
		return parent[x];
	}
	// union
	
	
	public static void main(String[] args) throws Exception {
		FileInputStream fi = new FileInputStream(new File(BJ1922.class.getResource("").getPath() + "BJ1922.txt"));
		System.setIn(fi);
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.valueOf(br.readLine());
		M = Integer.valueOf(br.readLine());
		
		ArrayList<Computer> al = new ArrayList<Computer>();
		StringTokenizer st;
		for(int i=0; i<M; i++){
			st = new StringTokenizer(br.readLine());
			al.add(new Computer(Integer.valueOf(st.nextToken()), Integer.valueOf(st.nextToken()), Integer.valueOf(st.nextToken())));
		}
		
		Collections.sort(al, new Comparator<Computer>() {
			@Override
			public int compare(Computer c1, Computer c2) {				
				return c1.c >= c2.c ? 1:-1;
			}
		});
		
		
		

	}

}
