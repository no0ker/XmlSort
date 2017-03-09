package ru.orcsoft;


import org.apache.commons.lang3.StringUtils;
import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class XmlSort {
    public static List<Node> getSortedMessages(File file) throws ParserConfigurationException, IOException, SAXException, TransformerException {
        ArrayList<Node> resultNodes = new ArrayList<Node>();

        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
        Document doc = dBuilder.parse(file);



        NodeList messages = doc.getChildNodes().item(0).getChildNodes();

        for (int i = 0; i < messages.getLength(); i++) {
                Node node = messages.item(i);
                if(StringUtils.isNotEmpty(getMessageName(node))){
                    resultNodes.add(node);
                    doc.getChildNodes().item(0).removeChild(node);
                }
            System.out.println(getMessageName(node));
        }

        Collections.sort(resultNodes, new Comparator<Node>() {
            public int compare(Node o1, Node o2) {
                String name1 = getMessageName(o1);
                String name2 = getMessageName(o2);
                return name1.compareTo(name2);
            }
        });

        for (int i = 0; i < resultNodes.size(); i++) {
            System.out.println(getMessageName(resultNodes.get(i)));
            doc.getChildNodes().item(0).appendChild(resultNodes.get(i));
        }

        writeFile(doc);

        return resultNodes;
    }

    public static String getMessageName(Node node){
        NamedNodeMap nameNodeMap;
        if((nameNodeMap = node.getAttributes()) == null){
            return "";
        }
        Node parameterNode;
        if((parameterNode = nameNodeMap.getNamedItem("name")) == null){
            return "";
        }

        return parameterNode.toString();
    }

    public static void writeFile(Document document) throws IOException, TransformerException {
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        transformer.setOutputProperty(OutputKeys.INDENT, "yes");
        transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");
        DOMSource source = new DOMSource(document);
        StreamResult result = new StreamResult(new File("C:\\Users\\Юля\\Desktop\\ttttttttttttttt.xml"));
        transformer.transform(source, result);
    }

}
