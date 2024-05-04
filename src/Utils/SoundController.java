package Utils;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.io.File;

public class SoundController {
    private String filePath;
    private MediaPlayer mediaPlayer;
    public  SoundController(String filePath){
        this.filePath = filePath;
        Media sound = new Media(new File(filePath).toURI().toString());

        mediaPlayer = new MediaPlayer(sound);
    }
    public MediaPlayer getMediaPlayer(){
        return mediaPlayer;
    }
    public void playMusic(){
        Thread ae = new Thread(() -> {
            if (mediaPlayer.getStatus() == MediaPlayer.Status.PLAYING){
                mediaPlayer.stop();
                mediaPlayer.seek(mediaPlayer.getStartTime());

            }
            mediaPlayer.play();
            Thread.yield();
        });
        ae.start();
    }
}
