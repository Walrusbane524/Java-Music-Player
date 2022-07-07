package application;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;


public class View implements Initializable{
	
	Controller controller;
	Image padrao;
	/*alteracao foi da 19 a 34*/
	@FXML
	private BorderPane main_Pane;
	
	@FXML
	private void change_to_Home(ActionEvent e) {
		FxmlLoader object = new FxmlLoader();
		Pane view = object.getPage("home");
		main_Pane.setCenter(view);
	}
	
	@FXML
	private void change_to_Settings(ActionEvent e) {
		FxmlLoader object = new FxmlLoader();
		Pane view = object.getPage("Settings_Pane");
		main_Pane.setCenter(view);
	}
	
	public View(Controller c) {
		this.controller = c;
		this.padrao = null;
	}
	
	public Controller getController() {
		return this.controller;
	}
	
	public void setCapa() {
		Image img = this.getController().getPlayer().getCurrent().getCapa();
		if (img != null) {			
			this.getController().capa.setImage(img);
			return;
		}
		this.getController().capa.setImage(padrao);
	}
	
	public void setTitle(String nome_musica) {
		this.getController().titulo.setText("  " + nome_musica);
	}
	
	public void setBand(String nome_banda) {
		this.getController().band.setText("   " + nome_banda);
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
	}
	
}
