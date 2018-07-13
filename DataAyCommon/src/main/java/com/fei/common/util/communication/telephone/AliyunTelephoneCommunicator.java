package com.fei.common.util.communication.telephone;

import java.util.List;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.dysmsapi.model.v20170525.QuerySendDetailsRequest;
import com.aliyuncs.dysmsapi.model.v20170525.QuerySendDetailsResponse;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsRequest;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.exceptions.ServerException;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.profile.IClientProfile;
import com.fei.common.Response;
import com.fei.common.util.communication.AbstractCommunicator;
import com.fei.common.util.communication.Content;

public class AliyunTelephoneCommunicator extends AbstractCommunicator implements TelephoneCommunicator{
	
	private long defaultConnectTimeout = 10000;

	private long defaultReadTimeout = 10000;

	private String product = "Dysmsapi";

	private String domain = "dysmsapi.aliyuncs.com";

	private String accessKeyId = null;

	private String accessKeySecret = null;

	private IClientProfile profile = null;

	private String signName = "";

	private String templateCode;
	

	public IClientProfile getProfile() {
		return profile;
	}

	public void setProfile(IClientProfile profile) {
		this.profile = profile;
	}

	public String getSignName() {
		return signName;
	}

	public void setSignName(String signName) {
		this.signName = signName;
	}

	public String getTemplateCode() {
		return templateCode;
	}

	public void setTemplateCode(String templateCode) {
		this.templateCode = templateCode;
	}

	public long getDefaultConnectTimeout() {
		return defaultConnectTimeout;
	}

	public void setDefaultConnectTimeout(long defaultConnectTimeout) {
		this.defaultConnectTimeout = defaultConnectTimeout;
	}

	public long getDefaultReadTimeout() {
		return defaultReadTimeout;
	}

	public void setDefaultReadTimeout(long defaultReadTimeout) {
		this.defaultReadTimeout = defaultReadTimeout;
	}

	public String getProduct() {
		return product;
	}

	public void setProduct(String product) {
		this.product = product;
	}

	public String getDomain() {
		return domain;
	}

	public void setDomain(String domain) {
		this.domain = domain;
	}

	public String getAccessKeyId() {
		return accessKeyId;
	}

	public void setAccessKeyId(String accessKeyId) {
		this.accessKeyId = accessKeyId;
	}

	public String getAccessKeySecret() {
		return accessKeySecret;
	}

	public void setAccessKeySecret(String accessKeySecret) {
		this.accessKeySecret = accessKeySecret;
	}

	public void ini() {
		System.setProperty("sun.net.client.defaultConnectTimeout", String.valueOf(defaultConnectTimeout));
		System.setProperty("sun.net.client.defaultReadTimeout", String.valueOf(defaultReadTimeout));
		try {
			DefaultProfile.addEndpoint("cn-hangzhou", "cn-hangzhou", product, domain);
			this.profile = DefaultProfile.getProfile("cn-hangzhou", accessKeyId, accessKeySecret);
		} catch (ClientException e) {
			e.printStackTrace();
		}
		super.ini();
	}

	public Response sendRequest(SendSmsRequest request) {
		IAcsClient acsClient = new DefaultAcsClient(profile);
		SendSmsResponse sendSmsResponse;
		try {
			sendSmsResponse = acsClient.getAcsResponse(request);
			if (sendSmsResponse.getCode() != null && sendSmsResponse.getCode().equals("OK")) {
				return Response.ok() ; 
			}else{
				return Response.fail(sendSmsResponse.getMessage()) ;
			}
		} catch (ClientException e) {
			e.printStackTrace();
			return Response.fail("异常") ; 
		}
	}

	public QuerySendDetailsResponse querySendDetails(QuerySendDetailsRequest request) {
		IAcsClient acsClient = new DefaultAcsClient(profile);
		QuerySendDetailsResponse querySendDetailsResponse;
		try {
			querySendDetailsResponse = acsClient.getAcsResponse(request);
			if (querySendDetailsResponse.getCode() != null && querySendDetailsResponse.getCode().equals("OK")) {
				querySendDetailsResponse.getSmsSendDetailDTOs();
			}
			return querySendDetailsResponse;
		} catch (ServerException e) {
			e.printStackTrace();
		} catch (ClientException e) {
			e.printStackTrace();
		}
		return null;
	}
 

	@Override
	public Response send(Content content) {
		SendSmsRequest request = new SendSmsRequest();
		request.setMethod(MethodType.POST);
		List<String> strs = content.getTos() ; 
		StringBuilder sb = new StringBuilder("") ; 
		for(String s:strs){
			sb.append(s+",") ; 
		}
		request.setPhoneNumbers(sb.toString());
		request.setSignName(signName);
		request.setTemplateCode(content.getTitle());
		request.setTemplateParam(content.getContent());
		return sendRequest(request) ; 
	}

}
