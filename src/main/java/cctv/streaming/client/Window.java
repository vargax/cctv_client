package cctv.streaming.client;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

@SuppressWarnings("serial")
public class Window extends JFrame {
	
	private Main main;
	private Mosaic mosaic;

	public Window(Main main) {
		this.main=main;
		setLayout(new BorderLayout());
		setBackground(Color.WHITE);
		setSize(800,800);
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);		
		
		// Inicializacion de paneles				
		mosaic = new Mosaic();
		
		// Registro de paneles				
		add(mosaic,BorderLayout.CENTER);

		addWindowListener(new WindowListener() {
			public void windowClosing(WindowEvent e) {
				onDispose();
			}
			public void windowActivated(WindowEvent e) {}
			public void windowClosed(WindowEvent e) {}
			public void windowDeactivated(WindowEvent e) {}
			public void windowDeiconified(WindowEvent e) {}
			public void windowIconified(WindowEvent e) {}
			public void windowOpened(WindowEvent e) {}
		});
		
		JOptionPane.showMessageDialog(this, "READY!");
		mosaic.run(this.main.getCameras());
	}

	public void onDispose() {
		mosaic.closeAll();
		super.dispose();
		System.exit(0);
	}
}
