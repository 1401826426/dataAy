package com.fei.common.util.encode;

public abstract class AbstractPasswordCoder extends AbstractCoder implements CoderNeedPassword{
	
	public abstract String getDefaultPasswordKey() ; 
	
	@Override
	public String encode(String content) {
		String password = System.getProperty(getDefaultPasswordKey()) ;
		if(password==null||"".equals(password.trim())){
			throw new RuntimeException("没有密码") ; 
		}
		return encode(content,password);
	}

	@Override
	public String decode(String content) {
		String password = System.getProperty(getDefaultPasswordKey()) ;
		if(password==null||"".equals(password.trim())){
			throw new RuntimeException("没有密码") ; 
		}
		return decode(content,password);
	}

}
