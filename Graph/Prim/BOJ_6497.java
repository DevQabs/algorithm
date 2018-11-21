package Prim;

/* 마을을 나누는 기준이 없는 안 좋은 문제 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_6497 {
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
		int start, end, cost;
		Node(int start, int end, int cost) {
			this.start = start;
			this.end = end;
			this.cost = cost;
		}
		
		@Override
		public int compareTo(Object node) {
			Node nd = (Node)node;
			if (this.cost > nd.cost) return 1;
			else return -1;
		}
	}
	
	public static void main(String[] args) throws IOException {
		Scan sc = new Scan();
		int N = sc.nextInt();
		int M = sc.nextInt();
		
		ArrayList<Node>[] listNode = new ArrayList[N+1];
		Boolean[] visited = new Boolean[N+1];

		for (int i = 1; i < listNode.length; i++) {
			listNode[i] = new ArrayList<Node>();
		}
		
		while(M-- > 0) {
			int A = sc.nextInt();
			int B = sc.nextInt();
			int C = sc.nextInt();
			
			listNode[A].add(new Node(A, B, C));
			listNode[B].add(new Node(B, A, C));
		}
		
		MST(listNode, visited);
	}
	
	static void MST(ArrayList<Node>[] listNode, Boolean[] visited) {
		PriorityQueue<Node> pq = new PriorityQueue<Node>();
		Queue<Integer> q = new LinkedList<Integer>();
		int max = 0, sum = 0;
		q.add(1);
		
		while(!q.isEmpty()) {
			int poll = q.poll();
			visited[poll] = true;
			for (int i = 0; i < listNode[poll].size(); i++) {
				if (visited[listNode[poll].get(i).end] == null) {
					pq.add(listNode[poll].get(i));
				}
			}
			
			while(!pq.isEmpty()) {
				Node node = pq.poll();
				
				if (visited[node.end] == null) {
						if (max < node.cost) max = node.cost;
						sum += node.cost; 
						q.add(node.end);
						break;
				}
			}	
		}
		System.out.println(sum - max);
	}
}
