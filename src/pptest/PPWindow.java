package pptest;

import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class PPWindow {
	private Stage stage;
	private Scene scene;
	private double winWidth,winHeight;
	private String title = "PPWindow"; 
	
	public PPWindow(BorderPane root, double xDim,double yDim) {
		stage = new Stage();
		winWidth=xDim;
		winHeight=yDim;
		scene = new Scene(root, winWidth, winHeight );
	}
}
