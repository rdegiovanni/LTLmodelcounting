package main;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.MathContext;
import java.util.HashMap;
import java.util.Map;

public class Statistics {

	
	public static void main(String[] args) throws IOException{
		String domName = args[0];
		String fName = args[1];
		
		
		Map<Integer, BigInteger> dom = Statistics.loadResultFile(domName);
		Map<Integer, BigInteger> bc1 = Statistics.loadResultFile(fName);
		
		Map<Integer, BigInteger> domk = Statistics.computeExactKvalue(dom);
		Map<Integer, BigInteger> fk = Statistics.computeExactKvalue(bc1);
		
		Map<Integer, Double> plot = Statistics.printStatistics(domk, fk);
		if(args[2]!=null){
			String out = args[2];
			writeStatistics(out, plot);
		}
		Statistics.printStatistics(plot);
	}
	
	public static  Map<Integer, Double> loadFile(String filename) throws IOException{
		BufferedReader br = new BufferedReader(new FileReader(filename));
		Map<Integer, Double> plot = new HashMap<Integer, Double>();
		try {
		    String line = br.readLine();   
		    while (line != null) {
		        String[] arr = line.split("\t");
		        Integer k = Integer.valueOf(arr[0]);
		        Double p = Double.valueOf(arr[1]);
		        plot.put(k, p);
		        line = br.readLine();
		    }
		} finally {
		    br.close();
		}
		return plot;
	}
	
	public static  Map<Integer, BigInteger> loadResultFile(String filename) throws IOException{
		BufferedReader br = new BufferedReader(new FileReader(filename));
		Map<Integer, BigInteger> plot = new HashMap<Integer, BigInteger>();
		try {
		    String line = br.readLine();   
		    while (line != null) {
		        String[] arr = line.split("\t");
		        Integer k = Integer.valueOf(arr[0]);
		        BigInteger p = new BigInteger(arr[1]);
		        plot.put(k, p);
		        line = br.readLine();
		    }
		} finally {
		    br.close();
		}
		return plot;
	}
	
	public static  void writeStatistics(String filename, Map<Integer, Double> plot) throws IOException{
		BufferedWriter output = null;
        try {
            File file = new File(filename);
            output = new BufferedWriter(new FileWriter(file));
            for(Integer k : plot.keySet()){
            	output.write(k+"\t"+plot.get(k)+"\n");
            }
        } catch ( IOException e ) {
            e.printStackTrace();
        } finally {
          if ( output != null ) {
            output.close();
          }
        }
	}
	
	public static void printStatistics(Map<Integer, Double> plot){
		int N = plot.size();
		
		double median = 0;
		double E = 0;
		
		for(Integer k : plot.keySet()){
			double p = plot.get(k).doubleValue();
			median += p/N;
			E += k*p;
		}
		
		double Var = 0;
		for(Integer k : plot.keySet()){
			double p = plot.get(k).doubleValue();
			Var += p*(k-E)*(k-E);
		}
		
		System.out.println("Median: "+median);
		System.out.println("E: "+E);
		System.out.println("Var: "+Var);
		System.out.println("SD: "+ Math.sqrt(Var));	
//		//Potential probability of F(BC)
//		double Prob = 1;
//		double Acum = 0;
//		for(Integer k : plot.keySet()){
//			double p = plot.get(k).doubleValue();
//			System.out.println("P(F(BC),"+k+") = " + (Prob * p));
//			Acum += (Prob * p);
//			Prob = Prob * (1-p);
//		}
//		System.out.println("Acum: "+ Acum);
	}
	
	public static Map<Integer, Double> printStatistics(Map<Integer, BigInteger> dom, Map<Integer, BigInteger> f){
		Map<Integer, Double> plot = new HashMap<>();
		for(Integer k : f.keySet()){
			BigDecimal d = new BigDecimal(dom.get(k));
			BigDecimal fd = new BigDecimal(f.get(k));
			BigDecimal p = fd.divide(d,MathContext.DECIMAL32);
//			double p = f.get(k).doubleValue()/dom.get(k).doubleValue();
//			if(Double.isNaN(p))
//				p = plot.get(k-1);
//				break;
			plot.put(k, p.doubleValue());
			System.out.println(k+"\t"+p);
		}
		return plot;
	}
	
	public static Map<Integer, BigInteger> computeExactKvalue(Map<Integer, BigInteger> f){
		Map<Integer, BigInteger> fk = new HashMap<>();
		BigInteger prev = BigInteger.ZERO;
		for(Integer k : f.keySet()){
			BigInteger d = f.get(k).subtract(prev);
			fk.put(k,d);
//			System.out.println(k+"\t"+d);
			prev = f.get(k);
		}
		return fk;
	}
	
}
