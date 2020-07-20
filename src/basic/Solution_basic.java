package basic;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution_basic {
	// 변수 처리 
	static int T;
	static long Res;

	public static void main(String[] args) throws Exception {
		// 파일 처리 
		FileInputStream fi = new FileInputStream(new File(Solution_basic.class.getResource("").getPath() + "basic"));
		System.setIn(fi);
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		T = Integer.parseInt(br.readLine());
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		for(int t=1; t<=T; t++){
			Res = 0;
			sb.setLength(0);
			sb.append("#").append(t).append(" ");
			
			// 경우의 수 구성하기 (순열)
			int[] arr = {1, 2, 3};				
			order(arr, 0, arr.length);
			
			
			
			
			
			sb.append(Res);
			System.out.println(sb.toString());
		}		
	} // end main
	
	// 경우의 수 구성하기 (순열)
	static void order(int[] arr, int depth, int size){
		if(depth == size){
			for(int i=0; i<arr.length; i++){
				System.out.print(arr[i] + ",");
			}
			System.out.println();
			return;
		}
		
		for(int i=depth; i<size; i++){
			swap(arr, i, depth);
			order(arr, depth + 1, size);
			swap(arr, i, depth);
		}
	}
	static void swap(int[] arr, int x, int y){
		int temp = arr[x];
		arr[x] = arr[y];
		arr[y]= temp;
	}
	
	
}