package com.chen.model;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.InputStream;
import java.io.OutputStream;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.chen.client.GetServerInfo;
import com.chen.client.LinkInfo;
import com.chen.utils.IOUtils;
import com.chen.utils.RequestCommand;

/**
 * 登录框
 * @author Administrator
 *
 */
public class Login {

	private static final Logger LOGGER = LoggerFactory.getLogger(Login.class);
	
	private JFrame jFrame = null;
	private JPanel jPanel = null;
	private JLabel username = null;
	private JLabel password = null;
	private JTextField iusername = null;
	private JPasswordField ipassword = null;
	private JButton register;
	private JButton login;
	private InputStream in;
	private OutputStream out;
	private LinkInfo linkInfo;
	private GetServerInfo gsi;
	
	public Login(LinkInfo linkInfo) {
		this.setLinkInfo(linkInfo);
		this.getJFrame().setVisible(true);;
	}
	//关闭窗口
	public void closeJFrame() {
		if(jFrame!=null) {
			jFrame.dispose();
		}
	}
	public JFrame getJFrame() {
		if(jFrame == null) {
			jFrame = new JFrame("登录");
			jFrame.setSize(new Dimension(389, 274));
			jFrame.setResizable(false);
			jFrame.setContentPane(getJPanel());
			jFrame.addWindowListener(new WindowAdapter() {

				@Override
				public void windowClosed(WindowEvent e) {
					System.exit(0);
				}
				
			});
		}
		return jFrame;
	}
	private JPanel getJPanel() {
		if(jPanel == null) {
			jPanel = new JPanel();
			username = new JLabel();
			username.setBounds(new Rectangle(18, 53, 335, 38));
			username.setFont(new Font("Dialog", Font.BOLD, 14));
			username.setText(" 登 录 ");
			password = new JLabel(" 密 码 ");
			password.setBounds(new Rectangle(18, 107, 335, 38));
			password.setFont(new Font("Dialog", Font.BOLD, 14));
			jPanel.setLayout(null);
			jPanel.add(username);
			jPanel.add(password);
			jPanel.add(getUsername());
			jPanel.add(getPassword());
			jPanel.add(doButtonReg());
			jPanel.add(doButtonLogin());
		}
		return jPanel;
	}
	private Component doButtonLogin() {
		if(login == null) {
			login = new JButton();
			login.setBounds(new Rectangle(143, 162, 120, 41));
			login.setText("登录");
			login.addActionListener(new ActionListener() {
				
				public void actionPerformed(ActionEvent e) {
					
					//登录操作
					doLogin();
				}
			});
		}
		return login;
	}
	private Component doButtonReg() {
		if(register == null) {
			register = new JButton();
			register.setBounds(new Rectangle(295, 214, 82, 28));
			register.setText("注册");
			register.addActionListener(new ActionListener() {
				
				public void actionPerformed(ActionEvent e) {
					closeJFrame();
					
				}
			});
			
		}
		return register;
	}
	
	/**
	 * 登录操作
	 */
	private void doLogin() {
		try {
			this.linkInfo.initSocket();
			if(this.getLinkInfo() != null) {
				String u = iusername.getText();
				String p = String.valueOf(ipassword.getPassword());
				if(u!=null && !"".equals(u) && p!=null && !"".equals(p)) {
					if(gsi == null) {
						gsi = new GetServerInfo(linkInfo);
						new Thread(gsi).start();
					}
					if(in == null)
						in = linkInfo.getSocket().getInputStream();
					if(out == null)
						out = linkInfo.getSocket().getOutputStream();
					IOUtils.writeShort(out, RequestCommand.LOGIN);
					IOUtils.writeString(out, u);
					IOUtils.writeString(out, p);
				} else {
					LOGGER.info("用户名或密码为空");
				}
			}
		} catch (Exception e) {
			LOGGER.error("登录异常", e);
		}
	}
	private void doReg() {
		
	}
	private Component getPassword() {
		if(ipassword == null) {
			ipassword = new JPasswordField();
			ipassword.setBounds(new Rectangle(84, 111, 266, 33));
			ipassword.addKeyListener(new KeyAdapter() {

				@Override
				public void keyPressed(KeyEvent e) {
					if(e.getKeyCode()==KeyEvent.VK_ENTER) {
						doLogin();
					}
				}
				
			});
		}
		return ipassword;
	}
	private Component getUsername() {
		if(iusername == null) {
			iusername = new JTextField();
			iusername.setBounds(new Rectangle(84, 56, 266, 33));
			iusername.addKeyListener(new KeyAdapter() {

				@Override
				public void keyPressed(KeyEvent e) {
					if(e.getKeyCode()==KeyEvent.VK_ENTER) {
						doLogin();
					}
				}
				
			});
		}
		return iusername;
	}
	public LinkInfo getLinkInfo() {
		return linkInfo;
	}
	public void setLinkInfo(LinkInfo linkInfo) {
		this.linkInfo = linkInfo;
	}
	
	
}
