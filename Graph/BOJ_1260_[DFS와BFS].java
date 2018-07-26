package Graph.BreadthFirstSearch;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_1260 {
	static Boolean[] visit = new Boolean[1001];
	static Boolean[] bfsB = new Boolean[1001];
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int n = Integer.parseInt(st.nextToken());	// 점의 개수 N
		int m = Integer.parseInt(st.nextToken()); // 간선의 개수 M
		int v = Integer.parseInt(st.nextToken()); // 탐색을 시작할 번호 V

		// 한 간선이 여러 번 주어질 수도 있음
		
		// 4 5 1
		// 1 2
		// 1 3
		// 1 4
		// 2 4
		// 3 4
		ArrayList<Point> list = new ArrayList<Point>();
		Queue<Point> queue = new LinkedList();
		
		//Arrays.fill(bfsB, false);
		
		while(m-- > 0) {
			// 중복제거 해줘야함
			StringTokenizer st1 = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st1.nextToken());
			int y = Integer.parseInt(st1.nextToken());
			
			Point p = new Point();
			p.x = x;
			p.y = y;
			list.add(p);
			queue.add(p);
		}
		visit[v] = true;
		bfsB[v] = true;
//		6 8 1
//		1 6 
//		6 2 
//		2 4 
//		4 3 
//		5 6 
//		5 1 
//		3 5 
//		2 3
//		DFS : 1 6 2 4 3 5 
//		BFS : 1 6 2 4 3 5
//		
//		정답
		// 1 -> 5 -> 3 -> 2 -> 4 -> 6
//		DFS : 1 5 3 2 4 6
		
		// 1 -> 5 -> 6 -> 3 -> 2 -> 4
//		BPS : 1 5 6 3 2 4 
//		System.out.println("DFS : " + v + " " + dfs(list, v));
//		System.out.println("BFS : " + v + bfs(queue, v));
	}
	
	static class Point {
		int x;
		int y;
	}
	
	private static String bfs(Queue<Point> _queue, int _v) {		
		String str = "";		
		
		while(!_queue.isEmpty()) {
			Point p = _queue.poll();
			// v나 next 둘중 하나가 true인지 먼저 검사
			if (bfsB[p.x] == null && bfsB[p.y] == null) {
				// 실패
			}
			
			if (bfsB[p.x] == null) {
				bfsB[p.x] = true;
				str += " " + p.x;
			}
			
			if (bfsB[p.y] == null) {
				bfsB[p.y] = true;
				str += " " + p.y;
			}
		}
		return str;
	}
	

//https://www.acmicpc.net/board/view/24356

	
	// Stack, 재귀호출, FILO
	private static String dfs(ArrayList<Point> _list, int _v) {
		String str = "";
		for (int i = 0; i < _list.size(); i++) {	
			if (_list.get(i).x == _v && visit[_list.get(i).y] == null) { // 정방향 검색
				visit[_list.get(i).y] = true;
				str += _list.get(i).y + " " + dfs(_list, _list.get(i).y);
				//return str;
			}
			else if (_list.get(i).y == _v && visit[_list.get(i).x] == null) { // 역방향 검색
				visit[_list.get(i).x] = true;
				str += _list.get(i).x + " " + dfs(_list, _list.get(i).x);
				//return str;
			}
		}
		return str;
	}
}
