package eu.nttdata.egovera.upscale.service.configuration;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import eu.nttdata.egovera.upscale.service.configuration.json.serializer.ArchitectureBuildingBlockSerializer;

@JsonSerialize(using = ArchitectureBuildingBlockSerializer.class)
public class ArchitectureBuildingBlock {
    private String Puri;
    private String Name;
    private String Description;
    private String Policy;
    private String View;
    private List<String> RelatedDigitalBusinessCapabilities;
    private List<String> RelatedDigitalPublicServices;

    public ArchitectureBuildingBlock(String puri, String name, String description, String policy, String view) {
        this.Puri = puri;
        this.Name = name;
        this.Description = description;
        this.Policy = policy;
        this.View = view;
        this.RelatedDigitalBusinessCapabilities = new ArrayList<String>();
        this.RelatedDigitalPublicServices = new ArrayList<String>();
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

    public List<String> getDbcRelationships() {
        return this.RelatedDigitalBusinessCapabilities;
    }

    public List<String> getDpsRelationships() {
        return this.RelatedDigitalPublicServices;
    }

    public void addDbcRelationships(String abbPuri, String dbcPuri) {
        if (abbPuri.equals(this.Puri) && !this.RelatedDigitalBusinessCapabilities.contains(dbcPuri)) {
            this.RelatedDigitalBusinessCapabilities.add(dbcPuri);
        }
    }

    public void addDpsRelationships(String abbPuri, String dpsPuri) {
        if (abbPuri.equals(this.Puri) && !this.RelatedDigitalPublicServices.contains(dpsPuri)) {
            this.RelatedDigitalPublicServices.add(dpsPuri);
        }
    }
}
