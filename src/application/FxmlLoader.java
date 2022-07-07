package application;

import java.net.URL;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.Pane;

public class FxmlLoader {
	private Pane view;
	
	public Pane getPage(String fileName) {
		try{
			URL fileUrl = Main.class.getResource("/front-end/" + fileName + ".fxml");
			if(fileUrl == null) {
				throw new java.io.FileNotFoundException();
			}
			view = new FXMLLoader().load(fileUrl);
		}
		catch(Exception e) {
			System.out.println("erro");
		}
		return view;
	}
}
