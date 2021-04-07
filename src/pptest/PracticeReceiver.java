package pptest;

import javax.sound.midi.MidiMessage;
import javax.sound.midi.Receiver;
import java.time.LocalDateTime;
public class PracticeReceiver implements Receiver{
	private long lastNoteOnTime=-1;
	private long firstNoteTime=-1;
	private long elaspsedTime =-1;
	private long delay=10000000;
	
	public PracticeReceiver() {
		
	}
	public PracticeReceiver(Receiver r) {
	
	}
	public PracticeReceiver(int i) {
		
	}
	@Override
	public void send(MidiMessage message, long timeStamp) {
		// TODO Auto-generated method stub
		System.out.println(timeStamp + "|| L: "+message.getLength()+"S: "+message.getStatus()+" "+message.getMessage());
		/**
		 * if(message is of type NOTE_ON)
		 * 		lastNoteOnTime = timeStamp;
		 * NOTE_ON: getStatus == 144
		 * 
		 */
		if(message.getStatus()==144) {
			if(lastNoteOnTime!=-1) {
				lastNoteOnTime = timeStamp;
		
			}
			else{
				lastNoteOnTime = timeStamp;
				firstNoteTime=timeStamp;
			}
		}
		else {
			if(lastNoteOnTime!=-1) {
				if(timeStamp - lastNoteOnTime >delay ) {
					elaspsedTime=lastNoteOnTime-firstNoteTime;
					System.out.println("SessionEnd: "+elaspsedTime/1000000);
					//TODO: Write session length to DB
					lastNoteOnTime =-1;
					firstNoteTime=-1;
				}
			}
		}
	}

	@Override
	public void close() {
		// TODO Auto-generated method stub
		
	}

}
