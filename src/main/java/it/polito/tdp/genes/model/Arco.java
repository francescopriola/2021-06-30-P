package it.polito.tdp.genes.model;

public class Arco {
	
	private String l1;
	private String l2;
	private double peso;
	
	public Arco(String l1, String l2, double peso) {
		super();
		this.l1 = l1;
		this.l2 = l2;
		this.peso = peso;
	}

	public String getL1() {
		return l1;
	}

	public void setL1(String l1) {
		this.l1 = l1;
	}

	public String getL2() {
		return l2;
	}

	public void setL2(String l2) {
		this.l2 = l2;
	}

	public double getPeso() {
		return peso;
	}

	public void setPeso(double peso) {
		this.peso = peso;
	}
	
	
	
	

}
