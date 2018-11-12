package FloydWarshall;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_11404 {
	static int city, bus;
	static int[][] graph;
	static int[][] dist;
	static final int max = 10000000;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer cityLine = new StringTokenizer(br.readLine());
		city = Integer.parseInt(cityLine.nextToken());
		StringTokenizer busLine = new StringTokenizer(br.readLine());
		bus = Integer.parseInt(busLine.nextToken());
		
		int start, end, cost;
		graph = new int[city][city];
		dist = new int[city][city];
		
		for (int i = 0; i < graph.length; i++) {
			Arrays.fill(graph[i], max);
			Arrays.fill(dist[i], max);
		}
		
		while(bus-- > 0) {
			StringTokenizer temp = new StringTokenizer(br.readLine());
			
			start = Integer.parseInt(temp.nextToken()) - 1;
			end = Integer.parseInt(temp.nextToken()) - 1;
			cost = Integer.parseInt(temp.nextToken());
			
			if (graph[start][end] > cost) {
				graph[start][end] = cost;
			}
		}
		
		// 거쳐가는 정점을 기준으로 알고리즘을 수행한다.
		// 즉, [거쳐가는 정점]의 모든 최단 거리를 구하는 것이다.
		for (int k = 0; k < city; k++) {	// 거쳐가는 정점
			for (int i = 0; i < city; i++) {	// 출발하는 정점
				for (int j = 0; j < city; j++) {	// 도착하는 정점
					if (graph[i][j] > graph[i][k] + graph[k][j]) {
						graph[i][j] = graph[i][k] + graph[k][j]; 
					}
				}
			}
		}
		
		for (int i = 0; i < city; i++) {
			for (int j = 0; j < city; j++) {
				if (graph[i][j] == max) {
					graph[i][j] = 0;
				}
			}
			graph[i][i] = 0;
		}
		
		StringBuffer sb = new StringBuffer();
		
		for (int i = 0; i < city; i++) {
			for (int j = 0; j < city; j++) {
				if (j != 0) sb.append(" ");
				sb.append(graph[i][j]);
			}
			sb.append("\n");
		}
		System.out.println(sb.toString());
	}
}
