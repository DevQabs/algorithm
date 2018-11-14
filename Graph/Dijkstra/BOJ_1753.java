package Dijkstra;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/*
 * 최단경로
 * 
 * 입력
 * ------
 * 첫째 줄에 정점의 개수 V와 간선의 개수 E가 주어진다. (1≤V≤20,000, 1≤E≤300,000) 
 * 모든 정점에는 1부터 V까지 번호가 매겨져 있다고 가정한다. 
 * 둘째 줄에는 시작 정점의 번호 K(1≤K≤V)가 주어진다. 
 * 셋째 줄부터 E개의 줄에 걸쳐 각 간선을 나타내는 세 개의 정수 (u, v, w)가 순서대로 주어진다. 
 * 이는 u에서 v로 가는 가중치 w인 간선이 존재한다는 뜻이다. 
 * u와 v는 서로 다르며 w는 10 이하의 자연수이다. 
 * 서로 다른 두 정점 사이에 여러 개의 간선이 존재할 수도 있음에 유의한다.
 * 
 * 5 6
 * 1
 * 5 1 1
 * 1 2 2
 * 1 3 3
 * 2 3 4
 * 2 4 5
 * 3 4 6
 * 
 * 출력
 * ------
 * 첫째 줄부터 V개의 줄에 걸쳐, i번째 줄에 i번 정점으로의 최단 경로의 경로값을 출력한다. 
 * 시작점 자신은 0으로 출력하고, 경로가 존재하지 않는 경우에는 INF를 출력하면 된다.
 * 
 * 0
 * 2
 * 3
 * 7
 * INF
 */

public class BOJ_1753 {
	static class Node  implements Comparable<Node>{
		int end, cost;
		
		Node(int end, int cost) {
			this.end = end;
			this.cost = cost;
		}

		@Override
		public int compareTo(Node arg0) {
			// TODO Auto-generated method stub
			if (this.cost > arg0.cost) return 1;
			else return -1;
		}
	}
//
//	6 7
//	1
//	1 5 4
//	1 2 3
//	2 3 3
//	3 4 3
//	4 6 1
//	5 4 4
//  4 2  1
	public static void main(String[] args) throws IOException {
		Scan sc = new Scan();
		int V = sc.nextInt();
		int E = sc.nextInt();
		int K = sc.nextInt();

		int[] dist = new int[V+1];
		Arrays.fill(dist, 200001);
		
		Boolean[] visited = new Boolean[V+1];
		Arrays.fill(visited, false);
		
		ArrayList<Node>[] list = new ArrayList[V+1];
		for (int i = 0; i < list.length; i++) list[i] = new ArrayList<Node>();

		for (int i = 1; i <= E; i++) {
			int u = sc.nextInt();
			int v = sc.nextInt();
			int spend = sc.nextInt();
			list[u].add(new Node(v, spend));
		}
		
		Dijkstra(dist, list, K, visited);
	}
	
	static void Dijkstra(int[] dist, ArrayList<Node>[] list, int start, Boolean[] visited) {
		PriorityQueue<Node> queue = new PriorityQueue<Node>();
		queue.add(new Node(start, 0));
		dist[start] = 0;
		
		while(!queue.isEmpty()) {
			Node current = queue.poll();
			int costs = current.cost;
			int remove = current.end;

			// 방문여부 체크 이유 : 항상 출발지에서 부터 최소비용대로 계산하므로 한 번 지나간 출발지는 최소비용으로 무조건 갱신이 되어진다.
			// 그러므로 다시 재방문할 이유가 전혀 없으므로 방문여부를 체크하여 밑의 연산을 줄일 수 있다.
			// 4 4
			// 1
			// 1 4 3 -> 3 큐에 추가됨
			// 1 4 4 -> 3 보다 크므로 추가 안됨
			// 1 4 2 -> 3보다 작으므로 추가 되며 최소 경로 갱신되며, 우선순위 큐에 따라 최소 경로로 큐의 최상위 출력에 위치하게 됨
			// 4 2 4 -> 처리
			if (visited[remove]) continue; 
			visited[remove] = true;
			System.out.println("----------------------");
//			System.out.println(remove + ", " + costs);
			for (int i = 0; i < list[remove].size(); i++) {
				int cost = list[remove].get(i).cost;
				int end = list[remove].get(i).end;
				
				System.out.println(remove + ", " + end + ", " + cost);
				if (dist[end] > dist[remove] + cost) {
					dist[end] = dist[remove] + cost;
//					System.out.println(remove + ", " + end + ", " + cost);
					queue.add(new Node(end, dist[end]));
				}
			}
		}
		
		StringBuffer sb = new StringBuffer();
		for (int i = 1; i < dist.length; i++) {
			if (dist[i] == 200001) sb.append("INF\n");
			else sb.append(dist[i] + "\n");	
		}
		System.out.println(sb);
		
		return;
	}
	
	static class Scan {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		int nextInt() throws IOException {
			if (st == null || !st.hasMoreTokens()) {
				st = new StringTokenizer(in.readLine());
			}
			return Integer.parseInt(st.nextToken());
		}
	}
}
