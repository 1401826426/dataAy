package com.fei.admin.controller;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fei.admin.common.dto.GroupDto;
import com.fei.admin.plugin.auth.module.Module;
import com.fei.admin.plugin.auth.module.ModuleEnum;
import com.fei.common.Response;

@RestController
@RequestMapping("/auth")
@Module(ModuleEnum.AUTH)
public class AuthController extends BaseController{
	
	@RequestMapping(path="/add",method=RequestMethod.POST)
	public Response add(int groupId,int moduleId,int permission){
		return Response.ok() ; 
	}
	
	@RequestMapping(path="/remove",method=RequestMethod.DELETE)
	public Response delete(int groupId,int moduleId,int permission){
		return Response.ok() ; 
	}
	
	@RequestMapping(path="/query/all",method=RequestMethod.GET)
	public Response queryAll(){
		return Response.ok() ; 
	}
	
	@RequestMapping(path="/group/add",method=RequestMethod.POST)
	public Response groupAdd(@RequestBody GroupDto groupDto){
		return Response.ok() ; 
	}
	
	@RequestMapping(path="/user/group/change",method=RequestMethod.PUT)
	public Response userGroupChange(long userId,int groupId){
		return Response.ok() ; 
	}
	
}






