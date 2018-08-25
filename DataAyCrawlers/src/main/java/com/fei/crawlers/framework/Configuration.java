package com.fei.crawlers.framework;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import util.clazz.ClazzUtil;

public class Configuration {
	
	private List<GeccoConf> confs  ;   
	
	public static Configuration readFrom(InputStream is){
		try{
			DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance() ;
			Document doc = null ; 
			try {
				doc = builderFactory.newDocumentBuilder().parse(is) ;
			} catch (SAXException | IOException | ParserConfigurationException e) {
				e.printStackTrace();
			}
			Configuration configuration = new Configuration() ; 
			NodeList nl = doc.getChildNodes() ;
			for(int i = 0;i < nl.getLength();i++){
				Node node = nl.item(i) ; 
				String name = node.getNodeName() ;
				if("Configuration".equals(name)){
					List<GeccoConf> geccoConfs = parseConf(node);
					configuration.setConfs(geccoConfs);
				}
			}
			return configuration ; 
		}catch(Exception e){
			e.printStackTrace(); 
		}
		return null ; 
	}

	private static List<GeccoConf> parseConf(Node node) throws IntrospectionException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		NodeList nl = node.getChildNodes() ;
		List<GeccoConf> confs = new ArrayList<>() ; 
		for(int i = 0;i < nl.getLength();i++){
			Node nn = nl.item(i) ;
			if("GeccoConf".equals(nn.getNodeName())){
				GeccoConf conf = parseGeccoConf(nn) ;
				confs.add(conf) ; 
			}
		}
		return confs;
	}

	private static GeccoConf parseGeccoConf(Node nn) throws IntrospectionException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		NodeList nl = nn.getChildNodes() ; 
		GeccoConf geccoConf = new GeccoConf() ; 
		BeanInfo beanInfo = Introspector.getBeanInfo(GeccoConf.class) ;
		PropertyDescriptor[] pds = beanInfo.getPropertyDescriptors() ;
		Map<String,String> map = new HashMap<String,String>() ; 
		for(int i = 0;i < nl.getLength();i++){
			Node node = nl.item(i) ; 
			if(node instanceof Element){
				Element ele = (Element)node ; 
				map.put(ele.getNodeName(), ele.getTextContent()) ; 
			}
		}
		for(PropertyDescriptor pd:pds){
			String name = pd.getName() ;
			String value = map.get(name) ;
			if(value != null){
				Object obj = ClazzUtil.strToSimpleObject(pd.getPropertyType(), value) ;
				if(obj != null){					
					pd.getWriteMethod().invoke(geccoConf,obj); 
				}
			}
		}
		return geccoConf;
	}

	public List<GeccoConf> getConfs() {
		return confs;
	}

	public void setConfs(List<GeccoConf> confs) {
		this.confs = confs;
	}
	
	public static void main(String[] args) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, IntrospectionException {
		InputStream is = Configuration.class.getClassLoader().getResourceAsStream("geccoConf.xml") ; 
		Configuration configuration = readFrom(is) ; 
		System.err.println(configuration);
	}
	
}








