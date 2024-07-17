package eu.nttdata.egovera.upscale.api.controller;

import org.springframework.web.bind.annotation.RestController;

import eu.nttdata.egovera.upscale.service.ResourcesService;
import eu.nttdata.egovera.upscale.service.configuration.ArchitectureBuildingBlock;
import eu.nttdata.egovera.upscale.service.configuration.DigitalBusinessCapability;
import eu.nttdata.egovera.upscale.service.configuration.DigitalPublicService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
@RequestMapping("/api/resources")
public class ResourcesController {
    @Autowired
    private ResourcesService mResourcesService;

    @GetMapping(path="/getABB", produces = "application/json")
    public ResponseEntity<List<ArchitectureBuildingBlock>> getABB() throws Exception {
        List<ArchitectureBuildingBlock> lResponse = mResourcesService.getABB();
        return new ResponseEntity<List<ArchitectureBuildingBlock>>(lResponse, HttpStatus.OK);
    }

    @GetMapping(path="/getDBC", produces="application/json")
    public ResponseEntity<List<DigitalBusinessCapability>> getDBC() throws Exception {
        List<DigitalBusinessCapability> lResponse = mResourcesService.getDBC();
        return new ResponseEntity<List<DigitalBusinessCapability>>(lResponse, HttpStatus.OK);
    }
    
    @GetMapping(path="/getDPS", produces="application/json")
    public ResponseEntity<List<DigitalPublicService>> getDPS() throws Exception {
        List<DigitalPublicService> lResponse = mResourcesService.getDPS();
        return new ResponseEntity<List<DigitalPublicService>>(lResponse, HttpStatus.OK);
    }
}
