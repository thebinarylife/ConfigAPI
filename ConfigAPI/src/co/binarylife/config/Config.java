package co.binarylife.config;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;

import co.binarylife.config.util.Checker;

/**
 * Configuration object
 * 
 * @author TheBinaryLife
 *
 */
public class Config {
	private File config;
	private String name;
	private String path;
	
	private HashMap<String, Object> kvPair;
	private ArrayList<String> keys;
	private ArrayList<Object> values;
	
	/**
	 * Used for creating configurations
	 * 
	 * @param path Path to the file
	 * @param filename Name of the file
	 * @throws IOException
	 */
	public Config(String path, String filename) throws IOException{
		config = new File(path, filename + ".txt");
		
		config.createNewFile();
		
		this.path = path;
		this.name = filename + ".txt";
		
		this.kvPair = new HashMap<>();
		this.keys = new ArrayList<>();
		this.values = new ArrayList<>();
	}
	
	/**
	 * Used for loading configurations
	 * 
	 * @param file File that is being loaded as a config
	 * @param path Path to the file
	 * @param filename The name of the file being loaded
	 * @param kvPair Keys and values that are loaded
	 * @param keys All loaded keys
	 * @param values All loaded values
	 */
	public Config(File file, String path, String filename, HashMap<String, Object> kvPair, ArrayList<String> keys, ArrayList<Object> values){
		this.config = file; 

		this.path = path;
		this.name = filename;
		
		this.kvPair = kvPair;
		this.keys = keys;
		this.values = values;
	}
	
	/**
	 * Retrieve an Object from the configuration file
	 * 
	 * @param s key
	 * @return desired object
	 */
	public Object get(String s){
		if(!kvPair.containsKey(s))
			return false;
		
		Object v = kvPair.get(s);
		
		return (Checker.isBoolean(v)) ? Checker.toBoolean(v) : false;
	}
	
	/**
	 * Retrieve a boolean from the configuration file
	 * 
	 * @param s key
	 * @return desired boolean
	 */
	public boolean getBoolean(String s){
		if(!kvPair.containsKey(s))
			return false;
		
		Object v = kvPair.get(s); 
		
		return (Checker.isBoolean(v)) ? Checker.toBoolean(v) : false;
	}
	
	/**
	 * Retrieve a boolean from the configuration file
	 * 
	 * @param s Key in the configuration
	 * @return desired Byte
	 */
	public byte getByte(String s){
		if(!kvPair.containsKey(s))
			return -1;
		
		Object v = kvPair.get(s); 
		
		return (Checker.isByte(v)) ? Checker.toByte(v) : -1;
	}
	
	/**
	 * Retrieve a double from the configuration file
	 * 
	 * @param s key in the config
	 * @return desired Double
	 */
	public double getDouble(String s){
		if(!kvPair.containsKey(s))
			return -1;
		
		Object v = kvPair.get(s); 
		
		return (Checker.isDouble(v)) ? Checker.toDouble(v) : -1;
	}
	
	public File getFile(){
		return config;
	}
	
	/**
	 * Gets the name of the file
	 * @return the name file name
	 */
	public String getFileName(){
		return name;
	}
	
	/**
	 * Retrieve a String from the configuration file
	 * 
	 * @param s key in the config
	 * @return desired String
	 */
	public float getFloat(String s){
		if(!kvPair.containsKey(s))
			return -1;
		
		Object v = kvPair.get(s); 
		
		return (Checker.isFloat(v)) ? Checker.toFloat(v) : -1;
	}

	/**
	 * Retrieve a integer from the configuration file
	 * 
	 * @param s key in the config
	 * @return desired integer
	 */
	public int getInt(String s){
		if(!kvPair.containsKey(s))
			return -1;
		
		Object v = kvPair.get(s); 
		
		return (Checker.isInt(v)) ? Checker.toInteger(v) : -1;
	}
	
	/**
	 * Retrieve a long from the configuration file
	 * 
	 * @param s key in the config
	 * @return desired long
	 */
	public Long getLong(String s){
		if(!kvPair.containsKey(s))
			return -1L;
		
		Object v = kvPair.get(s); 
		
		return (Checker.isLong(v)) ? Checker.toLong(v) : -1;
	}
	
	/**
	 * Gets the path of the configuration
	 * 
	 * @return configuration file's path
	 */
	public String getPath(){
		return path;
	}
	
	/**
	 * Gets the Size of the file
	 * 
	 * @return the size of the file
	 */
	public long getSize(){
		return config.length();
	}
	
	/**
	 * Retrieve a String from the configuration file
	 * 
	 * @param s key in the config
	 * @return desired string
	 */
	public String getString(String s){
		if(!kvPair.containsKey(s))
			return null;
		
		return kvPair.get(s).toString();
	}
	
	/**
	 * Sets a key value pair in the configuration file
	 * 
	 * @param key identifier
	 * @param value the value being stored in the file
	 */
	public void set(String key, Object value){
		BufferedWriter pStream; 
		try {
			
			FileWriter fWriter = new FileWriter(config, true);
			pStream = new BufferedWriter(fWriter);
			if(!config.canRead()){
				pStream.close();
				return;
			}

			pStream.write(key + ": " + value);
			
			pStream.newLine();
			pStream.close();
			
			kvPair.put(key, value);
			
			keys.add(key);
			values.add(value);
			
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}
