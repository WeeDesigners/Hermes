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
        if(ruleService.addRuleObject(rule)){
            URI location = ServletUriComponentsBuilder.fromCurrentRequestUri()
                    .replacePath("/rules/policy/{id}")
                    .buildAndExpand(rule.id)
                    .toUri();
            return ResponseEntity.created(location).body(rule);
        }
        return ResponseEntity.badRequest().build();
    }

    @PostMapping("/addRuleString")
    public String addRule(@RequestBody String ruleString) {
        if(ruleService.addRuleString(ruleString)){
            return "Rule added successfully";
        }
        return "Rule not added";
    }

    @GetMapping("/{id}")
    public PolicyRule getRuleObject(@PathVariable("id") long id) {
        return ruleService.getRuleObject(id);
    }

    @GetMapping("/{id}/getString")
    public String getRuleString(@PathVariable("id") long id) {
        String result = ruleService.getRuleString(id);
        if(result.isEmpty()){
            return "Cannot get rule of id " + id + ".";
        }
        return result;
    }

}
