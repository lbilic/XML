package com.uns.ac.rs.xml.util;

import org.exist.xmldb.EXistResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.ResourceUtils;
import org.xmldb.api.base.*;
import org.xmldb.api.modules.XQueryService;

import javax.annotation.PostConstruct;
import java.io.IOException;

@Component
public class Sequencer extends IOStreamer {

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
}
