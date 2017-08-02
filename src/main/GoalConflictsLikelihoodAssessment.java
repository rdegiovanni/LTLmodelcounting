package main;

public class GoalConflictsLikelihoodAssessment {

	
	public static String unfoldLTLFormula(String form, int k){
		String str = "";
		boolean first = true;
		for (int i=0;i<k;i++){
			if(first)
				first = false;
			else
				str += " && X(";
			str += form.toString();
		}
		for(int i=0; i<k-1;i++)
			str += ")";
		
		return str;
	}
	
	public static String firstTimeBChold(String BC, long k){
		if(k<=1){
			return BC;
		}
		else{
			String notBC = "";
			if (BC.length()==1)
				notBC = "! "+BC;
			else
				notBC = "! ("+BC+")";//add parenthesis if the formula is not just a proposition
			String str = "";
			boolean first = true;
			for (int i=1;i<k;i++){
				if(first)
					first = false;
				else
					str += " && X(";
				str += notBC.toString();
			}
			str += " && X("+BC;
			for(int i=1; i<k;i++)
				str += ")";
			
			return str;
		}
	}
}
