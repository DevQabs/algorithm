package unionFind;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_1717 {
	static int[] graph;
	static int[] depth;
	
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
	
	static int find(int index) {
		if (graph[index] == index)	return index;
		else {
			depth[index] += 1;
			return graph[index] = find(graph[index]);	// path Compression
		}
	}
	
	static void union(int a, int b) {
		if (a == b) return;
		
		// depth init
		depth[a] = 0;	
		depth[b] = 0;
		
		int rootA = find(a);
		int rootB = find(b);
		
		if (depth[rootA] < depth[rootB]) graph[rootA] = graph[rootB];
		else graph[rootB] = graph[rootA];
	}
	
	public static void main(String[] args) throws IOException {
		Scan sc = new Scan();
		StringBuffer sb = new StringBuffer();
		
		int n = sc.nextInt();
		int m = sc.nextInt();
		
		int union = 0;
		int find = 1;
		int a;
		int b;
		int c;	// union - find 구분자
		
		graph = new int[n+1];
		depth = new int[n+1];
		
		for (int i = 0; i < graph.length; i++) graph[i] = i;
		
		while(m-- > 0) {
			c = sc.nextInt();
			a = sc.nextInt();
			b = sc.nextInt();
			
			if (c == union) {
				union(a, b);
			} else if(c == find) {
				if (find(a) == find(b)) sb.append("YES\n");
				else sb.append("NO\n");
			}
		}
		
		System.out.println(sb.toString());
	}
}
