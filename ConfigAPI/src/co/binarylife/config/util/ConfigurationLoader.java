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
//		try{
//			BufferedReader br = new BufferedReader(new FileReader(f));
//			
//			HashMap<String, Object> kvPairs = new HashMap<>();
//			String line = null;
//			
//			while((line = br.readLine()) != null){
//				if(line.startsWith("# "));
//				else if(line.contains(":")){
//					String[] kv = line.split(": ");
//					
//					kvPairs.put(kv[0], kv[1]);
//				}
//			}
//			
//			br.close();
//			
//			return kvPairs;
//				
//		}catch(Exception e){
//			e.printStackTrace();
//			return null;
//		}
		
	}
	
	public static int toInteger(Object o){
		return Integer.parseInt(o.toString());
	}
	
	public static double toDouble(Object o){
		return Double.parseDouble(o.toString());
	}
	
	public static boolean toBoolean(Object o){
		return Boolean.parseBoolean(o.toString());
	}
	
	public static long toLong(Object o){
		return Long.parseLong(o.toString());
	}
	
	public static byte toByte(Object o){
		return Byte.parseByte(o.toString());
	}
	
	public static float toFloat(Object o){
		return Float.parseFloat(o.toString());
	}
	
	public static boolean isInt(Object o){
		try{
			Integer.parseInt(o.toString());
			return true;
		}catch(Exception e){
			return false;
		}
	}
	
	public static boolean isDouble(Object o){
		try{
			Double.parseDouble(o.toString());
			return true;
		}catch(Exception e){
			return false;
		}
	}
	
	public static boolean isLong(Object o){
		try{
			Long.parseLong(o.toString());
			return true;
		}catch(Exception e){
			return false;
		}
	}
	
	public static boolean isBoolean(Object o){
		try{
			Boolean.parseBoolean(o.toString());
			return true;
		}catch(Exception e){
			return false;
		}
	}
	
	public static boolean isByte(Object o){
		try{
			Byte.parseByte(o.toString());
			return true;
		}catch(Exception e){
			return false;
		}
	}
	
	public static boolean isFloat(Object o){
		try{
			Float.parseFloat(o.toString());
			return true;
		}catch(Exception e){
			return false;
		}
	}

	
}
