package pptest;

import javafx.event.EventHandler;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
public class WebConfigWindow{
	private Stage wConfigStage;
	private Scene wConfigScene;
	private static boolean wConfigShow=false;
	public WebConfigWindow() {
		
	}
	public WebConfigWindow(BorderPane root) {
		setwConfigStage(new Stage());
		wConfigScene = new Scene(root,400,400);
		
		getwConfigStage().setTitle("Web Configuration");
		getwConfigStage().setScene(wConfigScene);
		getwConfigStage().setOnCloseRequest(new EventHandler<WindowEvent>() {

			@Override
			public void handle(WindowEvent arg0) {
				if(wConfigShow) {
					setwConfigShow(false);
				}
				
			}
			
			
		});
		
	}
	protected Scene getwConfigScene() {
		return wConfigScene;
	}
	protected void setwConfigScene(Scene wConfigScene) {
		this.wConfigScene = wConfigScene;
	}
	protected static boolean iswConfigShow() {
		return wConfigShow;
	}
	protected void setwConfigShow(boolean wConfigShow) {
		this.wConfigShow = wConfigShow;
	}
	protected Stage getwConfigStage() {
		return wConfigStage;
	}
	protected void setwConfigStage(Stage wConfigStage) {
		this.wConfigStage = wConfigStage;
	}
	

}
