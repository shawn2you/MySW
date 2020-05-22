package pretest3;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * (중상) [기출P-0006] 아름다운 비트문자열 
 */
public class Solution_P0006 {

static int T, K, N;
static long X;
static int maxV = 1001;
static final long INF = (long) (Math.pow(2, 60));
static long[][] C = new long[maxV][maxV];
	
	public static void main(String[] args) throws Exception {
		
		FileInputStream fi = new FileInputStream(new File(Solution_P0006.class.getResource("").getPath() + "Solution_P0006.txt"));
		System.setIn(fi);
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));	
		
		T = Integer.parseInt(br.readLine());
		
		// Pascal 삼각함수
		C[0][0] = 1;
		
		for(int i=1; i<maxV; i++) {
			for(int j=0; j<=i; j++) {
				if(j==0) {
//					C[i][j] = C[i-1][j];
					C[i][j] = Math.min(C[i-1][j], INF);;
				}else {
//					C[i][j] = C[i-1][j] + C[i-1][j-1];
					C[i][j] = Math.min(C[i-1][j] + C[i-1][j-1], INF);
				}
			}			
		}
		
		StringBuilder sb = new StringBuilder();
		for (int t=1; t<=T; t++) {
			// 초기화 
			sb.setLength(0);
			sb.append("#").append(t).append(" ");
			
			// 1 ≤ K ≤ N ≤ 1,000
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			X = Long.parseLong(st.nextToken());
			
			for(int i = 1; i <= N; i++) {
				// N개중에 K를 선택하는 순열에서 
				// 오름 차순이므로 1을 선택하지 않은 경우(작은 숫자)와 선택한 경우(큰숫자)로 나눠서 생각
				// N-1개중에 K를 선택하는 개수 보다 X가 크면 
				// N-1개중에 K-1을 선택하는 개수에 포함이 된다. 
                if(X <= C[N - i][K]) {
                    sb.append("0");
                }
                else {
                	sb.append("1");
                    X -= C[N - i][K]; // 앞에순서만큼 빼준다.(시작 초기화)
                    K--; // 1을 개 사용했으니 빼준다
                }
            }
			
			System.out.println(sb.toString());
			
			
		} // end test case		
	} // end main

}
