package exetest;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Solution_TP0050 {
	
    static int T, N, seq;
    static long Sum;
    static int map[], cnt[], visited[]; 
	
	public static void main(String[] args) throws Exception {
		
		FileInputStream fi = new FileInputStream(new File(Solution_TP0050.class.getResource("").getPath() + "Solution_TP0050.txt"));
		System.setIn(fi);
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));	
		
		T = Integer.parseInt(br.readLine());
		
		for (int t=1; t<=T; t++) {
			// 초기화 
			seq = 0;
			Sum = 0;

			// 정점의 개수 N  (2 ≤ N ≤ 100,000) 
			N = Integer.parseInt(br.readLine());
			
            map = new int[N+1];
            visited = new int[N+1];
            cnt = new int[N+1];
             
            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int i=1;i<=N;i++){
                map[i] = Integer.parseInt(st.nextToken());
            }
                         
            Arrays.fill(cnt, -1);
            Arrays.fill(visited, 0);
            for(int i=1;i<=N;i++) {
                if(visited[i] == 0) {                   
                	seq = 1;                    
                    dfs(i);                 
                }
            }
            for(int i=1;i<=N;i++) {
            	Sum += ((long)cnt[i]*(long)(cnt[i]+1))/2;
            }
			
			System.out.println("#"+t+" "+Sum);
		} // end test case		
	} // end main

	static int dfs(int i) {
        int next = i;
        visited[next] = seq++;
         
        int ret = 0, ret2;  
        if(cnt[map[next]] != -1) 
            return cnt[next] = cnt[map[next]] + 1;
         
        if(visited[map[next]] == 0) {           
            ret2 = dfs(map[next]);
            if(cnt[i] == -1)// 사이클이 아니면
                ret = ret2 + 1;
            else ret = ret2;
        }else {
                     
            if(visited[i] >= visited[map[next]]) {
                int period = visited[i] - visited[map[next]] + 1;
                for(int j=0;j<period;j++) {         
                    ret = cnt[map[next]] = period - 1;
                    next = map[next];               
                }
            }else {
                ret = cnt[map[next]] + 1;
            }           
        }
         
        return cnt[i] = ret;
    }
}
