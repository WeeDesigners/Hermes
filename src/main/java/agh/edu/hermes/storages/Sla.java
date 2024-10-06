package agh.edu.hermes.storages;


import agh.edu.hermes.types.NotificationRule;
import agh.edu.hermes.types.Rule;

import java.util.ArrayList;
import java.util.List;


public class Sla {

    private final List<Rule> rules;
    private final List<NotificationRule> notificationRules;

    private static final Sla instance = new Sla();

    private Sla() {
        this.rules = new ArrayList<>();
        this.notificationRules = new ArrayList<>();
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
        for(NotificationRule rule : notificationRules){
            if(rule.id == id){
                return false;
            }
        }
        return true;
    }

    public boolean addRules(List<Rule> rules){
        for(Rule rule : rules){
            if(this.addRule(rule)){
                return false;
            }
        }
        return true;
    }

    public boolean addRule(Rule rule){
        if(!checkUniqueId(rule.id)){
            return false;
        }
        if(rule instanceof NotificationRule){
            this.notificationRules.add((NotificationRule) rule);
        }
        else {
            this.rules.add(rule);
        }
        return true;
    }

    public Rule removeRule(long id){
        for(Rule rule : rules){
            if(rule.id == id){
                rules.remove(rule);
                return rule;
            }
        }
        for(NotificationRule rule : notificationRules){
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

    public NotificationRule removeNotificationRule(long id){
        for(NotificationRule notificationRule : notificationRules){
            if(notificationRule.id == id){
                notificationRules.remove(notificationRule);
                return notificationRule;
            }
        }
        return null;
    }

    public void removeNotificationRules(){
        this.notificationRules.clear();
    }

    public void clearSla(){
        removeRules();
        removeNotificationRules();
    }

    public List<Rule> getRules() {
        return new ArrayList<>(rules);
    }

    public List<NotificationRule> getNotificationRules() {
        return new ArrayList<>(notificationRules);
    }


    @Override
    public String toString() {
        //TODO -> better concat
        return "\n====================================\n Rules:\n"
                + rules + "\n Notification Rules:\n"
                + notificationRules
                + "\n====================================\n";
    }

}
