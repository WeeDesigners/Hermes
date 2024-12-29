package agh.edu.hermes.controllers;


import agh.edu.hermes.services.SlaRuleService;
import agh.edu.hermes.types.SlaRule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;


@RestController
@RequestMapping("/rules/sla")
public class SlaRulesController {

    @Autowired
    private SlaRuleService slaRuleService;

    @PostMapping("/addRuleObject")
    public ResponseEntity<SlaRule> addRule(@RequestBody SlaRule rule) {
        if(slaRuleService.addRuleObject(rule) != null) {
            URI location = ServletUriComponentsBuilder.fromCurrentRequestUri()
                    .replacePath("/rules/sla/{id}")
                    .buildAndExpand(rule.getId())
                    .toUri();
            return ResponseEntity.created(location).body(rule);
        }
        return ResponseEntity.badRequest().build();
    }

    @PostMapping("/addRuleString")
    public ResponseEntity<SlaRule> addRule(@RequestBody String ruleString) {
        SlaRule rule = slaRuleService.addRuleString(ruleString);
        if(rule != null) {
            URI location = ServletUriComponentsBuilder.fromCurrentRequestUri()
                    .replacePath("/rules/sla/{id}")
                    .buildAndExpand(rule.getId())
                    .toUri();
            return ResponseEntity.created(location).body(rule);
        }
        return ResponseEntity.badRequest().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<SlaRule> getRuleObject(@PathVariable("id") long id) {
        SlaRule rule = slaRuleService.getRuleObject(id);
        if(rule == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(rule);
    }

}
