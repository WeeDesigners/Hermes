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
    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(
            name = "params",
            joinColumns = {
                @JoinColumn(
                        name = "action_id",
                        referencedColumnName = "id")
            })
    @MapKeyColumn(name = "param_key")
    @Column(name = "params")
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
