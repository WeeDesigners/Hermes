package agh.edu.hermes.types;

import agh.edu.hermes.generators.IdGenerator;
import agh.edu.hermes.types.attributes.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class PolicyRule {

    public final long id;
    public final String name;
    private final List<Condition> conditions;
    public final Action action;

    public PolicyRule(String name, Action action) {
        this.id = IdGenerator.getRuleId();
        this.name = name;
        this.conditions = new ArrayList<>();
        this.action = action;
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
