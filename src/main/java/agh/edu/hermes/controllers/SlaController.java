package agh.edu.hermes.controllers;

import agh.edu.hermes.services.SlaService;
import agh.edu.hermes.storages.Sla;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/sla")
public class SlaController {

    private final SlaService slaService;

    @Autowired
    public SlaController(SlaService slaService) {
        this.slaService = slaService;
    }

    @PostMapping("/setProperties")
    public String setSlaProperties(@RequestBody String slaString) {
        if(slaService.initializeSla(slaString)) {
            return "Successfully set sla properties";
        }
        return "Sla properties could not be set";
    }

    @PostMapping("/add/{id}")
    public String addRuleToSla(@PathVariable("id") long id){
        if(slaService.addRuleToSlaById(id)){
            return "Rule id=" + id + " added successfully to SLA";
        }
        return "Rule id=" + id + " not added to SLA";
    }

    @DeleteMapping("/remove/{id}")
    public String removeRuleFromSla(@PathVariable("id") long id){
        if(slaService.removeRuleFromSla(id)){
            return "Rule id=" + id + " is removed from SLA";
        }
        return "Rule id=" + id + " is not removed from SLA";
    }

    @GetMapping("/get")
    public Sla getSlaObject(){
        return slaService.getSla();
    }

    @GetMapping("/getString")
    public String getSlaString(){
        return slaService.getSlaString();
    }
}
