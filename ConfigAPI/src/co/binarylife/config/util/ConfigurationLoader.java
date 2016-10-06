package co.binarylife.config.util;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

import co.binarylife.config.Config;

public class ConfigurationLoader {
	
	public static Config loadConfig(String path, String name){
		File f = new File(path, name);
		
		if(!f.exists())
			return null;
		
		HashMap<String, Object> kvPairs = loadPairs(f);
		
		if(kvPairs == null)
			return null;

		ArrayList<String> keys = new ArrayList<>();
		
		for(String k : kvPairs.keySet()){
			keys.add(k);
		}
		
		ArrayList<Object> values = new ArrayList<>();
		
		for(Object v : kvPairs.values()){
			values.add(v);
		}
		
		
		return new Config(f, f.getPath(), f.getName(), kvPairs, keys, values);
	}
	
	private static HashMap<String, Object> loadPairs(File f){
		
		HashMap<String, Object> kvPairs = new HashMap<>();
		
		try{
			Scanner scanner = new Scanner(f);
			String line = null;
			
			while(scanner.hasNextLine()){
				line = scanner.nextLine();
				
				if(!line.startsWith("# ") && line.contains(": ")){
					String[] kv = line.split(": ");
					
					kvPairs.put(kv[0], kv[1]);
					
					
				}
			}
			scanner.close();
			
			return kvPairs;
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return null;
		
	}
	
}
