package pretest3;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * (중상) [연습P-0001] 큰수만들기
 */
public class Solution_P0001 {

static int T, K, N;
//static int[] reArr = new int[500001];
	
	public static void main(String[] args) throws Exception {
		
		FileInputStream fi = new FileInputStream(new File(Solution_P0001.class.getResource("").getPath() + "Solution_P0001.txt"));
		System.setIn(fi);
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));	
		
		T = Integer.parseInt(br.readLine());
		String num;
		char max;
		int idx = 0;
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		for (int t=1; t<=T; t++) {
			// 초기화 
			idx = 0;
			sb.setLength(0);
			sb.append("#");
			sb.append(t);
			sb.append(" ");
			// N자리 숫자에서 K개를 지운다. 
			st = new StringTokenizer(br.readLine());
			
			N = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());			

			num = br.readLine();			

			// N-K 자리가 선택할 숫자 자리수
			int len = N-K;
			int endIdx = K+1;
			for(int i=0; i<len; i++){
				max = '0';
				// 6(N)자리중에 2(K)개 제거시 (6-2 자리)
				// 6, 4, 5 (3, 2, 1) 중에 1개 6선택 [0][0][2=2+0]
				//    4, 5, 3 (2, 1) 중에 1개 5선택 [1][1][3=2+1]
				//          3  2 (1) 중에 1개 3선택 [2][3][4=2+2]
				//             2, 1  중에 1개 2선택 [3][4][5=2+3]
				for(int j=idx; j<=K+i; j++){
					char tmp = num.charAt(j);
					if(max < tmp){
						max = tmp;
						idx = j + 1; // 다음 시작포지션(선택한 다음 위치)
					}
					if(max == 9) continue; // 1자리에서 최대값이나오면 더이상 탐색 불필요
				}
				sb.append(max);
				
				endIdx++;
				
				if(idx == endIdx) {
					sb.append(num.substring(idx, N-idx));
					break;
				}
			}
			
//			int[] arr = new int[N];
//			for(int i = 0; i< N; i++) {
//				arr[i] = Integer.parseInt(num.substring(i, i+1));				
//			}
//
//			int pos = 0;
//			for(int i=0; i<N-K; i++){				
//				pos = searchMax(arr, i, pos, N-(N-K-i));
//			}			
			
//			for(int i = 0; i < N-K; i++) {
////				System.out.print(arr[i]);
//				sb.append(reArr[i]);
//			}
			
			System.out.println(sb.toString());
			
			
		} // end test case		
	} // end main

	// 최대값 찾기
//	static int searchMax(int[] arr, int n, int pos, int end){
//		int max = 0;
//		int rePos = 0;
//		for(int i = pos; i <= end; i++){
//			if(arr[i] > max){
//				max = arr[i];
//				rePos = i;
//			}
//		}
//		reArr[n] = max;
//		return rePos+1;
//	}
}
