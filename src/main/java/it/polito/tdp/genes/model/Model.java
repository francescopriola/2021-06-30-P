package it.polito.tdp.genes.model;

import java.util.List;

import org.jgrapht.Graph;
import org.jgrapht.Graphs;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleWeightedGraph;

import it.polito.tdp.genes.db.GenesDao;

public class Model {
	
	private GenesDao dao;
	private Graph<String, DefaultWeightedEdge> graph;
	
	public Model() {
		this.dao = new GenesDao();
	}
	
	public String creaGrafo() {
		this.graph = new SimpleWeightedGraph<>(DefaultWeightedEdge.class);
		
		Graphs.addAllVertices(this.graph, this.dao.getLocalization());
		
		for(Arco arco : this.dao.getArchi())
			Graphs.addEdgeWithVertices(this.graph, arco.getL1(), arco.getL2(), arco.getPeso());
		
		System.out.println(this.getNVertici() + "\n");
		System.out.println(this.getNArchi() + "\n");
		
		return "Grafo creato: " + this.getNVertici() + ", " + this.getNArchi() + ".\n";
		
	}

	private String getNArchi() {
		return this.graph.edgeSet().size() + " archi";
	}

	public String getNVertici() {
		return this.graph.vertexSet().size() + " vertici";
	}
	
	public List<String> getLocalization(){
		return this.dao.getLocalization();
	}

}