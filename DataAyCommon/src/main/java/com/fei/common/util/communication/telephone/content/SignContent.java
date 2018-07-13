package com.fei.common.util.communication.telephone.content;

import com.fei.common.util.communication.telephone.content.AbstractAliyunTelephoneContent;

public class SignContent extends AbstractAliyunTelephoneContent{
	
	public SignContent(int code){
		super(SMS_TEMPLATE_CODE.SIGN) ;
		addParam(SMS_TEMPLATE_PARAM.CODE.getName(),String.valueOf(code)) ; 
	}
	
}
