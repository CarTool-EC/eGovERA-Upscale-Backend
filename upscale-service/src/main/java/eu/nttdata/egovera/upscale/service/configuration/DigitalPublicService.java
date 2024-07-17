package eu.nttdata.egovera.upscale.service.configuration;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import eu.nttdata.egovera.upscale.service.configuration.json.serializer.DigitalPublicServiceSerializer;

@JsonSerialize(using = DigitalPublicServiceSerializer.class)
public class DigitalPublicService {
    private String Puri;
    private String Name;
    private String Description;
    private String Policy;
    private String View;
    private List<String> RelatedDigitalBusinessCapabilities;
    
    public DigitalPublicService(String puri, String name, String description, String policy, String view) {
        this.Puri = puri;
        this.Name = name;
        this.Description = description;
        this.Policy = policy;
        this.View = view;
        this.RelatedDigitalBusinessCapabilities = new ArrayList<String>();
    }

    public String getPuri() {
        return this.Puri;
    }

    public String getName() {
        return this.Name;
    }

    public String getDescription() {
        return this.Description;
    }

    public String getPolicy() {
        return this.Policy;
    }

    public String getView() {
        return this.View;
    }

    public List<String> getRelationships() {
        return this.RelatedDigitalBusinessCapabilities;
    }

    public void addRelationships(String dpsPuri, String dbcPuri) {
        if (dpsPuri.equals(this.Puri) && !this.RelatedDigitalBusinessCapabilities.contains(dbcPuri)) {
            this.RelatedDigitalBusinessCapabilities.add(dbcPuri);
        }
    }
}
