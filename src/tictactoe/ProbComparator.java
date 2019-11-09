package tictactoe;

import java.util.Comparator;

public class ProbComparator implements Comparator<String> {
	public int compare(String s1, String s2) { 
        int p1 = Integer.parseInt(s1.substring(0, s1.indexOf('$')));
        int p2 = Integer.parseInt(s2.substring(0, s2.indexOf('$')));
		if (p1 > p2) {
            return 1; 
		}
        else if (p1 < p2) {
            return -1; 
        }
        return 0; 
        }
}
