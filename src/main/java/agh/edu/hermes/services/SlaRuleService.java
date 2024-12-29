package agh.edu.hermes.services;

import agh.edu.hermes.repositories.SlaRuleRepository;
import agh.edu.hermes.services.parsers.RuleParserService;
import agh.edu.hermes.types.SlaRule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SlaRuleService {

    @Autowired
    private SlaRuleRepository slaRuleRepository;
    @Autowired
    private RuleParserService ruleParserService;


    public SlaRule addRuleObject(SlaRule rule){
        return slaRuleRepository.save(rule);
    }

    public SlaRule addRuleString(String ruleString){
        SlaRule rule = ruleParserService.parseSlaRuleStringToObject(ruleString);
        if(rule == null) {
            return null;
        }
        return addRuleObject(rule);
    }

    public SlaRule getRuleObject(long id){
        return slaRuleRepository.getReferenceById(id);
    }

    public String getRuleString(long id){
        SlaRule rule = getRuleObject(id);
        if(rule == null) {
            return "";
        }
        return ruleParserService.parseSlaRuleObjectToString(rule);
    }
}
