package baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;

public class b11279 {

	public static void main(String[] args) throws Exception{
		// TODO Auto-generated method stub

		/*
		ì²«ì§¸ ì¤„ì— ?—°?‚°?˜ ê°œìˆ˜ N(1?‰¤N?‰¤100,000)?´ ì£¼ì–´ì§„ë‹¤. ?‹¤?Œ Nê°œì˜ ì¤„ì—?Š” ?—°?‚°?— ???•œ ? •ë³´ë?? ?‚˜???‚´?Š” ? •?ˆ˜ xê°? ì£¼ì–´ì§„ë‹¤.
		 ë§Œì•½ xê°? ??—°?ˆ˜?¼ë©? ë°°ì—´?— x?¼?Š” ê°’ì„ ?„£?Š”(ì¶”ê??•˜?Š”) ?—°?‚°?´ê³?,
		xê°? 0?´?¼ë©? ë°°ì—´?—?„œ ê°??¥ ?° ê°’ì„ ì¶œë ¥?•˜ê³? ê·? ê°’ì„ ë°°ì—´?—?„œ ? œê±°í•˜?Š” ê²½ìš°?´?‹¤. ?…? ¥?˜?Š” ??—°?ˆ˜?Š” 2^31ë³´ë‹¤ ?‘?‹¤.		
		
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
