package pretest;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.StringTokenizer;
 
 
//class Node implements Comparable<Node>{
//  int start;
//  int end;
//  long speed;
//  
//  public Node(int start, int end, long speed) {
////        super();
//      this.start = start;
//      this.end   = end;
//      this.speed = speed;     
//  }
//  
//  @Override
//  public int compareTo(Node o) {
//      // 속도 기준으로 정렬(min)
//      if(o.speed > this.speed)        { 
//          return 1;
//      }else if(o.speed < this.speed){
//          return -1;
//      }else{
//          return 0;
//      }
//  }
//}
 
class Node{
    int start;
    int end;
    long speed;
     
    public Node(int start, int end, long speed) {
//      super();
        this.start = start;
        this.end   = end;
        this.speed = speed;     
    }
}
 
 
public class preA0031 {
     
    static int T, N, E, C, K, M;
//  static PriorityQueue<Node> pq;
    static int[] parent;
     
    // union-find 
    public static int find(int a){
        // 최초 정접 탐색시 자기 자신이 부모가 됨
        if(a == parent[a]) return a;
         
        // 탐색할때 부모는 최상위로 셋팅(성능개선)
        parent[a] = find(parent[a]);
         
        return parent[a];
    }
    public static void union(int a, int b){
        int aRoot = find(a);
        int bRoot = find(b);
         
        if(aRoot > bRoot){
            parent[b] = aRoot;
        }else{
        	parent[a] = bRoot;
        }        
    }
     
    public static long makeGraph(ArrayList<Node> al, int idx, int start, int end){
        int nStart, nEnd;
        long lSpeed = 0, dSpeed = -1;
         
        for(int i=1; i<=N; i++){
            parent[i] = i;
        }
         
        for(int i=idx; i<M; i++){
            Node nd = al.get(i);
             
            if(i == idx) lSpeed = nd.speed; // 하한값 설정
             
            nStart = nd.start;
            nEnd   = nd.end;
             
            // 해당 간선을 선택할 경우 사이클이 생기면 안되므로, 
            // 양쪽의 최상위(부모) 노드를 확인하여 
            // 만약 같으면 선택하지 않는다. (사이클)               
            int sRoot = find(nStart); 
            int eRoot = find(nEnd);
                             
            if(sRoot == eRoot) continue; // 싸이클
                         
             
            // 두개의 노드가 최상위가 다르다면 한쪽의 최상위 부모를 다른쪽의 부모 설정하고 선택한다.;
            if(sRoot != eRoot){             
                parent[sRoot] = eRoot;
            }
             
            // 시작과 끝 지점이 하나의 트리로 묶이면 중단하고 그때 노드의 상한, 하한을 계산
            if(find(start) == find(end)){               
                dSpeed = nd.speed - lSpeed;
                break;
            }
        }
        return dSpeed; // dSpeed가 -1 이면 신장트리가 만들어지지 않은것이다. 
    }
     
/*
[제한사항] 
1. 지점의 수 N 은 1 이상 1,000 이하의 정수이며, 각 지점은 1 부터 N 사이의 숫자로 표현한다.
2. 도로의 수 M 는 1 이상 4,000 이하의 정수이다.
3. 각 도로마다 유지해야 하는 속도는 1 이상 1,000,000,000(10억) 이하의 정수이다.
4. 시작 지점에서 도착 지점까지 주어진 도로를 이용하여 도달하지 못하는 경우는 주어지지 않는다.
 */
     
    public static void main(String[] args) throws Exception {
        FileInputStream fi = new FileInputStream(new File(preA0031.class.getResource("").getPath() + "preA0031.txt"));
        System.setIn(fi);
         
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
         
         
//      T = Integer.parseInt(br.readLine());
        T = Integer.valueOf(br.readLine());
        StringTokenizer st = null;
         
        for(int t=1; t<=T; t++){        
/*
[입력] 
맨 처음 테스트 케이스의 개수 C 가 주어지며 그 다음 C 개의 테스트 케이스가 주어진다. 
각 테스트 케이스는 여러 줄로 구성되며 첫 줄에 N, M 의 값이 공백으로 구분되어 주어진다. 
그 다음 M개의 줄에 각 도로의 정보를 나타내는 정수 A, B, S 가 공백으로 구분되어 주어진다. 
A 와 B 는 도로가 연결하고 있는 두 지점의 번호이며, S 는 해당 도로에서 유지해야 하는 속도이다. 
다음 줄에 시작 지점의 번호와 도착 지점의 번호가 공백으로 구분되어 주어진다.
 */
            st = new StringTokenizer(br.readLine());            
            N = Integer.valueOf(st.nextToken()); // 지점(노드)의 수
            M = Integer.valueOf(st.nextToken()); // 도로(간선)의 수
             
//          pq = new PriorityQueue<Node>(new Comparator<Node>() {
//              @Override
//              public int compare(Node arg0, Node arg1) {
//                  if(arg0.speed > arg1.speed){
//                      return 1;
//                  }else{
//                      return -1;
//                  }
//              }
//          });
             
            ArrayList<Node> al = new ArrayList<Node>();
             
            int start, end;
            long speed;
             
            for(int i=1; i<=M; i++){
                st = new StringTokenizer(br.readLine());
                start = Integer.valueOf(st.nextToken()); // A 지점
                end   = Integer.valueOf(st.nextToken()); // B 지점
                speed = Long.valueOf(st.nextToken()); // S 속도
                 
//              pq.add(new Node(start, end, speed));
                al.add(new Node(start, end, speed));
            }
             
            Collections.sort(al, new Comparator<Node>() {
                @Override
                public int compare(Node arg0, Node arg1) {
                    if(arg0.speed > arg1.speed){
                        return 1;
                    }else{
                        return -1;
                    }                   
                }
            });
             
            st = new StringTokenizer(br.readLine());
            start = Integer.valueOf(st.nextToken()); // 시작 지점
            end   = Integer.valueOf(st.nextToken()); // 도착 지점
             
/*           
문제 풀이의 핵심 아이디어는 가중치의 하한을 정해두고 최소 스패닝 트리를 만드는 것이다. 
간선을 선택할 때 그 간선의 가중치가 하한보다 작다면 그 간선은 선택되지 않는다. 
또한 간선을 가중치가 작은 순서부터 선택하기 때문에 이 문제의 조건인 시작점과 끝점이 연결되는 순간 알고리즘을 종료시키고, 
그 때의 가중치를 저장하면 그 가중치가 선택된 간선들의 가중치의 최대 값이 된다.
 
 
(접근방법)
1. 간선정보 가중치 오름차순 정렬
2. 간선정보 탐색
  - 현재 탐색중인 간선의 가중치를 임시 하한값으로 설정
  - 가중치가 하한값 이상인 간선만 사용하여 크루스칼 알고리즘으로 최소신장트리 만들기
    (최소신장트리 전체를 만들필요는 없음, 중간에 시작->끝 노드 이어지면 크루스칼 중단)
    1) 시작-끝 노드가 같은 그룹으로 묶인다면, 묶이는 순간 선택된 간선이
        시작 -> 끝 노드로 이동 가능한 가중치 범위의 상한값, 정답갱신
    2) 하한값 이상인 간선을 모두 사용해도 이어지지 않으면 간선정보 탐색 중단
       (다음 간선을 임시 하한값으로 설정해도 마찬가지로 시작 -> 끝 노드 못감)
 */
            // 부모노드는 우선 자신으로 셋팅(초기화)
            parent = new int[N+1];
            for(int i=1; i<=N; i++){
                parent[i] = i;
            }
             
            // 간선 탬색 시작         
            long dSpeed = 0;  // 상한값
            long ans = 1000000000;     // 차이값           
             
            for(int i=0; i<M; i++){
//              Node nd = pq.poll(); // 가장 작은 속도    
//              Node nd = al.get(i);
                 
                dSpeed = makeGraph(al, i, start, end);              
                 
                if(dSpeed == -1) break; // 신장트리가 생성되지 않으므로 중단
                ans = Math.min(ans, dSpeed);
            }
             
            System.out.println("#"+t+ " " + ans);
 
        } // end T
         
         
/*
[출력]
각 테스트 케이스에 대해 #x (x는 테스트 케이스 번호, 1부터 시작) 을 출력하고 공백을 하나 둔 다음, 
주어진 테스트 환경에서 시작 지점에서 도착 지점으로 가는 경로 중 최저속도와 최고속도의 차이가 가장 작은 경로의 최저속도와 최고속도의 차이를 출력한다.
 */
    } // main
 
}
