package cctv.streaming.client;

import java.io.Serializable;

public class Camera implements Serializable {
	
	private static final long serialVersionUID = 774648931592861756L;	

	private String mainstream;
	private String substream;
	
	public Camera(String mainstream, String substream) {
		this.mainstream = mainstream;
		this.substream = substream;
	}
	
	public String getMainstream() {
		return this.mainstream;
	}

	public String getSubstream() {
		return this.substream;
	}
}