package agh.edu.hermes.types;

import agh.edu.hermes.generators.IdGenerator;
import agh.edu.hermes.types.attributes.*;

import java.util.Objects;

public class PolicyRule {

    public final long id;
    public final String name;
    public final Condition condition;
    public final Action action;

    public PolicyRule(String name, Condition condition, Action action) {
        this.id = IdGenerator.getRuleId();
        this.name = name;
        this.condition = condition;
        this.action = action;
    }

    @Override
    public boolean equals(Object o) {
        PolicyRule rule = (PolicyRule) o;
        return super.equals(o)
                && this.id == rule.id
                && this.name.equals(rule.name)
                && this.condition.equals(rule.condition)
                && this.action.equals(rule.action);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, condition, action);
    }

    @Override
    public String toString() {
        // TODO -> better concat
        return "{ " + id + ", " + name + ", " + condition + ", " + action + " }";
    }
}
