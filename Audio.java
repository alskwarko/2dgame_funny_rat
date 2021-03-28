package rases;

import java.io.File;
import java.io.IOException;
import javax.sound.sampled.*;

public class Audio {
	private String track; // адрес трека(файла)
	private Clip clip = null; // ссылка на объект класса
	private FloatControl volumeC = null; // контролер громкости
	private double wt; // уровень громкости
	
	public Audio(String track, double wt) {
		this.track = track;
		this.wt = wt;
	}
	
	public void sound() {
		File f = new File(this.track); //передаем звуковой файл в f
		AudioInputStream tr = null; // объект потока AudioIтputStream пуст
		
		try {
			tr = AudioSystem.getAudioInputStream(f); // получаем AudioInputStream (нужный файл)
		} catch (UnsupportedAudioFileException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		try {
			clip = AudioSystem.getClip(); // получаем реализацию интерфейса Clip
			clip.open(tr); // загружаем звуковой поток в clip
			
			// получаем контролер громкости
			volumeC = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
					
			clip.setFramePosition(0); // устанавливаем указатель на старт
			clip.start(); // поехали!!
		}catch (LineUnavailableException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	//уровень громкости
	public void setVolume() {
		if(wt < 0) wt = 0; 
		if(wt > 1) wt = 1;
		float min = volumeC.getMinimum();
		float max = volumeC.getMaximum();
		volumeC.setValue((max - min) * (float)wt + min);
		}
}
