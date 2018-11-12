package FloydWarshall;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_2610 {
	static Boolean[][] graph = new Boolean[52][52];
	
	static class Scan {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		int nextInt() throws IOException {
			if (st == null || !st.hasMoreTokens()) {
				st = new StringTokenizer(in.readLine());
			}
			return Integer.parseInt(st.nextToken());
		}
		
		String nextString() throws IOException {
			if (st == null || !st.hasMoreTokens()) {
				st = new StringTokenizer(in.readLine());
			}
			return st.nextToken();
		}
	}
	
	public static void main(String[] args) throws IOException {
		StringBuffer sb = new StringBuffer();
		Scan sc = new Scan();
		int count = 0;
		
		for (int i = 0; i < 52; i++) Arrays.fill(graph[i], false);	
		
		char c;
		int x, y;
		int statement = sc.nextInt();
		
		while(statement-- > 0) {
			c = sc.nextString().charAt(0);
			x = (Character.isUpperCase(c)) ? c - 65 : c - 71;
			sc.nextString();
			c = sc.nextString().charAt(0);
			y = (Character.isUpperCase(c)) ? c - 65 : c - 71;
			graph[x][y] = true;
		}

		for (int k = 0; k < 52; k++) {
			for (int i = 0; i < 52; i++) {
				for (int j = 0; j < 52; j++) {
					if (i == k || i == j || k == j) continue;
					if (graph[i][k] && graph[k][j]) {
						graph[i][j] = true;
					}
				}
			}
		}
			
		for (int i = 0; i < 52; i++) {
			for (int j = 0; j < 52; j++) {
				if (i == j) continue;
				if (graph[i][j]) 	{
					if (i > 25 && j > 25) {
						sb.append((char)(i + 71) + " => " + (char)(j + 71) + "\n");
					} else if (i > 25) {
						sb.append((char)(i + 71) + " => " + (char)(j + 65) + "\n");
					} else if (j > 25) {
						sb.append((char)(i + 65) + " => " + (char)(j + 71) + "\n");
					} else {
						sb.append((char)(i + 65) + " => " + (char)(j + 65) + "\n");
					}
					count++;
				}
			}
		}
		System.out.println(count);
		System.out.println(sb.toString());
	}
}
