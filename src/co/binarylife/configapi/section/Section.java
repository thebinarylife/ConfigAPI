package co.binarylife.configapi.section;

import java.util.List;
import java.util.Map;

import co.binarylife.configapi.config.Config;

public interface Section
{
	
	public SubSection createSubSection(String name);
	
	
	public SubSection createSubSection(String name, Map<String, Object> keyValues);
	
	
	public boolean contains(String key);
	
	
	public Object get(String key);
	
	
	public boolean getBoolean(String key);
	
	
	public byte getByte(String key);
	
	
	public char getCharacter(String key);
	
	
	public Config getConfig();
	
	
	public double getDouble(String key);
	
	
	public String getFileName();
	
	
	public float getFloat(String key);
	
	
	public int getIndentNumber();
	
	
	public String getIndents();
	
	
	public int getInt(String key);
	
	
	public List<String> getKeys();
	
	
	public List<?> getList(String key);
	
	
	public long getLong(String key);
	
	
	public String getName();
	
	
	public Map<String, Object> getPairs();
	
	
	public String getPath();
	
	
	public Section getParentSection();
	
	
	public String getSectionPath();
	
	
	public short getShort(String key);
	
	
	public String getString(String key);
	
	
	public SubSection getSubSection(String path);
	
	
	public List<Object> getValues();
	
	
	public void set(String key, Object value);
	
}
