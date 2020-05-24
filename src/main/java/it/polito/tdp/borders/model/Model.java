package it.polito.tdp.borders.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.jgrapht.alg.connectivity.ConnectivityInspector;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.SimpleGraph;
import org.jgrapht.traverse.BreadthFirstIterator;
import org.jgrapht.traverse.GraphIterator;

import it.polito.tdp.borders.db.BordersDAO;

public class Model {
	private BordersDAO dao;
	private Map<Integer,Country>paesi;
	private SimpleGraph<Country, DefaultEdge> graph;
	private List<Border> confini;
	
	public Model() {
	dao= new BordersDAO();
	paesi=dao.loadAllCountries();
	graph = new SimpleGraph<>(DefaultEdge.class);
	}
	
	public void calcolaConfini(int anno) {
	// Ottengo coppie di paesi confinanti 
	confini = new ArrayList<Border>(dao.getCountryPairs(anno,paesi));
	for(Border b: confini) {
		graph.addVertex(b.getStato1());
		graph.addVertex(b.getStato2());
		graph.addEdge(b.getStato1(), b.getStato2(), new DefaultEdge());
		
	}
	
	
	}
	
	public List<Country> getCountries(){
		List<Country> output = new ArrayList<Country>(graph.vertexSet());
		Collections.sort(output);	
		return output;
	}
	public List<Country> getCountriesDegree(){
		List<Country> output = new ArrayList<Country>(graph.vertexSet());
		for (Country c: output) {
			c.setDegree(graph.degreeOf(c));
			
		}
		Collections.sort(output);	
		return output;	
		}
	public int getNumberOfConnectedComponents() {
		ConnectivityInspector<Country, DefaultEdge> ci = new ConnectivityInspector<Country, DefaultEdge>(graph);
		return ci.connectedSets().size();
		
		
	}	
	public List<Country> getCountriesReachable(Country paese){
		paese=paesi.get(2);
		List<Country> visited = new LinkedList<Country>();
		GraphIterator<Country, DefaultEdge> bfv = new BreadthFirstIterator<Country, DefaultEdge>(graph,paese);
	while (bfv.hasNext()) {
			visited.add(bfv.next());
	}
	return visited;	
	}
	
	
	
	
	
	}

