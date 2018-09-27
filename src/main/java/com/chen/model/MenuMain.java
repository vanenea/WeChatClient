package com.chen.model;

import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTree;

import com.chen.client.LinkInfo;

/**
 * 主窗口-好友列表
 * @author Administrator
 *
 */
public class MenuMain {
	
	private JFrame jFrame;
	private JPanel jPanel;
	private JButton jButtonFind;
	private JScrollPane jScrollPane;
	private JTree jTree;
	private List<String> users = new ArrayList<String>();
	private LinkInfo linkInfo;
	
	public MenuMain() {
		this.getJFrame().setVisible(true);
	}
	
	public void init(LinkInfo linkInfo) {
		this.linkInfo = linkInfo;
		this.linkInfo.setMenuMain(this);
	}
	
	private JFrame getJFrame() {
		if(jFrame == null) {
			jFrame = new JFrame();
			jFrame.setSize(new Dimension(207, 441));
			jFrame.setTitle("WeChat");
			jFrame.setResizable(false);
			jFrame.setContentPane(getContentPane());
			jFrame.addWindowListener(new WindowAdapter() {

				@Override
				public void windowClosing(WindowEvent e) {
					System.exit(0);
				}
			});
		}
		return jFrame;
	}

	private Container getContentPane() {
		if(jPanel == null) {
			jPanel = new JPanel();
			jPanel.setLayout(null);
			jPanel.add(getJButtonFind());
			jPanel.add(getJScrollPane());
		}
		return jPanel;
	}

	private Component getJScrollPane() {
		if(jScrollPane == null) {
			jScrollPane = new JScrollPane();
			jScrollPane.setBounds(new Rectangle(2, 2, 200, 376));
		}
		return jScrollPane;
	}

	private Component getJButtonFind() {
		if(jButtonFind == null) {
			jButtonFind = new JButton();
			jButtonFind.setBounds(new Rectangle(1, 384, 199, 29));
			jButtonFind.setText("进入群聊");
			jButtonFind.addActionListener(new ActionListener() {
				
				public void actionPerformed(ActionEvent e) {
					openTalkWindow();
				}

			});
		}
		return jButtonFind;
	}
	
	private void openTalkWindow() {
		
	}
}
