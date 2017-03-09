package ru.orcsoft;

import org.junit.Test;
import org.w3c.dom.Node;

import java.io.File;
import java.util.List;

public class XmlSortTest {
    @Test
    public void getSortedMessages() throws Exception {
        File xmlFile = new File("src/test/resources/XmlSortTest.xml");
        List<Node> nodes = XmlSort.getSortedMessages(xmlFile);
    }
}