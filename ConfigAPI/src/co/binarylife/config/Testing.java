package co.binarylife.config;

import java.io.IOException;

public class Testing {
	
	public static void main(String[] args) {
		
		try {
			@SuppressWarnings("unused")
			Config c = new Config("C:/Users/Zach/Desktop", "me");
			c.set("yo", "yo");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
