package agh.edu.hermes.services;


import agh.edu.hermes.storages.RuleStorage;
import agh.edu.hermes.storages.Sla;
import agh.edu.hermes.types.SlaRule;
import org.springframework.stereotype.Service;

@Service
public class SlaService {

    public boolean addRuleToSla(SlaRule rule){
        Sla sla = Sla.getInstance();
        //TODO (or TODELETE)
//        if(!SlaViolationChecker.checkRule(rule)){
//            return sla.addRule(rule);
//        }
        return false;
    }

    public boolean addRuleToSlaById(long id){
        RuleStorage rs = RuleStorage.getInstance();
        SlaRule rule = rs.getSlaRule(id);
        if(rule == null || rule.getClass() != SlaRule.class){
            return false;
        }
        return addRuleToSla((SlaRule) rule);
    }

    public boolean removeRuleFromSla(long id){
        Sla sla = Sla.getInstance();
        SlaRule removed = sla.removeRule(id);
        return (removed != null);
    }

    public Sla getSla(){
        return Sla.getInstance();
    }

}
