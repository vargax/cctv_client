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
public class PanelReproductor extends JPanel {

	private static final String NOHAYCAMARAS = "No hay camaras";
	
	private ArrayList<EmbeddedMediaPlayerComponent> mediaCameras;
	private EmbeddedMediaPlayerComponent mediaPlayer;
	private VideoPersonalFrame videopersonal;

	public PanelReproductor() {
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

	public void run(ArrayList<Camara> camaras) {
		removeAll();
		int tamano = camaras.size();
		System.out.println("tamaño de camaras "+ tamano);
		if (tamano > 0) {

			int grid=(int) Math.sqrt(tamano)+1;	
			setLayout(new GridLayout(grid,grid));

			mediaCameras = new ArrayList<EmbeddedMediaPlayerComponent>();

			for (int i = 0; i < camaras.size(); i++) {
				mediaPlayer = new EmbeddedMediaPlayerComponent();	
				mediaPlayer.setBounds(10,10,10,10);				
				mediaCameras.add(mediaPlayer);
				System.out.println("Agregando camara " + camaras.get(i).getPath());
				add(mediaPlayer);
			}

			for (int j = 0; j < mediaCameras.size(); j++) {			
				final EmbeddedMediaPlayerComponent camera = mediaCameras.get(j);
				Camara c= camaras.get(j);		
				final String ruta=c.getPath();
				System.out.println("Reproduciendo cámara "+ c.getPath());
				camera.getMediaPlayer().playMedia(ruta);
				camera.getMediaPlayer().setEnableMouseInputHandling(true);
				
				try {
					Thread.sleep(500);
				} catch (InterruptedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				camera.getVideoSurface().addMouseListener(new MouseListener() {

					public void mouseReleased(MouseEvent e) {}
					public void mousePressed(MouseEvent e) {}
					public void mouseExited(MouseEvent e) {}
					public void mouseEntered(MouseEvent e) {}

					public void mouseClicked(MouseEvent e) {
						if(videopersonal == null || !videopersonal.isVisible()){
							videopersonal = new VideoPersonalFrame();
							videopersonal.setVisible(true);
						}					
						videopersonal.toFront();
						videopersonal.setExtendedState(JFrame.NORMAL);
						videopersonal.reproducir(ruta);
					}
				});
				System.out.println("espere..");		
			}
			updateUI();
		} else {		
			JOptionPane.showMessageDialog(this, NOHAYCAMARAS);
		}
	}

	public void closeAll() {
		try {
			for(EmbeddedMediaPlayerComponent media : mediaCameras)
				media.getMediaPlayer().stop();
			removeAll();
		} catch(Exception e){
			e.printStackTrace();
		}
	}
}
