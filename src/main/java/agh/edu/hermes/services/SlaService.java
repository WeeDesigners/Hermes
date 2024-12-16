package agh.edu.hermes.services;


import agh.edu.hermes.checker.SlaViolationChecker;
import agh.edu.hermes.services.parsers.SlaParserService;
import agh.edu.hermes.storages.RuleStorage;
import agh.edu.hermes.storages.Sla;
import agh.edu.hermes.types.SlaRule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SlaService {

    private final SlaParserService slaParserService;

    @Autowired
    public SlaService(SlaParserService slaParserService) {
        this.slaParserService = slaParserService;
    }

    public boolean addRuleToSla(SlaRule rule){
        Sla sla = Sla.getInstance();
        //TODO (or TODELETE)
        if(!SlaViolationChecker.checkRule(null)){
            return sla.addRule(rule);
        }
        return false;
    }

    public boolean initializeSla(String slaString){
        return slaParserService.parseSlaString(slaString);
    }

    public boolean addRuleToSlaById(long id){
        RuleStorage rs = RuleStorage.getInstance();
        SlaRule rule = rs.getSlaRule(id);
        if(rule == null){
            return false;
        }
        return addRuleToSla(rule);
    }

    public boolean removeRuleFromSla(long id){
        Sla sla = Sla.getInstance();
        SlaRule removed = sla.removeRule(id);
        return (removed != null);
    }

    public Sla getSla(){
        return Sla.getInstance();
    }

    public String getSlaString(){
        return slaParserService.slaToJson(Sla.getInstance());
    }

}
