package main;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
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
		String dom = null;
		String BC = null;
		LinkedList<String> goals = new LinkedList<>();
		String alph = null;
		String outfile = null;
		int k = 0;
		int deph = 0;
		int init = 1;
		boolean exhaustive = false;
		
		for (int i = 0; i< args.length; i++ ){
//			System.out.println(args[i]);
			if(args[i].startsWith("-ltl=")){
				dom = args[i].replace("-ltl=", "");
			}
			else if(args[i].startsWith("-g=")){
				goals.add(args[i].replace("-g=", ""));
			}
			else if(args[i].startsWith("-bc=")){
				BC = args[i].replace("-bc=", "");
			}
			else if(args[i].startsWith("-alph=")){
				alph = args[i].replace("-alph=", "");
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
			
			while(bound<=k){
				LinkedList<String> formulas = new LinkedList<>();
				formulas.add(dom);
				formulas.addAll(goals);
				
				if(BC != null){
					String BCatPosK = "";
					if(deph==0)
						BCatPosK = GoalConflictsLikelihoodAssessment.firstTimeBChold(BC, bound);
					else if(deph!=0 && bound-deph>=1)
						BCatPosK = GoalConflictsLikelihoodAssessment.firstTimeBChold(BC, bound-deph);
					else
						BCatPosK = "FALSE";
					formulas.addFirst(BCatPosK);
				}

				double iTime = System.currentTimeMillis();
				BigInteger count = count(formulas,alph, bound);
				double time = getTimeInSecond(iTime,System.currentTimeMillis());
				System.out.println("Time: " + time); 
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
		System.out.println("Use -ltl=dom_goals_ltl [-bc=boundary_condition] -k=bound_for_model_counting");
	}
	
	private static BigInteger count(LinkedList<String> formulas, String alph, long bound) throws IOException, InterruptedException{
		
		LinkedList<String> abcStrs = new LinkedList<>();
		for(String f: formulas){
			String abcStr = genABCString(f, alph, bound);
			abcStrs.add(abcStr);
		}
		
		

//		System.out.println(LTLModelCounter.toABClanguage(s));
//		System.out.println();
//		BigInteger count = ABC.count(LTLModelCounter.toABClanguage(s), bound);
//		String [] arr = Discretizer.or(s);
		BigInteger count = BigInteger.ZERO;
		count = ABC.count(abcStrs, bound);
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
	
	public static String genABCString(String ltl, String alph, long bound) throws IOException, InterruptedException{
		String form = "LTL="+ltl;
		if(alph!=null && alph!="")
			form += ",ALPHABET="+alph;
//		Nfa dfa = LTLModelCounter.ltl2dfa(formula);
		Nba nba = LTLModelCounter.ltl2nba(form);
//		Nfa dfa = nba.toDeterministicNfa();
		String s = LTLModelCounter.automata2RE(nba);
		return s;
	}
}
