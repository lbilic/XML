package com.uns.ac.rs.xml.util;

import com.uns.ac.rs.xml.util.database.ConfigureConnection;
import com.uns.ac.rs.xml.util.database.DatabaseConnectionException;
import com.uns.ac.rs.xml.util.database.DatabaseResources;
import com.uns.ac.rs.xml.util.database.Mapper;
import org.exist.xmldb.EXistResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.ResourceUtils;
import org.xmldb.api.base.*;
import org.xmldb.api.modules.XQueryService;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

@Component
public class Sequencer {

    private long counter;

    @Autowired
    private ConfigureConnection connection;

    @Autowired
    private Mapper mapper;

    public Sequencer() {
        this.counter = 10L;
    }

    @PostConstruct
    public void init() {
        this.initialization();
    }

    private void initialization() {
        DatabaseResources resources = null;
        try {
            resources = connection.setupConnection(mapper.getCollection(), mapper.getDocument("doctors"));
            String pathToQuery = ResourceUtils.getFile(mapper.getQuery("counting")).getPath();
            XQueryService queryService = (XQueryService) resources.getCollection().getService("XQueryService", "1.0");
            queryService.setProperty("indent", "yes");
            String queryContent = this.loadFileContents(pathToQuery);
            CompiledExpression compiledXquery = queryService.compile(queryContent);
            ResourceSet result = queryService.execute(compiledXquery);
            ResourceIterator i = result.getIterator();
            Resource res = null;

            StringBuilder sb = new StringBuilder();

            while (i.hasMoreResources()) {

                try {
                    res = i.nextResource();
                    sb.append(res.getContent().toString());
                } finally {
                    if (res != null)
                        ((EXistResource) res).freeResources();

                }
            }
            String number = sb.toString();
            connection.freeResources(resources);
            this.counter = Long.parseLong(number);
        } catch (XMLDBException | IOException e) {
            connection.freeResources(resources);
            throw new DatabaseConnectionException("Access to database not possible!");
        } catch (IllegalAccessException | InstantiationException | ClassNotFoundException e) {
            throw new DatabaseConnectionException("Access to database not possible!");
        }
    }

    public long getId() {
        if (counter == -1) {
            this.initialization();
        }
        return ++counter;
    }

    String loadFileContents(String path) throws IOException {
        byte[] encoded = Files.readAllBytes(Paths.get(path));
        return new String(encoded, StandardCharsets.UTF_8);
    }
}
