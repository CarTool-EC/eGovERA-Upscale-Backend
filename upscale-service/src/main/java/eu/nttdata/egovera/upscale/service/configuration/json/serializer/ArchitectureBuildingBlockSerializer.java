package eu.nttdata.egovera.upscale.service.configuration.json.serializer;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import eu.nttdata.egovera.upscale.service.configuration.ArchitectureBuildingBlock;

public class ArchitectureBuildingBlockSerializer extends JsonSerializer<ArchitectureBuildingBlock> {
    
    public void serialize(ArchitectureBuildingBlock value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
        gen.writeStartObject();
        gen.writeStringField("Puri", value.getPuri());
        gen.writeStringField("Name", value.getName());
        gen.writeStringField("Description", value.getDescription());
        gen.writeStringField("Policy", value.getPolicy());
        gen.writeStringField("View", value.getView());
        gen.writeArrayFieldStart("RelatedDBCs");
        for (String lDBC : value.getDbcRelationships()) {
            gen.writeString(lDBC);
        }
        gen.writeEndArray();
        gen.writeArrayFieldStart("RelatedDPSs");
        for (String lDPS : value.getDpsRelationships()) {
            gen.writeString(lDPS);
        }
        gen.writeEndArray();
        gen.writeEndObject();
    }
}
