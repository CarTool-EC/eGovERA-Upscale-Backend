package eu.nttdata.egovera.upscale.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;

import eu.nttdata.egovera.upscale.service.ResourcesService;
import eu.nttdata.egovera.upscale.service.configuration.ArchitectureBuildingBlock;
import eu.nttdata.egovera.upscale.service.configuration.DigitalBusinessCapability;
import eu.nttdata.egovera.upscale.service.configuration.DigitalPublicService;
import eu.nttdata.egovera.upscale.service.configuration.Queries;

@Service
public class ResourcesServiceImpl implements ResourcesService {
    @Autowired
    private Environment mEnv;

    @Autowired
    private ObjectMapper mMapper;

    @Override
    public List<ArchitectureBuildingBlock> getABB() throws Exception {
        List<ArchitectureBuildingBlock> lAbbList = new ArrayList<ArchitectureBuildingBlock>();

        ArrayNode lQueryResults = this.executeQuery(Queries.GET_ABB_QUERY);
        lQueryResults.forEach(lNode -> {
            ArchitectureBuildingBlock lAbb = new ArchitectureBuildingBlock(
                lNode.get("puri").get("value").asText(), 
                lNode.get("name").get("value").asText(), 
                lNode.get("description").get("value").asText(), 
                this.resolvePolicy(lNode), 
                this.resolveView(lNode));

            lAbbList.add(lAbb);
        });

        ArrayNode lDbcRelationshipsQueryResults = this.executeQuery(Queries.GET_ABB_TO_DBC_RELATIONSHIPS_QUERY);
        lDbcRelationshipsQueryResults.forEach(lNode -> {
            lAbbList.forEach(lAbb -> {
                lAbb.addDbcRelationships(
                    lNode.get("abbPuri").get("value").asText(), 
                    lNode.get("dbcPuri").get("value").asText());
            });
        });

        ArrayNode lDpsRelationshipsQueryResults = this.executeQuery(Queries.GET_ABB_TO_DPS_RELATIONSHIPS_QUERY);
        lDpsRelationshipsQueryResults.forEach(lNode -> {
            lAbbList.forEach(lAbb -> {
                lAbb.addDpsRelationships(
                    lNode.get("abbPuri").get("value").asText(), 
                    lNode.get("dpsPuri").get("value").asText());
            });
        });

        return lAbbList;
    }

    @Override
    public List<DigitalBusinessCapability> getDBC() throws Exception {
        List<DigitalBusinessCapability> lDbcList = new ArrayList<DigitalBusinessCapability>();

        ArrayNode lQueryResults = this.executeQuery(Queries.GET_DBC_QUERY);
        lQueryResults.forEach(lNode -> {
            DigitalBusinessCapability lDbc = new DigitalBusinessCapability(
                lNode.get("puri").get("value").asText(), 
                lNode.get("name").get("value").asText(), 
                lNode.get("description").get("value").asText(),
                this.resolvePolicy(lNode),
                this.resolveView(lNode));

            lDbcList.add(lDbc);
        });

        ArrayNode lRelationshipsQueryResults = this.executeQuery(Queries.GET_DBC_TO_DPS_RELATIONSHIPS_QUERY);
        lRelationshipsQueryResults.forEach(lNode -> {
            lDbcList.forEach(lDbc -> {
                lDbc.addRelationships(
                    lNode.get("dbcPuri").get("value").asText(), 
                    lNode.get("dpsPuri").get("value").asText());
            });
        });

        return lDbcList;
    }

    @Override
    public List<DigitalPublicService> getDPS() throws Exception {
        List<DigitalPublicService> lDpsList = new ArrayList<DigitalPublicService>();

        ArrayNode lQueryResults = this.executeQuery(Queries.GET_DPS_QUERY);
        lQueryResults.forEach(lNode -> {
            DigitalPublicService lDps = new DigitalPublicService(
                lNode.get("puri").get("value").asText(), 
                lNode.get("name").get("value").asText(), 
                lNode.get("description").get("value").asText(), 
                this.resolvePolicy(lNode),
                this.resolveView(lNode));

            lDpsList.add(lDps);
        });

        ArrayNode lRelationshipsQueryResults = this.executeQuery(Queries.GET_DBC_TO_DPS_RELATIONSHIPS_QUERY);
        lRelationshipsQueryResults.forEach(lNode -> {
            lDpsList.forEach(lDps -> {
                lDps.addRelationships(
                    lNode.get("dpsPuri").get("value").asText(), 
                    lNode.get("dbcPuri").get("value").asText());
            });
        });

        return lDpsList;
    }

    private ArrayNode executeQuery(String pQuery) throws Exception {
        String lRequestUrl = mEnv.getProperty("egovera.upscale.graphdb.url") + mEnv.getProperty("egovera.upscale.graphdb.sparql.endpoint");

        System.out.println("Requesting to URL: " + lRequestUrl);

        HttpHeaders lRequestHeaders = new HttpHeaders();
        lRequestHeaders.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        lRequestHeaders.setBasicAuth(mEnv.getProperty("egovera.upscale.graphdb.username"), mEnv.getProperty("egovera.upscale.graphdb.password"));

        MultiValueMap<String, String> lRequestBody = new LinkedMultiValueMap<>();
        lRequestBody.add("query", pQuery);

        RestTemplate lRestTemplate = new RestTemplate();
        HttpEntity<MultiValueMap<String, String>> lRequest = new HttpEntity<>(lRequestBody, lRequestHeaders);
        try {
            ResponseEntity<String> lResponse = lRestTemplate.exchange(lRequestUrl, HttpMethod.POST, lRequest, String.class);
            JsonNode lResponseBody = mMapper.readTree(lResponse.getBody().toString());

            return (ArrayNode) lResponseBody.get("results").get("bindings");
        } catch (Exception ex) {
            throw ex;
        }

    }

    private String resolvePolicy(JsonNode pElement) {
        String lValue = pElement.get("policy") != null ? pElement.get("policy").get("value").asText() : "Business Agnostic";

        if (lValue.contains("Taxes")) {
            return "Taxes";
        } else if (lValue.contains("Customs")) {
            return "Customs";
        } else if (lValue.contains("Health")) {
            return "Health";
        } else {
            return lValue;
        }
    }

    private String resolveView(JsonNode pElement) {
        String lValue = pElement.get("view").get("value").asText();

        if (lValue.contains("Legal")) {
            return "Legal";
        } else if (lValue.contains("Organisational")) {
            return "Organisational";
        } else if (lValue.contains("Semantic")) {
            return "Semantic";
        } else if (lValue.contains("Technicalapplication")) {
            return "Technical - Application";
        } else if (lValue.contains("Technicalinfrastructure")) {
            return "Technical - Infrastructure";
        } else {
            return "N/A";
        }
    }
}
