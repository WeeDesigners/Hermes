package agh.edu.hermes.controllers;


import agh.edu.hermes.services.RuleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import agh.edu.hermes.types.Rule;

@RestController
@RequestMapping("/rules")
public class RulesController {

    private final RuleService ruleService;

    @Autowired
    public RulesController(RuleService ruleService) {
        this.ruleService = ruleService;
    }

    @PostMapping("/addRuleObject")
    public String addRule(Rule rule) {
        if(ruleService.addRuleObject(rule)){
            return "Rule added successfully";
        }
        return "Rule not added";
    }

    @PostMapping("/addRuleString")
    public String addRule(String ruleString) {
        if(ruleService.addRuleString(ruleString)){
            return "Rule added successfully";
        }
        return "Rule not added";
    }

    @GetMapping("/getRuleObject")
    public Rule getRuleObject(long id) {
        return ruleService.getRuleObject(id);
    }

    @GetMapping("/getRuleString")
    public String getRuleString(long id) {
        String result = ruleService.getRuleString(id);
        if(result.isEmpty()){
            return "Cannot get rule of id " + id + ".";
        }
        return result;
    }

}
