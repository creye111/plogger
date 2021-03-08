package pptest;

import javax.sound.midi.MidiMessage;
import javax.sound.midi.Receiver;

public class PracticeReceiver implements Receiver{
	private long lastNoteOnTime;
	public PracticeReceiver() {
		
	}
	public PracticeReceiver(Receiver r) {
	
	}
	@Override
	public void send(MidiMessage message, long timeStamp) {
		// TODO Auto-generated method stub
		System.out.println(timeStamp + "|| L: "+message.getLength()+"S: "+message.getStatus()+" "+message.getMessage());
		/**
		 * if(message is of type NOTE_ON)
		 * 		lastNoteOnTime = timeStamp;
		 */
		
	}

	@Override
	public void close() {
		// TODO Auto-generated method stub
		
	}

}
