package baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;

public class b11279 {

	public static void main(String[] args) throws Exception{
		// TODO Auto-generated method stub

		/*
		첫째 줄에 ?��?��?�� 개수 N(1?��N?��100,000)?�� 주어진다. ?��?�� N개의 줄에?�� ?��?��?�� ???�� ?��보�?? ?��???��?�� ?��?�� x�? 주어진다.
		 만약 x�? ?��?��?��?���? 배열?�� x?��?�� 값을 ?��?��(추�??��?��) ?��?��?���?,
		x�? 0?��?���? 배열?��?�� �??�� ?�� 값을 출력?���? �? 값을 배열?��?�� ?��거하?�� 경우?��?��. ?��?��?��?�� ?��?��?��?�� 2^31보다 ?��?��.		
		
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
