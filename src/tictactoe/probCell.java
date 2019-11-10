package tictactoe;

public class probCell {

	String locale;
	
	double prob;
	
	
	public probCell(double prob, String locale) {
		
		
		this.prob = prob;
		
		this.locale = locale;
		
		
	}
	
	public String getProbLocale() {
		
		
		return locale;
	}
	
}
