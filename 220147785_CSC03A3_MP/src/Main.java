import acsse.csc3a.gui.GraphPane;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application{


	@Override
	public void start(Stage primaryStage) throws Exception {
		primaryStage.setHeight(800);
		primaryStage.setWidth(1400);
		
		GraphPane pane = new GraphPane();
		Scene scene = new Scene(pane);
		primaryStage.setScene(scene); 
		primaryStage.show();
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
