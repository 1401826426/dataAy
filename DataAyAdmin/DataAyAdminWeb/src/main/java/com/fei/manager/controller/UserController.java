package com.fei.manager.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttribute;

import com.fei.common.Response;
import com.fei.common.ResponseStatus;
import com.fei.manager.dto.UserDto;
import com.fei.manager.plugin.auth.AuthConstants;
import com.fei.manager.pojo.User;
import com.fei.manager.service.impl.UserService;

@RestController
@RequestMapping("/user")
public class UserController extends BaseController{
	
	@Autowired
	private UserService userService ; 
	
	@RequestMapping(value={"/login"},method=RequestMethod.PUT)
	public Response login(@RequestBody User user,HttpSession session){
		Response response = userService.login(user) ;
		if(response.getStatus() == ResponseStatus.OK.state()){
			User userInDb = (User)response.getData() ;
			UserDto userDto = new UserDto() ; 
			userDto.clone(userInDb);
			session.setAttribute(AuthConstants.USER_DTO_ATTRIBUTE, userDto);
		}
		return response ; 
	}
	
	@RequestMapping(value={"/register"},method=RequestMethod.POST)
	public Response register(@RequestBody User user){
		return userService.register(user) ;  
	}
	
	@RequestMapping(value={"/bind/telephone"},method=RequestMethod.PUT)
	public Response bindTelephone(String telephone,@SessionAttribute(USER_DTO_ATTRIBUTE) UserDto userDto){
		if(userDto == null){
			return Response.fail("还没登录,请登录") ; 
		}
//		return userService.bindTelephone(telephone) ;
		return null ; 
	}
	
	
	@RequestMapping(value={"/bind/mail"},method=RequestMethod.PUT)
	public Response bindMail(String mail,@SessionAttribute(USER_DTO_ATTRIBUTE) UserDto userDto){
//		return userService.bindMail(mail) ;   
		return null ; 
	}
	
	
}










