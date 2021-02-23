package pptest;

import javafx.collections.ObservableList;
import javafx.collections.ObservableListBase;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
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
import javax.sound.midi.MidiMessage;
import javax.sound.midi.MidiSystem;
import javax.sound.midi.Receiver;
import javax.sound.midi.Sequence;
import javax.sound.midi.Sequencer;
import javax.sound.midi.Transmitter;

import java.util.Date;
import java.util.TimerTask;
import javax.sound.midi.Synthesizer;
public class MidiConfigWindow{
	private Stage midiConfigStage;
	private Scene midiConfigScene;
	private static boolean midiStageShow = false;
	TableView <InfoWrapper> midiDeviceTable;
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
		
		
		HBox buttonBox1 = new HBox();
		Button cancelButton = new Button("Cancel");
		cancelButton.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent arg0) {
				midiConfigStage.close();
			};
		});
		buttonBox1.getChildren().add(cancelButton);
		Button saveButton = new Button("Save");
		buttonBox1.getChildren().add(saveButton);
		
		
		buttonBox1.setAlignment(Pos.CENTER);
		
		HBox buttonBox2 = new HBox();
		buttonBox2.setAlignment(Pos.TOP_RIGHT);
		Button testButton = new Button("Test Selection...");
		buttonBox2.getChildren().add(testButton);
		
		
		Button refreshButton = new Button("Refresh");
		buttonBox2.getChildren().add(refreshButton);
		//HBox.setMargin(refreshButton,new Insets(5, 10, 10, 30));
		Rectangle r1 = new Rectangle(1200/3.0,1200/4.0,Color.BLACK);
		
		midiDeviceTable = new TableView<>();
		Info[] iArr = MidiSystem.getMidiDeviceInfo();
		
		MInfoList infoList = new MInfoList(iArr);
		for(Info i : MidiSystem.getMidiDeviceInfo()) {
			System.out.println("D/"+i);
		}
		ObservableList <InfoWrapper>x= infoList;
		midiDeviceTable.setItems(x);
		
		TableColumn<InfoWrapper, String> mDeviceNameCol = new TableColumn("Device Name");
		mDeviceNameCol.setCellValueFactory(new PropertyValueFactory<InfoWrapper,String>("mName"));
		
		TableColumn<InfoWrapper,String> mDescCol = new TableColumn("Description");
		mDescCol.setCellValueFactory(new PropertyValueFactory<InfoWrapper,String>("mDesc"));
		midiDeviceTable.getColumns().setAll(mDeviceNameCol,mDescCol);
		
		testButton.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				InfoWrapper selectedItem = midiDeviceTable.getSelectionModel().getSelectedItem();
				Info [] mList = MidiSystem.getMidiDeviceInfo();
				for(Info i : mList) {
					if(i.getName()==selectedItem.getMName()) {
						System.out.println("FOUND");
						System.out.println("D/testButton Press: "+selectedItem.getMName());
						try {
							MidiDevice selectedDevice = MidiSystem.getMidiDevice(i);
							selectedDevice.open();
							System.out.println("D/MidiConfigWindow: "+selectedDevice.isOpen());
							
							System.out.println("D/MidiConfigWindow: "+selectedDevice.isOpen());
											
							Transmitter t = selectedDevice.getTransmitter();
							Receiver r = new PracticeReceiver();
							t.setReceiver(r);
							
							
							//System.out.println(r.toString());
							selectedDevice.close();
						}
						catch(Exception e){
							e.printStackTrace();
						}
						break;
					}
				}
			}
			
		});
		
		root.setCenter(midiDeviceTable);
		root.setTop(topBox);
		VBox bottomBox = new VBox();

		bottomBox.getChildren().add(buttonBox2);
		bottomBox.getChildren().add(buttonBox1);
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
	private class ListenMidiTask extends TimerTask{
		private MidiDevice myD;
		public ListenMidiTask(MidiDevice d) {
			myD=d;
		}
		@Override
		public void run() {
			// TODO Auto-generated method stub
			System.out.println("[" + new Date() + "] " + "uh" + ": task executed!");
		}
		
	}
	

	
}
