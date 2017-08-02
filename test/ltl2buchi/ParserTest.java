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
import gov.nasa.ltl.trans.ParseErrorException;
import gov.nasa.ltl.trans.Translator;

public class ParserTest {

	@Test
	public void test1() throws ParseErrorException {
		Writer<String> w = Writer.getWriter (Writer.Format.FSP, System.out);
		String formula = "[] p";
		LTL2Buchi.reset_all_static();
		Graph<String> gba = Translator.translate(formula);	
		System.out.println("Generalized buchi automaton generated");
		w.write (gba);
		gba = SuperSetReduction.reduce(gba);
		System.out.println("Superset reduction");
		w.write (gba);
		Graph<String> ba = Degeneralize.degeneralize(gba);
		System.out.println("Degeneralized buchi automaton generated");
		w.write (ba);
		ba = SCCReduction.reduce(ba);
		System.out.println("Strongly connected component reduction");
		w.write (ba);
		//ba = Simplify.simplify(ba);
		ba = SFSReduction.reduce(ba);
		System.out.println("Fair simulation applied");
		w.write (ba);
	}
	
	
	@Test
	public void testMP() throws ParseErrorException {
		Writer<String> w = Writer.getWriter (Writer.Format.FSP, System.out);
		String formula = "[](hw -> X(po)) && [](m -> X(!po))";
		LTL2Buchi.reset_all_static();
		Graph<String> gba = Translator.translate(formula);	
		System.out.println("Generalized buchi automaton generated");
		w.write (gba);
		gba = SuperSetReduction.reduce(gba);
		System.out.println("Superset reduction");
		w.write (gba);
		Graph<String> ba = Degeneralize.degeneralize(gba);
		System.out.println("Degeneralized buchi automaton generated");
		w.write (ba);
		ba = SCCReduction.reduce(ba);
		System.out.println("Strongly connected component reduction");
		w.write (ba);
		//ba = Simplify.simplify(ba);
		ba = SFSReduction.reduce(ba);
		System.out.println("Fair simulation applied");
		w.write (ba);
	}

}
