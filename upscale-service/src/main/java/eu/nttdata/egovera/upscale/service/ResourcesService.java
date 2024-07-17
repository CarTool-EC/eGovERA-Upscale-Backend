package eu.nttdata.egovera.upscale.service;

import java.util.List;

import eu.nttdata.egovera.upscale.service.configuration.ArchitectureBuildingBlock;
import eu.nttdata.egovera.upscale.service.configuration.DigitalBusinessCapability;
import eu.nttdata.egovera.upscale.service.configuration.DigitalPublicService;

public interface ResourcesService {
    
    public List<ArchitectureBuildingBlock> getABB() throws Exception;
    
    public List<DigitalBusinessCapability> getDBC() throws Exception;

    public List<DigitalPublicService> getDPS() throws Exception;
}
