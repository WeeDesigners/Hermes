package agh.edu.hermes.types;

import agh.edu.hermes.generators.IdGenerator;
import agh.edu.hermes.types.attributes.*;


public class SlaRule {

    public final long id;
    public final ValueType valueType;
    public final Condition condition;

    public SlaRule(ValueType valueType, Condition condition) {
        this.id = IdGenerator.getRuleId();
        this.valueType = valueType;
        this.condition = condition;
    }
}
