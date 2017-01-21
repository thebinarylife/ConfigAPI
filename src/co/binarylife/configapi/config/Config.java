package co.binarylife.configapi.config;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import co.binarylife.configapi.section.Section;
import co.binarylife.configapi.section.SubSection;
import co.binarylife.configapi.util.Checker;

public class Config implements Section
{
	private String name;
	private File config;
	private String fileName;
	private String path;
	private String sectionPath;
	
	private int indentsNum;
	private String indents;
	
	private Map<String, Object> kvPairs;
	
	public Config(String name, String path)
	{
		
		// TODO CHANGE THIS CONSTRUCTOR
		this.fileName = name;
		this.name = name;
		this.path = path;
		this.sectionPath = path;
		
		this.indentsNum = 0;
		this.indents = "";
		
		if(!fileName.contains(".cnfg"))
			config = new File(path, fileName + ".cnfg");
		else
			config = new File(path, fileName);
			
		if(!config.exists())
		{
			try
			{
				config.createNewFile();
			}
			catch(IOException e)
			{
				e.printStackTrace();
			}
		}
		
		this.kvPairs = new HashMap<String, Object>();
	}
	
	public boolean contains(String s)
	{
		if(kvPairs.containsKey(s))
			return true;
		
		return false;
	}
	
	public SubSection createSubSection(String name, Map<String, Object> keyValues)
	{
		SubSection sec = new SubSection(name, indentsNum + 1, this, this, keyValues);
		kvPairs.put(name, sec);
		return sec;
	}
	
	public Object get(String key)
	{
		return kvPairs.get(key);
	}
	
	public boolean getBoolean(String key)
	{
		if(!kvPairs.containsKey(key))
			return false;
		
		Object v = kvPairs.get(key); 
		
		return (Checker.isBoolean(v)) ? Checker.toBoolean(v) : false;
	}
	
	public byte getByte(String key)
	{
		if(!kvPairs.containsKey(key))
			return -1;
		
		Object v = kvPairs.get(key);
		
		return (Checker.isByte(v)) ? Checker.toByte(v) : -1;
	}
	
	public char getCharacter(String key)
	{
		if(!kvPairs.containsKey(key))
			return 0;
		
		Object v = kvPairs.get(key);
		
		return (Checker.isChar(v)) ? Checker.toChar(v) : 0;
	}
	
	public Config getConfig()
	{
		return this;
	}
	
	public File getConfigFile()
	{
		return config;
	}
	
	public double getDouble(String key)
	{
		if(!kvPairs.containsKey(key))
			return -1;
		
		Object v = kvPairs.get(key);
		
		return (Checker.isDouble(v)) ? Checker.toDouble(v) : -1;
	}
	
	public float getFloat(String key)
	{
		if(!kvPairs.containsKey(key))
			return -1;
		
		Object v = kvPairs.get(key); 
		
		return (Checker.isFloat(v)) ? Checker.toFloat(v) : -1;
	}
	
	public int getIndentNumber()
	{
		return indentsNum;
	}
	
	public String getIndents()
	{
		return indents;
	}
	
	public int getInt(String key)
	{
		if(!kvPairs.containsKey(key))
			return -1;
		
		Object v = kvPairs.get(key); 
		
		return (Checker.isInt(v)) ? Checker.toInteger(v) : -1;
	}
	
	public List<?> getList(String key)
	{
		if(!kvPairs.containsKey(key))
			return null;
		
		Object v = kvPairs.get(key);
		
		return (v instanceof List<?>) ? (List<?>) v : null;
	}
	
	public long getLong(String key)
	{
		if(!kvPairs.containsKey(key))
			return -1L;
		
		Object v = kvPairs.get(key);
		
		return (Checker.isLong(v)) ? Checker.toLong(v) : -1;
	}
	
	public String getFileName()
	{
		return fileName;
	}
	
	public List<String> getKeys()
	{
		List<String> keys = new ArrayList<>();
		for(String s : kvPairs.keySet())
		{
			keys.add(s);
		}
		
		return keys;
	}
	
	public String getName()
	{
		return name;
	}
	
	public Map<String, Object> getPairs()
	{
		return kvPairs;
	}
	
	public Section getParentSection()
	{
		return null;
	}
	
	public String getPath()
	{
		return path;
	}
	
	public String getSectionPath()
	{
		return sectionPath;
	}
	
	public String getString(String key)
	{
		if(!kvPairs.containsKey(key))
			return null;
		
		return kvPairs.get(key).toString();
	}
	
	public SubSection getSubSection(String key)
	{
		if(kvPairs.get(key) instanceof SubSection)
			return (SubSection) kvPairs.get(key);
		
		return null;
	}
	
	public List<Object> getValues()
	{
		List<Object> values = new ArrayList<>();
		for(Object o : kvPairs.values())
		{
			values.add(o);
		}
		
		return values;
	}
	
	public void save()
	{
		String listPrefix = indents + "  - ";
		try
		{
			 Writer writer = new OutputStreamWriter(new FileOutputStream(config));
			if(!config.canRead())
			{
				writer.close();
				return;
			}
			
			for(String s : kvPairs.keySet())
			{
				if(kvPairs.get(s) instanceof SubSection)
				{
					writer.write(s +":");
					writer.write("\n");
					
					((SubSection)kvPairs.get(s)).print(writer);
					continue;
				}
				
				if(kvPairs.get(s) instanceof List<?>)
				{
					writer.write(s + ":");
					writer.write("\n");
					for(Object o : (List<?>)kvPairs.get(s))
					{
						writer.write(listPrefix + o.toString());
						writer.write("\n");
					}
					
					continue;
				}
				
				writer.write(s + ": " + kvPairs.get(s).toString());
				writer.write("\n");
			}
			writer.close();
		} catch (IOException e)
		{
			e.printStackTrace();
		}	
		
	}
	
	public void set(String key, Object value)
	{
		kvPairs.put(key, value);
	}

	@Override
	public SubSection createSubSection(String name)
	{
		SubSection sec = new SubSection(name, indentsNum + 1, this, this);
		kvPairs.put(name, sec);
		return sec;
	}

	@Override
	public short getShort(String key) {
		if(!kvPairs.containsKey(key))
			return -1;
		
		Object v = kvPairs.get(key);
		
		return (Checker.isShort(v)) ? Checker.toShort(v) : -1;
	}

}
