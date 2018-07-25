package Graph.BreadthFirstSearch;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class BackJoon_2583 {
	static int total;
	static int cnt;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		Boolean[][] bol = new Boolean[Integer.parseInt(st.nextToken())][Integer.parseInt(st.nextToken())];
		int size = Integer.parseInt(st.nextToken());
		
		for (int i = 0; i < size; i++) {
			st = new StringTokenizer(br.readLine());
			int left_x = Integer.parseInt(st.nextToken());
			int left_y = Integer.parseInt(st.nextToken());
			int right_x = Integer.parseInt(st.nextToken());
			int right_y = Integer.parseInt(st.nextToken());
			
			for (int x = left_x; x < right_x; x++) {
				for (int y = left_y; y < right_y; y++) {
					bol[y][x] = true;
				}
			}
		}
		
		for (int i = 0; i < bol.length; i++) {
			//System.out.println(Arrays.deepToString(bol[i]));
		}
		ArrayList<Integer> a = findNull(bol);
		a.sort(null);
		String str = "";
		
		for (int i = 0; i < a.size(); i++) {
			if (i == a.size()-1) {
				str += a.get(i);
			}
			else {
				str += a.get(i) + " ";
			}
			
		}
		System.out.println(total);
		System.out.println(str);
	}
	
	static ArrayList<Integer> findNull(Boolean[][] bol) {
		ArrayList<Integer> count = new ArrayList<Integer>();
		for (int i = 0; i < bol.length; i++) {
			for (int j = 0; j < bol[i].length; j++) {
				if (bol[i][j] == null) {
					++total;
					cnt = 0;
					count.add(countNull(bol, i, j));
				}
			}
		}
		return count;
	}
	
	static int countNull(Boolean[][] bol, int i, int j) {
		if (i < 0 || j < 0 || i >= bol.length || j >= bol[i].length) {
			return 0;
		}
		
		if (bol[i][j] == null) {
			bol[i][j] = false;
			++cnt;
		}
		else {
			return 0;
		}
		countNull(bol, i-1, j);
		countNull(bol, i, j-1);
		countNull(bol, i+1, j);
		countNull(bol, i, j+1);
		
		return cnt;
	}
}