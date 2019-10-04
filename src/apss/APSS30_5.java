package apss;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

/*
 * 소방차 : https://algospot.com/judge/problem/read/FIRETRUCKS#
 * 다익스트라
 */

class Node{
	int x;
	int y;
	int tm;
	public Node(int x, int y, int tm){
		super();
		this.x = x;
		this.y = y;
		this.tm = tm;
	}
}
public class APSS30_5 {

	static int T, V, E, n, m;
	// 방향없는 간선으로 양방향 셋팅 필요
	static ArrayList<Node> adj = new ArrayList<Node>();
	
	public static void main(String[] args) throws Exception {
		FileInputStream fi = new FileInputStream(new File(APSS30_5.class.getResource("").getPath() + "APSS30_5.txt"));
		System.setIn(fi);
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		T = Integer.parseInt(br.readLine());
		
		
		for(int t=0; t<T; t++){
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			V = Integer.parseInt(st.nextToken()); // 장소 V(2≤V≤1000)
			E = Integer.parseInt(st.nextToken()); // 도로(간선) E(0≤E≤V*(V-1)/2) , 1000*999/2 
			n = Integer.parseInt(st.nextToken()); // 화재장소 수
			m = Integer.parseInt(st.nextToken()); // 소버서 수
			
			int s, e, d;
			int[] time = new int[V+1]; // 해당 장소의 최소값
			
			for(int i=0; i<E; i++){
				st = new StringTokenizer(br.readLine());
				s = Integer.parseInt(st.nextToken()); // 시작
				e = Integer.parseInt(st.nextToken()); // 종료
				d = Integer.parseInt(st.nextToken()); // 시간
										
				adj.add(new Node(s, e, d));
				
				
			}
		
		}
		
		

	}

}
