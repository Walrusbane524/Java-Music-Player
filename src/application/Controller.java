package application;


import java.io.File;
import java.util.StringTokenizer;
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.JFileChooser;
import com.jfoenix.controls.JFXSlider;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;


public class Controller {
	
	Player player;
	View view;
	
	// fx:controller="application.Controller"
	
	@FXML
	ImageView capa;
	@FXML
	Label band;
	@FXML
	Label titulo;
	@FXML
	JFXSlider sound_Slider;
	@FXML
	BorderPane main_Pane;
	@FXML
	ProgressBar progress_Bar;
	
	@FXML
	void change_to_Home(ActionEvent e) {
		getView().change_to_Home();
	}
	
	@FXML
	void change_to_Settings(ActionEvent e) {
		getView().change_to_Settings();
	}
	
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
		
	public void play_selected(ActionEvent e) {
		String id = ((Button)e.getSource()).getId();
		StringTokenizer st = new StringTokenizer(id, "-");
		String playlist = st.nextToken();
		playlist.concat(".txt");
		int index = Integer.parseInt(st.nextToken());
		
		player.playSelected(playlist, index);
	}
		
}
