package com.fei.common.util.generator;

import java.lang.reflect.Field;
import java.util.List;

import org.mybatis.generator.api.GeneratedXmlFile;
import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.PluginAdapter;
import org.mybatis.generator.api.dom.xml.Attribute;
import org.mybatis.generator.api.dom.xml.Document;
import org.mybatis.generator.api.dom.xml.Element;
import org.mybatis.generator.api.dom.xml.TextElement;
import org.mybatis.generator.api.dom.xml.XmlElement;
import org.mybatis.generator.config.MergeConstants;

public class SelfCodeAdapterPlugin extends PluginAdapter{

	@Override
	public boolean validate(List<String> warnings) {
		return true;
	}

	@Override
	public boolean sqlMapGenerated(GeneratedXmlFile sqlMap, IntrospectedTable introspectedTable) {
		try {
			Field field = sqlMap.getClass().getDeclaredField("document") ;
			field.setAccessible(true);
			Document doc = (Document) field.get(sqlMap) ; 
			XmlElement root = doc.getRootElement() ; 
			List<Element> elements = root.getElements() ;
			XmlElement selectByExampleElement = null ; 
			for(Element element:elements){
				if(element instanceof TextElement){
					continue ; 
				}
				XmlElement xmlElement = (XmlElement)element ;
				if(xmlElement.getAttributes() != null){
					for(Attribute attr:xmlElement.getAttributes()){
						if("id".equals(attr.getName()) && "selectByExample".equals(attr.getValue())){
							selectByExampleElement = xmlElement ; 
						}
					}					
				}
				String content = "<!-- " + MergeConstants.OLD_ELEMENT_TAGS[0] +" -->" ; 
				Element commentElement = new TextElement(content) ; 
				xmlElement.getElements().add(0,commentElement);
			}
			if(selectByExampleElement != null){
				XmlElement newXmlElement = cloneXmlElement(selectByExampleElement) ;
				List<Attribute> attrs = newXmlElement.getAttributes() ; 
				for(int i = 0;i < attrs.size();i++){
					Attribute attr = attrs.get(i) ; 
					if("id".equals(attr.getName())){
						attrs.set(i, new Attribute("id","selectOneByExample")) ; 
					}
				}
				String content = "limit 0,1" ; 
				Element commentElement = new TextElement(content) ; 
				newXmlElement.getElements().add(commentElement);
				elements.add(newXmlElement) ; 
			}
			
 		} catch (NoSuchFieldException | SecurityException | IllegalArgumentException | IllegalAccessException e) {
			e.printStackTrace();
		} 
		return true ; 
	}

	private XmlElement cloneXmlElement(XmlElement xmlElement) {
		XmlElement newElement = new XmlElement(xmlElement.getName()) ;
		List<Attribute> attributes = xmlElement.getAttributes() ; 
		for(Attribute attribute:attributes){
			Attribute newAttribute = new Attribute(attribute.getName(),attribute.getValue()) ;
			newElement.addAttribute(newAttribute) ; 
		} 
		for(Element element:xmlElement.getElements()){
			if(element instanceof XmlElement){
				newElement.addElement(cloneXmlElement((XmlElement)element)) ; 
				continue ; 
			}
			if(element instanceof TextElement){
				TextElement textElement = (TextElement)element ; 
				TextElement newTextElement = new TextElement(textElement.getContent()) ; 
				newElement.addElement(newTextElement) ;
			}
		}
		return newElement ; 
	}
	
	
	
	
	

}
