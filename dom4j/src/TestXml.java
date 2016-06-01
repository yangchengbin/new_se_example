import org.dom4j.*;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

public class TestXml {
    public static void main(String[] args) throws IOException, DocumentException {
        String xmlPath = TestXml.class.getClassLoader().getResource("users.xml").getPath();
        Document document = parse(xmlPath);
        List list = document.selectNodes("//*");
        Element node = (Element) document.selectSingleNode("//id[@id='xxx' and @name='idMame']");
        System.out.println(node.getText());
    }

    public static Document parse(String xmlPath) throws DocumentException {
        SAXReader reader = new SAXReader();
        Document document = reader.read(xmlPath);
        return document;
    }

    public static void insert(Document document) {

    }

    public static void bar(Document document) throws DocumentException {

        Element root = document.getRootElement();
        Element element;
        for (Iterator i = root.elementIterator(); i.hasNext(); ) {
            element = (Element) i.next();
        }

        Element foo;
        for (Iterator i = root.elementIterator("user"); i.hasNext(); ) {
            foo = (Element) i.next();
        }

        Attribute attribute;
        for (Iterator i = root.attributeIterator(); i.hasNext(); ) {
            attribute = (Attribute) i.next();
        }
    }

    public static void treeWalk(Element element) {
        for (int i = 0, size = element.nodeCount(); i < size; i++) {
            Node node = element.node(i);
            String nodeTypeName = node.getNodeTypeName();
            String nodeValue = node.getText();
            System.out.println(nodeTypeName + nodeValue);
            if (node instanceof Element) {
                System.out.print(node.getName() + "    ");
                treeWalk((Element) node);
            } else {
                if (!"".equals(nodeValue.trim())) {
                    System.out.println(nodeValue);
                }
            }
        }
    }

    public static Document createDocument() {
        Document document = DocumentHelper.createDocument();
        Element root = document.addElement("root");
        root.addElement("author").addAttribute("name", "James").addAttribute("location", "UK").addText("李月新");
        root.addElement("author").addAttribute("name", "Bob").addAttribute("location", "US").addText("hello");
        return document;
    }

    public static void write(Document document, String xmlPath) throws IOException {
        FileOutputStream fos = new FileOutputStream(xmlPath);
        OutputFormat format = OutputFormat.createPrettyPrint();
        XMLWriter writer = new XMLWriter(fos, format);
        writer.write(document);
    }
}
