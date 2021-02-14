package pptest;

import javafx.collections.ObservableList;
import javafx.collections.ObservableListBase;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;
import javafx.geometry.Insets;
import javax.sound.midi.MidiDevice;
import javax.sound.midi.MidiDevice.Info;
import javax.sound.midi.MidiSystem;
public class MidiConfigWindow{
	private Stage midiConfigStage;
	private Scene midiConfigScene;
	private static boolean midiStageShow = false;
	public MidiConfigWindow() {
		// TODO Auto-generated constructor stub
		midiConfigStage = new Stage();
	}
	public MidiConfigWindow(BorderPane root) {
		midiConfigStage = new Stage();
		midiConfigScene = new Scene(root,400.0,400.0);

		midiConfigStage.setTitle("Midi Configuration");
		midiConfigStage.setScene(midiConfigScene);
		midiConfigStage.setOnCloseRequest(new EventHandler <WindowEvent>() {
			@Override
			public void handle(WindowEvent arg0) {
				if(getMConfigShow()) {
					midiStageShow=false;
				}
			};
		});
		
		HBox topBox = new HBox();
		Text topText = new Text("MIDI Settings. . .");
		topBox.getChildren().add(topText);
		topBox.setAlignment(Pos.CENTER);
		
		HBox bottomBox = new HBox();
		Button cancelButton = new Button("Cancel");
		cancelButton.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent arg0) {
				midiConfigStage.close();
			};
		});
		bottomBox.getChildren().add(cancelButton);
		Button saveButton = new Button("Save");
		bottomBox.getChildren().add(saveButton);
		Button refreshButton = new Button("Refresh");
		bottomBox.getChildren().add(refreshButton);
		HBox.setMargin(refreshButton,new Insets(5, 10, 10, 30));
		bottomBox.setAlignment(Pos.CENTER);
		
		Rectangle r1 = new Rectangle(1200/3.0,1200/4.0,Color.BLACK);
		
		TableView <InfoWrapper> midiDeviceTable = new TableView<>();
		Info[] iArr = MidiSystem.getMidiDeviceInfo();
		
		MInfoList infoList = new MInfoList(iArr);
		for(Info i : MidiSystem.getMidiDeviceInfo()) {
			System.out.print(i);
		}
		ObservableList <InfoWrapper>x= infoList;
		midiDeviceTable.setItems(x);
		
		TableColumn<InfoWrapper, String> mDeviceNameCol = new TableColumn("Device Name");
		mDeviceNameCol.setCellValueFactory(new PropertyValueFactory<InfoWrapper,String>("mName"));
		
		TableColumn<InfoWrapper,String> mDescCol = new TableColumn("Description");
		mDescCol.setCellValueFactory(new PropertyValueFactory<InfoWrapper,String>("mDesc"));
		midiDeviceTable.getColumns().setAll(mDeviceNameCol,mDescCol);
		
		root.setCenter(midiDeviceTable);
		root.setTop(topBox);
		root.setBottom(bottomBox);
		
		
	}
	
	public static boolean getMConfigShow() {
		return midiStageShow;
	}
	public void setMConfigShow(boolean x) {
		midiStageShow =x;
	}
	public Stage getStage() {
		return midiConfigStage;
	}
}
