package eu.nttdata.egovera.upscale.service.configuration;

public class Queries {
    public static final String GET_ABB_QUERY = "PREFIX eira: <http://data.europa.eu/dr8/eira/>\n" + //
                "PREFIX archimate: <http://data.europa.eu/dr8/archimate/>\n" + //
                "PREFIX skos: <http://www.w3.org/2004/02/skos/core#>\n" + //
                "SELECT DISTINCT ?name ?puri ?description ?policy ?view WHERE { \n" + //
                "    ?abb eira:concept eira:ArchitectureBuildingBlock;\n" + //
                "         eira:PURI ?puri ;\n" + //
                "         archimate:name ?name ;\n" + //
                "         skos:definition ?description ;\n" + //
                "         eira:eifLayer ?view .\n" + //
                "    \n" + //
                "    OPTIONAL { ?abb eira:businessDomain ?policy . }\n" + //
                "}\n" + //
                "";
    
    public static final String GET_DBC_QUERY = "PREFIX eira: <http://data.europa.eu/dr8/eira/>\n" + //
                "PREFIX dc: <http://purl.org/dc/elements/1.1/>\n" + //
                "SELECT DISTINCT ?puri ?name ?description ?policy ?view WHERE {\n" + //
                "    ?dbc eira:implementedBy \"http://data.europa.eu/dr8/DigitalBusinessCapability\"@en ;\n" + //
                "         dc:identifier ?puri ;\n" + //
                "         dc:title ?name ;\n" + //
                "         dc:description ?description ;\n" + //
                "         eira:eifLayer ?view .\n" + //
                "    \n" + //
                "    OPTIONAL { ?dbc eira:businessDomain ?policy . }\n" + //
                "}\n" + //
                "";
    
    public static final String GET_DPS_QUERY = "PREFIX eira: <http://data.europa.eu/dr8/eira/>\n" + //
                "PREFIX skos: <http://www.w3.org/2004/02/skos/core#>\n" + //
                "PREFIX dc: <http://purl.org/dc/elements/1.1/>\n" + //
                "SELECT DISTINCT ?puri ?name ?description ?policy ?view WHERE { \n" + //
                "    ?dps skos:broader ?dpsFiltering ;\n" + //
                "         dc:identifier ?puri ;\n" + //
                "         dc:title ?name ;\n" + //
                "         skos:definition ?description ;\n" + //
                "         eira:eifLayer ?view .\n" + //
                "    \n" + //
                "    OPTIONAL { ?dps eira:businessDomain ?policy . }\n" + //
                "    \n" + //
                "    FILTER ( str(?dpsFiltering) = \"http://data.europa.eu/dr8/DigitalPublicBusinessService\" )\n" + //
                "}\n" + //
                "";

    public static final String GET_ABB_TO_DBC_RELATIONSHIPS_QUERY = "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>\n" + //
                "PREFIX archimate: <http://data.europa.eu/dr8/archimate/>\n" + //
                "PREFIX eira: <http://data.europa.eu/dr8/eira/>\n" + //
                "SELECT DISTINCT ?abbPuri ?dbcPuri WHERE {\n" + //
                "    ?s archimate:hasSource ?abbPuri ;\n" + //
                "       archimate:hasTarget ?dbcPuri .\n" + //
                "       #eira:application eira:Upscale .\n" + //
                "    \n" + //
                "    ?abbPuri eira:concept eira:ArchitectureBuildingBlock .\n" + //
                "    ?dbcPuri eira:implementedBy \"http://data.europa.eu/dr8/DigitalBusinessCapability\"@en .\n" + //
                "}\n" + //
                "";

    public static final String GET_ABB_TO_DPS_RELATIONSHIPS_QUERY = "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>\n" + //
                "PREFIX archimate: <http://data.europa.eu/dr8/archimate/>\n" + //
                "PREFIX eira: <http://data.europa.eu/dr8/eira/>\n" + //
                "PREFIX skos: <http://www.w3.org/2004/02/skos/core#>\n" + //
                "SELECT DISTINCT ?abbPuri ?dpsPuri WHERE {\n" + //
                "    ?s archimate:hasTarget ?dpsPuri ;\n" + //
                "       archimate:hasSource ?abbPuri ;\n" + //
                "       eira:application eira:Upscale .\n" + //
                "    \n" + //
                "    ?abbPuri eira:concept eira:ArchitectureBuildingBlock .\n" + //
                "    ?dpsPuri skos:broader ?dpsFiltering .\n" + //
                "    FILTER (str(?dpsFiltering) = \"http://data.europa.eu/dr8/DigitalPublicBusinessService\") .\n" + //
                "}\n" + //
                "";

    public static final String GET_DBC_TO_DPS_RELATIONSHIPS_QUERY = "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>\n" + //
                "PREFIX archimate: <http://data.europa.eu/dr8/archimate/>\n" + //
                "PREFIX eira: <http://data.europa.eu/dr8/eira/>\n" + //
                "PREFIX skos: <http://www.w3.org/2004/02/skos/core#>\n" + //
                "PREFIX dc: <http://purl.org/dc/elements/1.1/>\n" + //
                "SELECT DISTINCT ?dbcPuri ?dpsPuri ?dpsName WHERE {\n" + //
                "    ?s archimate:hasTarget ?dbcPuri ;\n" + //
                "       archimate:hasSource ?dpsPuri ;\n" + //
                "       eira:application eira:Upscale .\n" + //
                "    \n" + //
                "    ?dbcPuri eira:implementedBy \"http://data.europa.eu/dr8/DigitalBusinessCapability\"@en .\n" + //
                "    ?dpsPuri skos:broader ?dpsFiltering ;\n" + //
                "             dc:title ?dpsName .\n" + //
                "    FILTER (str(?dpsFiltering) = \"http://data.europa.eu/dr8/DigitalPublicBusinessService\") .\n" + //
                "}\n" + //
                "";
}
