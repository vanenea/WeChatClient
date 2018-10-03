package com.chen.model;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import com.chen.client.LinkInfo;

/**
 * 聊天详情
 * @author chen
 *
 */
public class TalkWindow {

	private JFrame jFrame;
	private JPanel jContentPane;
	private JPanel jPanel;
	private JTextField jTextField;
	private JButton jButton;
	private JScrollPane jScrollPane;
	private JTextArea jTextArea;
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
