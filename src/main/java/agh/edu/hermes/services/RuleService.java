package agh.edu.hermes.services;

import agh.edu.hermes.services.parsers.RuleParserService;
import agh.edu.hermes.storages.RuleStorage;
import agh.edu.hermes.types.Rule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RuleService {

    private final RuleParserService ruleParserService;

    @Autowired
    public RuleService(RuleParserService ruleParserService) {
        this.ruleParserService = ruleParserService;
    }

    public boolean addRuleObject(Rule rule){
        RuleStorage rs = RuleStorage.getInstance();
        return rs.addRule(rule);
    }

    public boolean addRuleString(String ruleString){
        Rule rule = ruleParserService.parseRuleStringToObject(ruleString);
        if(rule == null) {
            return false;
        }
        return addRuleObject(rule);
    }

    public Rule getRuleObject(long id){
        RuleStorage rs = RuleStorage.getInstance();
        return rs.getRule(id);
    }

    public String getRuleString(long id){
        Rule rule = getRuleObject(id);
        if(rule == null) {
            return "";
        }
        return ruleParserService.parseRuleObjectToString(rule);
    }

}
