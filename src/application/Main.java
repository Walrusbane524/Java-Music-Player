package application;
	

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
<<<<<<< HEAD
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
=======
import javafx.stage.Stage;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

>>>>>>> refs/remotes/origin/main

public class Main extends Application {
	
	public static void main(String[] args) {	
		String directoryName = System.getProperty("user.dir");
<<<<<<< HEAD
		System.out.println("Current Working Directory is = " + directoryName);
=======
		System.out.println("Current Working Directory is = " +directoryName);
>>>>>>> refs/remotes/origin/main
		launch(args);	
	}

	@Override
	public void start(Stage stage) throws Exception {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("front-end\\HomeScene.fxml"));
			
			// instancia o controller e coloca ele no escopo do player
			Controller controller = new Controller(); 
			Player player = new Player(controller);
			
			// coloca o player no escopo do controller
			controller.setPlayer(player);
			
			loader.setController(controller);
			
			Parent root = loader.load();
			Scene scene = new Scene(root);

			stage.setScene(scene);
			
			stage.show();
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
}
