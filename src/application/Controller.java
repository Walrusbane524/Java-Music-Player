package application;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;


public class Controller {

	Organizador org = new Organizador("Music");
	
	@FXML
	MediaPlayer mediaPlayer = null;
	private double vol = 0.5;
	private int indexOfPrev = org.size()-1;
	private int indexOfCurrent = 0;
	private boolean rand = false;
	
	public void play(ActionEvent e) {
		// ESCOLHER O INDICE DA MUSICA
		playHitSound();
	}
	
	public void volUp(ActionEvent e){
		this.setVolume(this.getVolume() + 0.1);
	}
	
	public void volDown(ActionEvent e){
		this.setVolume(this.getVolume() - 0.1);
	}
	
	public void random(ActionEvent e) {
		// ativar underline ou desativar underline
		// se underline tiver ativado, ao clicar, será desativado e não será random
		// vice e versa...
		if (this.rand) {
			this.rand = false;
			org.filaNormal();
			// TODO: IMPLEMENTAR PARA QUE TOQUE DO COMEÇO DA LISTA ASSIM QUE APERTAR NEXT
			this.indexOfCurrent = -1; // usar isso somente aqui (-1)
		}
		else {
			this.rand = true;
			org.filaRandom();
		}
	}
	
	public void nextMusic(ActionEvent e) {
		setIndexOfCurrent(this.indexOfCurrent+1);
		setIndexOfPrev(this.indexOfPrev+1);
		System.out.println(this.indexOfCurrent);
		playHitSound();
	}
	
	public void prevMusic(ActionEvent e) {
		setIndexOfCurrent(this.indexOfCurrent-1);
		setIndexOfPrev(this.indexOfPrev-1);
		System.out.println(this.indexOfCurrent);
		playHitSound();
	}
	
	private void playHitSound() {
				
		Musica tocar = org.getMusica(this.indexOfCurrent);
	
		releasePlayer();
		System.out.println("TOCANDO: " + tocar.getNome_musica());
		Media media = tocar.getMedia();
		mediaPlayer = new MediaPlayer(media);
		this.setVolume(this.getVolume());
		mediaPlayer.setCycleCount(1);
		mediaPlayer.play();
	}
	
	private void releasePlayer() {
		if (mediaPlayer != null) {
			mediaPlayer.stop();
			mediaPlayer = null;
		}
	}
		
	private void setVolume(double vol) {
		mediaPlayer.setVolume(vol);
		this.vol = vol;
	}
	
	private double getVolume() {
		return this.vol;
	}
	
	private void setIndexOfPrev(int ind) {
		if (ind <= -1) {
			this.indexOfPrev = org.size()-1;
			return;
		}
		if (ind >= org.size()) {
			this.indexOfPrev = 0;
			return;
		}
		this.indexOfPrev = ind;
	}
	
	private void setIndexOfCurrent(int ind) {
		if (ind <= -1) {
			this.indexOfCurrent = org.size()-1;
			return;
		}
		if (ind >= org.size()) {
			this.indexOfCurrent = 0;
			return;
		}
		this.indexOfCurrent = ind;
	}
	
}
