package bj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * 그래프
 * 섬의 개수 : https://www.acmicpc.net/problem/4963
 */

//class Map{
//	int x;
//	int y;
//	int land;
//	public Map(int x, int y, int land) {
//		this.x = x;
//		this.y = y;
//		this.land = land;
//	}
//}

class BJ4963 {

	static int T=0, ans, w, h;
	static int[][] land;
	public static void main(String[] args) throws Exception{
//		FileInputStream fi = new FileInputStream(new File(BJ4963.class.getResource("").getPath() + "BJ4963.txt"));
//		System.setIn(fi);
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		
		StringTokenizer st;
		
		
		while(true){
			T++;
			ans = 0;
			st = new StringTokenizer(br.readLine());
			
			w = Integer.valueOf(st.nextToken());
			h = Integer.valueOf(st.nextToken());
			
			if(w == 0 && h == 0) break;
			
			land = new int[w][h];
			
			for(int j=0; j<h; j++){
				st = new StringTokenizer(br.readLine());
				for(int i=0; i<w; i++){
					land[i][j] = Integer.valueOf(st.nextToken());
				}
			}
			
			// 메인로직
			for(int i=0; i<w; i++){
				for(int j=0; j<h; j++){
					
					if(land[i][j] == 1){
						ans++;
						search(i, j); // 연결섬을 찾는다.
					}
				}
			}
			
			System.out.println(ans);
		}
	} // main 
	
	public static void search(int x, int y){
		//  범위를 벋어나는 경우 제외 처리 
		if(x < 0 || y < 0 || x > w-1 || y > h-1) return;
		if(land[x][y] == 1){
			land[x][y] = 0; // 방문 처리 
			// 좌우
			search(x-1, y);
			search(x+1, y);
			// 상하
			search(x, y+1);
			search(x, y-1);
			// 상단대각선
			search(x+1, y+1);
			search(x-1, y+1);
			// 하단대각선
			search(x-1, y-1);
			search(x+1, y-1);
		}else{
			return;
		}
	}
}