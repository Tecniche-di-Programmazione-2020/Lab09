package it.polito.tdp.borders.model;

import java.util.Map;

import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.SimpleGraph;

import it.polito.tdp.borders.db.BordersDAO;

public class Model {
	private BordersDAO dao;
	private Map<Integer,Country>paesi;
	private SimpleGraph<Country, DefaultEdge> graph;
	
	public Model() {
	dao= new BordersDAO();
	paesi=dao.loadAllCountries();
	graph = new SimpleGraph<>(DefaultEdge.class);
	}
	
	
}
