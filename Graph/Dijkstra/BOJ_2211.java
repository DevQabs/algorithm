package Dijkstra;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ_2211 {
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
		int end, cost;
		Node(int end, int cost) {
			this.end = end;
			this.cost = cost;
		}
		
		@Override
		public int compareTo(Object o) {
			Node node = (Node)o;
			if (this.cost > node.cost) return 1;
			else return -1;
		}
	}
	
	public static void main(String[] args) throws IOException {
		Scan sc = new Scan();
		int N = sc.nextInt();
		int M = sc.nextInt();
		ArrayList<Node>[] list = new ArrayList[N+1];
		int[] dist = new int[N+1];

		for (int i = 0; i < list.length; i++) {
			list[i] = new ArrayList<Node>();
			dist[i] = Integer.MAX_VALUE;
		}
			
		while(M-- > 0) {
			int A = sc.nextInt();
			int B = sc.nextInt();
			int C = sc.nextInt();
			
			list[A].add(new Node(B, C));
			list[B].add(new Node(A, C));
		}
		
		Dijkstra(list, dist);
	}
	
	static void Dijkstra(ArrayList<Node>[] list, int[] dist) {
		PriorityQueue<Node> pq = new PriorityQueue();
		Boolean[] visited = new Boolean[dist.length];
		int[] result = new int[dist.length];
		pq.add(new Node(1, 0));
		dist[1] = 0;
		
		while(!pq.isEmpty()) {
			Node node = pq.poll();
			int end = node.end;
			//int cost = node.cost;
			
			if (visited[end] != null) continue; 
			visited[end] = true;
			
			for (int i = 0; i < list[end].size(); i++) {
				Node listNode = list[end].get(i);
				int listEnd = listNode.end;
				int listCost = listNode.cost;
				
				if (dist[listEnd] > dist[end] + listCost) {
					dist[listEnd] = dist[end] + listCost;
					pq.add(listNode);
					System.out.println(end + " " + listEnd + " : " + dist[listEnd]);
					result[listEnd] = end;
				}
			}
		}
		
		int cnt = 0;
		StringBuffer sb = new StringBuffer();
		for (int i = 2; i < result.length; i++) {
			if (result[i] != 0) {
				cnt++;
				sb.append(result[i] + " " + i + "\n");	
			}
		}
		
		System.out.println(cnt);
		System.out.println(sb.toString());
	}
}
