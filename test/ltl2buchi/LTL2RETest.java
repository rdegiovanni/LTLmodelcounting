package ltl2buchi;

import static org.junit.Assert.*;

import org.junit.Test;

import gov.nasa.ltl.graph.Degeneralize;
import gov.nasa.ltl.graph.Graph;
import gov.nasa.ltl.graph.SCCReduction;
import gov.nasa.ltl.graph.SFSReduction;
import gov.nasa.ltl.graph.Simplify;
import gov.nasa.ltl.graph.SuperSetReduction;
import gov.nasa.ltl.graphio.Writer;
import gov.nasa.ltl.trans.LTL2Buchi;
import gov.nasa.ltl.trans.Parser;
import gov.nasa.ltl.trans.Translator;
import main.LTLModelCounter;
import main.LTLModelCounter2;

public class LTL2RETest {

	@Test
	public void test1() {
		String formula = "[](p)";
		Writer<String> w = Writer.getWriter (Writer.Format.FSP, System.out);
		LTL2Buchi.reset_all_static();
		//Translator.setAlgorithm(Translator.Algorithm.LTL2AUT);
		Graph<String> buchi = LTLModelCounter2.ltl2buchi(formula);
		System.out.println(Parser.propositions);

		//printStats(ba, "Bisimulation applied");
		Graph<String> ba = Simplify.simplify(buchi);
		
	    //printStats(ba, "Fair simulation applied");
		//ba = SFSReduction.reduce(ba);
		
		
		String s = LTLModelCounter2.buchi2RE(ba);
		System.out.println(s);
	}
	
	@Test
	public void testMPDOM() {
		String dom = "[]((po && X(po)) -> X(X(!hw)))";
		
		String formula = dom;
		Writer<String> w = Writer.getWriter (Writer.Format.FSP, System.out);
		LTL2Buchi.reset_all_static();
		//Translator.setAlgorithm(Translator.Algorithm.LTL2AUT);
		Graph<String> buchi = LTLModelCounter2.ltl2buchi(formula);

		System.out.println(Parser.propositions);

		//printStats(ba, "Bisimulation applied");
		Graph<String> ba = Simplify.simplify(buchi);
		
	    //printStats(ba, "Fair simulation applied");
		//ba = SFSReduction.reduce(ba);
		
		
		String s = LTLModelCounter2.buchi2RE(ba);
		System.out.println(s);
	}
	
	@Test
	public void test2RE() {
		String formula = "[](p -> X(q))";

		Writer<String> w = Writer.getWriter (Writer.Format.FSP, System.out);
		LTL2Buchi.reset_all_static();
		//Translator.setAlgorithm(Translator.Algorithm.LTL2AUT);
		Graph<String> buchi = LTLModelCounter2.ltl2buchi(formula);

		System.out.println(Parser.propositions);

		//printStats(ba, "Bisimulation applied");
		Graph<String> ba = Simplify.simplify(buchi);
		
	    //printStats(ba, "Fair simulation applied");
		//ba = SFSReduction.reduce(ba);
		
		
		String s = LTLModelCounter2.buchi2RE(ba);
		System.out.println(s);
	}
	
	@Test
	public void test2RE2() {
		String formula = "[](p -> q)";

		Writer<String> w = Writer.getWriter (Writer.Format.FSP, System.out);
		LTL2Buchi.reset_all_static();
		Translator.setAlgorithm(Translator.Algorithm.LTL2AUT);
		Graph<String> buchi = LTLModelCounter2.ltl2buchi(formula);

		System.out.println(Parser.propositions);

		//printStats(ba, "Bisimulation applied");
		Graph<String> ba = Simplify.simplify(buchi);
		
	    //printStats(ba, "Fair simulation applied");
		//ba = SFSReduction.reduce(ba);
		
		
		String s = LTLModelCounter2.buchi2RE2(ba);
		System.out.println(s);
	}
}



