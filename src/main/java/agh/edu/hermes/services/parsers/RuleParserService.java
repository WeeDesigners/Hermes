package agh.edu.hermes.services.parsers;

import agh.edu.hermes.types.PolicyRule;
import agh.edu.hermes.types.base.Rule;
import agh.edu.hermes.types.SlaRule;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import org.springframework.stereotype.Service;

@Service
public class RuleParserService {

    private final Gson gson;

    public RuleParserService(){
        gson = new Gson();
    }

    public String parseRuleObjectToString(Rule rule){
        return gson.toJson(rule);
    }

    public Rule parseRuleStringToObject(String ruleString){
        JsonObject jsonObject = gson.fromJson(ruleString, JsonObject.class);
        if(jsonObject.has("action")){
            return gson.fromJson(jsonObject, PolicyRule.class);
        }
        return gson.fromJson(jsonObject, SlaRule.class);
    }

}
