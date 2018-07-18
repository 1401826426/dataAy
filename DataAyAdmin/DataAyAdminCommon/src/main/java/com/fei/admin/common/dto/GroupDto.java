package com.fei.admin.common.dto;

import java.util.List;

public class GroupDto {
	
	private int groupId ; 
	
	private String name ; 
	
	private String info ; 
	
	private List<ModulePermissionDto> permissions ;

	public int getGroupId() {
		return groupId;
	}

	public void setGroupId(int groupId) {
		this.groupId = groupId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getInfo() {
		return info;
	}

	public void setInfo(String info) {
		this.info = info;
	}

	public List<ModulePermissionDto> getPermissions() {
		return permissions;
	}

	public void setPermissions(List<ModulePermissionDto> permissions) {
		this.permissions = permissions;
	} 
	
	
}
