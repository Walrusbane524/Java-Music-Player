package application;


import java.io.File;

import javax.swing.JFileChooser;

import com.jfoenix.controls.JFXSlider;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;


public class Controller {
	
	// fx:controller="application.Controller"
	
	@FXML
	ImageView capa;
	@FXML
	Label band;
	@FXML
	Label titulo;
	@FXML
	JFXSlider sound_Slider;
	
	Player player;
	View view;
	
	@FXML
	void newVol() {
		player.setVolume(sound_Slider.getValue()*0.01);
	}
	
	@FXML
	public void volUp(ActionEvent e){
		player.volUp();
	}
	
	@FXML
	public void volDown(ActionEvent e){
		player.volDown();
	}

	@FXML
	public void random(ActionEvent e) {
		player.random();
	}

	@FXML
	public void nextMusic(ActionEvent e) {
		player.nextMusic();
	}

	@FXML
	public void prevMusic(ActionEvent e) {
		player.prevMusic();
	}

	@FXML
	public void repeat(ActionEvent e) {
		player.repeat();
	}
	
	@FXML
	public void playPause(ActionEvent e){
		player.playPause();
	}
	
	public Player getPlayer() {
		return this.player;
	}
	
	public View getView() {
		return this.view;
	}
		
	public void setPlayer(Player p) {
		this.player = p;
	}

	public void setView(View view) {
		this.view = view;
	}
	
	public void chooseFile(ActionEvent e) {
		JFileChooser fileChooser = new JFileChooser();
		fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		int response = fileChooser.showOpenDialog(null);
		
		if(response == JFileChooser.APPROVE_OPTION) {
			Inicializador.processFiles(fileChooser.getSelectedFile());
		}
	}
	
}
