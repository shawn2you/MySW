package baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;

/*
 * 힙
 * 최대힙 : https://www.acmicpc.net/problem/11279
 */
public class B11279 {

	public static void main(String[] args) throws Exception{
		// TODO Auto-generated method stub

		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		
//		PriorityQueue<Integer> intPq = new PriorityQueue<Integer>();
		PriorityQueue<Integer> intPq = new PriorityQueue<Integer>(new Comparator<Integer>() {
				@Override
				public int compare(Integer a, Integer b) {
					if(a > b) return a;
					return b;
				}
			}				
		);
		
		int x = 0;
		for(int i=0; i < N; i++){
			x = Integer.parseInt(br.readLine());
			if(x == 0){
				if(intPq.isEmpty()){
					sb.append("0\n");
					continue;
				}else{
					sb.append(intPq.poll()+"\n");
				}
			}else{
				intPq.offer(x);
			}
			
		}
		
		System.out.print(sb);
	}
}
