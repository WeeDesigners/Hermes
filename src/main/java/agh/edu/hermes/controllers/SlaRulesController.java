package agh.edu.hermes.controllers;


import agh.edu.hermes.services.SlaRuleService;
import agh.edu.hermes.types.SlaRule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/rules/sla")
public class SlaRulesController {

    private final SlaRuleService ruleService;

    @Autowired
    public SlaRulesController(SlaRuleService ruleService) {
        this.ruleService = ruleService;
    }

    @PostMapping("/addRuleObject")
    public String addRule(@RequestBody SlaRule rule) {
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

    @GetMapping("/{id}")
    public SlaRule getRuleObject(@PathVariable("id") long id) {
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
