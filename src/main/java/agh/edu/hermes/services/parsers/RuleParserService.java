package agh.edu.hermes.services.parsers;

import agh.edu.hermes.types.NotificationRule;
import agh.edu.hermes.types.Rule;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import org.springframework.stereotype.Service;

@Service
public class RuleParserService {

    public RuleParserService(){

    }

    public String parseRuleObjectToString(Rule rule){

        return "";
    }

    public Rule parseRuleStringToObject(String ruleString){
        Gson gson = new Gson();

        JsonObject jsonObject = gson.fromJson(ruleString, JsonObject.class);

        if(jsonObject.has("email")){
            return gson.fromJson(jsonObject, NotificationRule.class);
        }
        return gson.fromJson(jsonObject, Rule.class);
    }

}
