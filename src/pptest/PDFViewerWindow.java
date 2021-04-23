package pptest;

import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.VBox;
public class PDFViewerWindow {
	private Stage stage;
	private Scene scene;
	
	public PDFViewerWindow(BorderPane root) {
		setStage(new Stage());
		scene = new Scene(root,400.0,200.0);
		getStage().setTitle("Sheet Music Viewer");
		
		
		FXMLLoader ldr = new FXMLLoader();
		try {
			String path = "file:///"+System.getProperty("user.dir")+"/src/PDFViewerWindow.fxml";
			System.out.println(path);
			ldr.setLocation(new URL(path));
			root = ldr.<BorderPane>load();
			getStage().setScene(new Scene(root,400.0,200.0));
			stage.show();
		} catch (IOException e) {
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
