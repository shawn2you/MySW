package exetest;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Solution_TP0048 {

    static int T, N, M;
    static final char FOWARD  = '0';
    static final char REVERSE = '1';
    static List<Node> edgeList;
    static List<List<Integer>> adjList;
    static List<Node> bothEList;
	
    static class Node {
    	  
        int from;
        int to;
        boolean isRdirected;
        boolean isBothWay;
  
        public Node(int from, int to, boolean isBothway) {
            this.from = from;
            this.to = to;
  
            this.isBothWay = isBothway;
  
            this.isRdirected = false;
        }
    }
	

	
	public static void main(String[] args) throws Exception {
		
		FileInputStream fi = new FileInputStream(new File(Solution_TP0048.class.getResource("").getPath() + "Solution_TP0048.txt"));
		System.setIn(fi);
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));	
		
		T = Integer.parseInt(br.readLine());
		
		for (int t=1; t<=T; t++) {

			  
            StringTokenizer st = new StringTokenizer(br.readLine().trim());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
  
            edgeList = new ArrayList<>();
            bothEList = new ArrayList<>(); // 양방향
            adjList = new ArrayList<>();   // 단방향
  
            for(int i = 0; i < N ; i++) {
  
                adjList.add(new ArrayList<>());
  
            }
  
  
            for(int i = 0; i < M ; i++) {
  
                st = new StringTokenizer(br.readLine().trim());
                int from = Integer.parseInt(st.nextToken()) - 1;
                int to = Integer.parseInt(st.nextToken()) -1;
                boolean isBothWayE = EdgeDirectionType.isBothwayEdge(st.nextToken().trim());
  
                Node edge = new Node(from, to, isBothWayE);
  
                edgeList.add(edge);
  
                if(isBothWayE) {
                    bothEList.add(edge);
  
                }else {
                    adjList.get(from).add(to);
  
                }
            }
  
            int bothWayEdgeListSize = bothEList.size();
            char[] result = new char[bothWayEdgeListSize];
  
            for(int i = 0; i < bothWayEdgeListSize; i++) {
  
                boolean[] isVisited = new boolean[N];
                boolean[] isFinished = new boolean[N];
  
                int from = bothEList.get(i).from;
                int to = bothEList.get(i).to;
                  
                if (isConnected(to, from, isVisited, isFinished)) {
  
                    result[i] = REVERSE;
                    adjList.get(to).add(from);
  
                } else {
  
                    result[i] = FOWARD;
                    adjList.get(from).add(to);
                }
            }
  
  
            boolean isCycleGraph = false;
            boolean[] isVisited = new boolean[N];
            boolean[] isFinished = new boolean[N];
  
            for( int v = 0; v < N; v++) {
  
                if(isVisited[v]) {
                    continue;
                }
  
                if(isCycle(v, isVisited, isFinished)) {
  
                    isCycleGraph = true;
                    break;
  
                }
            }
  
            if(isCycleGraph) {
  
                System.out.println("#" + t + " impossible");
  
            } else {
  
                System.out.println("#" + t + " " + String.valueOf(result));
  
            }
		} // end test case		
	} // end main

    static boolean isConnected(int startIdx, int endIdx, boolean[] isVisited, boolean[] isFinished) {
  
        if(startIdx == endIdx){
  
            return true;
        }
          
        isVisited[startIdx] = true;
          
        for (int nextIdx: adjList.get(startIdx)) {
            if(!isVisited[nextIdx]) {
                if(isConnected(nextIdx, endIdx, isVisited, isFinished)) {
                    return true;
                }
            }
        }
  
        isFinished[startIdx] = true;
        return false;
    }
  
    static boolean isCycle(int currIdx, boolean[] isVisited, boolean[] isFinished) {
  
        isVisited[currIdx] = true;
        for(int nextIdx: adjList.get(currIdx)) {
            if(!isVisited[nextIdx]) {
                if(isCycle(nextIdx, isVisited, isFinished)) {
                    return true;
                }
            }
  
            else if(!isFinished[nextIdx]) {
                return true;
            }
        }
  
        isFinished[currIdx] = true;
        return false;
    }
  
    public enum EdgeDirectionType {
  
        BOTH_WAYS("0", 0),
        ONE_WAY("1", 1);
  
        EdgeDirectionType(String type, int value) {
  
            this.type = type;
  
            this.value = value;
        }
        static boolean isBothwayEdge(String type) { return BOTH_WAYS.type.equals(type);}
  
        private String type;
        private int value;
    }
  

}
