package agh.edu.hermes.persistance.entities;

import agh.edu.hermes.checker.SlaViolationChecker;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Policies {

    @Id
    private long id = 1;
    @OneToMany(fetch = FetchType.EAGER)
    private List<PolicyRule> rules;

    public Policies() {
        this.rules = new ArrayList<>();
    }

    private boolean checkUniqueId(long id){
        for(PolicyRule rule : rules){
            if(rule.getId() == id){
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
        if(checkUniqueId(rule.getId())){
            this.rules.add(rule);
            return true;
        }
        return false;
    }

    public PolicyRule removeRule(long id){
        for(PolicyRule rule : rules){
            if(rule.getId() == id){
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
