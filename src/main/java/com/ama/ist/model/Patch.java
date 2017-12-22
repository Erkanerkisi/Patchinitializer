package com.ama.ist.model;

public class Patch {
	
	private int winNum;
	private String winName;
	private String destination;
	private String svnPath;
	private String detail;
	private User user;
	private boolean checked;
	
	
	

	public boolean isChecked() {
		return checked;
	}
	public void setChecked(boolean checked) {
		this.checked = checked;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public String getDetail() {
		return detail;
	}
	public void setDetail(String detail) {
		this.detail = detail;
	}
	public String getSvnPath() {
		return svnPath;
	}
	public void setSvnPath(String svnPath) {
		this.svnPath = svnPath;
	}
	public String getDestination() {
		return destination;
	}
	public void setDestination(String destination) {
		this.destination = destination;
	}
	public int getWinNum() {
		return winNum;
	}
	public void setWinNum(int winNum) {
		this.winNum = winNum;
	}
	public String getWinName() {
		return winName;
	}
	public void setWinName(String winName) {
		this.winName = winName;
	}
	
	
	

}
