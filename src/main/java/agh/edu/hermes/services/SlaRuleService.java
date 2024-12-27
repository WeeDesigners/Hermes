package agh.edu.hermes.services;

import agh.edu.hermes.services.parsers.RuleParserService;
import agh.edu.hermes.storages.RuleStorage;
import agh.edu.hermes.types.SlaRule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SlaRuleService {

    private final RuleParserService ruleParserService;

    @Autowired
    public SlaRuleService(RuleParserService ruleParserService) {
        this.ruleParserService = ruleParserService;
    }

    public SlaRule addRuleObject(SlaRule rule){
        RuleStorage rs = RuleStorage.getInstance();
        return rs.addSlaRule(rule);
    }

    public SlaRule addRuleString(String ruleString){
        SlaRule rule = ruleParserService.parseSlaRuleStringToObject(ruleString);
        if(rule == null) {
            return null;
        }
        return addRuleObject(rule);
    }

    public SlaRule getRuleObject(long id){
        RuleStorage rs = RuleStorage.getInstance();
        return rs.getSlaRule(id);
    }

    public String getRuleString(long id){
        SlaRule rule = getRuleObject(id);
        if(rule == null) {
            return "";
        }
        return ruleParserService.parseSlaRuleObjectToString(rule);
    }
}
