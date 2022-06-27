package application;


import java.io.File;
import javafx.scene.image.Image;
import javafx.scene.media.Media;


public class Musica {
	Media media;
	String nome_musica;
	String nome_album;
	String nome_artista;
	int ano;
	Image capa;
	
	Musica(String nome_musica, String path){
		this.media = new Media(new File(path).toURI().toString());
		this.nome_musica = nome_musica;
		// BUG: NÃO CONSIGO CHAMAR A FUNÇÃO AQUI, PORTANTO CHAMO EM Controller.java
		// atualizaDados();
	}
	
	public void atualizaDados() {
		// TODO: SEPARAR CADA TRY/CATCH
		try {			
		this.nome_musica = (String)media.getMetadata().get("title");
		this.nome_album = (String)media.getMetadata().get("album");
		this.nome_artista = (String)media.getMetadata().get("artist");
		this.capa = (javafx.scene.image.Image)media.getMetadata().get("image");
		this.ano = (int)media.getMetadata().get("year");
		} catch (Exception e) {
			this.nome_album = null;
			this.nome_artista = null;
			this.capa = null;
			this.ano = 0;
		}
	}
	
	public Media getMedia() {
		return media;
	}
	
	public String getNome_musica() {
		return nome_musica;
	}

	public String getNome_album() {
		return nome_album;
	}

	public String getNome_artista() {
		return nome_artista;
	}

	public int getAno() {
		return ano;
	}

	public Image getCapa() {
		return capa;
	}
	
	@Override
	public String toString(){
		return media.getSource(); 
	}
	
}
