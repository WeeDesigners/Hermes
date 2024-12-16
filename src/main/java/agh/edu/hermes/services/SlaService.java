package agh.edu.hermes.services;


import agh.edu.hermes.services.parsers.SlaParserService;
import agh.edu.hermes.storages.RuleStorage;
import agh.edu.hermes.types.Sla;
import agh.edu.hermes.storages.SlaStorage;
import agh.edu.hermes.types.SlaRule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SlaService {

    private final SlaParserService slaParserService;

    @Autowired
    public SlaService(SlaParserService slaParserService) {
        this.slaParserService = slaParserService;
    }

    public boolean create(String slaString){
        Sla sla = slaParserService.parseSlaString(slaString);
        if(sla == null){
            return false;
        }
        SlaStorage ss = SlaStorage.getInstance();
        ss.addSla(sla);
        return true;
    }

    public boolean addRuleToSlaById(long slaId, long ruleId){
        RuleStorage rs = RuleStorage.getInstance();
        SlaStorage ss = SlaStorage.getInstance();

        SlaRule rule = rs.getSlaRule(ruleId);
        Sla sla = ss.getSlaById(slaId);

        if(rule == null || sla == null){
            return false;
        }
        return sla.addRule(rule);
    }

    public boolean removeRuleFromSla(long slaId, long ruleId){
        SlaStorage ss = SlaStorage.getInstance();
        Sla sla = ss.getSlaById(slaId);
        SlaRule removed = sla.removeRule(ruleId);
        return (removed != null);
    }

    public Sla getSla(long id){
        return SlaStorage.getInstance().getSlaById(id);
    }

    public boolean removeSlaById(long id){
        SlaStorage ss = SlaStorage.getInstance();
        return (ss.removeSlaById(id) != null);
    }

    public String getSlaString(long id){
        Sla sla = SlaStorage.getInstance().getSlaById(id);
        if(sla == null){
            return null;
        }
        return sla.toString();
    }

    public List<Sla> getSlaList(){
        return SlaStorage.getInstance().getSlas();
    }

}
