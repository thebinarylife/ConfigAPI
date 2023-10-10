package co.binarylife.configapi.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import co.binarylife.configapi.config.Config;
import co.binarylife.configapi.section.Section;

public class ConfigLoader
{
	
	public static Config loadConfig(Config config) {
		File f = config.getConfigFile();
		
		try
		{
			Scanner scanner = new Scanner(f);
			Section section = config;
			String line = "";
			String indents = "";
			String storedKey = "";
			boolean next = true;
			boolean nextLine = true;
			while(next)
			{
				if(nextLine)
					line = scanner.nextLine();
				else
					nextLine = true;
				if(!scanner.hasNextLine())
					next = false;
				
				if(line.startsWith(indents + "  - "))
				{
					List<String> list = new ArrayList<>();
					while(line.startsWith(indents + "  - "))
					{
						list.add(line.replace(indents + "  - ", ""));
						line = scanner.nextLine();
					}
					
					section.set(storedKey, list);
					storedKey = "";
					nextLine = false;
					continue;
				}
				
				if(line.startsWith(indents + "  "))
				{
					section = section.createSubSection(storedKey);
					indents = section.getIndents();
					storedKey = "";
					nextLine = false;
					continue;
				}
				
				if(line.startsWith(indents))
				{
					line = line.replaceFirst(indents, "");
					String[] splittedLine = line.split(": ");
					
					if(splittedLine.length == 1)
					{
						storedKey = splittedLine[0].replaceAll(":", "");
						continue;
					}
					String value = "";
					for(int i = 1; i < splittedLine.length; i++)
					{
						if(i == 1)
						{
							value += splittedLine[i];
							continue;
						}
						
						value += ": "+ splittedLine[i];
					}
					
					section.set(splittedLine[0], value);
					continue;
				}
				
				nextLine = false;
				next= true;
				section = section.getParentSection();
				indents = section.getIndents();
				while(!line.startsWith(indents))
				{
					section = section.getParentSection();
					indents = section.getIndents();
				}
				
			}
			
			scanner.close();
			
			return config;
		}
		catch (FileNotFoundException e)
		{
			e.printStackTrace();
			return null;
		} 
	}
	
	public static Config loadConfig(File f)
	{
		if(!f.exists())
			return null;
		
		return loadConfig(new Config(f.getName(), f.getParent()));
	}
	
	public static Config loadConfig(String file, String path)
	{
		return loadConfig(new File(path, file + ".cnfg"));
	}
	
}
