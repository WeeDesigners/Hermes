package agh.edu.hermes.types;

import agh.edu.hermes.types.attributes.*;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class SlaRule {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private ValueType valueType;
    @OneToMany
    private List<Condition> conditions;

    public SlaRule(){
        valueType = null;
        conditions = new ArrayList<>();
    }

    public SlaRule(ValueType valueType) {
        this.valueType = valueType;
        this.conditions = new ArrayList<>();
    }

    public boolean addConditions(List<Condition> conditions) {
        for(Condition condition : conditions) {
            if(!this.conditions.contains(condition)) {
                return false;
            }
        }
        return true;
    }

    public boolean addCondition(Condition condition) {
        return conditions.add(condition);
    }

    public List<Condition> getConditions() {
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
