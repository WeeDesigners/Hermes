package agh.edu.hermes.types.attributes;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Condition {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String metric;
    private RelationType relation;
    private double value;


    public Condition() {}

    public Condition(String metric, RelationType relation, double value) {
        this.metric = metric;
        this.relation = relation;
        this.value = value;
    }


    public long getId() {
        return id;
    }

    public String getMetric() {
        return metric;
    }

    public RelationType getRelation() {
        return relation;
    }

    public double getValue() {
        return value;
    }

    @Override
    public String toString() {
        return "{" + metric + ", " + relation + ", " + value + "}";
    }
}
