package agh.edu.hermes.persistance.entities;


import agh.edu.hermes.enums.SlaType;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Sla {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private SlaType type;
    private String clientId;
    private String applicationId;
    @OneToMany(fetch = FetchType.EAGER)
    private List<SlaRule> slaRules;


    public Sla() {
        this.type = null;
        this.clientId = null;
        this.applicationId = null;
        this.slaRules = new ArrayList<>();
    }

    public Sla(SlaType type, String clientId, String applicationId) {
        this.type = type;
        this.clientId = clientId;
        this.applicationId = applicationId;
        this.slaRules = new ArrayList<>();
    }

    public Sla(SlaType type, String clientId, String applicationId, List<SlaRule> slaRules) {
        this.type = type;
        this.clientId = clientId;
        this.applicationId = applicationId;
        this.slaRules = slaRules;
    }

    private boolean checkUniqueId(long id){
        for(SlaRule rule : slaRules){
            if(rule.getId() == id){
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
        if(!checkUniqueId(rule.getId())){
            return false;
        }
        else {
            this.slaRules.add(rule);
        }
        return true;
    }

    public SlaRule removeRule(long id){
        for(SlaRule rule : slaRules){
            if(rule.getId() == id){
                slaRules.remove(rule);
                return rule;
            }
        }
        return null;
    }

    public void removeRules(){
        this.slaRules.clear();
    }

    public List<SlaRule> getSlaRules() {
        return new ArrayList<>(slaRules);
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public SlaType getType() {
        return type;
    }

    public String getClientId() {
        return clientId;
    }

    public String getApplicationId() {
        return applicationId;
    }

    @Override
    public String toString() {
        //TODO -> better concat
        return "\n====================================\n"
                + "id: " + id + "\n"
                + "type: " + type + "\n"
                + "clientId: " + clientId + "\n"
                + "applicationId: " + applicationId + "\n"
                + "slaRules:\n"
                + slaRules
                + "\n====================================\n";
    }
}
