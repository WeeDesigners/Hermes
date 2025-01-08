package agh.edu.hermes.persistance.entities;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class SlaRule {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String valueType;
    @OneToMany(fetch = FetchType.EAGER)
    private List<Clause> conditions;

    public SlaRule(){
        valueType = "";
        conditions = new ArrayList<>();
    }

    public boolean addConditions(List<Clause> clauses) {
        for(Clause clause : clauses) {
            if(!this.conditions.contains(clause)) {
                return false;
            }
        }
        return true;
    }

    public boolean addCondition(Clause clause) {
        return conditions.add(clause);
    }

    public List<Clause> getConditions() {
        return new ArrayList<>(conditions);
    }

    public void clearConditions() {
        this.conditions.clear();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getValueType() {
        return valueType;
    }

    @Override
    public String toString() {
        return "{" + id + ", " + valueType + ", " + conditions + "}";
    }

}
