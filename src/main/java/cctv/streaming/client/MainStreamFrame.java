package cctv.streaming.client;

import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JFrame;

import uk.co.caprica.vlcj.component.EmbeddedMediaPlayerComponent;

@SuppressWarnings("serial")
public class MainStreamFrame extends JFrame{

	
	private EmbeddedMediaPlayerComponent mainStreamPlayer;
	protected EmbeddedMediaPlayerComponent subStreamPlayer;
	private String path;

	public MainStreamFrame() {
		setSize(800,800);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);		
		setLocationRelativeTo(null);

		mainStreamPlayer = new EmbeddedMediaPlayerComponent();
		add(mainStreamPlayer);

		addWindowListener(new WindowListener() {
			
			public void windowOpened(WindowEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
			public void windowIconified(WindowEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
			public void windowDeiconified(WindowEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			 
			public void windowDeactivated(WindowEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
			public void windowClosing(WindowEvent arg0) {
				mainStreamPlayer.getMediaPlayer().stop();
                subStreamPlayer.getMediaPlayer().play();

                removeAll();
				dispose();
				
			}
			
			public void windowClosed(WindowEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
			public void windowActivated(WindowEvent arg0) {
				// TODO Auto-generated method stub
				
			}
		});
	}
	
	public void play(String path, EmbeddedMediaPlayerComponent subStreamPlayer){
		mainStreamPlayer.getMediaPlayer().stop();
		this.subStreamPlayer.getMediaPlayer().play();

		this.subStreamPlayer = subStreamPlayer;
		mainStreamPlayer.getMediaPlayer().playMedia(path);
	}
}
