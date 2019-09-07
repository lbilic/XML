package com.uns.ac.rs.xml.util;

import org.exist.xmldb.EXistResource;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.xmldb.api.DatabaseManager;
import org.xmldb.api.base.Collection;
import org.xmldb.api.base.Database;
import org.xmldb.api.base.XMLDBException;
import org.xmldb.api.modules.XMLResource;

import javax.xml.transform.OutputKeys;

@Configuration
@ConfigurationProperties(prefix = "conn")
public class ConfigureConnection {

    private String connectionUri = "xmldb:exist://%1$s:%2$s/exist/xmlrpc";

    private String host;
    private int port;
    private String user;
    private String password;
    private String driver;
    private String endpoint;
    private String dataset;
    private String query;
    private String update;
    private String data;


    public DatabaseResources setupConnection(String collectionId, String documentId) throws ClassNotFoundException,
            IllegalAccessException, InstantiationException, XMLDBException {
        System.out.println("[INFO] Loading driver class: " + this.getDriver());
        Class<?> cl = Class.forName(this.getDriver());

        Database database = (Database) cl.newInstance();
        database.setProperty("create-database", "true");

        DatabaseManager.registerDatabase(database);

        Collection collection = null;
        XMLResource resource = null;

        try {
            collection = DatabaseManager.getCollection(this.getUri() + collectionId);
            collection.setProperty(OutputKeys.INDENT, "yes");

            resource = (XMLResource) collection.getResource(documentId);

            if (resource == null) {
                this.freeResources(collection, null);
                throw new XMLDBException();
            } else {
                return new DatabaseResources(collection, resource);
            }

        } finally {
            this.freeResources(collection, resource);
        }
    }

    private void freeResources(Collection collection, XMLResource resource) {
        if (resource != null) {
            try {
                ((EXistResource) resource).freeResources();
            } catch (XMLDBException xe) {
                xe.printStackTrace();
            }
        }

        if (collection != null) {
            try {
                collection.close();
            } catch (XMLDBException xe) {
                xe.printStackTrace();
            }
        }
    }

    public void freeResources(DatabaseResources resourcei) {
        if (resourcei != null) {
            this.freeResources(resourcei.getCollection(), resourcei.getXmlResource());
        }
    }

    public String getConnectionUri() {
        return connectionUri;
    }

    public void setConnectionUri(String connectionUri) {
        this.connectionUri = connectionUri;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getDriver() {
        return driver;
    }

    public void setDriver(String driver) {
        this.driver = driver;
    }

    public String getEndpoint() {
        return endpoint;
    }

    public void setEndpoint(String endpoint) {
        this.endpoint = endpoint;
    }

    public String getDataset() {
        return dataset;
    }

    public void setDataset(String dataset) {
        this.dataset = dataset;
    }

    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
    }

    public String getUpdate() {
        return update;
    }

    public void setUpdate(String update) {
        this.update = update;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getQueryEndpoint() {
        return String.join("/", endpoint, dataset, query);
    }

    public String getUpdateEndpoint() {
        return String.join("/", endpoint, dataset, update);
    }

    public String getDataEndpoint() {
        return String.join("/", endpoint, dataset, data);
    }

    public String getUri() {
        return String.format(this.connectionUri, this.host, this.port);
    }
}
