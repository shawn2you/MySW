package pretest4;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

/* 
 * (중상) [교육P-0008] 임계 경로 
 */
public class Solution_SP0008 {

	static int T, M, N, Sum;
	static int[] inDegree = new int[100001];
	static int[] time = new int[100001];	
	
	static class Node {
	    int e, c;
	     
	    Node( int e, int c) {
	        this.e = e;
	        this.c = c;
	    }
	}
	static ArrayList<Node>[] Job = new ArrayList[100001];

	public static void main(String[] args) throws Exception {
		
		FileInputStream fi = new FileInputStream(new File(Solution_SP0008.class.getResource("").getPath() + "Solution_SP0008.txt"));
		System.setIn(fi);
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));	
		
		T = Integer.parseInt(br.readLine());
		
		for (int t=1; t<=T; t++) {
			// 초기화 
			Sum = 0;
			Arrays.fill(inDegree, 0);
			Arrays.fill(time, 0);
			
			
			// N개의 공정. 벨트의 수 M (1 ≤ N ≤ 100,000, 1 ≤ M ≤ 300,000)
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			
			for(int i=0; i<=N; i++) {
				Job[i] = new ArrayList<Node>();
			}
			
			int a, b, c;
			for(int i=0; i<M; i++) {
				 st = new StringTokenizer(br.readLine());
				 a = Integer.parseInt(st.nextToken());
				 b = Integer.parseInt(st.nextToken());
				 c = Integer.parseInt(st.nextToken());
				 
				 inDegree[b]++;
				 
				 Job[a].add(new Node(b, c));
			}
			caluCost();
			
			
//			Queue<Integer> que = new LinkedList<Integer>();
//			que.add(1); // 1번공정에서 시작
//			time[1] = 0;
//			
//			int curr, next;
//			Node temp;
//			while(!que.isEmpty()) {
//				curr = que.poll();
//				
//				for(int i=0; i<Job[curr].size(); i++) {
//					temp = Job[curr].get(i);
//					next = temp.e;
//					time[next] = Math.max(time[next], time[curr]+temp.c); // 현재공정까지 걸린시간 갱신										
//					inDegree[next]--;
//					if(inDegree[next] == 0) {
//						que.add(next);
//					}					
//				}
//			}
			
			System.out.println("#"+t+" "+time[N]);
		} // end test case		
	} // end main
	
	public static void caluCost() {
        Queue<Integer> que = new LinkedList<Integer>();
        que.add(1); // 1번공정에서 시작함
 
        Node nd;
        int next, curr, cost;
        while (!que.isEmpty()) {
        	curr = que.poll();
 
            if (inDegree[N] == 0) {
                // System.out.println("---------------"+N);
                break;
            }
 
            for (int j = 0; j < Job[curr].size(); j++) {
                nd = Job[curr].get(j);
                next = nd.e;
                cost = nd.c;
                // 방문하면서 비용을 누적한다.
                time[next] = Math.max(time[next], time[curr] + cost);
 
                if (--inDegree[next] == 0)
                	que.add(next);
            }
        }
    }

}
