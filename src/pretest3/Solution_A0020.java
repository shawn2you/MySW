package pretest3;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.StringTokenizer;

/*
 * (중) [연습A-0020] 탑 
 */
class Top{
	int id;
	int h;
	Top(int id, int h){
		this.id = id;
		this.h  = h;
	}
}

public class Solution_A0020 {


	static int T, M, N, Sum;
	
	public static void main(String[] args) throws Exception {
		
		FileInputStream fi = new FileInputStream(new File(Solution_A0020.class.getResource("").getPath() + "Solution_A0020.txt"));
		System.setIn(fi);
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));	
		
		T = Integer.parseInt(br.readLine());
		
		for (int t=1; t<=T; t++) {
			// 초기화 
			Sum = 0;

			// 탑의 수 N
			N = Integer.parseInt(br.readLine());
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			Top[] top = new Top[N+1];
			
			for(int i=1; i<=N; i++) {
				top[i] = new Top(i, Integer.parseInt(st.nextToken()));
			}
			
			// Top의 검증 방향을 반대로 생각해서,
			// 오른쪽으로 이동시 다음 탑이 길이가 낮으면 해당 탑과 송수신이 된다. 
			// 이동시 더 높은 탑이 나오면 해당 탑은 더이상 수신 받을 수 없으니 빼고, 다음탑을 담는다.
			LinkedList<Top> ts = new LinkedList<>();
			
			for(int i=1; i<=N; i++) {
				// 6 9 5 7 4
				while(!ts.isEmpty() && ts.peek().h < top[i].h) {
					ts.pop();
				}
				
				if(!ts.isEmpty()) {
					Sum = (Sum + ts.peek().id) % 1000000007;
				}
				// 다음 탑을 집어 넣는다.
				ts.push(top[i]);
			}
			
			System.out.println("#"+t+" " + Sum);
			
			
		} // end test case		
	} // end main

}
