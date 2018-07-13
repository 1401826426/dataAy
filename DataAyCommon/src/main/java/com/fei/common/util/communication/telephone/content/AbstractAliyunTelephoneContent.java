package com.fei.common.util.communication.telephone.content;

import java.util.HashMap;
import java.util.Map;

import com.fei.common.util.communication.AbstractContent;
import com.fei.common.util.communication.telephone.SMS_TEMPLATE_CODE;
import com.fei.common.util.communication.telephone.TemplateCoderProvider;

public abstract class AbstractAliyunTelephoneContent extends AbstractContent {
	
	private Map<String,String> contentMap  ; 
	
	private SMS_TEMPLATE_CODE coder ;  
	
	public AbstractAliyunTelephoneContent(SMS_TEMPLATE_CODE coder) {
		super();
		this.coder = coder;
	}

	protected void addParam(String key,String value){
		if(contentMap == null){
			contentMap = new HashMap<String,String>() ; 
		}
		contentMap.put(key, value) ; 
	}

	@Override
	public String getContent() {
		if(this.contentMap != null){
			StringBuilder sb = new StringBuilder("{") ; 
			boolean flag = false ; 
			for(Map.Entry<String,String> entry:contentMap.entrySet()){
				if(flag){
					sb.append(",") ; 
				}
				sb.append(entry.getKey()+":"+entry.getValue()) ;
				flag = true ; 
			}
			sb.append("}") ;
			return sb.toString(); 
		}
		return null ; 
	}

	@Override
	public String getTitle() {
		return TemplateCoderProvider.getCode(coder.getName()) ;
	}

	
	
	
	
}






























