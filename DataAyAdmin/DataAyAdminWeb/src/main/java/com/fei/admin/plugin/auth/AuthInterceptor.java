package com.fei.admin.plugin.auth;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactoryUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.fei.admin.dto.UserDto;
import com.fei.admin.mapper.GroupPermissionMapper;
import com.fei.admin.plugin.auth.module.ModuleEnum;
import com.fei.admin.plugin.auth.module.ModuleParser;
import com.fei.admin.plugin.auth.module.ModuleParserComposite;
import com.fei.admin.pojo.GroupPermission;
import com.fei.admin.pojo.GroupPermissionExample;
import com.fei.admin.pojo.GroupPermissionExample.Criteria;

@Component
public class AuthInterceptor extends HandlerInterceptorAdapter implements ApplicationContextAware{
	
	@Autowired
	private GroupPermissionMapper groupPermissionMapper ; 
	
	private ModuleParser parser ; 
	
//	private AuthExcludeMatcher excludeMatcher ; 
	
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
//		if(excludeMatcher.match(request, handler)){
//			return true ; 
//		}
		HttpSession session = request.getSession() ; 
		UserDto userDto = (UserDto) session.getAttribute(AuthConstants.USER_DTO_ATTRIBUTE) ;
		if(userDto == null){
			return false ; 
		}
		ModuleEnum moduleEnum = null  ; 
		if(parser.support(handler)){
			moduleEnum = parser.parseModule(request, handler) ; 
		}
		if(moduleEnum == null){
			return false ; 
		}
		int moduleId = moduleEnum.getModuleId() ; 
		int groupId = userDto.getRoleGroup() ; 
		GroupPermissionExample example = new GroupPermissionExample() ; 
		Criteria criteria = example.createCriteria() ;
		criteria.andGroupIdEqualTo(groupId).andModuleIdEqualTo(moduleId) ;
		List<GroupPermission> permissions = groupPermissionMapper.selectByExample(example) ; 
		if(permissions.size() > 0){
			GroupPermission groupPermission = permissions.get(0) ; 
			if(groupPermission.getPermission() == null){
				return false ; 
			}
			int auth = AuthConstants.METHOD_AUTH.get(request.getMethod()) ;
			if((groupPermission.getPermission()&auth) != 0){
				return true ; 
			}
		}
		return false ; 
	}

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		String[] beanNames  = BeanFactoryUtils.beanNamesForTypeIncludingAncestors(applicationContext,ModuleParser.class) ;
		ModuleParserComposite paserComposite = new ModuleParserComposite();
		for(String name:beanNames){
			ModuleParser moduleParser = (ModuleParser) applicationContext.getBean(name) ; 
			paserComposite.addParser(moduleParser) ; 
		}
		this.parser = paserComposite ; 
//		beanNames = BeanFactoryUtils.beanNamesForTypeIncludingAncestors(applicationContext,AuthExcludeMatcher.class) ;
//		AuthExcludeMatcherComposite authExcludeMatcherComposite = new AuthExcludeMatcherComposite() ; 
//		for(String name:beanNames){
//			AuthExcludeMatcher authExcludeMatcher = (AuthExcludeMatcher) applicationContext.getBean(name) ; 
//			authExcludeMatcherComposite.add(authExcludeMatcher) ; 
//		}
//		this.excludeMatcher = authExcludeMatcherComposite ; 
	}
	
	
	
}







