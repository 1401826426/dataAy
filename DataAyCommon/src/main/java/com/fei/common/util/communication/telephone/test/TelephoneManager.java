//package com.fei.common.util.communication.telephone.test;
//
//import com.aliyuncs.DefaultAcsClient;
//import com.aliyuncs.IAcsClient;
//import com.aliyuncs.dysmsapi.model.v20170525.QuerySendDetailsRequest;
//import com.aliyuncs.dysmsapi.model.v20170525.QuerySendDetailsResponse;
//import com.aliyuncs.dysmsapi.model.v20170525.SendSmsRequest;
//import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
//import com.aliyuncs.exceptions.ClientException;
//import com.aliyuncs.exceptions.ServerException;
//import com.aliyuncs.http.MethodType;
//import com.aliyuncs.profile.DefaultProfile;
//import com.aliyuncs.profile.IClientProfile;
//import com.fei.common.util.communication.telephone.test.TemplateCoderProvider.TEMPLATE_CODER;
//
//public class TelephoneManager {
//
//	private long defaultConnectTimeout = 10000;
//
//	private long defaultReadTimeout = 10000;
//
//	private String product = "Dysmsapi";
//
//	private String domain = "dysmsapi.aliyuncs.com";
//
//	private String accessKeyId = null;
//
//	private String accessKeySecret = null;
//
//	private IClientProfile profile = null;
//
//	private String signName = "";
//
//	private String templateCode;
//	
//	private TemplateCoderProvider templateCoderProvider ; 
//
//	public TemplateCoderProvider getTemplateCoderProvider() {
//		return templateCoderProvider;
//	}
//
//	public void setTemplateCoderProvider(TemplateCoderProvider templateCoderProvider) {
//		this.templateCoderProvider = templateCoderProvider;
//	}
//
//	public IClientProfile getProfile() {
//		return profile;
//	}
//
//	public void setProfile(IClientProfile profile) {
//		this.profile = profile;
//	}
//
//	public String getSignName() {
//		return signName;
//	}
//
//	public void setSignName(String signName) {
//		this.signName = signName;
//	}
//
//	public String getTemplateCode() {
//		return templateCode;
//	}
//
//	public void setTemplateCode(String templateCode) {
//		this.templateCode = templateCode;
//	}
//
//	public long getDefaultConnectTimeout() {
//		return defaultConnectTimeout;
//	}
//
//	public void setDefaultConnectTimeout(long defaultConnectTimeout) {
//		this.defaultConnectTimeout = defaultConnectTimeout;
//	}
//
//	public long getDefaultReadTimeout() {
//		return defaultReadTimeout;
//	}
//
//	public void setDefaultReadTimeout(long defaultReadTimeout) {
//		this.defaultReadTimeout = defaultReadTimeout;
//	}
//
//	public String getProduct() {
//		return product;
//	}
//
//	public void setProduct(String product) {
//		this.product = product;
//	}
//
//	public String getDomain() {
//		return domain;
//	}
//
//	public void setDomain(String domain) {
//		this.domain = domain;
//	}
//
//	public String getAccessKeyId() {
//		return accessKeyId;
//	}
//
//	public void setAccessKeyId(String accessKeyId) {
//		this.accessKeyId = accessKeyId;
//	}
//
//	public String getAccessKeySecret() {
//		return accessKeySecret;
//	}
//
//	public void setAccessKeySecret(String accessKeySecret) {
//		this.accessKeySecret = accessKeySecret;
//	}
//
//	public void ini() {
//		System.setProperty("sun.net.client.defaultConnectTimeout", String.valueOf(defaultConnectTimeout));
//		System.setProperty("sun.net.client.defaultReadTimeout", String.valueOf(defaultReadTimeout));
//		try {
//			DefaultProfile.addEndpoint("cn-hangzhou", "cn-hangzhou", product, domain);
//			this.profile = DefaultProfile.getProfile("cn-hangzhou", accessKeyId, accessKeySecret);
//		} catch (ClientException e) {
//			e.printStackTrace();
//		}
//	}
//
//	public void sendRequest(SendSmsRequest request) {
//		IAcsClient acsClient = new DefaultAcsClient(profile);
//		SendSmsResponse sendSmsResponse;
//		try {
//			sendSmsResponse = acsClient.getAcsResponse(request);
//			if (sendSmsResponse.getCode() != null && sendSmsResponse.getCode().equals("OK")) {
//
//			}
//		} catch (ClientException e) {
//			e.printStackTrace();
//		}
//	}
//
//	public void sendSignCode(String telephone, int code) {
//		SendSmsRequest request = new SendSmsRequest();
//		request.setMethod(MethodType.POST);
//		request.setPhoneNumbers(telephone);
//		request.setSignName(signName);
//		request.setTemplateCode(templateCoderProvider.getCode(TEMPLATE_CODER.SIGN));
//		request.setTemplateParam("{\"code\":+" + code + "}");
//		sendRequest(request) ; 
//	}
//
//	public QuerySendDetailsResponse querySendDetails(QuerySendDetailsRequest request) {
//		IAcsClient acsClient = new DefaultAcsClient(profile);
//		QuerySendDetailsResponse querySendDetailsResponse;
//		try {
//			querySendDetailsResponse = acsClient.getAcsResponse(request);
//			if (querySendDetailsResponse.getCode() != null && querySendDetailsResponse.getCode().equals("OK")) {
//				querySendDetailsResponse.getSmsSendDetailDTOs();
//			}
//			return querySendDetailsResponse;
//		} catch (ServerException e) {
//			e.printStackTrace();
//		} catch (ClientException e) {
//			e.printStackTrace();
//		}
//		return null;
//	}
//
//}
