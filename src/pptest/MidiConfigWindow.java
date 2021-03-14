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
import javafx.scene.control.Alert.AlertType;

import java.util.Date;
import java.util.TimerTask;
import javax.sound.midi.Synthesizer;
public class MidiConfigWindow{
	private Stage midiConfigStage;
	private Scene midiConfigScene;
	private boolean isTesting=false;
	private static boolean midiStageShow = false;
	private TableView <InfoWrapper> midiDeviceTable;
	private  Transmitter selectedTransmitter;
	private Receiver ioMidiRec;
	private InfoWrapper selectedItem;
	private MidiDevice selectedDevice;
	
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
				if (selectedDevice != null) {
					if(selectedDevice.isOpen()) {
						selectedDevice.close();
					}
				}
			};
		});
		
		HBox topBox = new HBox();
		Text topText = new Text("MIDI Settings. . .");
		topBox.getChildren().add(topText);
		topBox.setAlignment(Pos.CENTER);
		
		
		HBox buttonBox1 = new HBox();
		Button cancelButton = new Button("Cancel");
		
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
				try {
					setSelectedItem(midiDeviceTable.getSelectionModel().getSelectedItem());
					Info [] mList = MidiSystem.getMidiDeviceInfo();
					for(Info i : mList) {
						if(i.getName()==getSelectedItem().getMName()&&i.hashCode()==getSelectedItem().getMHash()) {
							System.out.println("FOUND");
							System.out.println("D/testButton Press: "+getSelectedItem().getMName()+"HASH: "+getSelectedItem().getMHash());
							
								setSelectedDevice(MidiSystem.getMidiDevice(i));
								if(!isTesting()) {
									getSelectedDevice().open();
									System.out.println("D/MidiConfigWindow: selectedDevice isOpen:"+getSelectedDevice().isOpen());
									selectedTransmitter = getSelectedDevice().getTransmitter();
									ioMidiRec = new PracticeReceiver();
									selectedTransmitter.setReceiver(ioMidiRec);
									setTesting(true);
									break;
								}
								else {
									getSelectedDevice().close();
									System.out.println("D/MidiConfigWindow: selectedDevice isOpen:"+getSelectedDevice().isOpen());
									setTesting(false);
								}
						}
					}
				}catch(Exception e){
					if(e.getClass().getCanonicalName()=="java.lang.NullPointerException") {
						System.out.println("NO DEVICE SELECTED!!!");
					}
					else {
						e.printStackTrace();
						System.out.println(e.getClass().getCanonicalName());
					}
				}
			}});
		
		saveButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0) {
				try {
					if(!isTesting()) {
						
					}else {
						getSelectedDevice().close();
						System.out.println("D/MidiConfigWindow: selectedDevice isOpen:"+getSelectedDevice().isOpen());
						setTesting(false);
						
					}
					if(getMConfigShow()) {
						midiStageShow=false;
					}
					midiConfigStage.close();
				}catch(Exception e) {
					if(e.getClass()==NullPointerException.class) {
						System.out.println("No Device selected!!!!");
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
		cancelButton.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent arg0) {
				if(getMConfigShow()) {
					midiStageShow=false;
				}
				if(isTesting()) {
					getSelectedDevice().close();
					System.out.println("D/MidiConfigWindow: selectedDevice isOpen:"+getSelectedDevice().isOpen());
					setTesting(false);
				}
				midiConfigStage.close();
			};
		});
		
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
	private boolean isTesting() {
		return isTesting;
	}
	private void setTesting(boolean isTesting) {
		this.isTesting = isTesting;
	}
	private MidiDevice getSelectedDevice() {
		return selectedDevice;
	}
	private void setSelectedDevice(MidiDevice selectedDevice) {
		this.selectedDevice = selectedDevice;
	}
	private InfoWrapper getSelectedItem() {
		return selectedItem;
	}
	private void setSelectedItem(InfoWrapper selectedItem) {
		this.selectedItem = selectedItem;
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
