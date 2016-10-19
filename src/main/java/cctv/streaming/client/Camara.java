package cctv.streaming.client;

import java.io.Serializable;

public class Camara implements Serializable {
	
	private static final long serialVersionUID = 774648931592861756L;	

	private String path;
	
	public Camara(String path) {
		this.path = path;
	}
	
	public String getPath() {
		return this.path;
	}
}