package application;


import java.io.File;

import javafx.scene.media.Media;


public class Musica {
	Media media;
	String nome_musica;
	String nome_album;
	String nome_artista;
	
	Musica(String nome_musica, String path){
		this.media = new Media(new File(path).toURI().toString());
		this.nome_musica = nome_musica;
	}
	
	@Override
	public String toString(){
		return media.getSource() + ";;;" + nome_musica; 
	}
	
	public String getNome_musica() {
		return this.nome_musica;
	}
	
	public Media getMedia() {
		return this.media;
	}
}