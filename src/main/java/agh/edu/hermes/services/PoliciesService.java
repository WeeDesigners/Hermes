package agh.edu.hermes.services;


import agh.edu.hermes.persistance.repositories.PoliciesRepository;
import agh.edu.hermes.persistance.repositories.PolicyRuleRepository;
import agh.edu.hermes.persistance.entities.Policies;
import agh.edu.hermes.persistance.entities.PolicyRule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PoliciesService {

    private final PoliciesRepository policiesRepository;
    private final PolicyRuleRepository policyRuleRepository;

    @Autowired
    public PoliciesService(PoliciesRepository pr, PolicyRuleRepository prr){
        this.policiesRepository = pr;
        this.policyRuleRepository = prr;
    }

    public Policies addRuleToPolicies(PolicyRule rule){
        Policies policies = getPolicies();
        if(policies.addRule(rule)){
            return policiesRepository.save(policies);
        }
        return null;
    }

    public Policies addRuleToPoliciesById(long id){
        PolicyRule rule = policyRuleRepository.getReferenceById(id);
        return addRuleToPolicies(rule);
    }

    public boolean removeRuleFromPolicies(long id){
        Policies policies = getPolicies();
        policiesRepository.save(policies);
        return policies.removeRule(id);
    }

    public Policies getPolicies() {
        //if Policies object does not exist, then create new
        if(this.policiesRepository.count() == 0){
            this.policiesRepository.save(new Policies());
        }
        return policiesRepository.getReferenceById(1L);
    }


}
