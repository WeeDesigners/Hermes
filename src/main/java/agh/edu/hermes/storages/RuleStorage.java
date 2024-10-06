package agh.edu.hermes.storages;

import agh.edu.hermes.types.NotificationRule;
import agh.edu.hermes.types.Rule;

import java.util.ArrayList;
import java.util.List;

public class RuleStorage {

    private static final RuleStorage instance = new RuleStorage();

    private final List<Rule> rules;

    private RuleStorage() {
        rules = new ArrayList<>();
    }

    public static RuleStorage getInstance() {
        return instance;
    }

    public boolean addRule(Rule rule) {
        if(getRule(rule.id) == null){
            rules.add(rule);
            return true;
        }
        return false;
    }

    public List<Rule> getRules() {
        return new ArrayList<>(rules);
    }

    public Rule getRule(long id){
        for(Rule rule : rules){
            if(rule.id == id){
                return rule;
            }
        }
        return null;
    }

}
