package com.fei.common.util.communication.telephone.content;

import com.fei.common.util.communication.telephone.SMS_TEMPLATE_CODE;
import com.fei.common.util.communication.telephone.SMS_TEMPLATE_PARAM;

public class SubscriptionNoticeContent extends AbstractAliyunTelephoneContent{

	public SubscriptionNoticeContent(String product) {
		super(SMS_TEMPLATE_CODE.SUBSCRIPTION_NOTICE);
		addParam(SMS_TEMPLATE_PARAM.PRODUCT.getName(),product);
	}

}
