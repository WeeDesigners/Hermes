package agh.edu.hermes.storages;

import agh.edu.hermes.checker.SlaViolationChecker;
import agh.edu.hermes.types.PolicyRule;

import java.util.ArrayList;
import java.util.List;

public class Policies {

    private final List<PolicyRule> rules;


    private final static Policies instance = new Policies();

    private Policies() {
        this.rules = new ArrayList<>();
    }

    public static Policies getInstance() {
        return instance;
    }

    private boolean checkUniqueId(long id){
        for(PolicyRule rule : rules){
            if(rule.id == id){
                return false;
            }
        }
        return true;
    }

    public boolean addRules(List<PolicyRule> rules){
        for(PolicyRule rule : rules){
            if(!this.addRule(rule)){
                return false;
            }
        }
        return true;
    }

    public boolean addRule(PolicyRule rule){
        if(SlaViolationChecker.checkRule(rule)){
            return false;
        }
        if(checkUniqueId(rule.id)){
            this.rules.add(rule);
            return true;
        }
        return false;
    }

    public PolicyRule removeRule(long id){
        for(PolicyRule rule : rules){
            if(rule.id == id){
                rules.remove(rule);
                return rule;
            }
        }
        return null;
    }

    public List<PolicyRule> getRules() {
        return new ArrayList<>(rules);
    }

    @Override
    public String toString() {
        //TODO -> better concat
        return "\n====================================\n "
                + "Policies:\n"
                + rules
                + "\n====================================\n";
    }


}
