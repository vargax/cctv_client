package cctv.streaming.client;

import uk.co.caprica.vlcj.component.EmbeddedMediaPlayerComponent;

import java.util.ArrayList;
import java.util.TimerTask;

/**
 * Created by cvargasc on 2/16/17.
 */
public class CameraTestTask extends TimerTask {

    private ArrayList<EmbeddedMediaPlayerComponent> mediaPlayers;

    public CameraTestTask(ArrayList<EmbeddedMediaPlayerComponent> mediaPlayers) {
        this.mediaPlayers = mediaPlayers;
        System.out.println("CameraTesterTask ready with "+mediaPlayers.size()+" cameras");
    }

    public void run() {
        System.out.println("CameraTesterTask starts!");
        for (int i = 0; i < mediaPlayers.size(); i++) {
            EmbeddedMediaPlayerComponent player = mediaPlayers.get(i);

            if (player.getMediaPlayer().isPlaying()) {
                System.out.println("+ Camera "+i+" is playing --> OK");
            } else {
                System.out.println("+ Camera "+i+" is stopped --> Restarting...");
                player.getMediaPlayer().play();
            }

            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
