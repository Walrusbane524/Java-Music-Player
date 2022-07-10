package application;

import java.io.File;

import javafx.fxml.FXMLLoader;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;

public class View {
	
	Controller controller;
	Image nao_mutado, mutado, padrao, miniPlay, miniRemove;
	
	
	public View(Controller c) {
		this.controller = c;
		this.padrao = null;
		this.nao_mutado = new Image(View.class.getResourceAsStream("front-end/icons/sound.png"));
		this.mutado = new Image(View.class.getResourceAsStream("front-end/icons/sound-off.png"));
		this.miniPlay = new Image(View.class.getResourceAsStream("front-end/icons/delete.png"));
		this.miniRemove = new Image(View.class.getResourceAsStream("front-end/icons/miniplay-button.png"));
	
	}
	
	public View() {
		this.padrao = null;
		this.nao_mutado = new Image(View.class.getResourceAsStream("front-end/icons/sound.png"));
		this.mutado = new Image(View.class.getResourceAsStream("front-end/icons/sound-off.png"));
		this.miniPlay = new Image(View.class.getResourceAsStream("front-end/icons/delete.png"));
		this.miniRemove = new Image(View.class.getResourceAsStream("front-end/icons/miniplay-button.png"));
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
		getController().main_Pane.setCenter(FxmlLoader.getPage("home", controller));
	}
	
	public void change_to_Settings() {
		getController().main_Pane.setCenter(FxmlLoader.getPage("Settings_Pane", controller));
	}
	
	public void change_to_Library() {
		getController().main_Pane.setCenter(FxmlLoader.getPage("Playlists_Pane", controller));
	}
	
	public void change_to_YourBeats() {
		getController().main_Pane.setCenter(FxmlLoader.getPage("Musics_Pane", controller));
	}
	
	public void change_to_CreatePlaylist() {
		getController().main_Pane.setCenter(FxmlLoader.getPage("Criar_Playlist_Pane", controller));
	}
	
	public void changeSomIMG(boolean mute) {
		if (mute) {
			getController().sound_Img.setImage(mutado);
		}else {
			getController().sound_Img.setImage(nao_mutado);
		}
	}
	
}
