package agh.edu.hermes.persistance.entities;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
public class PolicyRule {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    public final String name;

    public int getMaxRetry() {
        return maxRetry;
    }

    public void setMaxRetry(int maxRetry) {
        this.maxRetry = maxRetry;
    }

    public long getCooldownSec() {
        return cooldownSec;
    }

    public void setCooldownSec(long cooldownSec) {
        this.cooldownSec = cooldownSec;
    }

    private int maxRetry;
    private long cooldownSec;
    @OneToMany(fetch = FetchType.EAGER)
    private List<Clause> conditions;
    @OneToOne(fetch = FetchType.EAGER)
    private final Action action;

    public PolicyRule() {
        this.name = null;
        this.conditions = new ArrayList<>();
        this.action = null;
    }

    public PolicyRule(String name, Action action) {
        this.name = name;
        this.conditions = new ArrayList<>();
        this.action = action;
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

    public Long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public Action getAction() {
        return action;
    }

    @Override
    public boolean equals(Object o) {
        PolicyRule rule = (PolicyRule) o;
        return super.equals(o)
                && this.id == rule.id
                && this.name.equals(rule.name)
                && this.conditions.equals(rule.getConditions())
                && this.action.equals(rule.action);
    }


    @Override
    public int hashCode() {
        return Objects.hash(id, name, conditions, action);
    }

    @Override
    public String toString() {
        // TODO -> better concat
        return "{ " + id + ", " + name + ", " + conditions + ", " + action + " }";
    }
}
