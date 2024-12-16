package agh.edu.hermes.types.attributes;


public class Condition {
    public final Metric metric;
    public final RelationType relation;
    public final double value;

    public Condition(Metric metric, RelationType relation, double value) {
        this.metric = metric;
        this.relation = relation;
        this.value = value;
    }

    @Override
    public String toString() {
        return "{" + metric + ", " + relation + ", " + value + "}";
    }
}
