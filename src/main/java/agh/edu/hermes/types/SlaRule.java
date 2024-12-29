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
    private List<Clause> clauses;

    public SlaRule(){
        valueType = null;
        clauses = new ArrayList<>();
    }

    public SlaRule(ValueType valueType) {
        this.valueType = valueType;
        this.clauses = new ArrayList<>();
    }

    public boolean addConditions(List<Clause> clauses) {
        for(Clause clause : clauses) {
            if(!this.clauses.contains(clause)) {
                return false;
            }
        }
        return true;
    }

    public boolean addCondition(Clause clause) {
        return clauses.add(clause);
    }

    public List<Clause> getConditions() {
        return new ArrayList<>(clauses);
    }

    public void clearConditions() {
        this.clauses.clear();
    }

    public long getId() {
        return id;
    }

    public ValueType getValueType() {
        return valueType;
    }

    @Override
    public String toString() {
        return "{" + id + ", " + valueType + ", " + clauses + "}";
    }

}
