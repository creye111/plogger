package pptest;

import java.io.IOException;

import javafx.application.Application;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.shape.Rectangle;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;
import javafx.scene.text.*;
import javafx.scene.paint.*;
public class PPLoggerMain extends  Application{
	private double xDim=600.0;
	private double yDim=200.0;
	private static boolean midiStageShow = false;
	/**
	 *hello
	 */
	@Override
	public void start(Stage primaryStage) throws Exception {
		// TODO Auto-generated method stub
		primaryStage.setTitle("Piano Practice Logger 0.1");
		
		
		BorderPane root = new BorderPane();
		
		Scene scene = new Scene(root,xDim,yDim);
		HBox topBox = new HBox();
		Rectangle r1 = new Rectangle(xDim/3.0,yDim/4.0,Color.BLACK);
		Rectangle r2 = new Rectangle(xDim/3.0,yDim/4.0,Color.BLACK);
		
		Text t = new Text(20, 50, "Piano Practice Logger 0.1");
		t.setWrappingWidth(xDim/3.0);
		t.setTextAlignment(TextAlignment.CENTER);
		t.setFont(new Font(20));
		topBox.getChildren().add(r1);
		topBox.getChildren().add(t);
		
		topBox.getChildren().add(r2);
		//Setup Center Button Box
		VBox buttonVbox = new VBox();
		buttonVbox.setSpacing(5);
		Button configMidiButton = new Button("Configure MIDI Device...");
		buttonVbox.getChildren().add(configMidiButton);
		Button setupWebConfigButton = new Button("Setup Web Config...");
		buttonVbox.getChildren().add(setupWebConfigButton);
		VBox.setMargin(setupWebConfigButton, new Insets(5, 10, 10, 10));
		VBox.setMargin(configMidiButton, new Insets(5, 10, 10, 10));
		Button closeWindow = new Button("Close");
		VBox.setMargin(closeWindow, new Insets(5,10,10,10));
		
		buttonVbox.getChildren().add(closeWindow);
		
		configMidiButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				try {
					BorderPane mPane = new BorderPane();
					MidiConfigWindow mWindow = new MidiConfigWindow(mPane);
					Stage m;
					m = mWindow.getStage();
					if(!mWindow.getMConfigShow()) {
						m.show();
						mWindow.setMConfigShow(true);
					}
				}catch(Exception ex) {
					ex.printStackTrace();
				}
			}
		});
		
		setupWebConfigButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0) {
				try {
					BorderPane wPane = new BorderPane();
					WebConfigWindow wWindow = new WebConfigWindow(wPane);
					Stage wStage = new Stage();
					wStage = wWindow.getwConfigStage();
					if(!wWindow.iswConfigShow()) {
						wStage.show();
						wWindow.setwConfigShow(true);
					}
				} catch(Exception ex) {
					ex.printStackTrace();
				}
			}
		});
		closeWindow.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				primaryStage.close();
				
			}
			
		});
		Rectangle bigBlue = new Rectangle(xDim/3.0, 3.0*yDim/4.0, Color.BLACK);
		root.setLeft(bigBlue);
		Rectangle rightBlue = new Rectangle(xDim/3.0,3.0*yDim/4.0, Color.BLACK);
		root.setTop(topBox);
		root.setCenter(buttonVbox);
		root.setRight(rightBlue);
		
		primaryStage.setScene(scene);
		primaryStage.show();
		
	}
//	public static void midiConfigStageShow(){
//		BorderPane midiConfigRoot;
//		midiConfigRoot = new BorderPane();
//		Stage midiConfigStage = new Stage();
//		midiConfigStage.setTitle("Midi Configuration");
//		Scene midiConfigScene = new Scene(midiConfigRoot,400.0,400.0);
//		midiConfigStage.setScene(midiConfigScene);
//		midiConfigStage.show();
//		midiConfigStage.setOnCloseRequest(new EventHandler <WindowEvent>() {
//			@Override
//			public void handle(WindowEvent arg0) {
//				if(checkMidiStageShowing()) {
//					midiStageShow=false;
//				}
//			};
//		});
//		
//	}
	
	public static void main(String args[]) {
		Application.launch(args);
	}
}	
