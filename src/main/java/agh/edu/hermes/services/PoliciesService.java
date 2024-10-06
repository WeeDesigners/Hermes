package agh.edu.hermes.services;


import agh.edu.hermes.checker.SlaViolationChecker;
import agh.edu.hermes.storages.Policies;
import agh.edu.hermes.storages.RuleStorage;
import agh.edu.hermes.storages.Sla;
import agh.edu.hermes.types.Rule;
import org.springframework.stereotype.Service;

@Service
public class PoliciesService {

    public boolean addRuleToPolicies(Rule rule){
        Policies policies = Policies.getInstance();
        policies.addRule(rule);
        return true;
    }

    public boolean addRuleToPoliciesById(long id){
        Policies policies = Policies.getInstance();
        RuleStorage rs = RuleStorage.getInstance();
        Rule rule = rs.getRule(id);
        if(rule == null){
            return false;
        }
        policies.addRule(rule);
        return true;
    }

    public boolean removeRuleFromPolicies(long id){
        Policies policies = Policies.getInstance();
        Rule removed = policies.removeRule(id);
        return (removed != null);
    }


}
