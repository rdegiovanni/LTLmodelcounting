package ltl2buchi;

import static org.junit.Assert.*;

import org.junit.Test;

import main.GoalConflictsLikelihoodAssessment;

public class GoalConflictsLikelihoodAssessmentTest {

	@Test
	public void test1() {
		String form = "p";
		String res = GoalConflictsLikelihoodAssessment.unfoldLTLFormula(form, 1);
		System.out.println(res);
		assertEquals("p", res);
	}
	
	@Test
	public void test2() {
		String form = "p";
		String res = GoalConflictsLikelihoodAssessment.unfoldLTLFormula(form, 2);
		System.out.println(res);
		assertEquals("p && X(p)", res);
	}
	
	@Test
	public void test3() {
		String form = "p";
		String res = GoalConflictsLikelihoodAssessment.firstTimeBChold(form, 1);
		System.out.println(res);
		assertEquals("p", res);
	}
	
	@Test
	public void test4() {
		String form = "p";
		String res = GoalConflictsLikelihoodAssessment.firstTimeBChold(form, 2);
		System.out.println(res);
		assertEquals("! p && X(p)", res);
	}
	
	@Test
	public void test5() {
		String form = "p";
		String res = GoalConflictsLikelihoodAssessment.firstTimeBChold(form, 3);
		System.out.println(res);
		assertEquals("! p && X(! p && X(p))", res);
	}
	
	@Test
	public void test6() {
		String form = "p && q";
		String res = GoalConflictsLikelihoodAssessment.firstTimeBChold(form, 2);
		System.out.println(res);
		assertEquals("! (p && q) && X(p && q)", res);
	}

}
