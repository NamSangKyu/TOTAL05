import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class XMLTest1 {

	public static void main(String[] args) throws Exception {
		File file = new File("test.xml");
		DocumentBuilderFactory f = DocumentBuilderFactory.newDefaultInstance();
		DocumentBuilder dBulider = f.newDocumentBuilder();
		Document doc = dBulider.parse(file);
		doc.getDocumentElement().normalize();
		
		String root = doc.getDocumentElement().getNodeName();
		System.out.println("root : " + root);
		NodeList list = doc.getElementsByTagName(root); // company 선택
		
		Node temp = list.item(0);
		NodeList child = temp.getChildNodes();
		for(int i=0;i<child.getLength();i++) {
			Node n = child.item(i);
			if(n.getNodeType() == Node.ELEMENT_NODE) {
				System.out.println(n.getNodeName());
				System.out.println("no : " + n.getAttributes().getNamedItem("no").getTextContent());
				
				if(n.getNodeName().equals("staff")) {
					NodeList sChild = n.getChildNodes();
					for(int j=0;j<sChild.getLength();j++) {
						Node t = sChild.item(j);
						if(t.getNodeType() == Node.ELEMENT_NODE) {
							System.out.println(t.getNodeName() + " : "+t.getTextContent() + " " + t.getChildNodes().getLength());
						}
					}
					
				}
			}
		}
	
	}

}









