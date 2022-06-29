package application;

import java.io.File;
import javafx.collections.ObservableMap;
import javafx.scene.image.Image;
import javafx.scene.media.Media;

public class Musica {
	Media media;
	MusicaInfo musica_info;
	
	public Musica(Media media) {
		this.media = media;
		//organizaDados(this.media.getMetadata());
		//System.out.println(this.media.getMetadata());
	}
	
	public Musica(String path) {
		this.media = new Media(new File(path).toURI().toString());
		//organizaDados(this.media.getMetadata());
		//System.out.println(this.media.getMetadata());
	}
	
	public Musica(File file_media) {
		this.media = new Media(file_media.toURI().toString());
		//organizaDados(this.media.getMetadata());
		//System.out.println(this.media.getMetadata());
	}
	
	public void organizaDados() {
				
		// System.out.println(mp.getMedia().getMetadata());
		// ObservableMap<String, Object> metadados = mp.getMedia().getMetadata();
		
		ObservableMap<String, Object> metadados = this.media.getMetadata();
		
		Object array[] = new Object[4];
		for (String key : metadados.keySet()) {
			key = key.toLowerCase();
			if (key.contains("title") && !key.contains("album") && !key.contains("ep")) {
				array[0] = (String)metadados.get(key);
			}
			else if (key.contains("album") || key.contains("ep")) {
				array[1] = (String)metadados.get(key);
			}
			else if (key.contains("artist") || key.contains("band")) {
				array[2] = (String)metadados.get(key);
			}
			else if (key.contains("image")) {
				array[3] = (Image)metadados.get(key);
			}
		}
		
		this.musica_info = new MusicaInfo(array);
	}

	public Media getMedia() {
		return media;
	}

	public MusicaInfo getMusica_info() {
		return musica_info;
	}

	public int getId() {
		return getMusica_info().getId();
	}

	public void setId(int id) {
		getMusica_info().id = id;
	}

	public String getNome_musica() {
		return getMusica_info().getNome_musica();
	}

	public String getNome_album() {
		return getMusica_info().getNome_album();
	}

	public String getNome_artista() {
		return getMusica_info().getNome_artista();
	}

	public Image getCapa() {
		return getMusica_info().getCapa();
	}

	@Override
	public String toString() {
		return "Musica [musica_info=" + musica_info + "]";
	}
	
	
}
