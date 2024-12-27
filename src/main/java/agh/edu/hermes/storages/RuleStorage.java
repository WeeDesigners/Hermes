package agh.edu.hermes.storages;

import agh.edu.hermes.types.PolicyRule;
import agh.edu.hermes.types.SlaRule;

import java.util.ArrayList;
import java.util.List;

public class RuleStorage {

    private static final RuleStorage instance = new RuleStorage();

    private final List<SlaRule> slaRules;
    private final List<PolicyRule> policyRules;

    private RuleStorage() {
        slaRules = new ArrayList<>();
        policyRules = new ArrayList<>();
    }

    public static RuleStorage getInstance() {
        return instance;
    }

    public SlaRule addSlaRule(SlaRule rule) {
        if(getSlaRule(rule.id) == null){
            slaRules.add(rule);
            return rule;
        }
        return null;
    }

    public PolicyRule addPolicyRule(PolicyRule rule) {
        if(getPolicyRule(rule.id) == null){
            policyRules.add(rule);
            return rule;
        }
        return null;
    }

    public SlaRule getSlaRule(long id){
        for(SlaRule rule : slaRules){
            if(rule.id == id){
                return rule;
            }
        }
        return null;
    }

    public PolicyRule getPolicyRule(long id){
        for(PolicyRule rule : policyRules){
            if(rule.id == id){
                return rule;
            }
        }
        return null;
    }

}
