package com.uns.ac.rs.xml.util.database;

import com.uns.ac.rs.xml.util.database.ConfigureConnection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class SPARQLMapper {

    @Autowired
    private ConfigureConnection connection;

    public SPARQLMapper() {

    }

    @PostConstruct
    public void init() {
        this.prefix = connection.getDataEndpoint() + "/";
        FILTER_SELECTION = VOCABULAR + " SELECT DISTINCT  ?s \n" +
                "FROM <" + this.getPrefix() + "charts>\n" +
                "FROM <" + this.getPrefix() + "prescriptions>\n" +
                "FROM <" + this.getPrefix() + "reports>\n" +
                "FROM <" + this.getPrefix() + "referrals>\n" +
                "FROM <" + this.getPrefix() + "doctors>\n" +
                "FROM <" + this.getPrefix() + "choices>" +
                "WHERE { %1$s }";
        LINK_SELECTION = VOCABULAR +
                "SELECT DISTINCT  ?s\n" +
                "FROM <" + this.getPrefix() + "charts>\n" +
                "FROM <" + this.getPrefix() + "prescriptions>\n" +
                "FROM <" + this.getPrefix() + "reports>\n" +
                "FROM <" + this.getPrefix() + "referrals>\n" +
                "FROM <" + this.getPrefix() + "doctors>\n" +
                "FROM <" + this.getPrefix() + "choices>" +
                "WHERE {\n" +
                "  ?s ?p <%1$s>\n" +
                "  }";
        DELETE_TEMPLATE_GRAPH = VOCABULAR +
                "WITH <%1$s> DELETE { <%2$s> ?p ?o. } WHERE { <%2$s> ?p ?o.};\n" +
                "WITH <" + this.getPrefix() + "charts> DELETE { ?s ?p <%2$s>. } WHERE { ?s ?p <%2$s>.};\n" +
                "WITH <" + this.getPrefix() + "prescriptions> DELETE { ?s ?p <%2$s>. } WHERE { ?s ?p <%2$s>.};\n" +
                "WITH <" + this.getPrefix() + "reports> DELETE { ?s ?p <%2$s>. } WHERE { ?s ?p <%2$s>.};\n" +
                "WITH <" + this.getPrefix() + "referrals> DELETE { ?s ?p <%2$s>. } WHERE { ?s ?p <%2$s>.};\n" +
                "WITH <" + this.getPrefix() + "choices> DELETE { ?s ?p <%2$s>. } WHERE { ?s ?p <%2$s>.};\n" ;
    }

    private String prefix;

    private final String DELETE_DATABASE = "DROP ALL";

    private final String DELETE_GRAPH = "DROP GRAPH <%s>";

    private final String UPDATE_TEMPLATE = "INSERT DATA { %s }";

    private final String UPDATE_TEMPLATE_GRAPH = "INSERT DATA { GRAPH <%1$s> { %2$s } }";

    private final String SELECT_TEMPLATE_GRAPH = "SELECT * FROM <%1$s> WHERE { %2$s }";

    private final String SWITCH_TEMPLATE_GRAPH = "WITH <%1$s> DELETE { <%2$s> ?p ?o. } WHERE { <%2$s> ?p ?o.};"
            + "INSERT DATA { GRAPH <%1$s> { %3$s } }";

    private final String VOCABULAR = "PREFIX voc: <http://www.zis.rs/rdf/voc#>\n" +
            "PREFIX xsd: <http://www.w3.org/2001/XMLSchema#>\n";

    private final String SWITCH_FIELD = VOCABULAR + "WITH <%1$s> DELETE { <%2$s> %3$s ?o. } WHERE { <%2$s> %3$s ?o.};"
            + "INSERT DATA { GRAPH <%1$s> {  <%2$s> %3$s %4$s } }";

    private String FILTER_SELECTION;

    private String LINK_SELECTION;

    private String DELETE_TEMPLATE_GRAPH;

    public static final String NTRIPLES = "N-TRIPLES";

    public static final String RDF_XML = "RDF/XML";

    public static final String JSON = "JSON";

    public String enterData(String graphURI, String ntriples) {
        return String.format(UPDATE_TEMPLATE_GRAPH, graphURI, ntriples);
    }

    public String switchData(String graphURI, String nodeURI, String tripleti) {
        return String.format(SWITCH_TEMPLATE_GRAPH, graphURI, nodeURI, tripleti);
    }

    public String selectData(String graphURI, String sparqlUslovi) {
        return String.format(SELECT_TEMPLATE_GRAPH, graphURI, sparqlUslovi);
    }

    public String selectFilter(String sparqlUslovi) {
        return String.format(FILTER_SELECTION, sparqlUslovi);
    }

    public String switchField(String graphURI, String id, String field, String tripleti) {
        return String.format(SWITCH_FIELD, graphURI, id, field, tripleti);
    }

    public String selectLinks(String id) {
        return String.format(LINK_SELECTION, id);
    }

    public String deleteData(String graphURI, String nodeURI) {
        return String.format(DELETE_TEMPLATE_GRAPH, graphURI, nodeURI);
    }

    public String getPrefix() {
        if (this.prefix == null) {
            this.prefix = connection.getDataEndpoint() + "/";
        }
        return this.prefix;
    }
}
