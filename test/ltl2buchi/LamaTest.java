package ltl2buchi;

import static org.junit.Assert.*;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Iterator;
import java.util.LinkedList;

import org.junit.Test;

import automata.FiniteAutomaton;
import de.uni_luebeck.isp.rltlconv.automata.State;
import de.uni_luebeck.isp.rltlconv.automata.DirectedState;
import de.uni_luebeck.isp.rltlconv.automata.Nfa;
import de.uni_luebeck.isp.rltlconv.automata.Sign;
import de.uni_luebeck.isp.rltlconv.cli.Conversion;
import de.uni_luebeck.isp.rltlconv.cli.Conversion.ConversionVal;
import de.uni_luebeck.isp.rltlconv.cli.Main;
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
	public void test3() throws IOException {
		String formula = "LTL=G(p)";
		Nfa dfa = LTLModelCounter.ltl2dfa(formula);
		System.out.println(dfa);

		String s = LTLModelCounter.automata2RE(dfa);
		System.out.println(s);
	}
	
	@Test
	public void test4() throws IOException {
		String formula = "LTL=G(p -> q)";
		Nfa dfa = LTLModelCounter.ltl2dfa(formula);
		System.out.println(dfa);

		String s = LTLModelCounter.automata2RE(dfa);
		System.out.println(s);
		
		
	}
	
	@Test
	public void test5() throws IOException {
		String formula = "LTL=G(p -> X(q)) && ! p && X(! p && X(p))";

		Nfa dfa = LTLModelCounter.ltl2dfa(formula);
//		System.out.println(dfa);

		String s = LTLModelCounter.automata2RE(dfa);
//		System.out.println(s);
		String abcStr = LTLModelCounter.toABClanguage(s);
		System.out.println(abcStr);
		
	}
	
	@Test
	public void test6() throws IOException {
		String formula = "LTL=G((po && X(po)) -> X(X(! hw))) && ! ((hw && m)) && X(! ((hw && m)) && X(! ((hw && m)) && X((hw && m)))),ALPHABET=[po,hw,m]";

		//write results to file
		File f = new File("rltlconv.txt");
		f.createNewFile();
		FileWriter writer = new FileWriter(f);
		writer.append(formula);
		writer.close();
		
		String ff = Main.load("@rltlconv.txt");
//		ConversionVal[] conv = {Conversion.PROPS(), Conversion.FORMULA(),Conversion.APA(),Conversion.NBA(), Conversion.MIN(), Conversion.NFA(), Conversion.DFA()};
		String [] conv = {"--props", "--formula", "--apa", "--nba", "--min", "--nfa", "--dfa"};
		Object res = RltlConv.convert(ff, conv);
		
		Nfa fsa = (Nfa) res;
		System.out.println(fsa);
	}
	
	@Test
	public void test7() throws IOException {
		String formula = "LTL=G((po && X(po)) -> X(X(! hw))) && ! ((hw && m)) && X(! ((hw && m)) && X(! ((hw && m)) && X((hw && m)))),ALPHABET=[po,hw,m]";

		//write results to file
		File f = new File("rltlconv.txt");
		f.createNewFile();
		FileWriter writer = new FileWriter(f);
		writer.append(formula);
		writer.close();
		
		Process p = Runtime.getRuntime().exec("./rltlconv.sh @rltlconv.txt --props --formula --apa --nba --min --nfa --dfa > rltlconv-out.txt");
		Object res = Main.load("@rltlconv-out.txt");
		
		Nfa fsa = (Nfa) RltlConv.convert(res, Conversion.DFA());
		System.out.println(fsa);
	}
	
	@Test
	public void test8() throws IOException {
		String formula = "LTL=G((po && X(po)) -> X(X(! hw))) && ! ((hw && m)) && X(! ((hw && m)) && X(! ((hw && m)) && X((hw && m)))),ALPHABET=[po,hw,m]";

		ConversionVal[] conv = {Conversion.PROPS(), Conversion.FORMULA(),Conversion.APA(),Conversion.NBA(), Conversion.MIN(), Conversion.NFA(), Conversion.DFA()};
//		String [] conv = {"--props", "--formula", "--apa", "--nba", "--min", "--nfa", "--dfa"};
		Object res = RltlConv.convert(formula, conv);
		Nfa fsa = (Nfa) res;
		
		//write results to file
		File f = new File("rltlconv-out.txt");
		f.createNewFile();
		FileWriter writer = new FileWriter(f);
		writer.append(fsa.toString());
		writer.close();

		res = Main.load("@rltlconv-out.txt");
		fsa = (Nfa) RltlConv.convert(res, Conversion.DFA());
		System.out.println(fsa.toNamedNfa());
	}
	
}
