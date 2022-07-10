package application;

import com.jfoenix.controls.JFXSlider;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;

/**
 * Responsável por receber todos os requests do usuário e enviar às outras classes
 */
public class Controller{
	
	Player player;
	View view;
	boolean meta_adq = false;
	
	// fx:controller="application.Controller"
	
	// HomeScene.fxml
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
	ImageView sound_Img;
	
	// Musics_Pane.fxml
	@FXML
	TableView<MusicaInfo> table;
	@FXML
	TableColumn<MusicaInfo, String> nome;
	@FXML
	TableColumn<MusicaInfo, String> album;
	@FXML
	TableColumn<MusicaInfo, String> artista;
	@FXML
	TableColumn<MusicaInfo, Button> play;
	
	/**
	 * Chama a função do view de mudar para a tela inicial.
	 * 
	 * @param e ActionEvent
	 */
	@FXML
	void change_to_Home(ActionEvent e) {
		getView().change_to_Home();
	}
	
	/**
	 * Chama a função do view de mudar para a tela de configurações.
	 * 
	 * @param e ActionEvent
	 */
	@FXML
	void change_to_Settings(ActionEvent e) {
		getView().change_to_Settings();
	}
	
	/**
	 * Chama a função do view de mudar para a tela de biblioteca de playlists.
	 * 
	 * @param e ActionEvent
	 */
	@FXML
	void change_to_Library(ActionEvent e) {
		getView().change_to_Library();
	}
	
	/**
	 * Chama a função do view de mudar para a tela de biblioteca de todas as músicas.
	 * 
	 * @param e ActionEvent
	 */
	@FXML
	void change_to_YourBeats(ActionEvent e) {
		attDados();
		getView().change_to_YourBeats();
		atualiza();
	}
	
	/**
	 * Chama a função do view de mudar para a tela de biblioteca de criação de playlist.
	 * 
	 * @param e ActionEvent
	 */
	@FXML
	void change_to_CreatePlaylist(ActionEvent e) {
		getView().change_to_CreatePlaylist();
	}
	
	/**
	 * Chama a função do view de mudar a imagem de mudo/som.
	 * 
	 * @param e ActionEvent
	 */
	@FXML
	void changeImg(ActionEvent e) {
		if (getPlayer().getVolume() > 0) {
			getPlayer().setVolume(0);
			getView().changeSomIMG(true);
		}else {
			// desmuta
			newVol();
			if (getPlayer().getVolume() != 0) {
				getView().changeSomIMG(false);
			}
		}
	}
	
	/**
	 * Chama a função do player de atualizar o volume.
	 * 
	 * @param e ActionEvent
	 */
	@FXML
	void newVol() {
		getPlayer().setVolume(sound_Slider.getValue()*0.01);
	}
	
	/**
	 * Chama a função do player de alternar entre modo ordem aleatória ou ordem normal.
	 * 
	 * @param e ActionEvent
	 */
	@FXML
	public void random(ActionEvent e) {
		getPlayer().random();
	}
	
	/**
	 * Chama a função do player de tocar a próxima música.
	 * 
	 * @param e ActionEvent
	 */
	@FXML
	public void nextMusic(ActionEvent e) {
		attDados();
		getPlayer().nextMusic();
	}
	
	/**
	 * Chama a função do player de tocar a música anterior.
	 * 
	 * @param e ActionEvent
	 */
	@FXML
	public void prevMusic(ActionEvent e) {
		attDados();
		getPlayer().prevMusic();
	}
	
	/**
	 * Chama a função do player de alternar modos de repeat.
	 * 
	 * @param e ActionEvent
	 */
	@FXML
	public void repeat(ActionEvent e) {
		getPlayer().repeat();
	}
	
	/**
	 * Chama a função do player de play/pause.
	 * 
	 * @param e ActionEvent
	 */
	@FXML
	public void playPause(ActionEvent e){
		attDados();
		getPlayer().playPause();
	}
	
	/**
	 * Chama a função do player de tocar uma música específica.
	 * 
	 * @param e ActionEvent
	 */
	public void playInd(int id) {
		System.out.println(id);
		getPlayer().playSelected(id);
	}
	
	
	public Player getPlayer() {
		return this.player;
	}
	
	public View getView() {
		return this.view;
	}
	
	public ObservableList<MusicaInfo> getLista() {
		return getPlayer().org.listaInfo;
	}
	
	public void setPlayer(Player p) {
		this.player = p;
	}

	public void setView(View view) {
		this.view = view;
	}
	
	/**
	 * Atualiza o estado do player.
	 * 
	 * @param e ActionEvent
	 */
	void attDados() {
        if (!meta_adq) {
            getPlayer().setOrganizer(player.init.lib.get(0));
            getPlayer().playPause();
            getPlayer().setVolume(0);
            getPlayer().pauseMedia();
            meta_adq = true;
        }
    }
	
	/**
	 * Atualiza os dados da lista de metadados.
	 * 
	 * @param e ActionEvent
	 */
	public void atualiza () {
		 
		ObservableList<MusicaInfo> list = getLista();
		
		nome.setCellValueFactory(new PropertyValueFactory<>("nome_musica"));
		album.setCellValueFactory(new PropertyValueFactory<>("nome_album"));
		artista.setCellValueFactory(new PropertyValueFactory<>("nome_artista"));
		play.setCellValueFactory(new PropertyValueFactory<>("play"));
		
		table.setItems(list);
	}
		
}
