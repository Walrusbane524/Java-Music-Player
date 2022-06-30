package application;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.image.*;


public class Controller {

	@FXML
	ImageView capa;
	Player player = new Player();
	
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
		capa.setImage(player.getCurrent().getCapa());
	}
}
