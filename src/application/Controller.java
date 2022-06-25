package application;

import java.io.File;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

public class Controller {
	
	@FXML
	MediaPlayer mediaPlayer;
	
	public void play(ActionEvent e) {
		String fileName = "Gorillaz - Cracker Island.mp3";
		playHitSound(fileName);
	}
	private void playHitSound(String fileName) {
		String path = "/home/walrusbane/eclipse-workspace/HelloFX/Music/Gorillaz - Cracker Island.mp3";
		System.out.println(path);
		Media media = new Media(new File(path).toURI().toString());
		mediaPlayer = new MediaPlayer(media);
		mediaPlayer.setCycleCount(1);
		mediaPlayer.play();
	}
	public void volUp(ActionEvent e){

		mediaPlayer.setVolume(mediaPlayer.getVolume() + 0.1);
	}
	public void volDown(ActionEvent e){

		mediaPlayer.setVolume(mediaPlayer.getVolume() - 0.1);
	}
}
