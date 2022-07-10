package application;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.StringTokenizer;
import com.jfoenix.controls.JFXSlider;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

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
	
	@FXML
	void change_to_Home(ActionEvent e) {
		getView().change_to_Home();
	}
	
	@FXML
	void change_to_Settings(ActionEvent e) {
		getView().change_to_Settings();
	}
	
	@FXML
	void change_to_Library(ActionEvent e) {
		getView().change_to_Library();
	}
	
	@FXML
	void change_to_YourBeats(ActionEvent e) {
		if (!meta_adq) {	
			// conseguir metadados de todas as musicas, pegando o organizador com todas as musicas
			getPlayer().setOrganizer(player.init.lib.get(0));
			getPlayer().playPause();
			getPlayer().setVolume(0);
			getPlayer().pauseMedia();
			meta_adq = true;
		}
		
		getView().change_to_YourBeats();
		atualiza();
	}
	
	@FXML
	void change_to_CreatePlaylist(ActionEvent e) {
		getView().change_to_CreatePlaylist();
	}
	
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
	
	@FXML
	void newVol() {
		getPlayer().setVolume(sound_Slider.getValue()*0.01);
	}
	
	@FXML
	public void volUp(ActionEvent e){
		getPlayer().volUp();
	}
	
	@FXML
	public void volDown(ActionEvent e){
		getPlayer().volDown();
	}

	@FXML
	public void random(ActionEvent e) {
		getPlayer().random();
	}

	@FXML
	public void nextMusic(ActionEvent e) {
		getPlayer().nextMusic();
	}

	@FXML
	public void prevMusic(ActionEvent e) {
		getPlayer().prevMusic();
	}

	@FXML
	public void repeat(ActionEvent e) {
		getPlayer().repeat();
	}
	
	@FXML
	public void playPause(ActionEvent e){
		getPlayer().playPause();
	}
	
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
		
	public void play_selected(ActionEvent e) {
		String id = ((Button)e.getSource()).getId();
		StringTokenizer st = new StringTokenizer(id, "-");
		String playlist = st.nextToken();
		playlist.concat(".txt");
		int index = Integer.parseInt(st.nextToken());
		getPlayer().playSelected(playlist, index);
	}
	
	public void atualiza () {
		 
		ObservableList<MusicaInfo> list = getLista();
		
		nome.setCellValueFactory(new PropertyValueFactory<>("nome_musica"));
		album.setCellValueFactory(new PropertyValueFactory<>("nome_album"));
		artista.setCellValueFactory(new PropertyValueFactory<>("nome_artista"));
		play.setCellValueFactory(new PropertyValueFactory<>("play"));
		
		table.setItems(list);
	}
		
}
