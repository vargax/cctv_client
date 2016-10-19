package cctv.streaming.client;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;

public class Main implements Serializable {
	// -----------------------------------------------
	// Constants
	// -----------------------------------------------
	private static final long serialVersionUID = 917005948119571357L;
	private static final String RUTASER = "./config.bin";
	
	// -----------------------------------------------
	// Attributes
	// -----------------------------------------------
	private ArrayList<Camara> camaras;

	// -----------------------------------------------
	// Constructors
	// -----------------------------------------------
	public Main() {
		this.camaras = new ArrayList<Camara>();
	}
	
	public Main(ArrayList<Camara> camaras) {
		this.camaras = camaras;
	}
	
	// -----------------------------------------------
	// Main
	// -----------------------------------------------
	public static void main(String[] args) throws Exception {
		Main main = null;
		
		if (args.length == 1 && args[0].endsWith(".bin")) {
			System.out.println("Loading configuration from a serialized file...");
			main = deserializar(args[0]);
		} else if (args.length == 1 && args[0].endsWith(".plain")) {
			System.out.println("Loading configuration from a plain file...");
			main = configurar(args[0]);
		} else {
			System.out.println("No config file selected...");
			main = new Main();
		}
		
		Interfaz interfaz = new Interfaz(main);
		interfaz.setVisible(true);
	}

	// -----------------------------------------------
	// Persistence
	// -----------------------------------------------
	public void serializar() throws Exception {
		ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(RUTASER));
		out.writeObject(this);
		out.close();
		System.out.printf("Serialized file saved to "+ RUTASER);
	}

	public static Main deserializar(String path) throws Exception {
		ObjectInputStream in = new ObjectInputStream(new FileInputStream(path));
		Main main = (Main) in.readObject();
		in.close();
		return main;
	}
	
	public static Main configurar(String path) throws Exception {
		ArrayList<Camara> camaras = new ArrayList<Camara>();
		String line = null;
		
		BufferedReader bufferedReader = new BufferedReader(new FileReader(path));
		while ((line = bufferedReader.readLine()) != null)
			camaras.add(new Camara(line));
		
		bufferedReader.close();
		return new Main(camaras);
	}

	// -----------------------------------------------
	// Getters
	// -----------------------------------------------	
	public ArrayList<Camara> getCamaras() {
		return camaras;
	}
}
