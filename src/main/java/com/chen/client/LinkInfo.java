package com.chen.client;

import java.net.Socket;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.chen.model.Login;
import com.chen.utils.Config;

public class LinkInfo {
	private static final Logger LOGGER = LoggerFactory.getLogger(LinkInfo.class);
	private Login login;
	
	private Socket socket;
	
	/**
	 * 初始化连接
	 * @return
	 */
	public boolean initSocket() {
		if(socket == null) {
			try {
				socket = new Socket(Config.ip, Config.port);
				return true;
			} catch (Exception e) {
				LOGGER.error("连接服务器异常", e);
			}
		}
		return false;
	}

	public Socket getSocket() {
		return socket;
	}

	public void setSocket(Socket socket) {
		this.socket = socket;
	}

	public Login getLogin() {
		return login;
	}

	public void setLogin(Login login) {
		this.login = login;
	}
}
