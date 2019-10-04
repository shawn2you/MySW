package apss;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;


public class APSS28_6_A {    
    static int[][] adj;
    static int[] seen;
    static ArrayList<Integer> order;
    
    static BufferedReader br;
    static BufferedWriter bw;
    
    public static int min(int a, int b) {
    	return a < b ? a : b;
    }
    
    public static void main(String args[]) throws IOException {
    	FileInputStream fi = new FileInputStream(new File(APSS28_6_A.class.getResource("").getPath() + "APSS28_6.txt"));
    	System.setIn(fi);
    	
        br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));
        
        run();
        bw.flush();
        br.close();
        bw.close();
    }
    
    public static void run() throws IOException {
    	int C = Integer.parseInt(br.readLine());
    	ArrayList<int[]> results = new ArrayList<>();
    	while(C-- > 0) {
        	int N = Integer.parseInt(br.readLine());
        	ArrayList<String> words = new ArrayList<>();
        	
        	while(N-- > 0) {
        		words.add(br.readLine());
        	}
        	bw.newLine();
    		makeGraph(words);
    		int[] result = topologicalSort();
        	results.add(result);
    	}
    	
    	int[] cache;
    	for(int[] result : results) {
    		if(result == null)
        		System.out.println("INVALID HYPOTHESIS");
        	else {
        		cache = new int[26];
        		for(int x : result) {
        			cache[x] = 1;
        			System.out.print((char)(x + 'a'));
        		}
        		
                for (int i = 0; i < adj.length; i++) {
                	if(cache[i] != 1)
                		System.out.print((char) (i + 'a'));
                }
                System.out.println();
            }
    	}
    }
    

    public static void makeGraph(ArrayList<String> words) {
    	adj = new int[26][26]; 
    	for(int j = 1; j < words.size(); ++j) {
    		int i = j-1, len = min(words.get(i).length(), words.get(j).length());
    		
    		// word[i]가 word[j]앞에 오는 이유
    		for(int k = 0; k < len; ++k) {
    			if(words.get(i).toCharArray()[k] != words.get(j).toCharArray()[k]) {
    				int a = words.get(i).toCharArray()[k] - 'a';
    				int b = words.get(j).toCharArray()[k] - 'a';
    				adj[a][b] = 1; // a < b
    				break;
    			}
    		}
    		
    	}
    }
    
    public static void dfs(int here) {
    	seen[here] = 1;
    	for(int there = 0; there < adj.length; ++there) {
    		if(adj[here][there] == 1 && seen[there] != 1){
    			dfs(there);
    		}
    	}
		order.add(here);
    }
    
    public static int[] topologicalSort() {
    	int n = adj.length;
    	seen = new int[n];
    	order = new ArrayList<>();
    	
    	for(int i = 0 ; i< n; ++i) 
    		for(int there = 0 ; there< n; ++there) 
	    		if(adj[i][there] == 1 && seen[i] != 1)
	    			dfs(i);
    	
    	// 그래프가 DAG가 아니라면(싸이클 있다면) 정렬 결과에 역방향 간선이 있다.
    	for(int i = 0; i < n ; ++i)
    		for(int j = i+1; j < n; ++j) {
    			if(j >= order.size()) break;
    			if(adj[order.get(i)][order.get(j)] == 1) {
    				return null;
    			}
    		}
    	
    	int s = order.size();
    	
    	int[] reversed = new int[s];
    	
    	int a = 0;
    	while(s-a > 0){
    		reversed[s-1] = order.get(a);
    		reversed[a] = order.get(s-1);
    		a++;
    		s--;
    	}
    	return reversed;
    }
}