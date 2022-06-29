package application;


import javafx.scene.image.Image;


public class MusicaInfo {
	int id;
	// Media media;
	String nome_musica;
	String nome_album;
	String nome_artista;
	Image capa;
	
	public MusicaInfo(int id, String nome_musica, String nome_album, String nome_artista, Image capa) {
		this.id = id;
		this.nome_musica = nome_musica;
		this.nome_album = nome_album;
		this.nome_artista = nome_artista;
		this.capa = capa;
		// BUG: NÃO CONSIGO CHAMAR A FUNÇÃO AQUI, PORTANTO CHAMO EM Controller.java
		// atualizaDados();
	}

	public MusicaInfo(String nome_musica, String nome_album, String nome_artista, Image capa) {
		this.id = 0;
		this.nome_musica = nome_musica;
		this.nome_album = nome_album;
		this.nome_artista = nome_artista;
		this.capa = capa;
		// BUG: NÃO CONSIGO CHAMAR A FUNÇÃO AQUI, PORTANTO CHAMO EM Controller.java
		// atualizaDados();
	}
	
	public MusicaInfo(Object array[]) {
		this.id = 0;
		this.nome_musica = (String)array[0];
		this.nome_album = (String)array[1];
		this.nome_artista = (String)array[2];
		this.capa = (Image)array[3];
		// BUG: NÃO CONSIGO CHAMAR A FUNÇÃO AQUI, PORTANTO CHAMO EM Controller.java
		// atualizaDados();
	}



	/*
	public void atualizaDados() {
		// TODO: SEPARAR CADA TRY/CATCH
		// TODO: detectar substring pro album, title, artist etc...
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
	*/
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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

	public Image getCapa() {
		return capa;
	}

	@Override
	public String toString() {
		return "MusicaInfo [id=" + id + ", nome_musica=" + nome_musica + ", nome_album=" + nome_album
				+ ", nome_artista=" + nome_artista + ", capa=" + capa + "]";
	}
	
}