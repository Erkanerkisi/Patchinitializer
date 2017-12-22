package com.ama.ist.model;

public class User {
	
	
	private String userName;
	
	private String password;
	
	private String SvnPath;
	
	private String localPath;


	public String getLocalPath() {
		return localPath;
	}

	public void setLocalPath(String localPath) {
		this.localPath = localPath;
	}

	public String getSvnPath() {
		return SvnPath;
	}

	public void setSvnPath(String svnPath) {
		SvnPath = svnPath;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	

}
