package com.fei.admin.dto;

import com.fei.admin.pojo.User;

public class UserDto {
	
	private long userId ; 
	
	private String userName ; 
	
	private int roleGroup ; 
	
	private String telephone ; 
	
	private String mail ; 
	
	private String pic ;

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public int getRoleGroup() {
		return roleGroup;
	}

	public void setRoleGroup(int roleGroup) {
		this.roleGroup = roleGroup;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public String getPic() {
		return pic;
	}

	public void setPic(String pic) {
		this.pic = pic;
	} 
	
	public void clone(User user){
		this.userId = user.getId() ; 
		this.mail = user.getMail() ; 
		this.pic = user.getPic() ; 
		this.roleGroup = user.getRoleGroup() ; 
		this.telephone = user.getTelephone() ; 
		this.mail = user.getMail() ; 
		this.userName = user.getName() ; 
	}
	
}
