package cctv.streaming.client;

import uk.co.caprica.vlcj.component.EmbeddedMediaPlayerComponent;

import java.util.ArrayList;
import java.util.TimerTask;

/**
 * Created by cvargasc on 2/16/17.
 */
public class CameraTestTask extends TimerTask {

    public final static String TEST = "test";
    public final static String RESET = "reset";

    private ArrayList<EmbeddedMediaPlayerComponent> mediaPlayers;
    private String mode;

    public CameraTestTask(ArrayList<EmbeddedMediaPlayerComponent> mediaPlayers, String mode) {
        this.mediaPlayers = mediaPlayers;
        this.mode = mode;

        System.out.println("CameraTesterTask setup as "+mode+" with "+mediaPlayers.size()+" cameras");
    }

    public void test(EmbeddedMediaPlayerComponent player, int index) {
        if (player.getMediaPlayer().isPlaying()) {
            System.out.println("+ Camera "+index+" is playing --> OK");
        } else {
            System.out.println("+ Camera "+index+" is stopped --> Restarting...");
            player.getMediaPlayer().play();
        }
    }

    public void reset (EmbeddedMediaPlayerComponent player, int index) {
        System.out.println("! Resetting camera "+index+"...");
        player.getMediaPlayer().stop();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        player.getMediaPlayer().start();
    }

    public void run() {
        System.out.println("CameraTesterTask starts!");
        for (int i = 0; i < mediaPlayers.size(); i++) {
            EmbeddedMediaPlayerComponent player = mediaPlayers.get(i);

            if (mode.equals(TEST)) {
                test(player, i);
            } else if (mode.equals(RESET)) {
                reset(player, i);
            }

            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
