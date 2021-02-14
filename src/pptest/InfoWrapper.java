package pptest;

import javax.sound.midi.MidiDevice.Info;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class InfoWrapper {
	private StringProperty mDesc,mVendor,mVer,mName;
	public InfoWrapper() {
		mDesc =new SimpleStringProperty("description_null");
		mVendor=new SimpleStringProperty("vender_null");
		mVer = new SimpleStringProperty("ver_null");
		mName = new SimpleStringProperty("name_null");
	}
	public InfoWrapper(Info i) {
		System.out.println("D/InfoWrapper: "+i.getName());
		mDesc = new SimpleStringProperty(i.getDescription());
		mVendor = new SimpleStringProperty(i.getVendor());
		mVer = new SimpleStringProperty( i.getVersion());
		mName = new SimpleStringProperty(i.getName());
	}
	public String getMName() {
		return mName.get();
	}
	public String getMDesc() {
		return mDesc.get();
		
	}
	public String getMVender() {
		return mVendor.get();
	}
	public StringProperty mNameProperty() {
		if (mName ==null) {
			mName = new SimpleStringProperty("name_null");
		}
		return mName;
	}
	
}
