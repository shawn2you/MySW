package pretest2;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;
 
 
public class preA0033 {
     
    static int T, N, M, K, ans;
    static char[][] pro = new char[1001][101];
    static int[] eng = new int[26];
    static int[] visited = new int[26];
    static int[][] Ieng = new int[101][26];
           
/*
[제한사항] 
1. N 은 1 이상 5000 이하의 자연수, M 은 1 이상 100 이하의 자연수이다. 
2. K 는 1 이상 5 이하의 자연수이다. 
3. K 명의 엔지니어를 모두 투입 시킬 필요는 없다. 
4. K 명의 엔지니어 중 서로 다른 두 담당자의 담당 부품이 동일 할 수도 있다.
 */
     
    public static void main(String[] args) throws Exception {
        FileInputStream fi = new FileInputStream(new File(preA0033.class.getResource("").getPath() + "preA0033.txt"));
        System.setIn(fi);
         
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
         
        T = Integer.valueOf(br.readLine());
        StringTokenizer st = null;
         
        for(int t=1; t<=T; t++){        
        	
        	// 초기화
        	for(int i=0; i<26; i++){
        		visited[i] = 0;
        	}
/*
[입력] 
첫 줄에 테스트 케이스의 개수 T 가 주어지며, 그 다음 T 개의 테스트 케이스가 주어진다. 
각 테스트 케이스는 여러 줄로 구성되어 있으며, 첫 줄에는 각 라인에서 하루에 처리하는 부품의 수 N, 전체 라인의 수 M, 투입되는 엔지니어의 수 K 가 공백으로 구분되어 주어진다. 
그 다음 줄에는 각 엔지니어가 담당하는 부품 알파벳이 K 개 주어진다. 
그 다음부터 M 개의 줄에 걸쳐 각 생산 라인의 일일 생산계획이 주어진다. 각 생산 라인의 일일 생산계획은 N 개의 알파벳으로 주어진다.
 */
            st = new StringTokenizer(br.readLine());            
            N = Integer.valueOf(st.nextToken()); // 부품의 수 N
            M = Integer.valueOf(st.nextToken()); // 전체 라인의 수 M
            K = Integer.valueOf(st.nextToken()); //  엔지니어의 수 K
            
            // 엔지니어 셋팅
            String line = br.readLine();            
        	for(int j=0; j<line.length(); j++) {
            	eng[j] = line.charAt(j)-65; // A : 65
            }
            
            // 공정/제품 셋팅 
            for(int i=0; i<M; i++){
                line = br.readLine();
                for(int j=0; j<line.length(); j++) {
                	pro[i][j] = line.charAt(j);	
                }
            }
            
            // 검증
//            for(int i=0; i<M; i++){
//            	for(int j=0; j<N; j++) {
//            		System.out.print(pro[i][j] + " ");
//            	}
//            	System.out.println();
//            }

             
/*           
(접근방법)
*/
            for(int j=0; j<N; j++) {
	            for(int i=0; i<M; i++){
	            	// 기술자 투입가능한 부품 초기화
	            	if(i==0 && j>0){
	            		for(int k=0; k<K; k++){
//	            			Ieng[j][eng[k]] = Ieng[j-1][eng[k]]; // 누적 처리시 
	            			Ieng[j][eng[k]] = 0; // 초기화
	            		}	            		
	            	}
	            	int p = pro[i][j]-65; // A : 65
	            	
	            	Ieng[j][p]++;
	            	
	            	Ieng[N][p]++; // 최대값 계산

//	            	System.out.println(pro[i][j] + ", " + Ieng[j][p]);
	            }
//	            System.out.println("--------------------");
            }
            
//            for(int j=0; j<26; j++) {
//            	System.out.print(Ieng[N-1][j] + " ");
//            }
            ans = 0;
            int curr=0, pre=0, cMax=0, iEng ;
            PriorityQueue<Integer> pq = new PriorityQueue<>();
            
            for(int i=0; i<M; i++){
            	// 공정순서별로 탐색을 시작한다. 
            	// 해당 공정에 기술자 투입이 가능할 경우 투입 후 남은 공정수가 가장 작은 수를 선택
            	// 해당 작은수가 같을 경우 현재 투입시 가장 이득인 경우를 투입 결정
            	for(int k=0; k<K; k++){
            		curr = Ieng[i][eng[k]];
            		Ieng[N][eng[k]] -= curr;
            		
            		cMax = Math.max(cMax, curr);
            	}            	
            }
            

            System.out.println("#"+t+ " " + ans);
 
        } // end T
         
         
/*
[출력]
각 테스트 케이스에 대해 #x (x는 테스트 케이스 번호, 1부터 시작)을 출력하고 공백을 하나 둔 다음, 최대 생산 가능한 정상부품의 수를 출력한다.
 */
    } // main
 
}

