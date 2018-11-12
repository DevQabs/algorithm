package DFS;

import java.util.Scanner;

public class BackJoon_1012 {
	public static Boolean[][] findNearByCabbages(int _x, int _y, Boolean[][] _cabbages) {
		if (_cabbages[_x][_y] != null && !_cabbages[_x][_y]) {
			_cabbages[_x][_y] = true;
		}
		
		if (_x-1 >= 0 && _cabbages[_x-1][_y] != null && !_cabbages[_x-1][_y] ) {
			findNearByCabbages(_x-1, _y, _cabbages);
		}
		if (_y-1 >= 0 && _cabbages[_x][_y-1] != null && !_cabbages[_x][_y-1] ) {
			findNearByCabbages(_x, _y-1, _cabbages);
		}
		if (_x+1 < _cabbages.length && _cabbages[_x+1][_y] != null && !_cabbages[_x+1][_y] ) {
			findNearByCabbages(_x+1, _y, _cabbages);
		}
		if (_y+1 < _cabbages[_x].length && _cabbages[_x][_y+1] != null && !_cabbages[_x][_y+1] ) {
			findNearByCabbages(_x, _y+1, _cabbages);
		}
		
		return _cabbages;
	}	

	public static Integer countSwarm(Boolean[][] _cabbages) {
		Integer result = 0;
		for (int i = 0; i < _cabbages.length; i++) {
			for (int j = 0; j < _cabbages[i].length; j++) {
				if (_cabbages[i][j] != null && !_cabbages[i][j]) {
					_cabbages = findNearByCabbages(i, j, _cabbages);
					result++;
				}
			}
		}
		
		return result;
	}
	
	public static void main(String[] args) {
		Boolean[][] cabbages;
		Scanner scan = new Scanner(System.in);
		Integer loop = Integer.parseInt(scan.nextLine());
		Integer[] result = new Integer[loop];
		for (int i = 0; i < loop; i++) {
			String[] str = scan.nextLine().split(" ");			
			cabbages = new Boolean[Integer.parseInt(str[0])][Integer.parseInt(str[1])];
			for (int j = 0; j < Integer.parseInt(str[2]); j++) {
				String[] location = scan.nextLine().split(" ");
				cabbages[Integer.parseInt(location[0])][Integer.parseInt(location[1])] = false;
			}
			
			result[i] = countSwarm(cabbages);
		}
		
		for (int i = 0; i < result.length; i++) {
			System.out.println(result[i]);
		}
		scan.close();
	}
}