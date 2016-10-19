package cctv.streaming.client;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

@SuppressWarnings("serial")
public class Interfaz extends JFrame {
	
	private Main main;
	private PanelReproductor panelReproductor;

	public Interfaz(Main main) {
		this.main=main;
		setLayout(new BorderLayout());
		setBackground(Color.WHITE);
		setSize(800,800);
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);		
		
		// Inicializacion de paneles				
		panelReproductor= new PanelReproductor();		
		
		// Registro de paneles				
		add(panelReproductor,BorderLayout.CENTER);

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
		panelReproductor.run(this.main.getCamaras());
	}

	public void onDispose() {		
		super.dispose();	
		panelReproductor.closeAll();	
		try {
			main.serializar();
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.exit(0);
	}
}
