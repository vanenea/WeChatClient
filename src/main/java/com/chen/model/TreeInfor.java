package com.chen.model;

import javax.swing.tree.DefaultMutableTreeNode;

public class TreeInfor {

	private String user;
	private DefaultMutableTreeNode node;
	
	public TreeInfor() {
		super();
	}
	
	public TreeInfor(String user, DefaultMutableTreeNode node) {
		this.user = user;
		this.node = node;
	}

	public String getUser() {
		return user;
	}
	public void setUser(String user) {
		this.user = user;
	}
	public DefaultMutableTreeNode getNode() {
		return node;
	}
	public void setNode(DefaultMutableTreeNode node) {
		this.node = node;
	}
	@Override
	public String toString() {
		return "TreeInfor [user=" + user + ", node=" + node + "]";
	}
	
	
	
}
