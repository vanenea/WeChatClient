package com.chen.client;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.chen.model.Alert;
import com.chen.model.Login;

/**
 * 客户端入口函数
 * @author Administrator
 *
 */
public class Talking {

	private static final Logger LOGGER = LoggerFactory.getLogger(Talking.class);
	private LinkInfo linkInfo = new LinkInfo();
	
	public void showLogin() {
		LOGGER.info("启动登录");
		this.linkInfo.setLogin(new Login(this.linkInfo));
	}
	
	public static void main(String[] args) {
		Talking talking = new Talking();
		talking.linkInfo.setAlert(new Alert());
		talking.showLogin();
				
	}
}
