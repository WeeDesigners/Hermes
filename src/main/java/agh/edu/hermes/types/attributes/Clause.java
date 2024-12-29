package agh.edu.hermes.types.attributes;

import jakarta.persistence.*;

@Entity
public class Clause {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String metric;
    @Enumerated(EnumType.STRING)
    private RelationType relation;
    private double value;


    public Clause() {}

    public Clause(String metric, RelationType relation, double value) {
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
