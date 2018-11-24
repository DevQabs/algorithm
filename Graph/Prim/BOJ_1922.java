package Prim;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

/*
도현이는 컴퓨터와 컴퓨터를 모두 연결하는 네트워크를 구축하려 한다. 
하지만 아쉽게도 허브가 있지 않아 컴퓨터와 컴퓨터를 직접 연결하여야 한다. 
그런데 모두가 자료를 공유하기 위해서는 모든 컴퓨터가 연결이 되어 있어야 한다. 
(a와 b가 연결이 되어 있다는 말은 a에서 b로의 경로가 존재한다는 것을 의미한다. 
a에서 b를 연결하는 선이 있고, b와 c를 연결하는 선이 있으면 a와 c는 연결이 되어 있다.)

그런데 이왕이면 컴퓨터를 연결하는 비용을 최소로 하여야 컴퓨터를 연결하는 비용 외에 다른 곳에 
돈을 더 쓸 수 있을 것이다. 이제 각 컴퓨터를 연결하는데 필요한 비용이 주어졌을 때 
모든 컴퓨터를 연결하는데 필요한 최소비용을 출력하라. 모든 컴퓨터를 연결할 수 없는 경우는 없다.

입력 
---
첫째 줄에 컴퓨터의 수 N (1 ≤ N ≤ 1000)가 주어진다.
둘째 줄에는 연결할 수 있는 선의 수 M (1 ≤ M ≤ 100,000)가 주어진다.
셋째 줄부터 M+2번째 줄까지 총 M개의 줄에 각 컴퓨터를 연결하는데 드는 비용이 주어진다. 
이 비용의 정보는 세 개의 정수로 주어지는데, 
만약에 a b c 가 주어져 있다고 하면 a컴퓨터와 b컴퓨터를 연결하는데 비용이 c (1 ≤ c ≤ 10,000) 
만큼 든다는 것을 의미한다.

6
9
1 2 5
1 3 4
2 3 2
2 4 7
3 4 6
3 5 11
4 5 3
4 6 8
5 6 8

출력
---
23
 */
public class BOJ_1922 {
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
	
	static class Node implements Comparable{
		int start;
		int end;
		int cost = Integer.MAX_VALUE;
		
		public Node(int start, int end, int cost) {
			this.start = start;
			this.end = end;
			this.cost = cost;
		}
		
		@Override
		public int compareTo(Object o) {
			Node node = (Node)o;
			
			if (this.cost > node.cost) {
				return 1;
			} else {
				return -1;
			}
		}
		
	}
	
	public static void main(String[] args) throws IOException {
		Scan sc = new Scan();
		
		int N = sc.nextInt();	// edge
		int M = sc.nextInt();	// vertex
		ArrayList<Node>[] list = new ArrayList[N+1];
		for (int i = 0; i < list.length; i++) {
			list[i] = new ArrayList<Node>();
		}
		
		while(M-- > 0) {
			int start = sc.nextInt();
			int end = sc.nextInt();
			int cost = sc.nextInt();
			
			list[start].add(new Node(start, end, cost));
			list[end].add(new Node(end, start, cost));
		}
		
		System.out.println(Prim(list));
		
	}
	
	static int Prim(ArrayList<Node>[] node) {
		PriorityQueue<Node> pq = new PriorityQueue<>();	// 최소비용을 위한 큐
		Queue<Integer> q = new LinkedList<Integer>();	// 정점 모두를 방문하기 위한 큐
		Boolean[] visited = new Boolean[node.length];
		Arrays.fill(visited, false);
		int result = 0;
		q.add(1);
		
		while(!q.isEmpty()) {
			int poll = q.poll();
			ArrayList<Node> listNode = node[poll];
			visited[poll] = true;
			
			for (int i = 0; i < listNode.size(); i++) {
				Node nd = listNode.get(i);
				if (!visited[nd.end]) {
					pq.add(nd);	// 최단경로로 받아오는 경로들을 계속해서 넣어준다.
				}
			}
			// 1
			// 5 : 1 -> 2								3에서 방문
			// 4 : 1 -> 3								1
				// 3
				// 2 : 3 -> 2							2
				// 6 : 3 -> 4							3
				// 11 : 3 -> 5							4에서 방문
					// 2
					// 7 : 2 -> 4						3에서 방문
						// 4
						// 3 : 4 -> 5					4
						// 8 : 4 -> 6					5
							// 5 
							// 8 : 5 -> 6				4에서 방문
			
			// 쌓이나 우선순위에 의해서 항상 가중치가 낮은 것을 먼저 계산한다.
			while(!pq.isEmpty()) {
				Node minNode = pq.poll();

				if (!visited[minNode.end]) {
					q.add(minNode.end);
					result += minNode.cost;
					break;	// 핵심!! : 기존에 있던 경로들은 남겨준다.
				}
			}
		}
		
		return result;
	}
}
