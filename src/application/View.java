package application;

import javafx.scene.image.Image;

public class View {
	
	Controller controller;
	Image padrao;
	
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
		this.getController().titulo.setText(nome_musica);
	}
	
	public void setBand(String nome_banda) {
		this.getController().band.setText(nome_banda);
	}
	
	public void setProgresso(double t) {
		getController().progress_Bar.setProgress(t);
	}
	
	public void setProgresso(double current, double end) {
		getController().progress_Bar.setProgress(current/end);
	}
	
	public void change_to_Home() {
		getController().main_Pane.setCenter(FxmlLoader.getPage("home"));
	}
	
	public void change_to_Library() {
		
	}
	
	public void change_to_YourBeats() {
		
	}
	
	public void change_to_Settings() {
		getController().main_Pane.setCenter(FxmlLoader.getPage("Settings_Pane"));
	}
	
}
