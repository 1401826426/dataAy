package com.fei.common.util.communication.telephone.content;

import com.fei.common.util.communication.telephone.SMS_TEMPLATE_CODE;
import com.fei.common.util.communication.telephone.SMS_TEMPLATE_PARAM;

public class ChangePasswordContent extends AbstractAliyunTelephoneContent{

	public ChangePasswordContent(String password) {
		super(SMS_TEMPLATE_CODE.CHANGE_PASSWORD);
		addParam(SMS_TEMPLATE_PARAM.PASSWORD.getName(),password);
	}
	
}
