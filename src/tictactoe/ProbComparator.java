//https://www.geeksforgeeks.org/implement-priorityqueue-comparator-java/
package tictactoe;

import java.util.Comparator;

public class ProbComparator implements Comparator<probCell> {
	public int compare(probCell s1, probCell s2) { 
        
         
		if (s1.prob < s2.prob) {
            return 1; 
		}
        else if (s1.prob > s2.prob) {
            return -1; 
        }
        return 0; 
        }
}
