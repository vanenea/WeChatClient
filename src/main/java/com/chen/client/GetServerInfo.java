package com.chen.client;

import java.io.IOException;
import java.io.InputStream;

import org.omg.CORBA.Request;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.chen.utils.IOUtils;
import com.chen.utils.ResponseCommand;

/**
 * 获取服务器信息的线程
 * @author chen
 *
 */
public class GetServerInfo implements Runnable {

	private static final Logger LOGGER = LoggerFactory.getLogger(GetServerInfo.class);
	private LinkInfo linkInfo;
	private InputStream in;
	
	public GetServerInfo(LinkInfo linkInfo) {
		this.linkInfo = linkInfo;
		try {
			in = this.linkInfo.getSocket().getInputStream();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void run() {
		try {
			int command = -1;
			while((command = in.read()) != -1){
				switch (command) {
				case ResponseCommand.LOGIN_RESPONSE:
					LOGGER.info("用户登录");
					String msg = IOUtils.readString(in);
					if("loginSuccess".equals(msg)) {
						LOGGER.info("登录成功");
					} else {
						LOGGER.info(msg);
					}
					break;
				default:
					break;
				}
			}
		} catch (Exception e) {
			
		}
	}

}