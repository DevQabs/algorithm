package kruskal;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ_1197 {
	static int[] disJoint;
	static PriorityQueue<Node> pq = new PriorityQueue<Node>();
	
	static int find(int a) {
		if (disJoint[a] == a) return a;
		return disJoint[a] = find(disJoint[a]);
	}
	
	static void union(int a, int b) {
		if (a==b) return;
		int setA = find(a);
		int setB = find(b);
		
		disJoint[setA] = disJoint[setB];
	}
	
	static int kruskal() {
		int result = 0;
		
		//3. 집합 S가 비어있지 않는 동안		
		while(!pq.isEmpty()) {
			//		3-1. 가장 작은 가중치의 변을 S에서 하나 빼낸다.
			Node poll = pq.poll();
			
			//		3-2. 그 변이 어떤 두 개의 나무를 연결한다면 두 나무를 연결하여 하나의 나무로 만든다.
			// 사이클 체크
			if (find(poll.start) != find(poll.end)) {
				union(poll.start, poll.end);
				result += poll.cost;
			}
			//		3-3. 그렇지 않다면 그 변은 버린다.
		}
		return result;
	}
	
	public static void main(String[] args) throws IOException {
		Scan sc = new Scan();
		int v = sc.nextInt();
		int e = sc.nextInt();
		int start, end, cost;
		ArrayList<Node>[] list = new ArrayList[v+1];
		disJoint = new int[v+1];
		
		// 사이클 체크 -> Disjoint-set
		//1. 그래프의 꼭짓점이 각각 하나의 나무가 되도록 하는 숲 F를 만든다.
		for(int i = 0; i < disJoint.length; i++) disJoint[i] = i;
		
		for (int i = 1; i <= e; i++) {
			start = sc.nextInt();
			end = sc.nextInt();
			cost = sc.nextInt();
			//2. 모든 변을 원소로 갖는 집합 S를 만든다.
			pq.add(new Node(start, end, cost));
		}
		
		System.out.println(kruskal());
	}
	
	static class Node implements Comparable{
		int start, end, cost;
		Node(int start, int end, int cost) {
			this.end = end;
			this.cost = cost;
			this.start = start;
		}
		@Override
		public int compareTo(Object arg) {
			Node node = (Node)arg;
			if (this.cost > node.cost) return 1;
			return -1;
		}
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
