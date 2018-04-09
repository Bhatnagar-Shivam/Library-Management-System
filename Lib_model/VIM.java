package Lib_Model;

import java.io.Serializable;

public class VIM implements Serializable{

	private String name; 
	private byte[] data;
	
	public VIM(){
		name = null;
		data = null;
	}
	
	public VIM(String name, byte[] data){
		this.name = name;
		this.data = data;
	}
	
	public String getName(){
		return name;
	}
	
	public byte[] getData(){
		return data;
	}
	
}

