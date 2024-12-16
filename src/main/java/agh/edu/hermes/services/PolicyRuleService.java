package agh.edu.hermes.services;

import agh.edu.hermes.services.parsers.RuleParserService;
import agh.edu.hermes.storages.RuleStorage;
import agh.edu.hermes.types.PolicyRule;
import agh.edu.hermes.types.SlaRule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PolicyRuleService {

    private final RuleParserService ruleParserService;

    @Autowired
    public PolicyRuleService(RuleParserService ruleParserService) {
        this.ruleParserService = ruleParserService;
    }

    public boolean addRuleObject(PolicyRule rule){
        RuleStorage rs = RuleStorage.getInstance();
        return rs.addPolicyRule(rule);
    }

    public boolean addRuleString(String ruleString){
        PolicyRule rule = ruleParserService.parseRuleStringToObject(ruleString);
        if(rule == null) {
            return false;
        }
        return addRuleObject(rule);
    }

    public PolicyRule getRuleObject(long id){
        RuleStorage rs = RuleStorage.getInstance();
        return rs.getPolicyRule(id);
    }

    public String getRuleString(long id){
        PolicyRule rule = getRuleObject(id);
        if(rule == null) {
            return "";
        }
        return ruleParserService.parseRuleObjectToString(rule);
    }
}
