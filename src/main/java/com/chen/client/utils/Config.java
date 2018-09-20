package com.chen.client.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Config {

	public static String ip;
	public static int port;
	
	static {
		Properties pt = new Properties();
		InputStream inStream = Config.class.getClassLoader().getResourceAsStream("data.properties");
		try {
			pt.load(inStream);
			ip = pt.getProperty("ip");
			port = Integer.parseInt(pt.getProperty("port"));
			
			
			
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
