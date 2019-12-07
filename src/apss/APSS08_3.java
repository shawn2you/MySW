package apss;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * DP : http://algospot.com/judge/problem/read/TRIANGLEPATH
 * https://gwpark.tistory.com/1815
 */
public class APSS08_3 {
	static int T, N;
	static int[][] cache = new int[101][101];
	static int[][] ret   = new int[101][101];

	public static void main(String[] args) throws Exception{
		FileInputStream fi = new FileInputStream(new File(APSS08_3.class.getResource("").getPath() + "APSS08_3.txt"));
		System.setIn(fi);
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		T = Integer.parseInt(br.readLine());
		StringTokenizer st = null;
		
		for(int t = 0; t < T; t++) {
            N = Integer.parseInt(br.readLine());
//            cache = new int[N][N]; // 초기화(많을 경우 for 문 처리)
//            ret   = new int[N][N]; 
            
            for(int i = 0; i < N; i++) {
            	st = new StringTokenizer(br.readLine());
                for(int j = 0; j <= i; j++){
                	cache[i][j] = Integer.parseInt(st.nextToken());
                	ret[i][j] = -1;
                }
            }
            
//            System.out.println(solve());
            
            System.out.println(solve2(0,0));
        }
        
	} // end main
	
	
	// 아래에서 부터 탐색 시작
	static int solve() {
        for(int i = N - 2; i >= 0; i--) {
            for(int j = 0; j <= i; j++) {
                cache[i][j] += Math.max(cache[i + 1][j], cache[i + 1][j + 1]);
            }
        }
        return cache[0][0];
    }
	
	static int solve2(int x, int y) {
		if(y == N -1) return ret[x][y] = cache[x][y];
		
		if(ret[x][y] != -1) return ret[x][y];
		
        return ret[x][y] = cache[x][y] + Math.max(solve2(x + 1,y), solve2(x + 1, y + 1));
       
    }
}
