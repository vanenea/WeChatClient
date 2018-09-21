package com.chen.client;

import com.chen.model.Login;

/**
 * 客户端入口函数
 * @author Administrator
 *
 */
public class Talking {

	private LinkInfo linkInfo = new LinkInfo();
	
	public void showLogin() {
		this.linkInfo.setLogin(new Login(this.linkInfo));
	}
	
	public static void main(String[] args) {
		Talking talking = new Talking();
		talking.showLogin();
				
	}
}
