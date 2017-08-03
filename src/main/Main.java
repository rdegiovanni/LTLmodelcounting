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

import de.uni_luebeck.isp.rltlconv.automata.Nfa;
import regular.Discretizer;

public class Main {

	public static void main(String[] args) throws Exception {
		String formula = null;
		String BC = null;
		String alph = null;
		String outfile = null;
		int k = 0;
		int init = 1;
		boolean exhaustive = false;
		
		for (int i = 0; i< args.length; i++ ){
//			System.out.println(args[i]);
			if(args[i].startsWith("-ltl=")){
				formula = args[i].replace("-ltl=", "");
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
			else{
				correctUssage();
				return;
			}
		}
		
		if(formula==null || k <= 0){
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
				String ltl_str = "LTL="+formula;
				if(BC != null){
					String BCatPosK = GoalConflictsLikelihoodAssessment.firstTimeBChold(BC, bound);
					ltl_str = ltl_str+" && "+BCatPosK;
				}			

				if(alph != null){
					ltl_str += ",ALPHABET="+alph;
				}
//				String formula2 = "LTL=G((p && X(p)) -> X(X(! h))) && ! ((h && m)) && X(! ((h && m)) && X(! ((h && m)) && X((h && m)))),ALPHABET=[p,h,m]";
//				
////				if(!ltl_str.equals(formula2))
////					throw new RuntimeException();
				
//				System.out.print(bound + ": "+ ltl_str);
				
				double iTime = System.currentTimeMillis();
				BigInteger count = count(ltl_str, bound);
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
		catch(Exception e){
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
	
	private static BigInteger count(String formula, long bound) throws IOException{
//		System.out.println(formula);
		
		Nfa dfa = LTLModelCounter.ltl2dfa(formula);
		String s = LTLModelCounter.automata2RE(dfa);
//		System.out.println(LTLModelCounter.toABClanguage(s));
//		System.out.println();
//		BigInteger count = ABC.count(LTLModelCounter.toABClanguage(s), bound);
		String [] arr = Discretizer.or(s);
		BigInteger count = BigInteger.ZERO;
		for(int i=0; i<arr.length; i++){
			String abcStr = LTLModelCounter.toABClanguage(arr[i]);
			if(abcStr=="")
				abcStr="\"\"";
//			System.out.println(abcStr);
			BigInteger or_count = ABC.count(abcStr, bound);
			count = count.add(or_count);
		}
		return count;
	}
	
	public static double getTimeInSecond (double initialTime,double finalTime){
		//Compute execution time
		double time = finalTime - initialTime;
		//Translate to minutes and seconds
		double sec = (time/1000);
		return sec;
	}
}
