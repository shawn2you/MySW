package pretest;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class preA0029 {
	
	static long startTime = System.currentTimeMillis();
	
	
	/*
[입력]
맨 처음 테스트 케이스의 개수 T가 주어지며, 그 다음 줄부터 T개의 테스트 케이스가 주어진다.     
각 테스트 케이스의 첫 줄에는 판매점의 수 N과 상사의 명령의 수 Q가 공백으로 구분되어 주어진다.
다음 Q개의 줄에 상사의 명령이 순서대로 주어진다. 입력되는 형식은 [제한사항]의 5 ~ 7번을 참고하시오.


[제한사항]
1. 판매점의 수 N은 1 이상 100000 이하의 자연수다.
2. 상사의 명령 Q는 1 이상 100000 이하의 자연수다.
3. 입력으로 주어지는 납품 수량은 1 이상 100 이하의 자연수다.
4. 입력으로 주어지는 세트 상품 판매 요청 수량은 1 이상 10000000 이하의 자연수다.
5. 납품: 1 x y k c
   => x번 판매점부터 y번 판매점까지(x≤y) 순서대로 상품코드가 k인 상품을 c개씩 납품
6. 판매: 2 x c
   => x번 판매점으로 c개 세트 상품 판매 요청
7. 판매수량 조사: 3 x y 
   => x번 판매점부터 y번 판매점까지 누적 판매된 신상품 세트의 수량 조사

[출력]
각각의 테스트 케이스에 대하여 #x (x는 테스트 케이스 번호, 1부터 시작)을 출력하고 공백을 하나 둔 다음
출력을 간단하게 하기 위해 판매수량 조사 명령으로 조사되는 결과의 합을 출력한다.
	 */
	
	static int T, N, Q; // N 상점수, Q 상사의 명령
	static double Sum;

//	static class Store{
//		int x; // 모자(code=1)
//		int y; // 상의(code=2)
//		int z; // 하의(code=3)
//		int small=0; // 상품최소수량
//		double sum=0; // 누적판매
//		int sm[] = new int[3];
//				
//		public void minCheck(){
//			Arrays.sort(sm);
//			this.small = sm[0];
//			
////			this.small = Math.min(Math.min(this.x, this.y), this.z);
//		}
//		
//		public void Purchase(int code, int cnt){
//			if(code == 1){
//				this.x = this.x + cnt;
//				sm[0] = this.x;
//			}else if(code == 2){
//				this.y = this.y + cnt;
//				sm[1] = this.y;
//			}else if(code == 3){
//				this.z = this.z + cnt;
//				sm[2] = this.z;
//			}
//		}
//		
//		public void Sale(int cnt){
////			System.out.println("1=" + cnt);
//			// 재고가 만매수량에 만족해야 함(주문셋트수량내에서 셋트구성이 되면 셋트구성만큼 판매)
//			// 재고 상품중 최소값 찾기
//			minCheck();
//			if(cnt > this.small){
//				cnt = this.small;
//			}
//			if(this.x >= cnt && this.y >= cnt && this.z >= cnt){					
//				this.x = this.x - cnt;
//				this.y = this.y - cnt;
//				this.z = this.z - cnt;
//				sum = sum + cnt;
//			}
//		}
//	}
	
//	static Store St[] = new Store[100001]; // 상점수만큼 메모리 할당
	static int[][] NSt = new int[100001][4];
	static int[] storeC = new int[3];
	
	public static void main(String[] args) throws Exception{		
		
		FileInputStream fi = new FileInputStream(new File(preA0029.class.getResource("").getPath() + "preA0029.txt"));
		System.setIn(fi);
		
//		Scanner sc = new Scanner(System.in);
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		T = Integer.parseInt(br.readLine());
				
		StringTokenizer st = null;
		
		for(int i=1; i<=T;i++){
			Sum = 0;
			st = new StringTokenizer(br.readLine());
			
			N = Integer.parseInt(st.nextToken()); // 상점수
			Q = Integer.parseInt(st.nextToken()); // 명령수
						
			for(int s=1; s <= N; s++){				
//				St[s] = new Store(); // 상점 초기화
				NSt[s][0] = 0;
				NSt[s][1] = 0;
				NSt[s][2] = 0;
				NSt[s][3] = 0;
			}
			
			int Order = 0;
//			int bfOrder = 0;
			
			for(int q=0; q < Q; q++){
				st = new StringTokenizer(br.readLine());
				
				Order = Integer.parseInt(st.nextToken());
				
				if(Order == 1){
					// 입고
					int start = Integer.parseInt(st.nextToken());
					int end   = Integer.parseInt(st.nextToken());
					int code = Integer.parseInt(st.nextToken());
					int pCnt = Integer.parseInt(st.nextToken());				
					
					// 성능 개선 포인트 찾기
//					for(int x=start; x <= end; start++ ){
//						St[start].Purchase(code, pCnt);
//					}
					
					// 상품입고시 for문 횟수를 줄이기 위해										
					PurchaseAll(start, end, code, pCnt);
				}else if(Order == 2){
					// 판매
//					St[Integer.parseInt(st.nextToken())].Sale(Integer.parseInt(st.nextToken()));
					int storeN = Integer.parseInt(st.nextToken());
					int cnt   = Integer.parseInt(st.nextToken());
					
					storeC[0] = NSt[storeN][1];
					storeC[1] = NSt[storeN][2];
					storeC[2] = NSt[storeN][3];
					
					Arrays.sort(storeC);					
					int small = storeC[0];
					
					if(cnt > small){
						cnt = small;
					}
					if(NSt[storeN][1] >= cnt && NSt[storeN][2] >= cnt && NSt[storeN][3] >= cnt){					
						NSt[storeN][1] = NSt[storeN][1] - cnt;
						NSt[storeN][2] = NSt[storeN][2] - cnt;
						NSt[storeN][3] = NSt[storeN][3] - cnt;
						NSt[storeN][0] = NSt[storeN][0] + cnt;
					}
					
				}else{
					// 수량조사( Order == 3 )
					int x = Integer.parseInt(st.nextToken());
					int X = Integer.parseInt(st.nextToken());
					for(; x <= X; x++ ){
						Sum =  Sum + NSt[x][0];
					}
				}
				
//				bfOrder = Order;
			}
			
						
//			System.out.println("#" + i + " " + Sum);
			System.out.printf("#%d %.0f", i, Sum);
			System.out.println();			
		}// end for T
		long endTime = System.currentTimeMillis();
		System.out.println("##  소요시간(초.0f) : " + ( endTime - startTime )/1000.0f +"초");
	} // main 
	
	public static void PurchaseAll(int s, int e, int cd, int cn){		
		
//		int divide = (e - s)/2;
//		
//		if(divide <= 20000){
//			for(int i=s;i <= e ;i++){
//				St[i].Purchase(cd, cn);
//			}			
//			return;
//		}		
//		PurchaseAll(s, divide, cd, cn);
//		PurchaseAll(divide, e, cd, cn);
		int m = 1000;
		
		int divide = (e - s)/m;
		
		if(divide <= 100){
			for(int i=s;i <= e ;i++){
//				St[i].Purchase(cd, cn);
				NSt[i][cd] = NSt[i][cd] + cn;
			}
			return;
		}
		PurchaseAll(s, divide, cd, cn);
		for(int i=1;i < m-1; i++ ){
			PurchaseAll(divide*i+1, divide*(i+1), cd, cn);
		}		
		PurchaseAll(divide*m+1, e, cd, cn);
	}
}
