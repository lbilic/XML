package com.uns.ac.rs.xml.util;

import com.sun.org.apache.xalan.internal.xsltc.trax.TransformerFactoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.ResourceUtils;

import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import java.io.*;

@Component
public class MetadataExtractor extends IOStreamer {

    private TransformerFactory transformerFactory;

    @Autowired
    private Mapper mapper;

    public MetadataExtractor() {
        transformerFactory = new TransformerFactoryImpl();
    }

    public ByteArrayInputStream extractMetadata(InputStream in, OutputStream out) {
        try {
            StreamSource transformSource = new StreamSource(new File(ResourceUtils
                    .getFile(mapper.getTransformation("metadata")).getPath()));
            Transformer grddlTransformer = transformerFactory.newTransformer(transformSource);
            grddlTransformer.setOutputProperty("{http://xml.apache.org/xalan}indent-amount", "2");
            grddlTransformer.setOutputProperty(OutputKeys.INDENT, "yes");
            StreamSource source = new StreamSource(in);
            StreamResult result = new StreamResult(out);
            grddlTransformer.transform(source, result);
            return new ByteArrayInputStream(((ByteArrayOutputStream) result.getOutputStream()).toByteArray());
        } catch (TransformerException | FileNotFoundException e) {
            throw new com.uns.ac.rs.xml.util.TransformerException("Error while processing data!");
        }

    }
}
