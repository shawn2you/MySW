package pretest2;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;
 
 
public class preA0033_1 {
     
    static int T, N, M, K, ans;
    static char[][] pro = new char[101][5001];
    static int[] eng = new int[26]; // 최초 기술자 목록(원본)
    static int[][] Ieng = new int[5001][26];
    
    static ArrayList<Integer> engList = new ArrayList<Integer>();
    static int[] engOrder; // = new int[5]; // 조합된 기술자목록
    
    // 순열 조합별로 구성하여 계산
    static int[][] dp = new int[6][5001]; // dp[기술자, 라인수]
           
/*
[제한사항] 
1. N 은 1 이상 5000 이하의 자연수, M 은 1 이상 100 이하의 자연수이다. 
2. K 는 1 이상 5 이하의 자연수이다. 
3. K 명의 엔지니어를 모두 투입 시킬 필요는 없다. 
4. K 명의 엔지니어 중 서로 다른 두 담당자의 담당 부품이 동일 할 수도 있다.
 */
     
    public static void main(String[] args) throws Exception {
        FileInputStream fi = new FileInputStream(new File(preA0033_1.class.getResource("").getPath() + "preA0033.txt"));
        System.setIn(fi);
         
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
         
        T = Integer.valueOf(br.readLine());
        StringTokenizer st = null;
         
        for(int t=1; t<=T; t++){
        	// 초기화 
        	ans = 0;
        	engList.clear();
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
            
            engOrder = new int[K]; // 조합된 기술자목록
            // 엔지니어 셋팅
            String line = br.readLine();            
        	for(int j=0; j<line.length(); j++) {
            	eng[j] = line.charAt(j)-65; // A : 65
            	engList.add(eng[j]);
            }
            
            // 공정/제품 셋팅 
            for(int i=1; i<=M; i++){
                line = br.readLine();
                for(int j=1; j<=line.length(); j++) {
                	pro[i][j] = line.charAt(j-1);	
                }
            }
            
            // 검증
//            for(int i=1; i<=M; i++){
//            	for(int j=1; j<=N; j++) {
//            		System.out.print(pro[i][j] + " ");
//            	}
//            	System.out.println();
//            }

            for(int j=1; j<=N; j++) {
	            for(int i=1; i<=M; i++){
	            	// 기술자 투입가능한 부품 초기화
	            	if(i==1){
	            		for(int k=0; k<K; k++){
//	            			Ieng[j][eng[k]] = Ieng[j-1][eng[k]]; // 누적 처리시 
	            			Ieng[j][eng[k]] = 0; // 초기화
	            		}	            		
	            	}
	            	int p = pro[i][j]-65; // A : 65	            	
	            	Ieng[j][p]++;	            	
	            	
//	            	Ieng[N][p]++; // 최대값 계산

//	            	System.out.println(pro[i][j] + ", " + Ieng[j][p]);
	            }
//	            System.out.println("--------------------");
            }
            
            // 검증
//        	for(int k=0; k<K; k++){
//        		for(int j=1; j<=N; j++) {
//            		System.out.print((char)(eng[k]+65) + "["+eng[k]+"]:"+ Ieng[j][eng[k]] + "  ");
//            	}
//            	System.out.println();
//            }
            
        	
        	reOrder(0);       	

            System.out.println("#"+t+ " " + ans);
 
        } // end T
         
         
/*
[출력]
각 테스트 케이스에 대해 #x (x는 테스트 케이스 번호, 1부터 시작)을 출력하고 공백을 하나 둔 다음, 최대 생산 가능한 정상부품의 수를 출력한다.
 */
    } // main
    

    
    static void reOrder(int depth) {    	
    	if(depth == K) {
    		// 조합 구성시 DP 수행
//    		System.out.println(Arrays.toString(engOrder));
    		
    		//초기화 
    		for(int i=0; i<=K; i++) {
    			for(int j=0; j<=N; j++) {
    				dp[i][j] = 0;
    			}
    		}

			// engOrder[i] 기술자 , Ieng[공정][기술자]
    		for(int i=1; i<=K; i++) {
    			for(int j=1; j<=N; j++) {
//    				System.out.printf("%c:%d", (char)(engOrder[i-1]+65), Ieng[j][engOrder[i-1]]);
    				dp[i][j] = Math.max(dp[i][j-1] + Ieng[j][engOrder[i-1]], dp[i-1][j-1] + Ieng[j][engOrder[i-1]]);
//    				System.out.printf(" (%d, %d) = %d  ", i, j, dp[i][j]);
    			}
//    			System.out.println();
    		}
    		for(int i=1; i<=K; i++) {    			
    			ans = Math.max(ans, dp[i][N]);
    		}
    		
//    		System.out.println(ans);
    	}
    	
    	for(int i=0; i<K-depth; i++) {
    		// 첫번째 선택하여 제거
    		engOrder[depth] = engList.remove(i);
    		reOrder(depth + 1);
    		engList.add(i, engOrder[depth]); // 중복 조합하지 않기 위해 원위치 시킴
    	}
    }
}

