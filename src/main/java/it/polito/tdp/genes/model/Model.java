package it.polito.tdp.genes.model;

import java.util.LinkedList;
import java.util.List;

import org.jgrapht.Graph;
import org.jgrapht.Graphs;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleWeightedGraph;

import it.polito.tdp.genes.db.GenesDao;

public class Model {
	
	private GenesDao dao;
	private Graph<String, DefaultWeightedEdge> graph;
	private List<String> best;
	
	public Model() {
		this.dao = new GenesDao();
	}
	
	public String creaGrafo() {
		this.graph = new SimpleWeightedGraph<>(DefaultWeightedEdge.class);
		
		Graphs.addAllVertices(this.graph, this.dao.getLocalization());
		
		for(Arco arco : this.dao.getArchi()) {
			Graphs.addEdgeWithVertices(this.graph, arco.getL1(), arco.getL2(), arco.getPeso());	
		}
		
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
	
	public List<Adiacenti> getAdiacenti(String l){
		List<Adiacenti> result = new LinkedList<Adiacenti>();
		for(String localization : Graphs.neighborListOf(this.graph, l)) {
			result.add(new Adiacenti(localization, this.graph.getEdgeWeight(this.graph.getEdge(l, localization))));
		}
		
		return result;
	}
	
	public List<String> calcolaPercorso(String sorgente){
		best = new LinkedList<>();
		List<String> parziale = new LinkedList<>();
		parziale.add(sorgente);
		cerca(0, parziale);
		return best;
	}

	private void cerca(Integer l, List<String> parziale) {
//		Condizione di terminazione
		if(condizioneDiTerminazione(parziale)) {
//			E' la soluzione migliore?
			double lunghezza = calcolaLunghezza(parziale);
			if(best == null || calcolaLunghezza(best) < lunghezza)
				best = new LinkedList<>(parziale);
			return;
		}
		
//		Scorro i vicini dell'ultimo inserito e provo le varie strade
		for(String v : Graphs.neighborListOf(this.graph, parziale.get(parziale.size()-1))) {
			if(!parziale.contains(v)) {
				parziale.add(v);
				cerca(l, parziale);
				parziale.remove(parziale.size()-1);
			}
		}
		
	}

	private boolean aggiuntaValida(String v, List<String> parziale) {
		if(parziale.size() == 0)
			return true;
		else {
			String ultimoVertice = parziale.get(parziale.size()-1);
			if(this.graph.containsEdge(ultimoVertice, v) && !parziale.contains(v))
				return true;
		}
		return false;
	}

	private double calcolaLunghezza(List<String> parziale) {
		double l = 0;
		for(int i = 0; i < parziale.size()-1; i++)
			l += this.graph.getEdgeWeight(this.graph.getEdge(parziale.get(i), parziale.get(i+1)));
		return l;
	}

	private boolean condizioneDiTerminazione(List<String> parziale) {
		if(parziale.size() > 0) {
//			int ultimoVertice = parziale.get(parziale.size()-1);
			int conta = 0;	//conta i vertici non inseriti
			for(String v : this.graph.vertexSet())
				if(!aggiuntaValida(v, parziale))
					conta++;
			
			if(conta == this.graph.vertexSet().size())
				return true;
		}
		return false;
	}

}