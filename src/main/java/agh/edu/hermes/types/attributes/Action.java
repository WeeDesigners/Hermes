package agh.edu.hermes.types.attributes;


import jakarta.persistence.*;
import org.hibernate.annotations.Type;

import java.util.Map;

@Entity
public class Action {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String collectionName;
    private String actionName;
    @ElementCollection
    @CollectionTable(name = "params")
    @MapKeyColumn(name = "key")
    @Column(name = "value")
    private Map<String, String> params;

    public Action() {}

    public Action(String collectionName, String actionName, Map<String, String> params) {
        this.collectionName = collectionName;
        this.actionName = actionName;
        this.params = params;
    }

    public long getId() {
        return id;
    }

    public String getCollectionName() {
        return collectionName;
    }

    public String getActionName() {
        return actionName;
    }

    public Map<String, String> getParams() {
        return params;
    }
}
