package com.chen.client;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.chen.client.utils.Config;

public class Client {

	private static final Logger LOGGER = LoggerFactory.getLogger(Client.class);
	
	public void start() {
		try {
			Socket socket = new Socket(Config.ip, Config.port);
		} catch (UnknownHostException e) {
			LOGGER.error("连接服务器异常", e);
		} catch (IOException e) {
			LOGGER.error("连接服务器异常", e);
		}
		
	}
}
