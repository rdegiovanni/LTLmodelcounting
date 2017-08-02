package main;

import java.awt.Point;
import java.util.AbstractMap.*;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Queue;
import java.util.Set;

import automata.Automaton;
import automata.State;
import automata.Transition;
import automata.fsa.FSAToRegularExpressionConverter;
import automata.fsa.FSATransition;
import automata.fsa.FiniteStateAutomaton;
import automata.fsa.Minimizer;
import automata.fsa.NFAToDFA;
import gov.nasa.ltl.graph.Degeneralize;
import gov.nasa.ltl.graph.Edge;
import gov.nasa.ltl.graph.Graph;
import gov.nasa.ltl.graph.Node;
import gov.nasa.ltl.graph.SCCReduction;
import gov.nasa.ltl.graph.Simplify;
import gov.nasa.ltl.graph.SuperSetReduction;
import gov.nasa.ltl.graphio.Writer;
import gov.nasa.ltl.trans.Formula;
import gov.nasa.ltl.trans.LTL2Buchi;
import gov.nasa.ltl.trans.Parser;
import gov.nasa.ltl.trans.Translator;

public class LTLModelCounter2 {

	public static Graph<String> ltl2buchi(String formula){
		Graph<String> gba = Translator.translate(formula);	
		gba = SuperSetReduction.reduce(gba);
		Graph<String> ba = Degeneralize.degeneralize(gba);
		ba = SCCReduction.reduce(ba);
		return ba;
	}
	
	public static String buchi2RE(Graph<String> ltl_ba){
		FiniteStateAutomaton fsa = new FiniteStateAutomaton();
	
		//Map nodes to states ids
		Map<Integer,Integer> ids = new HashMap<>();
		//get initial node
		Node<String> in = ltl_ba.getInit();
		//create and set initial state
		State is = fsa.createState(new Point());
		fsa.setInitialState(is);
		
		//Map labels to ids
		Map<String,Integer> labelIDs = new HashMap<>();			
		//initial node ids
		ids.put(in.getId(), is.getID());
		//initial transition ids
		for (Node<String> n: ltl_ba.getNodes()){
			for(Edge<String> e : n.getOutgoingEdges()){
				String l = Writer.formatSMGuard(e.getGuard());
				if(!l.contains("TRUE")){			
					int label = 0;
					if(labelIDs.containsKey(l)){
						label = labelIDs.get(l);
					}
					else{
						label = labelIDs.keySet().size();
						labelIDs.put(l, label);
					}
				}
			}
		}
		System.out.println(labelIDs.toString());
		
		for (Node<String> from : ltl_ba.getNodes()){
			//checks if ID exists
			int ID = 0;
			State fromState = null;
			if (ids.containsKey(from.getId())){
				ID = ids.get(from.getId());
				fromState = fsa.getStateWithID(ID);
			}
			else{
				//create new state
				fromState = fsa.createState(new Point());
				//update ids
				ids.put(from.getId(), fromState.getID());
				ID = fromState.getID();
			}
			
			for (Edge<String> e: from.getOutgoingEdges()){
				//check if toState exists
				State toState = null;
				Node<String> to = e.getNext();
				if (ids.containsKey(to.getId())){
					ID = ids.get(to.getId());
					toState = fsa.getStateWithID(ID);
				}
				else{
					//create new state
					toState = fsa.createState(new Point());
					//update ids
					ids.put(to.getId(), toState.getID());
					ID = toState.getID();
				}
				
				//create the transition
				String l = Writer.formatSMGuard(e.getGuard());
				String label = "";
				if(l.contains("TRUE")){
					boolean first = true;
					for(int d : labelIDs.values()){
						if (first)
							first = false;
						else
							label += "|";
						label += Integer.toString(d);	
					}
					label = "(" + label + ")";
				}
				else{
						label = Integer.toString(labelIDs.get(l));
				}
				
				FSATransition t = new FSATransition(fromState,toState,label);
				fsa.addTransition(t);
			}
			
			//add final states
			if (from.getBooleanAttribute ("accepting"))
				fsa.addFinalState(fromState);
		
		
		}
		
		//convertToDFA
		//FiniteStateAutomaton dfa = (new NFAToDFA()).convertToDFA(fsa);
		
		//minimize automaton
		//Automaton m = (new Minimizer()).getMinimizeableAutomaton(dfa);
		
		//System.out.println(fsa.toString());
		
		//removeEmptyTransitions(fsa);
		
		FSAToRegularExpressionConverter.convertToSimpleAutomaton(fsa);
		System.out.println(fsa.toString());
		
	
		return FSAToRegularExpressionConverter.convertToRegularExpression(fsa);
	}
	
	private static void removeEmptyTransitions(FiniteStateAutomaton fsa){
		//remove empty transitions
		for(Transition t : fsa.getTransitions()){
			if (t.getDescription().equals(FSAToRegularExpressionConverter.EMPTY))
				fsa.removeTransition(t);
		}
		//check that all nodes have at least 1 transition (EMPTY if not)
//		for(State f : fsa.getStates()){
//			if(fsa.getTransitionsFromState(f).length==0){
//				Transition trans = new FSATransition(f, f, FSAToRegularExpressionConverter.EMPTY);
//				fsa.addTransition(trans);
//			}	
//		}

		System.out.println(fsa.toString());
		
	}
	
	
	public static String buchi2RE2(Graph<String> ltl_ba){
		Automaton fsa = new FiniteStateAutomaton();
	
		//Map nodes to states ids
		Map<Integer,Integer> ids = new HashMap<>();
		//get initial node
		Node<String> in = ltl_ba.getInit();
		//create and set initial state
		State is = fsa.createState(new Point());
		fsa.setInitialState(is);
		
		//Map labels to ids
		Map<String,Integer> labelIDs = new HashMap<>();			
		//initial node ids
		ids.put(in.getId(), is.getID());
		//initial transition ids
		for (Node<String> n: ltl_ba.getNodes()){
			for(Edge<String> e : n.getOutgoingEdges()){
				String l = Writer.formatSMGuard(e.getGuard());
				if(!l.contains("TRUE")){			
					int label = 0;
					if(labelIDs.containsKey(l)){
						label = labelIDs.get(l);
					}
					else{
						label = labelIDs.keySet().size();
						labelIDs.put(l, label);
					}
				}
			}
		}
		System.out.println(labelIDs.toString());
		
		for (Node<String> from : ltl_ba.getNodes()){
			//checks if ID exists
			int ID = 0;
			State fromState = null;
			if (ids.containsKey(from.getId())){
				ID = ids.get(from.getId());
				fromState = fsa.getStateWithID(ID);
			}
			else{
				//create new state
				fromState = fsa.createState(new Point());
				//update ids
				ids.put(from.getId(), fromState.getID());
				ID = fromState.getID();
			}
			
			for (Edge<String> e: from.getOutgoingEdges()){
				//check if toState exists
				State toState = null;
				Node<String> to = e.getNext();
				if (ids.containsKey(to.getId())){
					ID = ids.get(to.getId());
					toState = fsa.getStateWithID(ID);
				}
				else{
					//create new state
					toState = fsa.createState(new Point());
					//update ids
					ids.put(to.getId(), toState.getID());
					ID = toState.getID();
				}
				
				//create the transition
				FSATransition t = new FSATransition(fromState,toState,"");
				fsa.addTransition(t);
			}
			
			//add final states
			if (from.getBooleanAttribute ("accepting"))
				fsa.addFinalState(fromState);
		}
		
		for (Node<String> from : ltl_ba.getNodes()){
			int ID = ids.get(from.getId());
			State fromState =  fsa.getStateWithID(ID);
			String label = "";
			boolean first = true;
			for(Edge<String> e : from.getIncomingEdges()){
				if(first)
					first = false;
				else
					label += "|";
				//create the transition
				String l = Writer.formatSMGuard(e.getGuard());
				if(l.contains("TRUE")){
					label = "";
					boolean b = true;
					for(int d : labelIDs.values()){
						if (b)
							b = false;
						else
							label += "|";
						label += Integer.toString(d);	
					}
					label = "(" + label + ")";
					break;
				}
				else{
						label += Integer.toString(labelIDs.get(l));
				}
			}
			fromState.setLabel(label);
		}
		
		
		//convertToDFA
		//FiniteStateAutomaton dfa = (new NFAToDFA()).convertToDFA(fsa);
		
		//minimize automaton
		//Automaton m = (new Minimizer()).getMinimizeableAutomaton(dfa);
		
		//System.out.println(fsa.toString());
		
		//removeEmptyTransitions(fsa);
		System.out.println(fsa.toString());

		FSAToRegularExpressionConverter.convertToSimpleAutomaton(fsa);
		System.out.println(fsa.toString());
		
	

		
		return FSAToRegularExpressionConverter.convertToRegularExpression(fsa);
	}
	
}
