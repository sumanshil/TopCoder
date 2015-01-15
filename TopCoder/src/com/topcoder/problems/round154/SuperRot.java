package com.topcoder.problems.round154;

public class SuperRot {

	public String decoder(String message)
	{
		StringBuffer sb = new StringBuffer();
		for(int i = 0 ; i < message.length() ; i++)
		{
			char transformed = decode(message.charAt(i));
			sb.append(transformed);
		}
		return sb.toString();
	}
	
	
	public char decode(char c)
	{
		if (c >= 'A' && c <= 'M')
			return ((char)(c+13));
		else if (c >= 'N' && c <= 'Z')
			return ((char)(c-13));
		else if (c >= 'a' && c <= 'm')
			return ((char)(c+13));
		else if (c >= 'n' && c <= 'z')
			return ((char)(c-13));
		else if (c >= '0' && c <= '4')
			return ((char)(c+5));
		else if (c >= '5' && c <= '9')
			return ((char)(c-5));
		return c;

	}
	/**
	 * @param args
	 */
	public static void main(String[] args) 
	{
		String input = "Gur dhvpx oebja sbk whzcf bire n ynml qbt";
		String result = new SuperRot().decoder(input);
		System.out.println(result);

	}

}
