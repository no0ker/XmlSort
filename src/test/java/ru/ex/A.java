package ru.ex;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;

public class A {


    private void writeToFile(Document configDoc, String fileName) throws IOException {
        XMLOutputter outputter = new XMLOutputter();
        Format format = Format.getPrettyFormat();
        format.setExpandEmptyElements(true);
        outputter.setFormat(format);
        OutputStreamWriter outputStreamWriter = new OutputStreamWriter(new FileOutputStream(fileName), "UTF-8");
        outputter.output(configDoc, outputStreamWriter);
        outputStreamWriter.close();
    }
}