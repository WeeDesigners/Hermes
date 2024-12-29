package agh.edu.hermes.controllers;

import agh.edu.hermes.persistance.entities.SlaRule;
import agh.edu.hermes.services.SlaRuleService;
import agh.edu.hermes.services.SlaService;
import agh.edu.hermes.persistance.entities.Sla;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/sla")
public class SlaController {

    @Autowired
    private SlaService slaService;
    @Autowired
    private SlaRuleService slaRuleService;


    @PutMapping("")
    public ResponseEntity<Sla> createSla(@RequestBody String slaString) {
        Sla sla = slaService.create(slaString);
        if(sla != null) {
            URI location = ServletUriComponentsBuilder.fromCurrentRequestUri()
                    .replacePath("/sla/{id}")
                    .buildAndExpand(sla.getId())
                    .toUri();
            return ResponseEntity.created(location).body(sla);
        }
        return ResponseEntity.badRequest().build();
    }

    @GetMapping("")
    public ResponseEntity<List<Sla>> getSlaList() {
        return ResponseEntity.ok(slaService.getSlaList());
    }

    @PostMapping("/{sla_id}")
    public ResponseEntity<Sla> modifySlaObject(@PathVariable("sla_id") long id, String slaString) {
        return ResponseEntity.ok(slaService.modifySla(id, slaString));
    }

    @GetMapping("/{sla_id}")
    public ResponseEntity<Sla> getSlaObject(@PathVariable("sla_id") long id) {
        Sla sla = slaService.getSla(id);
        if(sla == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(sla);
    }

    @DeleteMapping("/{sla_id}")
    public ResponseEntity<?> removeSla(@PathVariable("sla_id") long id){
        slaService.removeSlaById(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{sla_id}/rule")
    public ResponseEntity<List<SlaRule>> getAllRules(@PathVariable("sla_id") long id) {
        Sla sla = slaService.getSla(id);
        if(sla != null) {
            return ResponseEntity.ok(sla.getSlaRules());
        }
        return ResponseEntity.notFound().build();
    }

    @PutMapping("/{sla_id}/rule")
    public ResponseEntity<List<SlaRule>> addNewRule(@PathVariable("sla_id") long id, @RequestBody String ruleString) {
        SlaRule slaRule = slaRuleService.addRuleString(ruleString);
        if(slaService.getSla(id) != null){
            slaService.addRuleToSlaById(id, slaRule.getId());
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping("/{sla_id}/rule/{rule_id}")
    public ResponseEntity<?> addRuleToSla(@PathVariable("sla_id") long slaId, @PathVariable("rule_id") long ruleId) {
        if(slaService.addRuleToSlaById(slaId, ruleId) != null) {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.badRequest().build();
    }

    @DeleteMapping("/{sla_id}/remove/{rule_id}")
    public ResponseEntity<?> removeRuleFromSla(@PathVariable("sla_id") long slaId, @PathVariable("rule_id") long ruleId){
        if(slaService.removeRuleFromSla(slaId, ruleId) != null) {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.badRequest().build();
    }

}
