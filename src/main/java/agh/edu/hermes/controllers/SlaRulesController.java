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

    private final SlaRuleService ruleService;

    @Autowired
    public SlaRulesController(SlaRuleService ruleService) {
        this.ruleService = ruleService;
    }

    @PostMapping("/addRuleObject")
    public ResponseEntity<SlaRule> addRule(@RequestBody SlaRule rule) {
        if(ruleService.addRuleObject(rule) != null) {
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
        SlaRule rule = ruleService.addRuleString(ruleString);
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
        SlaRule rule = ruleService.getRuleObject(id);
        if(rule == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(ruleService.getRuleObject(id));
    }

}
