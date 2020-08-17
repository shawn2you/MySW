package pretest5;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 롤러코스터
 * @author shawn
 *
 */
public class Solution_EP0002 {
	static int Sum, T, L, N, B;
	static int INF = Integer.MAX_VALUE;
	static class Item{
		int s, e, c;
		long f;
		Item(int s, int e, int c, long f){
			this.s = s;
			this.e = e;
			this.c = c;
			this.f = f;
		}
	}
	static ArrayList<Item> il[] = new ArrayList[10001];
	
	static int[] cost;
	static long[] happy;
	static int[] indegree;
	
	public static void main(String[] args) throws Exception {
		FileInputStream fi = new FileInputStream(new File(Solution_EP0002.class.getResource("").getPath() + "Solution_EP0002.txt"));
		System.setIn(fi);
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		T = Integer.parseInt(br.readLine());
		
		for(int t=1; t<=T; t++){
			
			// 롤러코스터 길이 L (1 ~ 1000)
			// 부품 N (1 ~ 10000), 부품길이 W (1 ~ L)
			// 재미도 F, 비용 C, 예산 B
			StringTokenizer st = new StringTokenizer(br.readLine());
			L = Integer.parseInt(st.nextToken());
			N = Integer.parseInt(st.nextToken());
			B = Integer.parseInt(st.nextToken());
			// 초기화
			for(int i=0; i<=L; i++){
				il[i] = new ArrayList<>();
				
			}
			indegree = new int[L+1];
			cost = new int[L+1];
			happy    = new long[L+1];
			
			for(int i=1; i<=N; i++){
				st = new StringTokenizer(br.readLine());
				// X, W, F, C
				int s = Integer.parseInt(st.nextToken());
				int w = Integer.parseInt(st.nextToken());
				int e = s + w;
				int f = Integer.parseInt(st.nextToken());
				int c = Integer.parseInt(st.nextToken());
				
				il[s].add(new Item(s, e, c, f));
				indegree[e]++;
			}
			// 위상정렬
			topological();
			
			System.out.println("#" + t + " " + happy[L]);
		} // end test case
		
	} // end main
	
	static void topological(){
		Queue<Integer> aq = new LinkedList<Integer>();
		
		aq.add(0);
		cost[0] = 0;
		
		int curr, end;
		Item citem;
		while(!aq.isEmpty()){
			curr = aq.poll();
			
			for(int i=0; i<il[curr].size(); i++){
				citem = il[curr].get(i);
				// 재미도가 높은것을 선택한다.
				if(happy[citem.e] < happy[citem.s] + citem.f){
					// 비용 계산 d[i] = d[i-1] + c[i]
					cost[citem.e] = cost[citem.s] + citem.c;
					if(cost[citem.e] > B) continue;
					happy[citem.e] = happy[citem.s] + citem.f;
				}
				
				if(--indegree[citem.e] == 0){
					aq.add(citem.e);
				}
			}
		}
		
		
	}

}
