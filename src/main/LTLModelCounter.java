package main;

import java.awt.Point;
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

	public static Nfa ltl2dfa(String formula){
		
		ConversionVal[] conv = {Conversion.PROPS(), Conversion.FORMULA(), Conversion.APA(),Conversion.NBA(), Conversion.MIN(), Conversion.NFA(), Conversion.DFA()};
		Object res = RltlConv.convert(formula, conv);
		Nfa fsa = (Nfa) res;
		
		return fsa.toNamedNfa();
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
	
	public static String toABClanguage(String re){
		String abcStr = "";
		abcStr = re.replace("λ", "\"\"");
		abcStr = abcStr.replace("+", "|");
		return abcStr;
	}
	
	
}
