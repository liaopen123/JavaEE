package day08tomcatxml;

import java.io.File;
import java.util.List;

import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.junit.Test;

public class Dom4jDemo {

	private SAXReader reader;
	@Test
	public void f1() throws Exception{
		reader = new SAXReader();
		Document doc = reader.read(new File("C:\\Users\\Pony\\workspace\\day08tomcatxml\\xml\\web.xml"));
		Element rootElement = doc.getRootElement();
		List<Element> elements = rootElement.elements();
			for(Element e :elements){
//				String attribute = e.attributeValue("class");
//				System.out.println(attribute);
			String elementText = e.elementText("servlet-name");
			System.out.println(elementText);
			}
	}
	
	
	
}
