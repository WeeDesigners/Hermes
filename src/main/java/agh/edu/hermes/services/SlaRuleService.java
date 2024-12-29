package agh.edu.hermes.services;

import agh.edu.hermes.persistance.entities.PolicyRule;
import agh.edu.hermes.persistance.repositories.ClauseRepository;
import agh.edu.hermes.persistance.repositories.SlaRuleRepository;
import agh.edu.hermes.services.parsers.RuleParserService;
import agh.edu.hermes.persistance.entities.SlaRule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SlaRuleService {

    @Autowired
    private SlaRuleRepository slaRuleRepository;
    @Autowired
    private ClauseRepository clauseRepository;
    @Autowired
    private RuleParserService ruleParserService;


    public SlaRule addRuleObject(SlaRule rule){
        clauseRepository.saveAll(rule.getConditions());
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

    public void removeSlaRule(long id){
        slaRuleRepository.deleteById(id);
    }

    public SlaRule modifySlaRule(long id, String ruleString){
        SlaRule newRule = ruleParserService.parseSlaRuleStringToObject(ruleString);
        newRule.setId(id);
        return addRuleObject(newRule);
    }

}
