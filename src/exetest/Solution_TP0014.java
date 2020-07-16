package exetest;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class Solution_TP0014 {

	static int T, N, K, seq;
	static int[] link, visit, vcnt;
	static long ans;
	public static void main(String[] args) throws Exception{
		FileInputStream fi = new FileInputStream(new File(Solution_TP0014.class.getResource("").getPath() + "Solution_TP0014.txt"));
		System.setIn(fi);
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		T = Integer.parseInt(br.readLine());
		
		for(int t=1; t<=T; t++){
			
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			
			link = new int[N + 1];
			visit = new int[N + 1];
			vcnt = new int[N + 1];
			
			
			st = new StringTokenizer(br.readLine());
			for(int i=1; i<=N; i++){
				link[i] = Integer.parseInt(st.nextToken());
			}
			
			seq = 1;
			ans = 0;
			for(int i=1; i<=N; i++){
				if(visit[i] == 0) find(i);
			}
			
			System.out.println("#" + t + " " + ans);
			
		} // end test case
	}

	static void find(int n){
		Stack<Integer> sta = new Stack<>();
		int curr = n;
		while(true){
			if(visit[curr] != 0){
				if(visit[curr] >= visit[n]){
					int cnt = seq - visit[curr];
					for(int i=0; i<cnt; i++){
						vcnt[sta.pop()] = cnt - 1;
					}
				}
				break;
			}
			sta.add(curr);
			visit[curr] = seq++;
			curr = link[curr];
		}
		
		while(!sta.isEmpty()){
			int top = sta.pop();
			vcnt[top] = vcnt[link[top]] + 1;
		}
	}
}
