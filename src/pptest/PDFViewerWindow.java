package pptest;

import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.net.MalformedURLException;
import java.net.URL;

import javafx.fxml.FXMLLoader;

public class PDFViewerWindow {
	private Stage stage;
	private Scene scene;
	
	public PDFViewerWindow(BorderPane root) {
		setStage(new Stage());
		scene = new Scene(root,400.0,200.0);
		getStage().setTitle("Sheet Music Viewer");
		getStage().setScene(scene);
		
		FXMLLoader ldr = new FXMLLoader();
		try {
			ldr.setLocation(new URL("file:///"+System.getProperty("user.dir")+"/src.PDFViewerWindow.fxml"));
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	public Stage getStage() {
		return stage;
	}

	public void setStage(Stage stage) {
		this.stage = stage;
	}
	

}
