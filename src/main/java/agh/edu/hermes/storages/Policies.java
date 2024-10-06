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

    public void addRules(List<Rule> rules){
        this.rules.addAll(rules);
    }

    public void addRule(Rule rule){
        this.rules.add(rule);
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
