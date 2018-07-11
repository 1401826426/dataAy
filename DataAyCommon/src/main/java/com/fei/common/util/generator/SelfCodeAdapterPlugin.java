package com.fei.common.util.generator;

import java.lang.reflect.Field;
import java.util.List;

import org.mybatis.generator.api.GeneratedXmlFile;
import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.PluginAdapter;
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
			for(Element element:elements){
				if(element instanceof TextElement){
					continue ; 
				}
				XmlElement xmlElement = (XmlElement)element ;
				String content = "<!-- " + MergeConstants.OLD_ELEMENT_TAGS[0] +" -->" ; 
				Element commentElement = new TextElement(content) ; 
				xmlElement.getElements().add(0,commentElement);
			}
			
 		} catch (NoSuchFieldException | SecurityException | IllegalArgumentException | IllegalAccessException e) {
			e.printStackTrace();
		} 
		return true ; 
	}
	
	
	
	
	

}
