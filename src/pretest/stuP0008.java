package pretest;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

class Node2 implements Comparable<Node2> {
	int a, c;

	public Node2(int a, int c) {
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
	static int[] inCost = new int[100001];

	public static void main(String[] args) throws Exception {
		FileInputStream fi = new FileInputStream(new File(stuP0008.class.getResource("").getPath() + "stuP0008.txt"));
		System.setIn(fi);

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		T = Integer.parseInt(br.readLine());

		StringTokenizer st = null;

		for (int t = 1; t <= T; t++) {
			st = new StringTokenizer(br.readLine());

			N = Integer.parseInt(st.nextToken()); // 공정(정점)
			M = Integer.parseInt(st.nextToken()); // 벨트(간선)
			al.clear();

			for (int i = 0; i <= N; i++) {
				al.add(new ArrayList<Node2>());
				inCost[i] = 0;
				inDegree[i] = 0;
			}

			/*
			 * [입력] 입력 파일에는 여러 테스트 케이스가 포함될 수 있다. 파일의 첫째 줄에 케이스의 개수 T가 주어지고, 이후
			 * 차례로 T개 테스트 케이스가 주어진다. (1 ≤ T ≤ 15) 각 케이스의 첫 줄에 공정의 수 N과 컨베이어 벨트의
			 * 수 M이 공백으로 구분되어 주어진다. (1 ≤ N ≤ 100,000, 1 ≤ M ≤ 300,000) 그리고 다음
			 * M개의 줄에 각 컨베이어 벨트에 대한 정보를 나타내는 세 정수가 공백으로 구분되어 주어진다. "a b c"라고
			 * 주어졌을 때 a번 공정에서 b번 공정으로 작업을 보내야하는데 보내는데 걸리는 시간이 c라는 것을 의미한다. (1 ≤
			 * a, b ≤ N, 1 ≤ c ≤ 10,000, a ≠ b) 작업은 항상 1번 공정에서 시작하며, N번 공정에서
			 * 끝난다. 입력으로 주어지는 정보를 그래프로 표현했을 때, 그래프는 DAG(Directed Acyclic Graph)가
			 * 되며, 1번 정점으로 들어오는 간선의 개수는 0이고, N번 정점에서 나가는 간선의 개수 또한 0이다. 그리고 항상
			 * 작업이 완료되도록 입력이 주어진다.
			 */
			int a, b, c;
			for (int i = 0; i < M; i++) {
				st = new StringTokenizer(br.readLine());

				a = Integer.parseInt(st.nextToken()); // 시작
				b = Integer.parseInt(st.nextToken()); // 종료
				c = Integer.parseInt(st.nextToken()); // 비용

				// 방향 존재
				al.get(a).add(new Node2(b, c));
				inDegree[b]++;
			}

			caluCost();

			/*
			 * [출력] 각 테스트 케이스의 답을 순서대로 표준출력으로 출력하며, 각 케이스마다 줄의 시작에 “#x”를 출력하여야
			 * 한다. 이때 x는 케이스의 번호이다. 같은 줄에, 1번 공정에서 작업을 시작하여 N번 공정에서 작업이 완료되는데
			 * 걸리는 최소 시간을 출력한다.
			 */
			System.out.println("#" + t + " " + inCost[N]);

		} // end test case
	}

	public static void caluCost() {
		PriorityQueue<Node2> pq = new PriorityQueue<Node2>();

		pq.offer(new Node2(1, 0)); // 1번공정에서 시작함

		Node2 nd;
		int next, curr, cost;
		while (!pq.isEmpty()) {
			nd = pq.poll();
			curr = nd.a;

			if (inDegree[N] == 0) {
				// System.out.println("---------------"+N);
				break;
			}

			for (int j = 0; j < al.get(curr).size(); j++) {
				nd = al.get(curr).get(j);
				next = nd.a;
				cost = nd.c;
				// 방문하면서 비용을 누적한다.
				inCost[next] = Math.max(inCost[next], inCost[curr] + cost);

				if (--inDegree[next] == 0)
					pq.offer(new Node2(next, inCost[next]));
			}
		}
	}

}
