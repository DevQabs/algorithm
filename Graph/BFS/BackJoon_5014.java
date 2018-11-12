package BFS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BackJoon_5014 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int f = Integer.parseInt(st.nextToken());	// 최고층
		int s = Integer.parseInt(st.nextToken());	// 현재층
		int g = Integer.parseInt(st.nextToken());	// 목표층
		int u = Integer.parseInt(st.nextToken());	// up층
		int d = Integer.parseInt(st.nextToken());	// down층
		if (s == g) {
			System.out.println(0);
			return;
		}
		Queue<Integer> queue = new LinkedList();
		Boolean[] log = new Boolean[f+1];
		int[] count = new int[f + 1];
		queue.add(s);
		log[s] = true;
		
		while(!queue.isEmpty()) {
			int floor = queue.poll();
			int down = floor - d;
			int up = floor + u;
			
			if (down >= 1 && log[down] == null) {
				if (down == g) {
					System.out.println(count[floor] + 1);
					return;
				}
				queue.add(down);
				log[down] = true;
				count[down] = count[floor] + 1;
				
			}
			if (up <= f && log[up] == null) {
				if (up == g) {
					System.out.println(count[floor] + 1);
					return;
				}
				queue.add(up);
				log[up] = true;
				count[up] = count[floor] + 1;
			}
		}

		System.out.println("use the stairs");
	}
}
