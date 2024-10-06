package agh.edu.hermes.storages;

import agh.edu.hermes.types.Rule;

import java.util.ArrayList;
import java.util.List;

public class Policies {

    private final List<Rule> rules;

    private final static Policies instance = new Policies();

    private Policies() {
        this.rules = new ArrayList<>();
    }

    public static Policies getInstance() {
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

    public boolean addRules(List<Rule> rules){
        for(Rule rule : rules){
            if(!this.addRule(rule)){
                return false;
            }
        }
        return true;
    }

    public boolean addRule(Rule rule){
        if(checkUniqueId(rule.id)){
            this.rules.add(rule);
            return true;
        }
        return false;
    }

    public Rule removeRule(long id){
        for(Rule rule : rules){
            if(rule.id == id){
                rules.remove(rule);
                return rule;
            }
        }
        return null;
    }


    public List<Rule> getRules() {
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
