package baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;

public class b11279 {

	public static void main(String[] args) throws Exception{
		// TODO Auto-generated method stub

		/*
		μ²«μ§Έ μ€μ ?°?°? κ°μ N(1?€N?€100,000)?΄ μ£Όμ΄μ§λ€. ?€? Nκ°μ μ€μ? ?°?°? ??? ? λ³΄λ?? ????΄? ? ? xκ°? μ£Όμ΄μ§λ€.
		 λ§μ½ xκ°? ??°??Όλ©? λ°°μ΄? x?Ό? κ°μ ?£?(μΆκ???) ?°?°?΄κ³?,
		xκ°? 0?΄?Όλ©? λ°°μ΄?? κ°??₯ ?° κ°μ μΆλ ₯?κ³? κ·? κ°μ λ°°μ΄?? ? κ±°ν? κ²½μ°?΄?€. ?? ₯?? ??°?? 2^31λ³΄λ€ ??€.		
		
5
3
0
2
1
0
		*/
		
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
