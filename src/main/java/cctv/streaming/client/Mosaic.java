package cctv.streaming.client;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import uk.co.caprica.vlcj.component.EmbeddedMediaPlayerComponent;

@SuppressWarnings("serial")
public class Mosaic extends JPanel {

	private static final String NO_CAMERAS_FOUND = "No cameras found!";
    private static final float ASPECT_RATIO = 16/9;
	
	private ArrayList<EmbeddedMediaPlayerComponent> mediaPlayers;
	private EmbeddedMediaPlayerComponent mediaPlayer;
	private MainStreamFrame mainStreamFrame;

	public Mosaic() {
		setSize(500, 500);		
		setBorder(BorderFactory.createEmptyBorder(0,10,10,10));
		setBackground(Color.BLACK);				
	}

	public EmbeddedMediaPlayerComponent getMediaPlayer() {
		return mediaPlayer;
	}

	public void setMediaPlayer(EmbeddedMediaPlayerComponent mediaPlayer) {
		this.mediaPlayer = mediaPlayer;
	}

	public void run(ArrayList<Camera> cameras) {
		removeAll();
		int size = cameras.size();
		if (size > 0) {

			int rows=(int) Math.sqrt(ASPECT_RATIO*size);
			setLayout(new GridLayout(rows,0));

			mediaPlayers = new ArrayList<EmbeddedMediaPlayerComponent>();

			for (int i = 0; i < cameras.size(); i++) {
				mediaPlayer = new EmbeddedMediaPlayerComponent();	
				mediaPlayer.setBounds(10,10,10,10);				

                mediaPlayers.add(mediaPlayer);

                System.out.println("Adding camera " + cameras.get(i).getSubstream());
				add(mediaPlayer);
			}

			for (int j = 0; j < mediaPlayers.size(); j++) {
				final EmbeddedMediaPlayerComponent player = mediaPlayers.get(j);
				Camera c = cameras.get(j);

                final String mainstream = c.getMainstream();
				final String substream = c.getSubstream();

				System.out.println("Playing camera "+ c.getSubstream());

                player.getMediaPlayer().playMedia(substream);
				player.getMediaPlayer().setEnableMouseInputHandling(true);
				
				try {
					Thread.sleep(500);
				} catch (InterruptedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				player.getVideoSurface().addMouseListener(new MouseListener() {

					public void mouseReleased(MouseEvent e) {}
					public void mousePressed(MouseEvent e) {}
					public void mouseExited(MouseEvent e) {}
					public void mouseEntered(MouseEvent e) {}

					public void mouseClicked(MouseEvent e) {
                        player.getMediaPlayer().stop();

						if(mainStreamFrame == null || !mainStreamFrame.isVisible()){
							mainStreamFrame = new MainStreamFrame();
							mainStreamFrame.setVisible(true);
						}					
						mainStreamFrame.toFront();
						mainStreamFrame.setExtendedState(JFrame.NORMAL);
						mainStreamFrame.play(mainstream);

                        player.getMediaPlayer().play();
					}
				});
			}
			updateUI();
		} else {		
			JOptionPane.showMessageDialog(this, NO_CAMERAS_FOUND);
		}
	}

	public void closeAll() {
		try {
			for(EmbeddedMediaPlayerComponent media : mediaPlayers)
				media.getMediaPlayer().stop();
			removeAll();
		} catch(Exception e){
			e.printStackTrace();
		}
	}
}
