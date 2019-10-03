package apss;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

/*
 * 알고리즘 문제해결 전략 
 * 고대어 사전(28.3) : https://algospot.com/judge/problem/read/DICTIONARY
 */
public class APSS28_3 {
	
	static int T, N;
	static int[] inDegree, location;
	static ArrayList<Integer>[] word;
	static ArrayList<ArrayList<Integer>> word2;
	static Queue<Integer> q;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		T = Integer.parseInt(br.readLine());
		
		for(int t=1; t<=T; t++){
			N = Integer.parseInt(br.readLine());
			q = new LinkedList<Integer>();
			word = new ArrayList[N];
			word2 = new ArrayList<>();
			inDegree = new int[26]; // 문자 순서
			location = new int[26]; // 입력된 고대문자 위치
			
			for(int i=0; i<N; i++){
				word[i] = new ArrayList<Integer>();
			}
			
			for(int i=0; i<26; i++){
				word2.add(new ArrayList<Integer>());
			}
			
//			int x = 'z'-'a';
			String st;
			for(int i=0; i<N; i++){
				st = br.readLine();
				for(int c=0; c<st.length(); c++){
					int idx = st.charAt(c) - 'a';
					word[i].add(idx);
					location[idx] = 1; // 입력문자
				}
			}
			// 그래프 생성(정렬순서에 맞게)
			for(int i=0; i<N-1; i++){
				// word[i] 와 word[i+1] 을 비교 한다. 
				int len = word[i].size() >  word[i+1].size() ? word[i+1].size() : word[i].size();
				
				for(int l=0; l<len; l++){
					if(word[i].get(l) != word[i+1].get(l)){
						word2.get(word[i].get(l)).add(word[i+1].get(l));
						inDegree[word[i+1].get(l)]++;
					}
				}
			}
			
			for(int i=0; i<26; i++){
				if(inDegree[i] == 0 && location[i] == 1)
					q.add(i);
			}
			
			topologicalSort();
					
			
			
		}// end Test case

	} // end main
	
	static void topologicalSort(){	
		StringBuilder sb = new StringBuilder();
		
		while(!q.isEmpty()){
			char idx = (char) (q.poll() + 'a');
			sb.append(idx);
			
			
		}
		
		System.out.println(sb.toString());
	}

}
