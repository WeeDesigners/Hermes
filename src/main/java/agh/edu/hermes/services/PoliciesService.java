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
        //there can be only one Policies object in database
        if(this.policiesRepository.count() == 0){
            this.policiesRepository.save(new Policies());
        }
    }

    public Policies addRuleToPolicies(PolicyRule rule){
        Policies policies = policiesRepository.getReferenceById(1L);
        if(policies.addRule(rule)){
            return policiesRepository.save(policies);
        }
        return null;
    }

    public Policies addRuleToPoliciesById(long id){
        PolicyRule rule = policyRuleRepository.getReferenceById(id);
        return addRuleToPolicies(rule);
    }

    public Policies removeRuleFromPolicies(long id){
        Policies policies = policiesRepository.getReferenceById(1L);
        policies.removeRule(id);
        return policiesRepository.save(policies);
    }

    public Policies getPolicies() {
        return policiesRepository.getReferenceById(1L);
    }


}
