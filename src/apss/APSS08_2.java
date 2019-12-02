package apss;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * DP : https://algospot.com/judge/problem/read/WILDCARD
 */
public class APSS08_2 {
	static int T, N;
	public static void main(String[] args) throws Exception{
		FileInputStream fi = new FileInputStream(new File(APSS08_2.class.getResource("").getPath() + "APSS08_2.txt"));
		System.setIn(fi);
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		T = Integer.parseInt(br.readLine());
		StringTokenizer st = null;
		
		for(int t=1; t<=T; t++){
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(br.readLine());
		
		// * 카드 기준으로 문자를 탐색한다. 
			
			
		} // end test case

	} // end main

}
