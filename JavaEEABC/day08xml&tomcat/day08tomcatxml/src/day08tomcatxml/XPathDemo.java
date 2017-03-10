package day08tomcatxml;

import java.io.File;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.Node;
import org.dom4j.io.SAXReader;

public class XPathDemo {

	public static void main(String[] args) throws Exception {
		SAXReader	reader = new SAXReader();
		Document doc = reader.read(new File("/Users/liaopenghui/Desktop/JAVAEE/JavaEEABC/day08xml&tomcat/day08tomcatxml/xml/web.xml"));
		List<Element> selectNodes = doc.selectNodes("/web-app/servlet/servlet-name");
		System.out.println(selectNodes.get(0).getText());
		
		//getSingleNode
		Node selectSingleNode = doc.selectSingleNode("//servlet/servlet-name");
		System.out.println(selectSingleNode.getText());
	}

}
