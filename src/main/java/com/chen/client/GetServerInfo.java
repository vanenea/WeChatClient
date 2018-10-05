package com.chen.client;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.chen.model.MenuMain;
import com.chen.model.TalkWindow;
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
	private String me;
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
			short command = -1;
			String fromUser;
			while((command = IOUtils.readShort(in)) != -1){
				switch (command) {
				case ResponseCommand.LOGIN_RESPONSE:
					LOGGER.info("用户登录");
					String msg = IOUtils.readString(in);
					if("loginSuccess".equals(msg)) {
						LOGGER.info("登录成功");
						this.me = this.linkInfo.getMe();
						MenuMain mm = new MenuMain();
						mm.init(this.linkInfo);
						this.linkInfo.setMenuMain(mm);
						this.linkInfo.getLogin().closeJFrame();
					} else {
						LOGGER.info(msg);
						this.linkInfo.getAlert().showAlert("<html><center><h1>登陆失败</h1>原因:"+ msg +"</center></html>");
					}
					break;
					
				case ResponseCommand.USER_LIST_RESPONSE :
					LOGGER.info("更新用户列表");
					List<String> allFriend = new ArrayList<String>();
					String[] users = IOUtils.readString(in).split(",");
					for (int i = 0; i < users.length; i++) {
						allFriend.add(users[i]);
					}
					MenuMain mm = this.linkInfo.getMenuMain();
					mm.showAllfriend(allFriend);
					break;
				
				case ResponseCommand.MESSAGE_TO_ALL_RESPONSE:
					LOGGER.info("群聊信息");
					fromUser = IOUtils.readString(in);
					String message = IOUtils.readString(in);
					TalkWindow tw = getTalkWindow("ALL");
					tw.showTalkWindow(fromUser, message);
					
				case ResponseCommand.MESSAGE_TO_ONE_RESPONSE:
					LOGGER.info("私聊信息");
					fromUser = IOUtils.readString(in);
					String mesg = IOUtils.readString(in);
					TalkWindow twa = getTalkWindow(fromUser);
					twa.showTalkWindow(fromUser, mesg);
					
				case ResponseCommand.FILE_TO_ONE_RESPONSE:
					LOGGER.info("接收文件");
					
				default:
					break;
				}
			}
		} catch (Exception e) {
			LOGGER.error("", e);
		}
	}

	/**
	 * 窗口
	 * @param string
	 * @return
	 */
	private TalkWindow getTalkWindow(String string) {
		TalkWindow tw = null;
		List<TalkWindow> talkWindow = this.linkInfo.getTalkWindow();
		for (TalkWindow talk : talkWindow) {
			if(talk.getTarget().equals(string)) {
				return talk;
			}
		}
		tw = new TalkWindow(string, linkInfo);
		this.linkInfo.getTalkWindow().add(tw);
		return tw;
	}

}
