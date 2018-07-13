package com.fei.common.util.communication.telephone.test;
//package com.fei.common.util.communication.telephone.content;
//
//import java.util.List;
//import java.util.Map;
//
//import com.fei.common.util.communication.AbstractContent;
//
//public class AliyunTelephoneContentBuilder {
//	
//	private Map<String,String> contentMap ; 
//	
//	private AbstractContent content  ; 
//	
//	public static AliyunTelephoneContentBuilder to(List<String> tos){
//		AliyunTelephoneContentBuilder builder = new AliyunTelephoneContentBuilder() ;
//		builder.content = new AbstractContent() ; 
//		return builder ; 
//	}
//	
//	public AliyunTelephoneContentBuilder title(String title){
//		content.setTitle(title);
//		return this ; 
//	}
//	
//	public AliyunTelephoneContentBuilder addParam(String key,String value){
//		this.contentMap.put(key, value) ; 
//		return this ; 
//	}
//	
//	public AliyunTelephoneContentBuilder templateCoder(SMS_TEMPLATE_CODE coder){
//		return title(coder.getName()) ; 
//	}
//	
//	public AbstractContent build(){
//		if(this.contentMap != null){
//			StringBuilder sb = new StringBuilder("{") ; 
//			boolean flag = false ; 
//			for(Map.Entry<String,String> entry:contentMap.entrySet()){
//				if(flag){
//					sb.append(",") ; 
//				}
//				sb.append(entry.getKey()+":"+entry.getValue()) ;
//				flag = true ; 
//			}
//			sb.append("}") ;
//			content.setContent(sb.toString());
//		}
//		return content ; 
//
//	}
//	
//}
