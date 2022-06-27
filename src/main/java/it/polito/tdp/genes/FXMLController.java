package it.polito.tdp.genes;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import it.polito.tdp.genes.model.Adiacenti;
import it.polito.tdp.genes.model.Model;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;

public class FXMLController {
	
	private Model model ;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button btnStatistiche;

    @FXML
    private Button btnRicerca;

    @FXML
    private ComboBox<String> boxLocalizzazione;

    @FXML
    private TextArea txtResult;

    @FXML
    void doRicerca(ActionEvent event) {
    	this.txtResult.clear();
    	
    	String l = this.boxLocalizzazione.getValue();
    	
    	if(l== null) {
    		this.txtResult.appendText("Seleziona una localizzazione valida!\n");
    		return;
    	}
    	
    	List<String> result = new ArrayList<String>(this.model.calcolaPercorso(l));
    	
    	for(String loc : result) {
    		this.txtResult.appendText(loc + "\n");
    	}

    }

    @FXML
    void doStatistiche(ActionEvent event) {
    	this.txtResult.clear();
    	
    	String l = this.boxLocalizzazione.getValue();
    	
    	if(l== null) {
    		this.txtResult.appendText("Seleziona una localizzazione valida!\n");
    		return;
    	}
    	
    	this.txtResult.appendText("Adiacenti a: " + l + "\n");
    	for(Adiacenti a : this.model.getAdiacenti(l)) {
    		txtResult.appendText(a.getLocalization() + "     " + a.getPeso() + "\n");
    	}

    }

    @FXML
    void initialize() {
        assert btnStatistiche != null : "fx:id=\"btnStatistiche\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnRicerca != null : "fx:id=\"btnRicerca\" was not injected: check your FXML file 'Scene.fxml'.";
        assert boxLocalizzazione != null : "fx:id=\"boxLocalizzazione\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtResult != null : "fx:id=\"txtResult\" was not injected: check your FXML file 'Scene.fxml'.";

    }

	public void setModel(Model model) {
		this.model = model;
		
		this.txtResult.appendText(this.model.creaGrafo());
		this.boxLocalizzazione.getItems().clear();
		this.boxLocalizzazione.getItems().addAll(this.model.getLocalization());
	}
}
