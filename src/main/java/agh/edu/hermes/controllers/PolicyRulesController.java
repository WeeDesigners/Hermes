package agh.edu.hermes.controllers;


import agh.edu.hermes.services.PolicyRuleService;
import agh.edu.hermes.persistance.entities.PolicyRule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;


@RestController
@RequestMapping("/policies")
public class PolicyRulesController {

    @Autowired
    private PolicyRuleService policyRuleService;

    @PostMapping("")
    public ResponseEntity<PolicyRule> addRule(@RequestBody String ruleString) {
        PolicyRule rule = policyRuleService.addRuleString(ruleString);
        if(rule != null){
            URI location = ServletUriComponentsBuilder.fromCurrentRequestUri()
                    .replacePath("/rules/policy/{id}")
                    .buildAndExpand(rule.getId())
                    .toUri();
            return ResponseEntity.created(location).body(rule);
        }
        return ResponseEntity.badRequest().build();
    }

    @GetMapping("")
    public ResponseEntity<List<PolicyRule>> getRules() {
        return ResponseEntity.ok(policyRuleService.getAllRules());
    }

    @PostMapping("/{id}")
    public ResponseEntity<PolicyRule> getRuleObject(@PathVariable("id") long id, @RequestBody String ruleString) {
        return ResponseEntity.ok(policyRuleService.modifyPolicyRule(id, ruleString));
    }

    @GetMapping("/{id}")
    public ResponseEntity<PolicyRule> getRuleObject(@PathVariable("id") long id) {
        PolicyRule rule = policyRuleService.getRuleObject(id);
        if(rule == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(policyRuleService.getRuleObject(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<PolicyRule> deleteRuleObject(@PathVariable("id") long id) {
        policyRuleService.removePolicyRule(id);
        return ResponseEntity.ok().build();
    }

}
