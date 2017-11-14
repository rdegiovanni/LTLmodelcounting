package icse2018;

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.LinkedList;

import org.junit.Test;

import main.Main;

public class ReadFromFile {

	@Test
	public void test() throws IOException {
		LinkedList<String> dom = new LinkedList<>();
		LinkedList<String> BCs = new LinkedList<>();
		LinkedList<String> goals = new LinkedList<>();
		LinkedList<String> alph = new LinkedList<>();
		String fname = "/Users/renzodegiovanni/Documents/workspaces/workspace/LTLmodelcounting/examples/minepump.txt";
		Main.readFromFile(fname, dom, BCs, goals, alph);
		System.out.println(dom);
		System.out.println(BCs);
		System.out.println(goals);
		System.out.println(alph);
	}

}
