package application;
	

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
	
	public static void main(String[] args) {	
		String directoryName = System.getProperty("user.dir");
		System.out.println("Current Working Directory is = " + directoryName);
		launch(args);	
	}

	@Override
	public void start(Stage stage) throws Exception {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("front-end/HomeScene.fxml"));
			
			// instancia o controller e coloca ele no escopo do view e o view no escopo do player
			Controller controller = new Controller();
			View view = new View(controller);
			Player player = new Player(view);
			
			// TODO: fazer sistema de receber a playlist do usuário. Esta linha é só para debug
			player.setOrganizer(player.init.lib.get(player.init.map_playlist.get("c.txt")));
			
			// coloca o player e o view no escopo do controller
			controller.setPlayer(player);
			//controller.setView(view);
			
			loader.setController(controller);
			
			Parent root = loader.load();
			Scene scene = new Scene(root);
			
			stage.setScene(scene);
			stage.setResizable(false);
			
			stage.show();
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
}
