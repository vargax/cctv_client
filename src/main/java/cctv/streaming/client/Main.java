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
	private static final String SERIAL_VERSION_PATH = "./config.bin";
	
	// -----------------------------------------------
	// Attributes
	// -----------------------------------------------
	private ArrayList<Camera> cameras;

	// -----------------------------------------------
	// Constructors
	// -----------------------------------------------
	public Main() {
		this.cameras = new ArrayList<Camera>();
	}
	
	public Main(ArrayList<Camera> cameras) {
		this.cameras = cameras;
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
            main.serializar();
		} else {
			System.out.println("No config file selected...");
			main = new Main();
		}

		Window window = new Window(main);
		window.setVisible(true);
	}

	// -----------------------------------------------
	// Persistence
	// -----------------------------------------------
	public void serializar() throws Exception {
		ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(SERIAL_VERSION_PATH));
		out.writeObject(this);
		out.close();
		System.out.printf("Serialized file saved to "+ SERIAL_VERSION_PATH);
	}

	public static Main deserializar(String path) throws Exception {
		ObjectInputStream in = new ObjectInputStream(new FileInputStream(path));
		Main main = (Main) in.readObject();
		in.close();
		return main;
	}
	
	public static Main configurar(String path) throws Exception {
		ArrayList<Camera> cameras = new ArrayList<Camera>();
		String line = null;
        int lineNumber = 0;
		
		BufferedReader bufferedReader = new BufferedReader(new FileReader(path));
		while ((line = bufferedReader.readLine()) != null) {
			lineNumber++;
            if (line.length() == 0 || line.startsWith("#"))
				continue;

			String[] streams = line.split(" ");
			if (streams.length != 2) {
				System.err.println("Skipping line "+lineNumber+" :: Invalid camera description! Expected mainstreamPath substreamPath");
			}
			String mainStream = streams[0];
			String subStream = streams[1];
			cameras.add(new Camera(mainStream, subStream));
		}
		
		bufferedReader.close();
		return new Main(cameras);
	}

	// -----------------------------------------------
	// Getters
	// -----------------------------------------------	
	public ArrayList<Camera> getCameras() {
		return cameras;
	}
}
