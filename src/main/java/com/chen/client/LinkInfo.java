package com.chen.client;

import java.net.Socket;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.chen.model.Alert;
import com.chen.model.Login;
import com.chen.model.MenuMain;
import com.chen.utils.Config;

public class LinkInfo {
	private static final Logger LOGGER = LoggerFactory.getLogger(LinkInfo.class);
	private Login login;
	private Alert alert;
	private Socket socket;
	private MenuMain menuMain;
	

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

	public Alert getAlert() {
		return alert;
	}

	public void setAlert(Alert alert) {
		this.alert = alert;
	}
	public MenuMain getMenuMain() {
		return menuMain;
	}

	public void setMenuMain(MenuMain menuMain) {
		this.menuMain = menuMain;
	}
}
