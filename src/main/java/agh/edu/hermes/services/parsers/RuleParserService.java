package agh.edu.hermes.services.parsers;

import agh.edu.hermes.persistance.entities.PolicyRule;
import agh.edu.hermes.persistance.entities.SlaRule;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import org.springframework.stereotype.Service;

@Service
public class RuleParserService {

    private final Gson gson;

    public RuleParserService(){
        gson = new Gson();
    }

    public String parseSlaRuleObjectToString(SlaRule rule){
        return gson.toJson(rule);
    }

    public String parsePolicyRuleObjectToString(PolicyRule rule){
        return gson.toJson(rule);
    }

    public SlaRule parseSlaRuleStringToObject(String ruleString){
        JsonObject jsonObject = gson.fromJson(ruleString, JsonObject.class);
        return gson.fromJson(jsonObject, SlaRule.class);
    }

    public PolicyRule parsePolicyRuleStringToObject(String ruleString){
        JsonObject jsonObject = gson.fromJson(ruleString, JsonObject.class);
        return gson.fromJson(jsonObject, PolicyRule.class);
    }

}
