import java.applet.Applet;
import java.applet.AudioClip;

public class AnnounceTimeOnSeparateThread implements Runnable{
	
	private int hour, minute;
	private AudioClip[] hourAudio = new AudioClip[12];
	private AudioClip[] minuteAudio = new AudioClip[60];
	private AudioClip amAudio = Applet.newAudioClip(this.getClass().getResource("/audio/am.au"));
	private AudioClip pmAudio = Applet.newAudioClip(this.getClass().getResource("/audio/pm.au"));

	
	/** Get Audio clips */
	public AnnounceTimeOnSeparateThread(int hour,int minute) {
		initAudio();
		this.hour = hour;
		this.minute = minute;
		//setHour(hour);
		//setMinute(minute);
	}

	// Init array with file audio
	public void initAudio() {
		// Create audio clips for pronouncing hours
		for (int i = 0; i < 12; i++)
			hourAudio[i] = Applet.newAudioClip(this.getClass().getResource("/audio/hour" + i + ".au"));
		// Create audio clips for pronouncing minutes
		for (int i = 0; i < 60; i++)
			minuteAudio[i] = Applet.newAudioClip(this.getClass().getResource("/audio/minute" + i + ".au"));
	}


	@Override
	public void run() {
		try {
			hourAudio[hour % 12].play();
			Thread.sleep(1500);
			minuteAudio[minute].play();
			Thread.sleep(1500);
		} catch (InterruptedException ex) {
		}
		if (hour < 12)
			amAudio.play();
		else
			pmAudio.play();
	}

	public int getHour() {
		return hour;
	}

	public void setHour(int hour) {
		this.hour = hour;
	}

	public int getMinute() {
		return minute;
	}

	public void setMinute(int minute) {
		this.minute = minute;
	}
	
	

}
