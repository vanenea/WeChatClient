package com.chen.client;

import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.chen.model.Alert;
import com.chen.model.Login;
import com.chen.model.MenuMain;
import com.chen.model.TalkWindow;
import com.chen.utils.Config;

public class LinkInfo {
	private static final Logger LOGGER = LoggerFactory.getLogger(LinkInfo.class);
	private Login login;
	private Alert alert;
	private Socket socket;
	private MenuMain menuMain;
	private String me;
	private List<TalkWindow> TalkWindow = new ArrayList<TalkWindow>();
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

	public String getMe() {
		return me;
	}

	public void setMe(String me) {
		this.me = me;
	}

	public List<TalkWindow> getTalkWindow() {
		return TalkWindow;
	}

	public void setTalkWindow(List<TalkWindow> talkWindow) {
		TalkWindow = talkWindow;
	}
	
	
}
