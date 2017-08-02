package abc;

import static org.junit.Assert.*;

import java.math.BigInteger;

import org.junit.Test;

import de.uni_luebeck.isp.rltlconv.automata.Nfa;
import main.ABC;
import main.LTLModelCounter;
import regular.Discretizer;

public class ABCTest {

	@Test
	public void test1() {
		String formula = "G(p||q)";
		int bound = 2;
		BigInteger count = ABC.count(formula, bound);
		assertEquals(4, count);
	}


	public static void main(String [] args){
		String formula = "(0|1)*";
		int bound = 2;
		BigInteger count = ABC.count(formula, bound);
		assertEquals(BigInteger.valueOf(4), count);
	}
}
