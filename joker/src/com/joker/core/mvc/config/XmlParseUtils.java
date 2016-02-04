package com.joker.core.mvc.config;

import java.io.File;
import java.util.Iterator;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import com.joker.core.mvc.utils.StringUtils;

public class XmlParseUtils {
	public static void parse(JokerConfig jokerConfig,String filePath){
		if(!StringUtils.isNull(filePath)){
			File file = new File(filePath);
			parse(jokerConfig,file);
		}
	}
	
	public static void parse(JokerConfig jokerConfig,File file){
		SAXReader reader = new SAXReader();
		try {
			Document document =reader.read(file) ;
			if(document!= null){
				Element element = document.getRootElement();
				Iterator<Element> iterator =element.elementIterator();
				while(iterator.hasNext()){
					parse(jokerConfig,iterator.next());
				}
			}
		} catch (DocumentException e) {
			e.printStackTrace();
		}
	}
	private static void parse(JokerConfig jokerConfig, Element element) {
		if(element!= null){
			String url = element.attributeValue("url");
			String classPath = element.attributeValue("class");
			if(url!= null && !"".equals(url)&& classPath!=null&&!"".equals(classPath)){
				jokerConfig.setUrlMapping(url, classPath);
			}
		}
		
	}

}
