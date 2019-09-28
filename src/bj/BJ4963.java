package bj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * 그래프
 * 문제 : https://www.acmicpc.net/problem/4963
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
/*
 * 문제 고찰 
 * 1. 섬의 개수가 50*50으로 Map 형태로 처리 해도 무리는 없을듯함. 
 * 2. 섬여부를 1, 0으로 담아놓고, 순차적으로 탐색을 시작한다. 
 * 3. 첫번째 섬에서 상하좌우대각선(8방향)으로 연결 탐색한다. 
 * 4. 탐색한 섬의 개숫을 세고 방문한 섬은 0으로 변경하여 제외 시킨다.
 * 5. 연결된 섬이 없으면 다음 섬으로 이동하여 3~4를 반복한다.   
 */

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
			
			// 처리 로직
			for(int i=0; i<w; i++){
				for(int j=0; j<h; j++){
					// 재귀함수
					if(land[i][j] == 1){
						ans++;
						search(i, j); // 연결된 섬을 모두 0으로 처리
					}
				}
			}
			
			System.out.println(ans);
		}
	} // main 
	
	public static void search(int x, int y){
		//  지도를 벋어나는지 확인
		if(x < 0 || y < 0 || x > w-1 || y > h-1) return;
		if(land[x][y] == 1){
			land[x][y] = 0; // 탐색 처리 
			// 좌우탐색
			search(x-1, y);
			search(x+1, y);
			// 상하탐색
			search(x, y+1);
			search(x, y-1);
			// 상대각탐색
			search(x+1, y+1);
			search(x-1, y+1);
			// 하대각탐색
			search(x-1, y-1);
			search(x+1, y-1);
		}else{
			return;
		}
	}
}