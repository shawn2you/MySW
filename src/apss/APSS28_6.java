package apss;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

/*
 * 단어 제한 끝말 잇기
 * https://www.algospot.com/judge/problem/read/WORDCHAIN
 */
public class APSS28_6 {
	static int T, N;
	static ArrayList<String>[][] s;
	static int[] inDegree;
	static Queue<Integer> q = new LinkedList<Integer>();
	
	@SuppressWarnings("unchecked")
	public static void main(String[] args) throws Exception {
		FileInputStream fi = new FileInputStream(new File(APSS28_6.class.getResource("").getPath() + "APSS28_6.txt"));
		System.setIn(fi);
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		T = Integer.parseInt(br.readLine());
		s = new ArrayList[26][26];
		
		for(int t=0; t<T; t++){
			q.clear();
			N = Integer.parseInt(br.readLine());
			
			for(int i=0; i<26; i++){
				inDegree[i] = 0;
				for(int j=0; j<26; j++){
					s[i][j] = new ArrayList<String>();
				}
			}
			
			
			for(int n=0; n<N; n++){
				String str = br.readLine();
				int st = str.charAt(0)-'a';
				int ed = str.charAt(str.length()-1)-'a';
				
				s[st][ed].add(str);
				inDegree[ed]++;
			}
			
			
			
			
		} // end test case
		
	} // end main 

}
