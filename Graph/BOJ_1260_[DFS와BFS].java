package Graph.BreadthFirstSearch;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int[][] graph = new int[1001][1001];
	static int[] visit = new int[1001];
	static String str = "";
	
	public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
		
		int n = sc.nextInt();
		int m = sc.nextInt();
		int v = sc.nextInt();
		
		while(m-- > 0) {
			int x = sc.nextInt();
			int y = sc.nextInt();
			
			graph[x][y] = graph[y][x] = 1;
		}
		visit[v] = 1;
		System.out.println(v + dfs(v));
		
		Arrays.fill(visit, 0);
		visit[v] = 1;
		bfs(v);
	}
	
	static String dfs(int v) {
		for (int i = 1; i < graph[v].length; i++) {
			if (graph[v][i] == 1 && visit[i] != 1) {
				str += " " + i;
				visit[i] = 1;
				dfs(i);
			}
		}
		return str;
	}
	
	static void bfs(int v) {
		Queue<Integer> queue = new LinkedList<Integer>();
		queue.add(v);
		System.out.print(v);
		while(!queue.isEmpty()) {
			int poll = queue.poll();
			for (int i = 1; i < graph[poll].length; i++) {
				if (graph[poll][i] == 1 && visit[i] != 1) {
					queue.add(i);
					visit[i] = 1;
					System.out.print(" " + i);
				}
			}
		}
	}
}

// 답은 맞으나 속도가 안 나옴
// 원인 : 전제 자체가 틀렸었음 -> class Point를 통하면 반드시 정렬을 위한 sort를 하여 시간을 잡아먹으나 배열로 했으면 처음부터 정렬이 되어있는 상태였기 때문에 정렬을 위한 시간을 아낄수 있었음
public class Main {
	static int[][] graph = new int[1001][1001];
	static int[] visit = new int[1001];
	static Boolean[][] visitDfs = new Boolean[1001][1001];
	static Boolean[][] visitBfs = new Boolean[1001][1001];
	static LinkedList<Point> list = new LinkedList<Point>();
	static ArrayList<Integer> p = new ArrayList<Integer>();
	
	static class Point implements Comparable<Point>{
		int x;
		int y;
		Point(int x, int y) {
			this.x = x;
			this.y = y;
		}
		@Override
		public int compareTo(Point p) {
			if (this.x < p.x) {
				return -1;
			} else if (this.x > p.x) {
				return 1;
			} else if (this.y < p.y) {
				return -1;
			} else if (this.y > p.y) {
				return 1;
			}
			return 0;
		}
	}
	
	public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
		
		int n = sc.nextInt();
		int m = sc.nextInt();
		int v = sc.nextInt();
		
		while(m-- > 0) {
			int x = sc.nextInt();
			int y = sc.nextInt();
			
			if (x > y) {
				Point p = new Point(y, x);
				list.add(p);
			} else {
				Point p = new Point(x, y);
				list.add(p);
			}
		}
		
		Collections.sort(list);
		String str = "";
		
		dfs(v);
		for (int i = 0; i < p.size(); i++) {
			if (i == p.size() - 1) {
				str += p.get(i);				
			} else {
				str += p.get(i) + " ";
			}
		}
		System.out.println(str);
		
		str = "";
		p.clear();
		
		bfs(v);
		for (int i = 0; i < p.size(); i++) {
			if (i == p.size() - 1) {
				str += p.get(i);				
			} else {
				str += p.get(i) + " ";
			}
		}
		System.out.println(str);
	}
	
	static void dfs(int v) {
		int x, y;
		
		for (int i = 0; i < list.size(); i++) {
			x = list.get(i).x;
			y = list.get(i).y;
			
			if (x == v && visitDfs[x][y] == null) {
				visitDfs[x][y] = true;
				if (p.indexOf(v) == -1) {
					p.add(v);					
				}
				dfs(y);
			}
			
			if (y == v && visitDfs[x][y] == null) {
				visitDfs[x][y] = true;
				if (p.indexOf(v) == -1) {
					p.add(v);					
				}
				dfs(x);
			}
		}
	}
	
	static void bfs(int v) {
		Queue<Integer> q = new LinkedList<Integer>();
		p.add(v);
		for (Point a : list) {
			if (a.x == v) {
				q.add(a.y);
				p.add(a.y);
				visitBfs[a.x][a.y] = true;
			} else if (a.y == v) {
				q.add(a.x);
				p.add(a.x);
				visitBfs[a.x][a.y] = true;
			}
		}
		while(!q.isEmpty()) {
			int c = q.poll();
			
			for (Point a : list) {
				if (a.x == c && visitBfs[a.x][a.y] == null) {
					q.add(a.y);
					visitBfs[a.x][a.y] = true;
					if (p.indexOf(a.y) == -1 ) {
						p.add(a.y);
					}					
				} else if (a.y == c && visitBfs[a.x][a.y] == null) {
					q.add(a.x);
					visitBfs[a.x][a.y] = true;
					if (p.indexOf(a.x) == -1 ) {
						p.add(a.x);
					}
				}
			}
		}
	}
}
