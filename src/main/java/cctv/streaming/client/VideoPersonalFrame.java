package cctv.streaming.client;

import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JFrame;

import uk.co.caprica.vlcj.component.EmbeddedMediaPlayerComponent;

@SuppressWarnings("serial")
public class VideoPersonalFrame extends JFrame{

	
	private EmbeddedMediaPlayerComponent temp;
	private String ruta;

	public VideoPersonalFrame() {
		setSize(800,800);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);		
		setLocationRelativeTo(null);
		temp = new EmbeddedMediaPlayerComponent();		
		add(temp);
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
				// TODO Auto-generated method stub
				temp.getMediaPlayer().stop();
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
	
	public void reproducir(String media){
		temp.getMediaPlayer().stop();
		ruta = media;
		temp.getMediaPlayer().playMedia(ruta);
	}

	public void close() {
		super.dispose();
		dispose();
		
	}
	
	
}
