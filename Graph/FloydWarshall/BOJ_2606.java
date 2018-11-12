package FloydWarshall;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_2606 {
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
		int Computer = sc.nextInt();
		int link = sc.nextInt();
		
		int result = 0;
		
		Boolean[][] warm = new Boolean[Computer+1][Computer+1];
		for (int i = 1; i < warm.length; i++) Arrays.fill(warm[i], false);
		
		for (int i = 1; i <= link; i++) {
			int x = sc.nextInt();
			int y = sc.nextInt();
			
			int min = Math.min(x, y);
			int max = Math.max(x, y);
			warm[min][max] = true;
		}
		
		for (int m = 1; m < warm.length; m++) {
			for (int i = 1; i < warm.length; i++) {
				for (int j = 1; j < warm.length; j++) {
					if (i == m || i == j || j == m) continue;
					if (warm[i][m] && warm[m][j] && !warm[i][j]) warm[i][j] = warm[j][i] = true;
				}
			}
		}
		
		for (int i = 1; i < warm.length; i++) {
			if (warm[i][1]) result++;
		}
		System.out.println(result);
	}
}
