package agh.edu.hermes.services;

import agh.edu.hermes.checker.SlaViolationChecker;
import agh.edu.hermes.storages.RuleStorage;
import agh.edu.hermes.storages.Sla;
import agh.edu.hermes.types.Rule;
import agh.edu.hermes.types.SlaRule;
import org.springframework.stereotype.Service;

@Service
public class SlaService {

    public boolean addRuleToSla(SlaRule rule){
        Sla sla = Sla.getInstance();
        if(SlaViolationChecker.checkRule(rule)){
            return sla.addRule(rule);
        }
        return false;
    }

    public boolean addRuleToSlaById(long id){
        RuleStorage rs = RuleStorage.getInstance();
        Rule rule = rs.getRule(id);
        if(rule == null || rule.getClass() != SlaRule.class){
            return false;
        }
        return addRuleToSla((SlaRule) rule);
    }

    public boolean removeRuleFromSla(long id){
        Sla sla = Sla.getInstance();
        Rule removed = sla.removeRule(id);
        return (removed != null);
    }

    public Sla getSla(){
        return Sla.getInstance();
    }

}
