package agh.edu.hermes.services.parsers;

import agh.edu.hermes.storages.Sla;
import agh.edu.hermes.types.attributes.SlaType;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import org.springframework.stereotype.Service;

@Service
public class SlaParserService {

    private final Gson gson;

    public SlaParserService() {
        this.gson = new Gson();
    }

    public boolean parseSlaString(String slaJson) {
        JsonObject jsonObject = gson.fromJson(slaJson, JsonObject.class);
        if (!jsonObject.has("type")) return false;
        if (!jsonObject.has("clientId")) return false;
        if(!jsonObject.has("applicationId")) return false;

        Sla sla = Sla.getInstance();
        sla.setType(SlaType.valueOf(jsonObject.get("type").getAsString()));
        sla.setClientId(jsonObject.get("clientId").getAsString());
        sla.setApplicationId(jsonObject.get("applicationId").getAsString());

        return true;
    }

    public String slaToJson(Sla sla) {
        JsonObject jsonObject = gson.toJsonTree(sla).getAsJsonObject();
        return jsonObject.toString();
    }
}
