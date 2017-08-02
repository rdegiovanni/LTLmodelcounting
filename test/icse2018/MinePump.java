package icse2018;

import static org.junit.Assert.*;

import java.math.BigInteger;

import org.junit.Test;

import de.uni_luebeck.isp.rltlconv.automata.Nfa;
import main.ABC;
import main.GoalConflictsLikelihoodAssessment;
import main.LTLModelCounter;
import main.Main;
import regular.Discretizer;

public class MinePump {

	@Test
	public void testDom() {
		String alphabet = ",ALPHABET=[po,hw,m]";
		String dom = "G((po && X(po)) -> X(X(! hw)))";
		String formula = "LTL="+dom;
		Nfa dfa = LTLModelCounter.ltl2dfa(formula+alphabet);
		System.out.println(dfa);

		String s = LTLModelCounter.automata2RE(dfa);
		System.out.println(s);

		String abcStr = LTLModelCounter.toABClanguage(s);
		System.out.println(abcStr);
	}
	
	@Test
	public void testDomBC1() {
		String dom = "G((po && X(po)) -> X(X(! hw)))";
		String BC1 = "(hw && m)";
		String alph = ",ALPHABET=[po,hw,m]";
		String BCatPosK = GoalConflictsLikelihoodAssessment.firstTimeBChold(BC1, 4);
		String formula = "LTL="+dom+" && "+BCatPosK+alph;
		System.out.println(formula);
		Nfa dfa = LTLModelCounter.ltl2dfa(formula);
		System.out.println(dfa);

		String s = LTLModelCounter.automata2RE(dfa);
		System.out.println(s);
		
		String abcStr = LTLModelCounter.toABClanguage(s);
		System.out.println(abcStr);
		String [] arr = Discretizer.or(s);
		for(int i=0; i<arr.length; i++){
			abcStr = LTLModelCounter.toABClanguage(arr[i]);
			System.out.println(abcStr);
		}
//		

	}
	
	@Test
	public void testDomBC1_ERROR() {
		
		String formula = "LTL=G((po && X(po)) -> X(X(! hw))) && ! ((hw && m)) && X(! ((hw && m)) && X(! ((hw && m)) && X((hw && m)))),ALPHABET=[po,hw,m]";
		Nfa dfa = LTLModelCounter.ltl2dfa(formula);
		String s = LTLModelCounter.automata2RE(dfa);
		System.out.println(LTLModelCounter.toABClanguage(s));
		System.out.println();
		String [] arr = Discretizer.or(s);
//		BigInteger count = BigInteger.ZERO;
		for(int i=0; i<arr.length; i++){
			String abcStr = LTLModelCounter.toABClanguage(arr[i]);
			System.out.println(abcStr);
//			BigInteger or_count = ABC.count(abcStr, 3);
//			count = count.add(or_count);
		}
//		

	}
	
	@Test
	public void testDomBC1G1() {
		String dom = "G((po && X(po)) -> X(X(! hw)))";
		String G1 = "G(hw -> X(po))";
		String G2 = "G(m -> X(! po))";
		String BC1 = "F(hw && m)";
		String formula = "LTL="+dom+" && "+BC1+" && "+G1+" && ! "+G2;
		Nfa dfa = LTLModelCounter.ltl2dfa(formula);
		System.out.println(dfa);

		String s = LTLModelCounter.automata2RE(dfa);
		System.out.println(s);
		
	}
	
	@Test
	public void testDomBC1G2() {
		String dom = "G((po && X(po)) -> X(X(!hw)))";
		String G1 = "G(hw -> X(po))";
		String G2 = "G(m -> X(! po))";
		String BC1 = "F(hw && m)";
		String formula = "LTL="+dom+" && "+BC1+" && ! "+G1+" && "+G2;
		Nfa dfa = LTLModelCounter.ltl2dfa(formula);
		System.out.println(dfa);

		String s = LTLModelCounter.automata2RE(dfa);
		System.out.println(s);
	}
	
	
	
	@Test
	public void testDomBC1Splitted() {
		String dom = "G((po && X(po)) -> X(X(! hw)))";
		String BC1 = "(hw && m)";
		String alph = ",ALPHABET=[po,hw,m]";
		String BCatPosK = GoalConflictsLikelihoodAssessment.unfoldLTLFormula("! "+BC1, 5);
		
		String domformula = "LTL="+dom+alph;
		System.out.println(domformula);
		Nfa dfa = LTLModelCounter.ltl2dfa(domformula);
//		System.out.println(dfa);
		String s = LTLModelCounter.automata2RE(dfa);
//		System.out.println(s);
		String abcStr = LTLModelCounter.toABClanguage(s);
		System.out.println(abcStr);
		
		String bcformula = "LTL=F"+BC1+" && "+BCatPosK+alph;
		System.out.println(bcformula);
		dfa = LTLModelCounter.ltl2dfa(bcformula);
//		System.out.println(dfa);
		s = LTLModelCounter.automata2RE(dfa);
//		System.out.println(s);
		abcStr = LTLModelCounter.toABClanguage(s);
		System.out.println(abcStr);
	}
	

	
	@Test
	public void testDomBC2() {
		String dom = "G((po && X(po)) -> X(X(! hw)))";
		String BC2 = "((hw && ! m && po && X(((! hw && ! po) || (hw && (m || ! po))))))";
		String alph = ",ALPHABET=[po,hw,m]";
		String BCatPosK = GoalConflictsLikelihoodAssessment.firstTimeBChold(BC2, 6);
		
		String formula = "LTL="+dom+" && "+BCatPosK+alph;
		System.out.println(formula);
		Nfa dfa = LTLModelCounter.ltl2dfa(formula);
//		System.out.println(dfa);
		String s = LTLModelCounter.automata2RE(dfa);
//		System.out.println(s);
		String abcStr = LTLModelCounter.toABClanguage(s);
		System.out.println(abcStr);
		
	}
	
	@Test
	public void testDomBC2Splitted() {
		String dom = "G((po && X(po)) -> X(X(! hw)))";
		String BC2 = "((hw && ! m && po && X(((! hw && ! po) || (hw && (m || ! po))))))";
		String alph = ",ALPHABET=[po,hw,m]";
		String BCatPosK = GoalConflictsLikelihoodAssessment.firstTimeBChold(BC2, 6);
		
		String domformula = "LTL="+dom+alph;
		System.out.println(domformula);
		Nfa dfa = LTLModelCounter.ltl2dfa(domformula);
//		System.out.println(dfa);
		String s = LTLModelCounter.automata2RE(dfa);
//		System.out.println(s);
		String abcStr = LTLModelCounter.toABClanguage(s);
		System.out.println(abcStr);
		
		String bcformula = "LTL="+BCatPosK+alph;
		System.out.println(bcformula);
		dfa = LTLModelCounter.ltl2dfa(bcformula);
//		System.out.println(dfa);
		s = LTLModelCounter.automata2RE(dfa);
//		System.out.println(s);
		abcStr = LTLModelCounter.toABClanguage(s);
		System.out.println(abcStr);
	}
	
	
}