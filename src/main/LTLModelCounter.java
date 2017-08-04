package main;

import java.awt.Point;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.LinkedList;

import automata.fsa.FSAToRegularExpressionConverter;
import automata.fsa.FSATransition;
import automata.fsa.FiniteStateAutomaton;
import de.uni_luebeck.isp.buchi.BuchiAutomaton;
import de.uni_luebeck.isp.buchi.Transition;
import de.uni_luebeck.isp.rltlconv.automata.DirectedState;
import de.uni_luebeck.isp.rltlconv.automata.Nba;
import de.uni_luebeck.isp.rltlconv.automata.Nfa;
import de.uni_luebeck.isp.rltlconv.automata.Sign;
import de.uni_luebeck.isp.rltlconv.automata.State;
import de.uni_luebeck.isp.rltlconv.cli.Conversion;
import de.uni_luebeck.isp.rltlconv.cli.Main;
import de.uni_luebeck.isp.rltlconv.cli.RltlConv;
import scala.Tuple2;
import scala.collection.Iterator;
import scala.collection.immutable.List;
import scala.collection.immutable.Map;
import scala.collection.immutable.Set;
import scala.collection.immutable.Vector;
import scala.collection.immutable.VectorIterator;
import de.uni_luebeck.isp.rltlconv.cli.Conversion.ConversionVal;
import scala.collection.immutable.VectorIterator;


public class LTLModelCounter {

	private static void writeFile(String fname,String text) throws IOException{
		BufferedWriter output = null;
        try {
            File file = new File(fname);
            output = new BufferedWriter(new FileWriter(file));
            output.write(text);
        } catch ( IOException e ) {
            e.printStackTrace();
        } finally {
          if ( output != null ) {
            output.close();
          }
        }
	}
	
	private static void runCommand() throws IOException, InterruptedException{
		Process p = Runtime.getRuntime().exec("./rltlconv.sh @rltlconv.txt --formula --apa --nba --min --nfa --dfa");
		
		InputStream in = p.getInputStream();
    	InputStreamReader inread = new InputStreamReader(in);
    	BufferedReader bufferedreader = new BufferedReader(inread);
    	String aux;
    	String out = "";
	    while ((aux = bufferedreader.readLine()) != null) {
	    	out += aux+"\n";
	    }
	    if(out!="")
	    	writeFile("rltlconv-out.txt",out);
	    
	 // Leer el error del programa.
    	InputStream err = p.getErrorStream();
    	InputStreamReader errread = new InputStreamReader(err);
    	BufferedReader errbufferedreader = new BufferedReader(errread);
    	
	    while ((aux = errbufferedreader.readLine()) != null) {
	    	System.out.println("ERR: " + aux);
	    }
	   
	    // Check for failure
		if (p.waitFor() != 0) {
			System.out.println("exit value = " + p.exitValue());
		}
  
		// Close the InputStream
    	bufferedreader.close();
    	inread.close();
    	in.close();
   		// Close the ErrorStream
   		errbufferedreader.close();
   		errread.close();
   		err.close();
    		
   		if (p!=null) {
//   			InputStream is = p.getInputStream();
 //  			InputStream es = p.getErrorStream();
  			OutputStream os = p.getOutputStream();
//   				if (is!=null) is.close();
//   				if (es!=null) es.close();
			if (os!=null) os.close();
   		}
	}
	public static Nfa ltl2dfa(String formula) throws IOException, InterruptedException{
//		ConversionVal[] conv = {Conversion.PROPS(), Conversion.FORMULA(),Conversion.APA(),Conversion.NBA(), Conversion.MIN(), Conversion.NFA(), Conversion.DFA()};
//		Object res = RltlConv.convert(formula, conv);
		
		//write results to file
		String fname = "rltlconv.txt";
		
		writeFile(fname,formula);
		
		runCommand();
		
		Object res = Main.load("@rltlconv-out.txt");
		
		Nfa fsa = (Nfa) RltlConv.convert(res, Conversion.DFA());
		return fsa.toNamedNfa();
	}
	
	public static Nba ltl2nba(String formula) throws IOException, InterruptedException{
		ConversionVal[] conv = {Conversion.FORMULA(),Conversion.PROPS(), Conversion.NBA(), Conversion.MIN()};
//		String [] conv = {"--props", "--formula", "--apa", "--nba", "--min", "--nfa", "--dfa"};
		Object res = RltlConv.convert(formula, conv);
		Nba nba = (Nba) res;		
		return nba.toNamedNba();
	}
	
//	//Map labels to ids
//	static java.util.Map<String,Integer> labelIDs = new HashMap<>();
//	
//	static String getLabel(String l){
//		if(!labelIDs.containsKey(l)){
//			labelIDs.put(l, labelIDs.keySet().size());
//		}
//		int base = 97;//a
//		String label = ""+Character.toChars(base+labelIDs.get(l))[0];
//		return label;
//	}
			
	public static String automata2RE(Nfa ltl_ba){
		
		FiniteStateAutomaton fsa = new FiniteStateAutomaton();
	
		//Map nodes to states ids
		java.util.Map<String,Integer> ids = new HashMap<>();
		//get initial node
		State in = ltl_ba.start().head(); //CUIDADO:que pasa si tenemos varios estados iniciales.

		//create and set initial state
		automata.State is = fsa.createState(new Point());
		fsa.setInitialState(is);
		
		//Map labels to ids
		java.util.Map<String,Integer> labelIDs = new HashMap<>();
					
		//initial node ids
		ids.put(in.name(), is.getID());
			
		Map<Tuple2<State,Sign>, List<DirectedState>> trans = (Map<Tuple2<State, Sign>, List<DirectedState>>) ltl_ba.transitions();
		Vector<Tuple2<Tuple2<State,Sign>,List<DirectedState>>> vector =  trans.toVector();
		VectorIterator<Tuple2<Tuple2<State,Sign>,List<DirectedState>>> ltl_ba_it = vector.iterator();
		while(ltl_ba_it.hasNext()){
			Tuple2<Tuple2<State,Sign>,List<DirectedState>> o = ltl_ba_it.next();
			State from = o._1()._1();
			//checks if ID exists
			int ID = 0;
			automata.State fromState = null;
			if (ids.containsKey(from.name())){
				ID = ids.get(from.name());
				fromState = fsa.getStateWithID(ID);
			}
			else{
				//create new state
				fromState = fsa.createState(new Point());
				//update ids
				ids.put(from.name(), fromState.getID());
				ID = fromState.getID();
			}
			
			//get Label
			String l = o._1()._2().toString();
			
//			String label = getLabel(l);
			if(!labelIDs.containsKey(l)){
				labelIDs.put(l, labelIDs.keySet().size());
			}
			int base = 97;//a
			String label = ""+Character.toChars(base+labelIDs.get(l))[0];
			
			Iterator<DirectedState> listIt = o._2().iterator();
			while(listIt.hasNext()){
				State to = listIt.next().state();
				//check if toState exists
				automata.State toState = null;
				
				if (ids.containsKey(to.name())){
					ID = ids.get(to.name());
					toState = fsa.getStateWithID(ID);
				}
				else{
					//create new state
					toState = fsa.createState(new Point());
					//update ids
					ids.put(to.name(), toState.getID());
					ID = toState.getID();
				}
				
				//add transition
				FSATransition t = new FSATransition(fromState,toState,label);
				fsa.addTransition(t);
			}
		}
		
		//add final states
		Iterator<State> ac_it = ltl_ba.accepting().iterator();
		while(ac_it.hasNext()){
			State a = ac_it.next();
			int ID = ids.get(a.name());
			automata.State as = fsa.getStateWithID(ID);
			fsa.addFinalState(as);
		}
		
		//convertToDFA
		//FiniteStateAutomaton dfa = (new NFAToDFA()).convertToDFA(fsa);
		
		//minimize automaton
		//Automaton m = (new Minimizer()).getMinimizeableAutomaton(dfa);
		
//		System.out.println(labelIDs);
		
		//removeEmptyTransitions(fsa);
//		System.out.println(fsa.toString());

		FSAToRegularExpressionConverter.convertToSimpleAutomaton(fsa);
//		System.out.println(fsa.toString());
		return FSAToRegularExpressionConverter.convertToRegularExpression(fsa);
	}
	
public static String automata2RE(Nba ltl_ba){
		
		FiniteStateAutomaton fsa = new FiniteStateAutomaton();
	
		//Map nodes to states ids
		java.util.Map<String,Integer> ids = new HashMap<>();
		//get initial node
		State in = ltl_ba.start().head(); //CUIDADO:que pasa si tenemos varios estados iniciales.

		//create and set initial state
		automata.State is = fsa.createState(new Point());
		fsa.setInitialState(is);
		
		//Map labels to ids
		java.util.Map<String,Integer> labelIDs = new HashMap<>();
					
		//initial node ids
		ids.put(in.name(), is.getID());
			
		Map<Tuple2<State,Sign>, List<DirectedState>> trans = (Map<Tuple2<State, Sign>, List<DirectedState>>) ltl_ba.transitions();
		Vector<Tuple2<Tuple2<State,Sign>,List<DirectedState>>> vector =  trans.toVector();
		VectorIterator<Tuple2<Tuple2<State,Sign>,List<DirectedState>>> ltl_ba_it = vector.iterator();
		while(ltl_ba_it.hasNext()){
			Tuple2<Tuple2<State,Sign>,List<DirectedState>> o = ltl_ba_it.next();
			State from = o._1()._1();
			//checks if ID exists
			int ID = 0;
			automata.State fromState = null;
			if (ids.containsKey(from.name())){
				ID = ids.get(from.name());
				fromState = fsa.getStateWithID(ID);
			}
			else{
				//create new state
				fromState = fsa.createState(new Point());
				//update ids
				ids.put(from.name(), fromState.getID());
				ID = fromState.getID();
			}
			
			//get Label
			String l = o._1()._2().toString();
			
//			String label = getLabel(l);
			if(!labelIDs.containsKey(l)){
				labelIDs.put(l, labelIDs.keySet().size());
			}
			int base = 97;//a
			String label = ""+Character.toChars(base+labelIDs.get(l))[0];
			
			Iterator<DirectedState> listIt = o._2().iterator();
			while(listIt.hasNext()){
				State to = listIt.next().state();
				//check if toState exists
				automata.State toState = null;
				
				if (ids.containsKey(to.name())){
					ID = ids.get(to.name());
					toState = fsa.getStateWithID(ID);
				}
				else{
					//create new state
					toState = fsa.createState(new Point());
					//update ids
					ids.put(to.name(), toState.getID());
					ID = toState.getID();
				}
				
				//add transition
				FSATransition t = new FSATransition(fromState,toState,label);
				fsa.addTransition(t);
			}
		}
		
		//add final states
		Iterator<State> ac_it = ltl_ba.accepting().iterator();
		while(ac_it.hasNext()){
			State a = ac_it.next();
			int ID = ids.get(a.name());
			automata.State as = fsa.getStateWithID(ID);
			fsa.addFinalState(as);
		}

		FSAToRegularExpressionConverter.convertToSimpleAutomaton(fsa);
//		System.out.println(fsa.toString());
		return FSAToRegularExpressionConverter.convertToRegularExpression(fsa);
	}
	
	public static String toABClanguage(String re){
		String abcStr = "";
		abcStr = re.replace("λ", "\"\"");
		abcStr = abcStr.replace("+", "|");
		return abcStr;
	}
	
	
}
