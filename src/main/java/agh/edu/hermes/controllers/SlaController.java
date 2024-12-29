package agh.edu.hermes.controllers;

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

    private final SlaService slaService;

    @Autowired
    public SlaController(SlaService slaService) {
        this.slaService = slaService;
    }

    @PostMapping("/create")
    public ResponseEntity<Sla> setSlaProperties(@RequestBody String slaString) {
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

    @PostMapping("/{sla_id}/add/{rule_id}")
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

    @DeleteMapping("/{sla_id}")
    public ResponseEntity<?> removeSla(@PathVariable("sla_id") long id){
        slaService.removeSlaById(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{sla_id}")
    public ResponseEntity<Sla> getSlaObject(@PathVariable("sla_id") long id) {
        Sla sla = slaService.getSla(id);
        if(sla == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(sla);
    }

    @GetMapping("")
    public ResponseEntity<List<Sla>> getSlaList() {
        return ResponseEntity.ok(slaService.getSlaList());
    }

}
