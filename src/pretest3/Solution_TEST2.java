package pretest3;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/*
 * http://182.193.11.65/common/practice/problem/view.do?problemId=AXI2b4iAHPCojUHh&_menuId=AVUU732mAAHBC0c9&_menuF=true
 */
public class Solution_TEST2 {

static int T, Q, N, S, maxSum, minSum;
static int maxV = Integer.MAX_VALUE;
static int minV = -1;
static int[] maxTree;
static int[] minTree;
	
	public static void main(String[] args) throws Exception {
		
		FileInputStream fi = new FileInputStream(new File(Solution_TEST2.class.getResource("").getPath() + "Solution_TEST2.txt"));
		System.setIn(fi);
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));	
		
		T = Integer.parseInt(br.readLine());
		
		

		
		for (int t=1; t<=T; t++) {
			// 초기화 
			maxSum = 0;
			minSum = 0;
					
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken()); // 수열의 개수(Tree node 개수 산정 기준)
			Q = Integer.parseInt(st.nextToken());
			
			// 수열 담기
			st = new StringTokenizer(br.readLine());
			
			S = 2;
			while(S < N) {
				S *= 2; 
			}
			
			maxTree = new int[S * 2];
			minTree = new int[S * 2];
			
			Arrays.fill(maxTree, minV);
			Arrays.fill(minTree, maxV);
			
			for(int i = S; i < N+S; i++) {				
				maxTree[i] = Integer.parseInt(st.nextToken());
				minTree[i] = maxTree[i];
			}
			// 최대, 최소값 Tree 구성
			makeTree();	
			
			
			for(int i=0; i<Q; i++) {				
				st = new StringTokenizer(br.readLine());
				
				int q = Integer.parseInt(st.nextToken());
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
				
				int aIdx = a + S - 1;
				int bIdx = b + S - 1;
				if(q == 0) {
					// 최대값 찾기
					int min=maxV, max=minV;
					while(aIdx <= bIdx) {
						// 왼쪽
						if(aIdx%2 == 1) {
							max = Math.max(max, maxTree[aIdx]);
							min = Math.min(min, minTree[aIdx]);
							aIdx++;
						}
						// 오른쪽
						if(bIdx%2 == 0) {
							max = Math.max(max, maxTree[bIdx]);
							min = Math.min(min, minTree[bIdx]);
							bIdx--;
						}
						
						aIdx = aIdx/2;
						bIdx = bIdx/2;
					}
					maxSum += max;
					minSum += min;
					
//					System.out.println(min);
					
				}else{
					maxTree[aIdx] = b;
					minTree[aIdx] = b;
					// 상위 Node를 Root까지 변경처리 한다.  
					while(aIdx > 0) {
						aIdx = aIdx/2; // 부모 찾기
						maxTree[aIdx] = Math.max(maxTree[aIdx*2], maxTree[aIdx*2+1]);
						minTree[aIdx] = Math.min(minTree[aIdx*2], minTree[aIdx*2+1]);
					}
				}				
			}
			
			System.out.println("#"+t+" "+maxSum+" "+minSum);
			
			
		} // end test case		
	} // end main
	
	static void makeTree() {
		for(int i=S-1; i>0; i--) {
			maxTree[i] = Math.max(maxTree[i*2], maxTree[i*2+1]); // 두값중 최대값을 셋팅
			minTree[i] = Math.min(minTree[i*2], minTree[i*2+1]); // 두값중 최대값을 셋팅
		}
	}
}
