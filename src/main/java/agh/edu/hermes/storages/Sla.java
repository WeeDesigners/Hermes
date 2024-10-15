package agh.edu.hermes.storages;


import agh.edu.hermes.types.Rule;
import agh.edu.hermes.types.SlaRule;

import java.util.ArrayList;
import java.util.List;


public class Sla {

    private final List<SlaRule> rules;

    private static final Sla instance = new Sla();

    private Sla() {
        this.rules = new ArrayList<>();
    }

    public static Sla getInstance() {
        return instance;
    }

    private boolean checkUniqueId(long id){
        for(Rule rule : rules){
            if(rule.id == id){
                return false;
            }
        }
        return true;
    }

    public boolean addRules(List<SlaRule> rules){
        for(SlaRule rule : rules){
            if(this.addRule(rule)){
                return false;
            }
        }
        return true;
    }

    public boolean addRule(SlaRule rule){
        if(!checkUniqueId(rule.id)){
            return false;
        }
        else {
            this.rules.add(rule);
        }
        return true;
    }

    public SlaRule removeRule(long id){
        for(SlaRule rule : rules){
            if(rule.id == id){
                rules.remove(rule);
                return rule;
            }
        }
        return null;
    }

    public void removeRules(){
        this.rules.clear();
    }

    public void clearSla(){
        removeRules();
    }

    public List<SlaRule> getRules() {
        return new ArrayList<>(rules);
    }


    @Override
    public String toString() {
        //TODO -> better concat
        return "\n====================================\n Rules:\n"
                + rules
                + "\n====================================\n";
    }

}
