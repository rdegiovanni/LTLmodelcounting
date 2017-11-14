package main;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.math.BigInteger;
import java.util.AbstractMap.SimpleEntry;
import java.util.Iterator;
import java.util.LinkedList;

import de.uni_luebeck.isp.rltlconv.automata.Nba;
import de.uni_luebeck.isp.rltlconv.automata.Nfa;
import regular.Discretizer;
import scala.util.control.Exception;

public class Main {

	public static void main(String[] args) throws RuntimeException, IOException, InterruptedException {
		LinkedList<String> dom = new LinkedList<>();
		LinkedList<String> BCs = new LinkedList<>();
		LinkedList<String> goals = new LinkedList<>();
		LinkedList<String> alph = new LinkedList<>();
		String outfile = null;
		int k = 0;
		int deph = 0;
		int init = 1;
		boolean exhaustive = false;
		
		for (int i = 0; i< args.length; i++ ){
//			System.out.println(args[i]);
			if(args[i].startsWith("-ltl=")){
				dom.add(args[i].replace("-ltl=", ""));
			}
			else if(args[i].startsWith("-g=")){
				goals.add(args[i].replace("-g=", ""));
			}
			else if(args[i].startsWith("-bc=")){
				BCs.add(args[i].replace("-bc=", ""));
			}
			else if(args[i].startsWith("-f=")){
				String fname = args[i].replace("-f=", "");
				readFromFile(fname, dom,goals,BCs,alph);
			}
			else if(args[i].startsWith("-alph=")){
				alph.add(args[i].replace("-alph=", ""));
//				System.out.println(alph);
			}
			else if(args[i].startsWith("-k=")){
				k = Integer.valueOf(args[i].replace("-k=", ""));
			}
			else if(args[i].startsWith("-i=")){
				init = Integer.valueOf(args[i].replace("-i=", ""));
			}
			else if(args[i].startsWith("-all")){
				exhaustive = true;
			}
			else if(args[i].startsWith("-out=")){
				outfile = args[i].replace("-out=", "");
			}
			else if(args[i].startsWith("-deph=")){
				deph = Integer.valueOf(args[i].replace("-deph=", ""));
			}
			else if(args[i].startsWith("-noprops")){
				LTLModelCounter.props = false;
			}
			else{
				correctUssage();
				return;
			}
		}
		
		if(dom==null || k <= 0){
			correctUssage();
			return;
		}
		
		//set initial value
		long bound = 1;
		if(!exhaustive)
			bound = k;
		else{
			bound = init;
		}
		
		LinkedList<SimpleEntry<BigInteger, Double>> results = new LinkedList<>();
		
		try{ 
			
			boolean first = true;
			
			while(bound<=k){
				BigInteger count = BigInteger.ZERO;
				double time = 0;
				if (first){
					LinkedList<String> formulas = new LinkedList<>();
					formulas.addAll(dom);
					formulas.addAll(goals);
					
//					if(BC != null){
//						String BCatPosK = "";
//						if(deph==0)
//							BCatPosK = GoalConflictsLikelihoodAssessment.firstTimeBChold(BC, bound);
//						else if(deph!=0 && bound-deph>=1)
//							BCatPosK = GoalConflictsLikelihoodAssessment.firstTimeBChold(BC, bound-deph);
//						else
//							BCatPosK = "FALSE";
//						formulas.addFirst(BCatPosK);						
//					}
	
					double iTime = System.currentTimeMillis();
					String alphStr = genAlph(alph);
					count = count(formulas, BCs, alphStr, bound);
					time = getTimeInSecond(iTime,System.currentTimeMillis());
					System.out.println("Time: " + time); 
					first = false;
				}
				else{
					double iTime = System.currentTimeMillis();
					if(ABC.result){
						if(LTLModelCounter.encoded_alphabet==1)
							count = ABC.count(bound*2);//each state is characterised by 2 characters
						else if(LTLModelCounter.encoded_alphabet==2)
							count = ABC.count(bound*3);//each state is characterised by 3 characters
						else
							count = ABC.count(bound);
					}
					else{
						System.out.println("Unsatisfiable constraint");
						break;
					}
					time = getTimeInSecond(iTime,System.currentTimeMillis());
					System.out.println("Time: " + time); 
				}
				results.addLast(new SimpleEntry<BigInteger,Double>(count,time));
				bound++;
			}
			
			//show final results
			System.out.println("\nSummary");
			int b = init;
			if(!exhaustive)
				b = k;	
			Iterator<SimpleEntry<BigInteger, Double>> it = results.iterator();
			while(it.hasNext()){
				SimpleEntry<BigInteger,Double> r = it.next();
				String line = b+"\t"+r.getKey()+"\t"+r.getValue()+"\n";
				System.out.print(line);
				b++;
			}
		}

		finally{
			if(results.isEmpty() || outfile==null)
				return;
			
			//dispose ABC 
			ABC.abcDriver.dispose(); // release resources
			
			//write results to file
			File f = new File(outfile);
			f.createNewFile();
			FileWriter writer = new FileWriter(f, true);
			
			int b = init;
			if(!exhaustive)
				b = k;	
			Iterator<SimpleEntry<BigInteger, Double>> it = results.iterator();
			while(it.hasNext()){
				SimpleEntry<BigInteger,Double> r = it.next();
				String line = b+"\t"+r.getKey()+"\t"+r.getValue()+"\n";
				writer.write(line);
				b++;
			}
			
			writer.close();
		}
	}
	
	private static void correctUssage(){
		System.out.println("Use -ltl=dom [-g=goal -bc=boundary_condition -f=formulas_file] [-i=initial_bound -all:1..k] -alph=[alphabet] -k=bound_for_model_counting");
	}
	
	private static BigInteger count(LinkedList<String> formulas, LinkedList<String> BCs, String alph, long bound) throws IOException, InterruptedException{
		
		LinkedList<String> abcStrs = new LinkedList<>();
		for(String f: formulas){
			String abcStr = genABCString(f, alph);
			abcStrs.add(abcStr);
		}
		
		if(!BCs.isEmpty()){
			for(String BC : BCs){
				String s = genABCString(BC, alph);	
				String [] arr = Discretizer.cat(s);
				String abcStr = "";
				for(int i=0; i < arr.length-1; i++){
					abcStr += arr[i];
				}
				abcStrs.add(abcStr);
			}
		}

//		System.out.println(LTLModelCounter.toABClanguage(s));
//		System.out.println();
//		BigInteger count = ABC.count(LTLModelCounter.toABClanguage(s), bound);
//		String [] arr = Discretizer.or(s);
		System.out.println("Model Counting...");
		BigInteger count = BigInteger.ZERO;
		if(LTLModelCounter.encoded_alphabet==0)
			count = ABC.count(abcStrs,bound*2);//each state is characterised by 2 characters
		else if(LTLModelCounter.encoded_alphabet==1)
			count = ABC.count(abcStrs,bound*3);//each state is characterised by 3 characters
		else
			count = ABC.count(abcStrs,bound);
//		System.out.print(arr.length+" ");
////		for(int i=0; i<arr.length; i++){
//			String abcStr = LTLModelCounter.toABClanguage(arr[i]);
//			if(abcStr=="")
//				abcStr="\"\"";
////			System.out.println(abcStr);
//			System.out.print(".");
//
//			BigInteger or_count = BigInteger.ZERO;
//			try { or_count = ABC.count(abcStr, bound); }
//			catch (RuntimeException e) {
//				System.out.println("\nERROR:"+abcStr);
//				throw e;
//			}
//			count = count.add(or_count);
//		}
		return count;
	}
	
	public static double getTimeInSecond (double initialTime,double finalTime){
		//Compute execution time
		double time = finalTime - initialTime;
		//Translate to minutes and seconds
		double sec = (time/1000);
		return sec;
	}
	
	public static String genABCString(String ltl, String alph) throws IOException, InterruptedException{
		String form = "LTL="+ltl;
		if(alph!=null && alph!="")
			form += ",ALPHABET="+alph;
//		Nfa dfa = LTLModelCounter.ltl2dfa(formula);
		if(LTLModelCounter.props && alph.split(",").length>5 && alph.split(",").length<12)
			LTLModelCounter.encoded_alphabet = 0;
		else if(LTLModelCounter.props && alph.split(",").length>=12)
			LTLModelCounter.encoded_alphabet = 1;
		System.out.println("Translating from LTL to NBA...");
		Nba nba = LTLModelCounter.ltl2nba(form);
//		Nfa dfa = nba.toDeterministicNfa();
		System.out.println("Generating RE...");
		String s = LTLModelCounter.automata2RE(nba);
		return LTLModelCounter.toABClanguage(s);
	}
	
	enum FormulaType {Goal, Dom, BC, ALPH};
	public static void readFromFile (String fname, LinkedList<String> dom , LinkedList<String> BCs, LinkedList<String> goals, LinkedList<String> alph) throws IOException{
		BufferedReader br = new BufferedReader(new FileReader(fname));
		try {
//		    StringBuilder sb = new StringBuilder();
		    String line = br.readLine();
		    FormulaType f = FormulaType.Dom;
		    while (line != null) {
		    	
		    	if (!line.startsWith("--")){
			    	if (line.toUpperCase().startsWith("DOM"))
			    		f = FormulaType.Dom;
			    	else if (line.toUpperCase().startsWith("BC"))
			    		f = FormulaType.BC;
			    	else if (line.toUpperCase().startsWith("GOAL"))
			    		f = FormulaType.Goal;
			    	else if (line.toUpperCase().startsWith("ALPH"))
			    		f = FormulaType.ALPH;
			    	else if (!line.trim().isEmpty()){
			    		//insert the formula in the corresponding section
			    		if (f==FormulaType.Dom)
			    			dom.add(line);
			    		else if (f==FormulaType.BC)
			    			BCs.add(line);
			    		else if (f==FormulaType.Goal)
			    			goals.add(line);
			    		else 
			    			alph.add(line);
			    	}
		    	}
//		    	
//		        sb.append(line);
//		        sb.append(System.lineSeparator());
		        line = br.readLine();
		    }
//		    String everything = sb.toString();
		} finally {
		    br.close();
		}
	}
	
	public static String genAlph(LinkedList<String> alph){
		String alphStr = "";
		for (String s : alph)
			alphStr += s;
		if (!alphStr.contains("["))
			alphStr = "["+alphStr;
		if (!alphStr.contains("]"))
			alphStr += "]";
		return alphStr;
	}
}
