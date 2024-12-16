package agh.edu.hermes.services;


import agh.edu.hermes.storages.Policies;
import agh.edu.hermes.storages.RuleStorage;
import agh.edu.hermes.types.PolicyRule;
import org.springframework.stereotype.Service;

@Service
public class PoliciesService {

    public boolean addRuleToPolicies(PolicyRule rule){
        Policies policies = Policies.getInstance();
        return policies.addRule(rule);
    }

    public boolean addRuleToPoliciesById(long id){
        Policies policies = Policies.getInstance();
        RuleStorage rs = RuleStorage.getInstance();
        PolicyRule rule = rs.getPolicyRule(id);
        if(rule == null || rule.getClass() != PolicyRule.class){
            return false;
        }
        return policies.addRule((PolicyRule) rule);
    }

    public boolean removeRuleFromPolicies(long id){
        Policies policies = Policies.getInstance();
        PolicyRule removed = policies.removeRule(id);
        return (removed != null);
    }

    public Policies getPolicies(){
        return Policies.getInstance();
    }


}
