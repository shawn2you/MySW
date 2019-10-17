package pretest;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;


class Node2 implements Comparable<Node2>{
	int a, c;
	public Node2(int a, int c){
		super();
		this.a = a;
		this.c = c;
	}
	@Override
	public int compareTo(Node2 o) {
		return this.c > o.c ? 1 : -1;
	}	
}

public class stuP0008 {
	static int T, N, M;
	static ArrayList<ArrayList<Node2>> al = new ArrayList<ArrayList<Node2>>();
	static int[] inDegree = new int[100001];
	static int[] inCost   = new int[100001];

	public static void main(String[] args) throws Exception {
		FileInputStream fi = new FileInputStream(new File(stuP0008.class.getResource("").getPath() + "stuP0008.txt"));
        System.setIn(fi);
         
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));         
         
        T = Integer.parseInt(br.readLine());
      
        StringTokenizer st = null;
         
        for(int t=1; t<=T; t++){
        	st = new StringTokenizer(br.readLine());
        	
        	N = Integer.parseInt(st.nextToken()); // ����(����)
        	M = Integer.parseInt(st.nextToken()); // ��Ʈ(����)
        	
        	for(int i=0; i<=N; i++) {
        		al.add(new ArrayList<Node2>());
        		inCost[i] = 0;
        		inDegree[i] = 0;
        	}
		
/*
[�Է�] 
�Է� ���Ͽ��� ���� �׽�Ʈ ���̽��� ���Ե� �� �ִ�. 
������ ù° �ٿ� ���̽��� ���� T�� �־�����, ���� ���ʷ� T�� �׽�Ʈ ���̽��� �־�����. (1 �� T �� 15) 
�� ���̽��� ù �ٿ� ������ �� N�� �����̾� ��Ʈ�� �� M�� �������� ���еǾ� �־�����. (1 �� N �� 100,000, 1 �� M �� 300,000)
�׸��� ���� M���� �ٿ� �� �����̾� ��Ʈ�� ���� ������ ��Ÿ���� �� ������ �������� ���еǾ� �־�����. 
"a b c"��� �־����� �� a�� �������� b�� �������� �۾��� �������ϴµ� �����µ� �ɸ��� �ð��� c��� ���� �ǹ��Ѵ�. (1 �� a, b �� N, 1 �� c �� 10,000, a �� b)
�۾��� �׻� 1�� �������� �����ϸ�, N�� �������� ������. 
�Է����� �־����� ������ �׷����� ǥ������ ��, �׷����� DAG(Directed Acyclic Graph)�� �Ǹ�, 
1�� �������� ������ ������ ������ 0�̰�, N�� �������� ������ ������ ���� ���� 0�̴�. �׸��� �׻� �۾��� �Ϸ�ǵ��� �Է��� �־�����.
 */
        	int a, b, c;
        	for(int i=0; i<M; i++) {
        		st = new StringTokenizer(br.readLine());
            	
            	a = Integer.parseInt(st.nextToken()); // ����
            	b = Integer.parseInt(st.nextToken()); // ����
            	c = Integer.parseInt(st.nextToken()); // ���

            	// ���� ����
        		al.get(a).add(new Node2(b, c));
        		inDegree[b]++;
        	}
		
        	caluCost();
        	
/*
[���] 
�� �׽�Ʈ ���̽��� ���� ������� ǥ��������� ����ϸ�, 
�� ���̽����� ���� ���ۿ� ��#x���� ����Ͽ��� �Ѵ�. �̶� x�� ���̽��� ��ȣ�̴�. 
���� �ٿ�, 1�� �������� �۾��� �����Ͽ� N�� �������� �۾��� �Ϸ�Ǵµ� �ɸ��� �ּ� �ð��� ����Ѵ�.		
 */
        	System.out.println("#"+t+ " " + inCost[N]);
        	
        } // end test case
	}
	
	public static void caluCost() {
		PriorityQueue<Node2> pq = new PriorityQueue<Node2>();
		
		pq.offer(new Node2(1, 0)); // 1���������� ������    	

    	Node2 nd;
    	int next, curr, cost;
    	while(!pq.isEmpty()) {
    		nd = pq.poll();
    		curr = nd.a;
    		
    		if(inDegree[N] == 0) {
    			System.out.println("---------------");
    			break;
    		}
    		
    		for(int j=0; j<al.get(curr).size(); j++) {
    			nd = al.get(curr).get(j);
    			next = nd.a;
    			cost = nd.c;
    			// �湮�ϸ鼭 ����� �����Ѵ�. 
    			inCost[next] = inCost[curr] + cost;    			
    			
    			if(--inDegree[next] == 0) pq.offer(new Node2(next, inCost[next]));
    		}
    	}
	}


}
