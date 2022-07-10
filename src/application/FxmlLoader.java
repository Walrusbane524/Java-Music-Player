package application;

import java.net.URL;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;

/**
 * Responsável por carregar os arquivos .fxml que mudam o painel principal da interface gráfica.
 */
public class FxmlLoader {
	public static AnchorPane view;
	
	/**
	 * Carrega o arquivo .fxml e retorna o painel
	 * 
	 * @param fileName Nome do arquivo .fxml
	 * @param c Controller
	 * @return Painel
	 */
	public static AnchorPane getPage(String fileName, Controller c) {
		try{
			URL fileUrl = Main.class.getResource("front-end/" + fileName + ".fxml");
			if(fileUrl == null) {
				throw new java.io.FileNotFoundException();
			}
			FXMLLoader loader = new FXMLLoader(fileUrl);
			loader.setController(c);
			System.out.println(loader.getController() == null);
			view = loader.load();
		}
		catch(Exception e) {
			System.out.println("erro" + e.getMessage());
		}
		return view;
	}
}
