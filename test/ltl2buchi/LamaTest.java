package ltl2buchi;

import static org.junit.Assert.*;

import java.util.Iterator;


import org.junit.Test;

import automata.FiniteAutomaton;
import de.uni_luebeck.isp.rltlconv.automata.State;
import de.uni_luebeck.isp.rltlconv.automata.DirectedState;
import de.uni_luebeck.isp.rltlconv.automata.Nfa;
import de.uni_luebeck.isp.rltlconv.automata.Sign;
import de.uni_luebeck.isp.rltlconv.cli.Conversion;
import de.uni_luebeck.isp.rltlconv.cli.Conversion.ConversionVal;
import gov.nasa.ltl.graph.Graph;
import gov.nasa.ltl.graph.Simplify;
import gov.nasa.ltl.graphio.Writer;
import gov.nasa.ltl.trans.LTL2Buchi;
import gov.nasa.ltl.trans.Parser;
import main.LTLModelCounter;
import main.LTLModelCounter2;
import de.uni_luebeck.isp.rltlconv.cli.RltlConv;
import scala.Tuple2;
import scala.collection.immutable.List;
import scala.collection.immutable.Map;
import scala.collection.immutable.Set;
import scala.collection.immutable.Vector;
import scala.collection.immutable.VectorIterator;

public class LamaTest {

	@Test
	public void test1() {
		String formula = "LTL=G(p -> X(q))";
		
		ConversionVal[] conv = {Conversion.PROPS(), Conversion.FORMULA(), Conversion.NBA(), Conversion.MIN(), Conversion.NFA(), Conversion.DFA()};
		Object res = RltlConv.convert(formula, conv);
		Nfa fsa = (Nfa) res;
		System.out.println(fsa);
		fsa = fsa.toNamedNfa();
		System.out.println(fsa);
		Map<Tuple2<State,Sign>, List<DirectedState>> trans = fsa.transitions();
		Vector<Tuple2<Tuple2<State,Sign>,List<DirectedState>>> vector =  trans.toVector();
		VectorIterator<Tuple2<Tuple2<State,Sign>,List<DirectedState>>> it = vector.iterator();
		while(it.hasNext()){
			Tuple2<Tuple2<State,Sign>,List<DirectedState>> o = it.next();
			System.out.println(o._1()._1().name());
			o._2().iterator();
		}
	}
	
	@Test
	public void test2() {
		String formula = "LTL=G(p -> X(q))";
		
		ConversionVal[] conv = {Conversion.PROPS(), Conversion.FORMULA(), Conversion.NBAS(),Conversion.MIN()};
		Object res = RltlConv.convert(formula, conv);
		System.out.println(res);
		
	}
	
	@Test
	public void test3() {
		String formula = "LTL=G(p)";
		Nfa dfa = LTLModelCounter.ltl2dfa(formula);
		System.out.println(dfa);

		String s = LTLModelCounter.automata2RE(dfa);
		System.out.println(s);
	}
	
	@Test
	public void test4() {
		String formula = "LTL=G(p -> q)";
		Nfa dfa = LTLModelCounter.ltl2dfa(formula);
		System.out.println(dfa);

		String s = LTLModelCounter.automata2RE(dfa);
		System.out.println(s);
		
		
	}
	
	@Test
	public void test5() {
		String formula = "LTL=G(p -> X(q)) && ! p && X(! p && X(p))";

		Nfa dfa = LTLModelCounter.ltl2dfa(formula);
//		System.out.println(dfa);

		String s = LTLModelCounter.automata2RE(dfa);
//		System.out.println(s);
		String abcStr = LTLModelCounter.toABClanguage(s);
		System.out.println(abcStr);
		
	}
	
}
