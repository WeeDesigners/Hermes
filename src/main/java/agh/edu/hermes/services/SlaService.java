package agh.edu.hermes.services;

import agh.edu.hermes.checker.SlaViolationChecker;
import agh.edu.hermes.storages.RuleStorage;
import agh.edu.hermes.storages.Sla;
import agh.edu.hermes.types.Rule;
import org.springframework.stereotype.Service;

@Service
public class SlaService {

    public boolean addRuleToSla(Rule rule){
        Sla sla = Sla.getInstance();
        if(SlaViolationChecker.checkRule(rule)){
            sla.addRule(rule);
            return true;
        }
        return false;
    }

    public boolean addRuleToSlaById(long id){
        Sla sla = Sla.getInstance();
        RuleStorage rs = RuleStorage.getInstance();
        Rule rule = rs.getRule(id);
        if(rule == null){
            return false;
        }
        if(SlaViolationChecker.checkRule(rule)){
            sla.addRule(rule);
            return true;
        }
        return false;
    }

    public boolean removeRuleFromSla(long id){
        Sla sla = Sla.getInstance();
        Rule removed = sla.removeRule(id);
        return (removed != null);
    }

}