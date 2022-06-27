package it.polito.tdp.genes.model;

public class Adiacenti {
	
	private String localization;
	private double peso;
	
	public Adiacenti(String localization, double peso) {
		super();
		this.localization = localization;
		this.peso = peso;
	}
	
	public String getLocalization() {
		return localization;
	}
	public void setLocalization(String localization) {
		this.localization = localization;
	}
	public double getPeso() {
		return peso;
	}
	public void setPeso(double peso) {
		this.peso = peso;
	}
	
	

}
