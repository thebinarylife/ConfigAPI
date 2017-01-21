package co.binarylife.configapi.util;

public class Checker
{
  
	public static boolean isBoolean(Object o)
	{
		try
		{
			Boolean.parseBoolean(o.toString());
			return true;
		}
		catch(Exception e)
		{
			return false;
		}
	}
	
	public static boolean isByte(Object o)
	{
		try
		{
			Byte.parseByte(o.toString());
			return true;
		}
		catch(Exception e)
		{
			return false;
		}
	}
	
	public static boolean isChar(Object o)
	{
		return (o.toString().length() == 1) ? true : false;
	}
	
	public static boolean isDouble(Object o)
	{
		try
		{
			Double.parseDouble(o.toString());
			return true;
		}
		catch(Exception e)
		{
			return false;
		}
	}
	
	public static boolean isFloat(Object o)
	{
		try
		{
			Float.parseFloat(o.toString());
			return true;
		}
		catch(Exception e)
		{
			return false;
		}
	}
	
	public static boolean isInt(Object o)
	{
		try
		{
			Integer.parseInt(o.toString());
			return true;
		}
		catch(Exception e)
		{
			return false;
		}
	}
	
	public static boolean isLong(Object o)
	{
		try
		{
			Long.parseLong(o.toString());
			return true;
		}
		catch(Exception e)
		{
			return false;
		}
	}
	
	public static boolean isShort(Object o)
	{
		try
		{
			Short.parseShort(o.toString());
			return true;
		}
		catch(Exception e)
		{
			return false;
		}
	}
	
	public static boolean toBoolean(Object o)
	{
		return Boolean.parseBoolean(o.toString());
	}
	
	public static byte toByte(Object o)
	{
		return Byte.parseByte(o.toString());
	}
	
	public static char toChar(Object o)
	{
		return o.toString().charAt(0);
	}
	
	public static double toDouble(Object o)
	{
		return Double.parseDouble(o.toString());
	}
	
	public static float toFloat(Object o)
	{
		return Float.parseFloat(o.toString());
	}
	
	public static int toInteger(Object o)
	{
		return Integer.parseInt(o.toString());
	}
	
	public static long toLong(Object o)
	{
		return Long.parseLong(o.toString());
	}
	
	public static short toShort(Object o)
	{
		return Short.parseShort(o.toString());
	}
	
}