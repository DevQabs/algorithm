package FloydWarshall;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_1238 {
	private static final int INF = 100000000;
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
	
	public static void main(String[] args) throws IOException {
		Scan sc = new Scan();
		int max = 0;
		int town = sc.nextInt();
		int road = sc.nextInt();
		int home = sc.nextInt();
		int[][] time = new int[town+1][town+1];
		
		for (int i = 1; i <= town; i++) {
			for (int j = 1; j <= town; j++) time[i][j] = (i==j) ? 0 : INF;
		}
		
		for (int i = 0; i < road; i++) {
			int x = sc.nextInt();
			int y = sc.nextInt();
			int cost = sc.nextInt();
			
			time[x][y] = cost;
		}
		
		for (int m = 1; m <= town; m++) {
			for (int i = 1; i <= town; i++) {
				for (int j = 1; j <= town; j++) {
					if (time[i][j] > time[i][m] + time[m][j]) {
						time[i][j] = time[i][m] + time[m][j];
					}
				}
			}
		}
		
		for (int i = 1; i <= town; i++) {
			if (time[i][home] + time[home][i] > max) max = time[i][home] + time[home][i];
		}
		System.out.println(max);
	}
}
