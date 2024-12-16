package agh.edu.hermes.services.parsers;

import agh.edu.hermes.types.Sla;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import org.springframework.stereotype.Service;

@Service
public class SlaParserService {

    private final Gson gson;

    public SlaParserService() {
        this.gson = new Gson();
    }

    public Sla parseSlaString(String slaJson) {
        JsonObject jsonObject = gson.fromJson(slaJson, JsonObject.class);
        return gson.fromJson(jsonObject, Sla.class);
    }

    public String slaToJson(Sla sla) {
        JsonObject jsonObject = gson.toJsonTree(sla).getAsJsonObject();
        return jsonObject.toString();
    }
}
