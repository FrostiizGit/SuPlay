package suplay.potato;

import java.io.*;
import java.util.concurrent.CountDownLatch;

//import javafx.scene.media.Media;
//import javafx.scene.media.MediaPlayer;

import javax.print.attribute.standard.Media;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineEvent;
import javax.sound.sampled.LineListener;
import javax.sound.sampled.SourceDataLine;

import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player;
import javazoom.jl.player.advanced.*;

public class SoundManager {
	public static void soundFormat(String absPath, String fExt) {
        //String fExt =  fName.substring(fName.lastIndexOf("."));
		
        if(fExt.equals(".wav")) {
        	wavSound(absPath);
        } else if (fExt.equals(".mp3")) {
        	jlayerSound(absPath);
        }
	}
	
	private static void jlayerSound(String path) {
		new Thread(new Runnable() {
			public void run() {
				try {
					InputStream in;
					in = (InputStream)new BufferedInputStream(new FileInputStream(new File(path)));
					Player player;
					try {
						player = new Player(in);
						player.play();
						if(player.isComplete()) {
							System.out.println("fefs");
							ListManager.songIndex += 1;
							ListManager.playAllSongs();
						}
					} catch (JavaLayerException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					 
				} catch (FileNotFoundException e) {
					// TODO Auto-gpublic void run() {enerated catch block
					e.printStackTrace();
				}
			}
		}).start();
	}
	private static void wavSound(String absPath) {
		new Thread(new Runnable() {
			public void run() {
				try {
					AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File(absPath));
			        Clip clip = AudioSystem.getClip();
			        
			        clip.open(audioInputStream);
			        clip.start();
			        clip.addLineListener(new LineListener() {

			            public void update(LineEvent event) {
			                if(event.getType() == LineEvent.Type.STOP) {
			                	System.out.println("frefrrfrefrere");
			                	//ListManager.songIndex += 1;
			                	ListManager.songIndex += 1;
								ListManager.playAllSongs();
			                }
			            }
			        });  
				} catch (Exception e) {
					System.err.println(e.getMessage());
				}
				System.out.println("end of song");
			}
		}).start();
	}
}
