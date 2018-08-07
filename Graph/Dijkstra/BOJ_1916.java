package Dijkstra;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;
/*
 * 우선순위 큐와 같은 경로에 여러 cost가 존재할 수도 있다는 것을 파악하지 못했음 
 */
public class BOJ_1916 {
	static Integer[] dist;
	static Integer[][] graph;
	static int city, bus;
	static int source, destination;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer cityLine = new StringTokenizer(br.readLine());
		city = Integer.parseInt(cityLine.nextToken());
		StringTokenizer busLine = new StringTokenizer(br.readLine());
		bus = Integer.parseInt(busLine.nextToken());
		
		int start, end, cost;
		dist = new Integer[city];
		graph = new Integer[city][city];
		
		Arrays.fill(dist, 1000000001);
		for (int i = 0; i < city; i++) {
			Arrays.fill(graph[i], 1000000001);
		}
		
		while(bus-- >= 0) {
			StringTokenizer temp = new StringTokenizer(br.readLine());

			if (bus > -1) {
				start = Integer.parseInt(temp.nextToken()) - 1;
				end = Integer.parseInt(temp.nextToken()) - 1;
				cost = Integer.parseInt(temp.nextToken());
				
				if (graph[start][end] > cost) graph[start][end] = cost;					
			} else {
				source = Integer.parseInt(temp.nextToken()) - 1;
				destination = Integer.parseInt(temp.nextToken()) - 1;
			}
		}
		
		System.out.println(Dijkstra());
	}
	
	static int Dijkstra() {
		PriorityQueue<Integer> q = new PriorityQueue<Integer>();
		q.add(source);
		dist[source] = 0;
		graph[source][source] = 0;
		
		while(!q.isEmpty()) {
			int u = q.poll();
			
			for (int i = 0; i < city; i++) {
				if (dist[u] + graph[u][i] < dist[i]) {
					dist[i] = dist[u] + graph[u][i];
					q.add(i);
				}
			}
		}
		return dist[destination];
	}
}
