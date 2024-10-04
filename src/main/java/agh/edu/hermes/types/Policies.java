package agh.edu.hermes.types;

import java.util.ArrayList;
import java.util.List;

public class Policies {

    public final long id;
    private final List<Rule> rules;

    private Policies(long id, List<Rule> rules) {
        this.id = id;
        this.rules = rules;
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
