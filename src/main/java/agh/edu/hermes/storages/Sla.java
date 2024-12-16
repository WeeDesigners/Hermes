package agh.edu.hermes.storages;


import agh.edu.hermes.generators.IdGenerator;
import agh.edu.hermes.types.SlaRule;
import agh.edu.hermes.types.attributes.SlaType;

import java.util.ArrayList;
import java.util.List;


public class Sla {

    public final long id;
    public final SlaType type;
    public final String clientId;
    public final String applicationId;
    private final List<SlaRule> slaRules;


    public Sla() {
        this.id = IdGenerator.getNextId();
        this.type = null;
        this.clientId = null;
        this.applicationId = null;
        this.slaRules = new ArrayList<>();
    }

    public Sla(SlaType type, String clientId, String applicationId) {
        this.id = IdGenerator.getNextId();
        this.type = type;
        this.clientId = clientId;
        this.applicationId = applicationId;
        this.slaRules = new ArrayList<>();
    }

    public Sla(SlaType type, String clientId, String applicationId, List<SlaRule> slaRules) {
        this.id = IdGenerator.getNextId();
        this.type = type;
        this.clientId = clientId;
        this.applicationId = applicationId;
        this.slaRules = slaRules;
    }

    private boolean checkUniqueId(long id){
        for(SlaRule rule : slaRules){
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
            this.slaRules.add(rule);
        }
        return true;
    }

    public SlaRule removeRule(long id){
        for(SlaRule rule : slaRules){
            if(rule.id == id){
                slaRules.remove(rule);
                return rule;
            }
        }
        return null;
    }

    public void removeRules(){
        this.slaRules.clear();
    }

    public List<SlaRule> getRules() {
        return new ArrayList<>(slaRules);
    }


    @Override
    public String toString() {
        //TODO -> better concat
        return "\n====================================\n Rules:\n"
                + slaRules
                + "\n====================================\n";
    }
}
