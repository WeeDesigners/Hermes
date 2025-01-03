package agh.edu.hermes.controllers;


import agh.edu.hermes.services.SlaRuleService;
import agh.edu.hermes.persistance.entities.SlaRule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;


@RestController
@RequestMapping("/sla/rules")
public class SlaRulesController {

    @Autowired
    private SlaRuleService slaRuleService;

    @PutMapping("")
    public ResponseEntity<SlaRule> addRule(@RequestBody String ruleString) {
        SlaRule rule = slaRuleService.addRuleString(ruleString);
        if(rule != null) {
            URI location = ServletUriComponentsBuilder.fromCurrentRequestUri()
                    .replacePath("sla/{id}")
                    .buildAndExpand(rule.getId())
                    .toUri();
            return ResponseEntity.created(location).body(rule);
        }
        return ResponseEntity.badRequest().build();
    }

    @GetMapping("")
    public ResponseEntity<List<SlaRule>> getAllRules() {
        return ResponseEntity.ok(slaRuleService.getAllRules());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<SlaRule> deleteRuleObject(@PathVariable("id") long id) {
        if(slaRuleService.getRuleObject(id) != null){
            slaRuleService.removeSlaRule(id);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<SlaRule> getRuleObject(@PathVariable("id") long id) {
        SlaRule rule = slaRuleService.getRuleObject(id);
        if(rule == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(rule);
    }

    @PostMapping("/{id}")
    public ResponseEntity<SlaRule> modifyRuleObject(@PathVariable("id") long id, @RequestBody String ruleString) {
        return ResponseEntity.ok(slaRuleService.modifySlaRule(id, ruleString));
    }

}
