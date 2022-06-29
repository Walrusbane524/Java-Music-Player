package application;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.image.*;


public class Controller {

	Organizador org = new Organizador("Music");
	Musica atual;
	
	@FXML
	ImageView capa;
	MediaPlayer mediaPlayer = null;
	private double vol = 0.1;
	private boolean rand = false;
	private boolean repeat = false;
	private boolean repeatSingle = false;
	
	public void play(ActionEvent e) {
		// ESCOLHER O INDICE DA MUSICA
		playCurrent();
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
			System.out.println("ORDEM DE NOME");
			this.rand = false;
			org.filaNormal();
		}
		else {
			System.out.println("DESORDENADO");
			this.rand = true;
			org.filaRandom();
		}
		org.listaFila();
	}
	
	public void nextMusic(ActionEvent e) {
		if(!repeatSingle)
			org.next(repeat);
		playCurrent();
	}
	
	public void prevMusic(ActionEvent e) {
		org.prev();
		playCurrent();
	}
	
	public void repeat(ActionEvent e) {
		if(repeat == false)
			repeat = true;
		else {
			if(repeatSingle == false)
				repeatSingle = true;
			else {
				repeat = false;
				repeatSingle = false;
			}
		}
		System.out.println("Repeat: " + repeat + "\nRepeat Single: " + repeatSingle);
	}
	
	private void playCurrent(){
		
		if(org.size() == 0){
			System.out.println("FILA VAZIA");
			return;
		}
		
		atual = org.getMusica(org.ATUAL);
		System.out.println(atual.getMedia().getMetadata());
		atual.atualizaDados();
	
		releasePlayer();
		System.out.println("TOCANDO: " + atual.getNome_musica());
		Media media = atual.getMedia();
		System.out.println(atual.getMedia().getMetadata());
		
		// PARTE DO VIEW AQUI
		if (atual.getCapa() != null)
			capa.setImage(atual.getCapa());
		else
			// TODO: COLOCAR IMAGEM PADRÃO PARA MUSICAS SEM CAPA
			capa.setImage(null);
		// FIM
		
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
	
}
