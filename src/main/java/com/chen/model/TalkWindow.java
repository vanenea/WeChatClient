package com.chen.model;

import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.chen.client.LinkInfo;
import com.chen.utils.IOUtils;
import com.chen.utils.RequestCommand;

/**
 * 聊天详情框
 * @author chen
 *
 */
public class TalkWindow {

	private static final Logger LOGGER = LoggerFactory.getLogger(TalkWindow.class);
	
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
	private File sendFile;
	private File getFile;
	public TalkWindow() {
		super();
	}
	public TalkWindow(String target, LinkInfo linkInfo) {
		this.getjFrame().setVisible(true);
		this.setTarget(target);
		this.linkInfo = linkInfo;
		if("ALL".equals(target)) {
			jFrame.setTitle("群聊中...");
		} else {
			jFrame.setTitle("与 "+ target +" 聊天中...");
		}
	}
	
	public void showTalkWindow(String fromUser, String message) {
		this.jTextArea.setText(jTextArea.getText() + fromUser + "说:\n" + message + "\n");
		this. jScrollPane.getVerticalScrollBar().setValue(this.jScrollPane.getVerticalScrollBar().getMaximum());
	}
	
	private JFrame getjFrame() {
		if(jFrame == null) {
			jFrame = new JFrame();
			jFrame.setLayout(null);
			jFrame.setSize(new Dimension(361, 349));
			jFrame.setResizable(false);
			jFrame.setContentPane(getJContentPane());
			jFrame.addWindowListener(new WindowAdapter() {

				@Override
				public void windowClosing(WindowEvent e) {
					jFrame.dispose();
					removeTalkingWindow();
				}

				
			});
		}
		
		return jFrame;
	}
	
	
	private void removeTalkingWindow() {
		this.linkInfo.getTalkWindow().remove(this);
	}
	
	
	private Container getJContentPane() {
		if(jContentPane == null) {
			jContentPane = new JPanel();
			jContentPane.setLayout(null);
			jContentPane.add(getContentPane());
		}
		return jContentPane;
	}
	
	
	private Container getContentPane() {
		if(jPanel == null) {
			jPanel = new JPanel();
			jPanel.setLayout(null);
			jPanel.setBounds(new Rectangle(0, 1, 345, 310));
			jPanel.add(getTextField());
			jPanel.add(getButton());
			jPanel.setVisible(true);
			jPanel.add(getJScrollPanel());
		}
		return jPanel;
	}
	
	/**
	 * 文字区域
	 * @return
	 */
	private Component getJScrollPanel() {
		if(jScrollPane == null) {
			jScrollPane = new JScrollPane();
			jScrollPane.setBounds(new Rectangle(1, 1, 336, 194));
			jScrollPane.setViewportView(getTextArea());
		}
		return jScrollPane;
	}
	
	private Component getTextArea() {
		if(jTextArea == null) {
			jTextArea = new JTextArea();
			jTextArea.setEnabled(true);
			jTextArea.setEditable(false);
		}
		return jTextArea;
	}
	/**
	 * 发送文件按钮
	 * @return
	 */
	private Component getButton() {
		if(jButton == null) {
			jButton = new JButton();
			jButton.setBounds(new Rectangle(193, 254, 138, 49));
			jButton.setText("发送文件");
			jButton.addActionListener(new ActionListener() {
				
				public void actionPerformed(ActionEvent e) {
					sendFile();
				}
			});
		}
		return jButton;
	}
	
	/**
	 * 发送文件
	 */
	private void sendFile() {
		JFileChooser jFile = new JFileChooser();
		jFile.showOpenDialog(null);
		File file = jFile.getSelectedFile();
		if(file!=null) {
			this.sendFile = file;
			this.jTextArea.setText(this.jTextArea.getText()+"开始传送文件...\n");
			new Thread() {
				public void run() {
					FileInputStream fis = null;
					try {
						IOUtils.writeShort(linkInfo.getSocket().getOutputStream(), RequestCommand.SEND_FILE);
						IOUtils.writeString(linkInfo.getSocket().getOutputStream(), target);
						IOUtils.writeString(linkInfo.getSocket().getOutputStream(), linkInfo.getMe());
						IOUtils.writeString(linkInfo.getSocket().getOutputStream(), sendFile.getName());
						IOUtils.writeLong(linkInfo.getSocket().getOutputStream(), sendFile.length());
						byte[] buf = new byte[1024*10];
						int len = -1;
						fis = new FileInputStream(sendFile);
						while((len = fis.read(buf)) != -1) {
							linkInfo.getSocket().getOutputStream().write(buf, 0, len);
						}
					} catch (IOException e) {
						LOGGER.error("发送文件失败", e);
					} finally {
						if(fis != null) {
							try {
								fis.close();
							} catch (IOException e) {
								LOGGER.error("关闭文件失败", e);
							}
						}
					}
					
				}
			}.start();
		}
	}
	
	/**
	 * 文字域
	 * @return
	 */
	private Component getTextField() {
		if(jTextField == null) {
			jTextField = new JTextField();
			jTextField.setBounds(new Rectangle(5, 203, 333, 35));
			jTextField.addKeyListener(new KeyAdapter() {

				@Override
				public void keyPressed(KeyEvent e) {
					if(e.getKeyCode()==KeyEvent.VK_ENTER) {
						sendMessageToServer();
					}
				}
			});
			
		}
		return jTextField;
	}
	
	/**
	 * 发送消息到服务器
	 */
	private boolean sendMessageToServer() {
		List<String> allFriend = this.linkInfo.getMenuMain().getAllFriend();
		if(allFriend.size()<=1) {
			jTextArea.setText("当前无用户在线");
			return false;
		}
		try {
			//群聊
			OutputStream out = this.linkInfo.getSocket().getOutputStream();
			if("ALL".equals(this.target)) {
				IOUtils.writeShort(out, RequestCommand.SEND_TEXT);
				IOUtils.writeString(out, this.linkInfo.getMe());
				IOUtils.writeString(out, jTextField.getText());
				jTextArea.setText(jTextArea.getText()+"我说:\n"+jTextField.getText()+"\n");
				this.jScrollPane.getVerticalScrollBar().setValue(this.jScrollPane.getVerticalScrollBar().getMaximum());
				this.jTextField.setText("");
				return true;
			} 
			
			//发送消息到个人
			IOUtils.writeShort(out, RequestCommand.SEND_TEXT_TO_ONE);
			IOUtils.writeString(out, target);
			IOUtils.writeString(out, this.linkInfo.getMe());
			IOUtils.writeString(out, jTextField.getText());
			jTextArea.setText(jTextArea.getText()+"我说:\n"+jTextField.getText()+"\n");
			this.jScrollPane.getVerticalScrollBar().setValue(this.jScrollPane.getVerticalScrollBar().getMaximum());
			this.jTextField.setText("");
			return true;
		} catch (IOException e) {
			LOGGER.error("发送消息失败", e);
		}
		return false;
	}
	
	public void getFile() {
		FileOutputStream fos = null ;
		try {
			InputStream in = this.linkInfo.getSocket().getInputStream();
			OutputStream out = this.linkInfo.getSocket().getOutputStream();
			JFileChooser jf = new JFileChooser();
			JTextField jtf = getTextField(jf);
			jtf.setText(IOUtils.readString(in));
			jf.showSaveDialog(null);
			this.getFile = jf.getSelectedFile();
			long length = IOUtils.readLong(in);
			byte[] buf = new byte[(int)(1024>length?length:1024)];
			long sum = 0;
			fos = new FileOutputStream(getFile);
			while(true) {
				in.read(buf);
				fos.write(buf);
				sum += buf.length;
				if( sum>= length) {
					break;
				}
				buf = new byte[(int)(1024>(length-sum)?(length-sum):1024)];
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if(fos != null) {
				try {
					fos.close();
				} catch (IOException e) {
					LOGGER.error("关闭文件失败", e);
				}
			}
		}
	}
	
	private JTextField getTextField(Container con) {
		JTextField jtf = null;
		for (int i = 0; i < con.getComponentCount(); i++) {
			Component component = con.getComponent(i);
			if(component instanceof JTextField) {
				return (JTextField)component;
			}
			if(component instanceof Container) {
				jtf = getTextField((Container)component);
				if(jtf!=null) {
					return jtf;
					
				}
			}
		}
		return jtf;
	}
	public String getTarget() {
		return target;
	}
	
	public void setTarget(String target) {
		this.target = target;
	}
}
