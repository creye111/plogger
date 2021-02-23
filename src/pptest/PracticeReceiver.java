package pptest;

import javax.sound.midi.MidiMessage;
import javax.sound.midi.Receiver;

public class PracticeReceiver implements Receiver{
	public PracticeReceiver() {
		
	}
	public PracticeReceiver(Receiver r) {
		
	}
	@Override
	public void send(MidiMessage message, long timeStamp) {
		// TODO Auto-generated method stub
		System.out.println(message.toString());
	}

	@Override
	public void close() {
		// TODO Auto-generated method stub
		
	}

}
