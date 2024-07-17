package eu.nttdata.egovera.upscale.service.configuration.json.serializer;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import eu.nttdata.egovera.upscale.service.configuration.DigitalBusinessCapability;

public class DigitalBusinessCapabilitySerializer extends JsonSerializer<DigitalBusinessCapability> {
    
    public void serialize(DigitalBusinessCapability value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
        gen.writeStartObject();
        gen.writeStringField("Puri", value.getPuri());
        gen.writeStringField("Name", value.getName());
        gen.writeStringField("Description", value.getDescription());
        gen.writeStringField("Policy", value.getPolicy());
        gen.writeStringField("View", value.getView());
        gen.writeArrayFieldStart("RelatedDPSs");
        for (String lDPS : value.getRelationships()) {
            gen.writeString(lDPS);
        }
        gen.writeEndArray();
        gen.writeEndObject();
    }
}
