package ru.ex;

import org.apache.commons.lang3.StringUtils;
import org.junit.Test;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class XmlSortTest {
    @Test
    public void sort() throws Exception {
        File xmlFile = new File("src/test/resources/XmlSortTest.xml");
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
        Document doc = dBuilder.parse(xmlFile);


        NodeList messages = doc.getChildNodes().item(0).getChildNodes();
        ArrayList<Pair<Integer, String>> pairs = new ArrayList<Pair<Integer, String>>(10);
        for (int i = 0; i < 10; i++) {
            pairs.add(i, new Pair<Integer, String>(i, null));
        }
        for (int i = 0; i < messages.getLength(); i++) {
            try {
                Node node = messages.item(i);
                System.out.println(i + " : " + messages.item(i).getNodeName() + " - " + messages.item(i).getAttributes().getNamedItem("name"));
                if ("message" .equals(node.getNodeName())) {
                    pairs.get(i).setKey(i);
                    pairs.get(i).setValue(node.getAttributes().getNamedItem("name").toString());
                }
            } catch (Exception e) {
                System.out.println(e);
            }
        }
        System.out.println("==============");
        System.out.println(pairs);

        Collections.sort(pairs, new Comparator<Pair<Integer, String>>() {
            public int compare(Pair<Integer, String> o1, Pair<Integer, String> o2) {
                if (StringUtils.isBlank(o1.getValue()) || StringUtils.isBlank(o2.getValue())) {
                    return 0;
                }
                return o1.getValue().compareTo(o2.getValue());

            }
        });



        System.out.println("==============");
        System.out.println(pairs);
    }
}


class Pair<K, V> {
    K key;
    V value;

    public Pair(K key, V value) {
        this.key = key;
        this.value = value;
    }

    public K getKey() {
        return key;
    }

    public void setKey(K key) {
        this.key = key;
    }

    public V getValue() {
        return value;
    }

    public void setValue(V value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "Pair{" +
                "key=" + key +
                ", value=" + value +
                '}';
    }

}