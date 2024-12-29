package agh.edu.hermes.services;

import agh.edu.hermes.repositories.ActionRepository;
import agh.edu.hermes.repositories.ConditionRepository;
import agh.edu.hermes.repositories.PolicyRuleRepository;
import agh.edu.hermes.services.parsers.RuleParserService;
import agh.edu.hermes.types.PolicyRule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PolicyRuleService {

    @Autowired
    private PolicyRuleRepository policyRuleRepository;
    @Autowired
    private ConditionRepository conditionRepository;
    @Autowired
    private ActionRepository actionRepository;
    @Autowired
    private RuleParserService ruleParserService;

    public PolicyRule addRuleObject(PolicyRule rule){
        conditionRepository.saveAll(rule.getConditions());
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
