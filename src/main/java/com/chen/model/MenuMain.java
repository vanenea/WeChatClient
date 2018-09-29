package com.chen.model;

import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;

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
	private LinkInfo linkInfo;
	private List<String> allFriend;
	private List<TreeInfor> treeInfor = new ArrayList<TreeInfor>();
	
	public MenuMain() {
		this.getJFrame().setVisible(true);
	}
	
	/**
	 * 初始化参数
	 * @param linkInfo
	 */
	public void init(LinkInfo linkInfo) {
		this.linkInfo = linkInfo;
		this.linkInfo.setMenuMain(this);
	}
	
	/**
	 * 显示员工
	 */
	public void showAllfriend(List<String> allFriend) {
		this.allFriend = allFriend;
		jScrollPane.setViewportView(getJTree(initTree()));
	}
	
	private Component getJTree(DefaultMutableTreeNode node) {
		jTree = new JTree(node);
		jTree.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent e) {
				//双击打开聊天框
				if(e.getClickCount()==2) {
					DefaultMutableTreeNode selectedNode = (DefaultMutableTreeNode) jTree.getLastSelectedPathComponent();
					if(!"我的列表".equals(selectedNode.toString())) 
						openTalkWindow(selectedNode);
				}
			}
			
		});
		return jTree;
	}

	private DefaultMutableTreeNode initTree() {
		DefaultMutableTreeNode root = new DefaultMutableTreeNode("我的列表");
		allFriend.clear();
		for (String user : allFriend) {
			DefaultMutableTreeNode first = new DefaultMutableTreeNode(user);
			root.add(first);
			TreeInfor ti = new TreeInfor(user, first);
			treeInfor.add(ti);
		}
		return root;
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
					openTalkWindow(null);
				}
				
			});
		}
		return jButtonFind;
	}
	
	private void openTalkWindow(DefaultMutableTreeNode node) {
		//群聊
		if(node==null) {
			
		}
	}
	
	
}
