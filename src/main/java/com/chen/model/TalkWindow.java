package com.chen.model;

import com.chen.client.LinkInfo;

/**
 * 聊天详情
 * @author chen
 *
 */
public class TalkWindow {

	private LinkInfo linkInfo;
	private String me;
	private String target;
	public TalkWindow() {
		super();
	}
	public TalkWindow(LinkInfo linkInfo) {
		this.linkInfo = linkInfo;
	}
	
	
}
