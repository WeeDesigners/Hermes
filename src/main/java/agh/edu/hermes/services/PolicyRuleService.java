package agh.edu.hermes.services;

import agh.edu.hermes.persistance.repositories.ActionRepository;
import agh.edu.hermes.persistance.repositories.ClauseRepository;
import agh.edu.hermes.persistance.repositories.PolicyRuleRepository;
import agh.edu.hermes.services.parsers.RuleParserService;
import agh.edu.hermes.persistance.entities.PolicyRule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PolicyRuleService {

    @Autowired
    private PolicyRuleRepository policyRuleRepository;
    @Autowired
    private ClauseRepository clauseRepository;
    @Autowired
    private ActionRepository actionRepository;
    @Autowired
    private RuleParserService ruleParserService;

    public PolicyRule addRuleObject(PolicyRule rule){
        clauseRepository.saveAll(rule.getConditions());
        actionRepository.save(rule.getAction());
        return policyRuleRepository.save(rule);
    }

    public PolicyRule addRuleString(String ruleString){
        PolicyRule rule = ruleParserService.parsePolicyRuleStringToObject(ruleString);
        if(rule == null) {
            return null;
        }
        return addRuleObject(rule);
    }

    public PolicyRule getRuleObject(long id){
        return policyRuleRepository.getReferenceById(id);
    }

    public String getRuleString(long id){
        PolicyRule rule = getRuleObject(id);
        if(rule == null) {
            return "";
        }
        return ruleParserService.parsePolicyRuleObjectToString(rule);
    }
}
