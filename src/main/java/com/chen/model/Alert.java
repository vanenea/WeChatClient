package com.chen.model;

import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Rectangle;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * 提示框
 * @author chen
 *
 */
public class Alert {

	private JFrame jFrame = null;
	private JPanel jPanel = null;
	private JLabel jLabel = null;
	private JButton jButton = null;
	
	private JFrame getFrame() {
		if(jFrame == null) {
			jFrame = new JFrame();
			jFrame.setSize(new Dimension(377, 281));
			jFrame.setTitle("提示框");
			jFrame.setResizable(false);
			jFrame.setContentPane(getContentPane());
		}
		return jFrame;
	}

	private Container getContentPane() {
		if(jPanel == null) {
			jLabel = new JLabel();
			jLabel.setBounds(new Rectangle(15, 16, 333, 142));
			jLabel.setEnabled(false);
			jLabel.setText("");
			jPanel = new JPanel();
			jPanel.setLayout(null);
			jPanel.add(jLabel);
			jPanel.add(getButton());
		}
		return jPanel;
	}

	private Component getButton() {
		return null;
	}
}
