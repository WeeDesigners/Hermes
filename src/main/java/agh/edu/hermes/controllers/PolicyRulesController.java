package agh.edu.hermes.controllers;


import agh.edu.hermes.services.PolicyRuleService;
import agh.edu.hermes.types.PolicyRule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;


@RestController
@RequestMapping("/rules/policy")
public class PolicyRulesController {

    private final PolicyRuleService ruleService;

    @Autowired
    public PolicyRulesController(PolicyRuleService ruleService) {
        this.ruleService = ruleService;
    }

    @PostMapping("/addRuleObject")
    public ResponseEntity<PolicyRule> addRule(@RequestBody PolicyRule rule) {
        if(ruleService.addRuleObject(rule) != null){
            URI location = ServletUriComponentsBuilder.fromCurrentRequestUri()
                    .replacePath("/rules/policy/{id}")
                    .buildAndExpand(rule.getId())
                    .toUri();
            return ResponseEntity.created(location).body(rule);
        }
        return ResponseEntity.badRequest().build();
    }

    @PostMapping("/addRuleString")
    public ResponseEntity<PolicyRule> addRule(@RequestBody String ruleString) {
        PolicyRule rule = ruleService.addRuleString(ruleString);
        if(rule != null){
            URI location = ServletUriComponentsBuilder.fromCurrentRequestUri()
                    .replacePath("/rules/policy/{id}")
                    .buildAndExpand(rule.getId())
                    .toUri();
            return ResponseEntity.created(location).body(rule);
        }
        return ResponseEntity.badRequest().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<PolicyRule> getRuleObject(@PathVariable("id") long id) {
        PolicyRule rule = ruleService.getRuleObject(id);
        if(rule == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(ruleService.getRuleObject(id));
    }

}
