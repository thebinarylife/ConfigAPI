package co.binarylife.configapi.section;

import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import co.binarylife.configapi.config.Config;
import co.binarylife.configapi.util.Checker;

public class SubSection implements Section
{
	private String name;
	private Config config;
	private Section parentSection;
	private String sectionPath;
	
	private int indentsNum;
	private String indents;
	
	private Map<String, Object> kvPairs;
	
	public SubSection(String name, int indentsNum, Section parentSection, Config config)
	{
		this.name = name;
		this.config = config;
		this.parentSection = parentSection;
		this.sectionPath = parentSection.getSectionPath() +"." + name;
		
		this.indentsNum = indentsNum;
		this.indents = "";
		
		this.kvPairs = new HashMap<String, Object>();
		
		for(int i = 0; i < indentsNum; i++)
		{
			indents += "  ";
		}
	}
	
	public SubSection(String name, int indentsNum, Section parentSection, Config config, Map<String, Object> kvPairs)
	{
		this(name, indentsNum, parentSection, config);
	}
	
	@Override
	public boolean contains(String key)
	{
		if(kvPairs.containsKey(key))
			return true;
		
		return false;
	}
	
	@Override
	public SubSection createSubSection(String name)
	{
		SubSection sec = new SubSection(name, indentsNum + 1, this, config);
		kvPairs.put(name, sec);
		return sec;
	}

	@Override
	public SubSection createSubSection(String name, Map<String, Object> keyValues)
	{
		SubSection sec = new SubSection(name, indentsNum + 1, this, config, keyValues);
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
		return config.getFileName();
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
	
	public Section getParentSection()
	{
		return parentSection;
	}
	
	public Map<String, Object> getPairs()
	{
		return kvPairs;
	}
	
	public String getPath()
	{
		return config.getPath();
	}
	
	public String getSectionPath()
	{
		return sectionPath;
	}
	
	public short getShort(String key)
	{
		if(!kvPairs.containsKey(key))
			return -1;
		
		Object v = kvPairs.get(key);
		
		return (Checker.isShort(v)) ? Checker.toShort(v) : -1;
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
	
	public void print(Writer writer)
	{
		try
		{
			for(String s : kvPairs.keySet())
			{
				if(kvPairs.get(s) instanceof SubSection)
				{
					writer.write(indents + s + ":");
					writer.write("\n");
					((SubSection)kvPairs.get(s)).print(writer);
					continue;
				}
				
				if(kvPairs.get(s) instanceof List<?>)
				{
					writer.write(indents + s + ":");
					writer.write("\n");
					for(Object o : (List<?>)kvPairs.get(s))
					{
						writer.write(indents + "  - " + o.toString());
						writer.write("\n");
					}
					
					continue;
				}
				
				writer.write(indents + s + ": " + kvPairs.get(s).toString());
				writer.write("\n");
			}
		}catch(IOException e)
		{
			e.printStackTrace();
		}
	}
	
	public void set(String key, Object value)
	{
		kvPairs.put(key, value);
	}
	
}
