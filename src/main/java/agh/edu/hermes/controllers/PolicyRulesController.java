package agh.edu.hermes.controllers;


import agh.edu.hermes.services.PolicyRuleService;
import agh.edu.hermes.types.PolicyRule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/rules/policy")
public class PolicyRulesController {

    private final PolicyRuleService ruleService;

    @Autowired
    public PolicyRulesController(PolicyRuleService ruleService) {
        this.ruleService = ruleService;
    }

    @PostMapping("/addRuleObject")
    public String addRule(@RequestBody PolicyRule rule) {
        if(ruleService.addRuleObject(rule)){
            return "Rule added successfully";
        }
        return "Rule not added";
    }

    @PostMapping("/addRuleString")
    public String addRule(@RequestBody String ruleString) {
        if(ruleService.addRuleString(ruleString)){
            return "Rule added successfully";
        }
        return "Rule not added";
    }

    @GetMapping("/getRuleObject/{id}")
    public PolicyRule getRuleObject(@PathVariable("id") long id) {
        return ruleService.getRuleObject(id);
    }

    @GetMapping("/getRuleString/{id}")
    public String getRuleString(@PathVariable("id") long id) {
        String result = ruleService.getRuleString(id);
        if(result.isEmpty()){
            return "Cannot get rule of id " + id + ".";
        }
        return result;
    }

}
