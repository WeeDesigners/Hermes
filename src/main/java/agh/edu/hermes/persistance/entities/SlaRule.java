package agh.edu.hermes.persistance.entities;

import agh.edu.hermes.enums.ValueType;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class SlaRule {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private ValueType valueType;
    @OneToMany(fetch = FetchType.EAGER)
    private List<Clause> conditions;

    public SlaRule(){
        valueType = null;
        conditions = new ArrayList<>();
    }

    public SlaRule(ValueType valueType) {
        this.valueType = valueType;
        this.conditions = new ArrayList<>();
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

    public ValueType getValueType() {
        return valueType;
    }

    @Override
    public String toString() {
        return "{" + id + ", " + valueType + ", " + conditions + "}";
    }

}
