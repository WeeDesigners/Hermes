package agh.edu.hermes.controllers;

import agh.edu.hermes.services.SlaService;
import agh.edu.hermes.types.Sla;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/sla")
public class SlaController {

    private final SlaService slaService;

    @Autowired
    public SlaController(SlaService slaService) {
        this.slaService = slaService;
    }

    @PostMapping("/create")
    public String setSlaProperties(@RequestBody String slaString) {
        if(slaService.create(slaString)) {
            return "Successfully created SLA";
        }
        return "Sla cannot be created";
    }



    @PostMapping("/{sla_id}/add/{rule_id}")
    public String addRuleToSla(@PathVariable("sla_id") long slaId, @PathVariable("rule_id") long ruleId) {
        if(slaService.addRuleToSlaById(slaId, ruleId)) {
            return "Rule id=" + ruleId + " added successfully to SLA " + slaId;
        }
        return "Rule id=" + ruleId + " not added to SLA " + slaId;
    }

    @DeleteMapping("/{sla_id}/remove/{rule_id}")
    public String removeRuleFromSla(@PathVariable("sla_id") long slaId, @PathVariable("rule_id") long ruleId){
        if(slaService.removeRuleFromSla(slaId, ruleId)) {
            return "Rule id=" + ruleId + " is removed from SLA " + slaId;
        }
        return "Rule id=" + ruleId + " is not removed from SLA " + slaId;
    }

    @DeleteMapping("/{sla_id}")
    public String removeSla(@PathVariable("sla_id") long id){
        if(slaService.removeSlaById(id)) {
            return "Sla id=" + id + " is removed.";
        }
        return "Sla id=" + id + " is not removed.";
    }

    @GetMapping("/{sla_id}")
    public Sla getSlaObject(@PathVariable("sla_id") long id) {
        return slaService.getSla(id);
    }

    @GetMapping("/{sla_id}/getString")
    public String getSlaString(@PathVariable("sla_id") long id) {
        String slaString = slaService.getSlaString(id);
        if(slaString == null) {
            return "Sla id=" + id + " is not found.";
        }
        return slaString;
    }

    @GetMapping("")
    public List<Sla> getSlaList() {
        return slaService.getSlaList();
    }

}
