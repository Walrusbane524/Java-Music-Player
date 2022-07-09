package application;

import java.util.Timer;
import java.util.TimerTask;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

public class Player {

	// TODO: ver se um construtor cai bem aqui
	View view;
	Inicializador init;
	Organizador org;
	Musica atual;
	MediaPlayer mediaPlayer;
	private double vol;
	private boolean rand;
	private boolean repeat;
	private boolean repeatSingle;
	Timer timer;
	TimerTask task;

	public Player(View v){
		this.view = v;
		init = new Inicializador();
		org = init.getSuperOrg();
		vol = 0.5;
		rand = false;
		repeat = false;
		repeatSingle = false;	
	}
	
	public void volUp(){
		vol += 0.1;
		if(vol > 1.0)
			vol = 1.0;
		this.setVolume(vol);
	}

	public void volDown(){
		vol -= 0.1;
		if(vol < 0.0)
			vol = 0.0;
		this.setVolume(vol);
	}
	
	public void playMedia() {
		beginTimer();
		mediaPlayer.play();
	}
	
	public void pauseMedia() {
		cancelTimer();
		mediaPlayer.pause();
	}
	
	public void random() {
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

	public void nextMusic() {
		if(!repeatSingle)
			org.next(repeat);
		setCurrentSong();
		playPause();
	}

	public void prevMusic() {
		org.prev();
		setCurrentSong();
		playPause();
	}

	public void repeat() {
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

	public void playPause(){
		if(mediaPlayer == null) {
			setCurrentSong();
		}
		else if(mediaPlayer.getStatus().toString() == "PLAYING") {
			pauseMedia();
		}
		else
			playMedia();
	}

	public void setCurrentSong(){
		if(org.size() == 0){
			releasePlayer();
			return;
		}
		
		atual = org.getMusica(org.ATUAL);
		atual.organizaDados();
		
		// sets do view
		view.setProgresso(0);
		view.setCapa();
		view.setTitle(atual.getNome_musica());
		view.setBand(atual.getNome_artista());

		releasePlayer();
		System.out.println("TOCANDO: " + atual.getNome_musica());
		Media media = atual.getMedia();
		System.out.println(atual.getMedia().getMetadata());

		mediaPlayer = new MediaPlayer(media);
		this.setVolume(this.getVolume());
		mediaPlayer.setCycleCount(1);
		playMedia();
		
		boolean contem = false;
		for(MusicaInfo mi: init.info_musica) {
			if(mi.getNome_arquivo().equals(atual.getNome_arquivo())) {
				contem = true;
				break;
			}
		}
		
		if(!contem) {
			for(MusicaInfo mi: init.info_musica)
				System.out.println(mi.getNome_arquivo() + " " + atual.getNome_arquivo());
			System.out.println(atual.getNome_arquivo());
			init.processFiles(atual.getMedia().getMetadata(), atual.getPath(), atual.getNome_arquivo());
		}
		
		// Autoplay
		mediaPlayer.setOnEndOfMedia(new Runnable() {

			@Override
			public void run() {
				nextMusic();
			}
		});
	}

	private void releasePlayer() {
		if (mediaPlayer != null) {
			mediaPlayer.stop();
			mediaPlayer = null;
		}
	}
	
	private void beginTimer() {
		if (mediaPlayer != null) {		
			timer = new Timer(); 
			
			task = new TimerTask() {
				
				public void run() {
					double current = getMediaPlayer().getCurrentTime().toSeconds();
					double end = getMediaPlayer().getTotalDuration().toSeconds();
					view.setProgresso(current, end);
					
					if (current/end == 1) {
						cancelTimer();
					}
				}
				
			};
			
			timer.scheduleAtFixedRate(task, 1000, 1000);
		}
	}
	
	private void cancelTimer() {
		timer.cancel();
	}
		

	public void setVolume(double vol) {
		if (mediaPlayer != null)
			mediaPlayer.setVolume(vol);
		this.vol = vol;
	}

	private double getVolume() {
		return this.vol;
	}

	public Musica getCurrent(){

		if(org.size() == 0) {
			return null;
		}
		else
			return atual;
	}
	
	public MediaPlayer getMediaPlayer() {
		return this.mediaPlayer;
	}
	
	public void setOrganizer(Organizador o) {
		this.org = o;
	}

	public void playSelected(String playlist, int index) {
		this.org = init.getPlaylistOrganizer(playlist);
		this.org.setCurrent(index);
		playPause();
	}
}
