package agh.edu.hermes.services;


import agh.edu.hermes.repositories.SlaRepository;
import agh.edu.hermes.repositories.SlaRuleRepository;
import agh.edu.hermes.services.parsers.SlaParserService;
import agh.edu.hermes.types.Sla;
import agh.edu.hermes.types.SlaRule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SlaService {

    @Autowired
    private SlaRepository slaRepository;
    @Autowired
    private SlaRuleRepository slaRuleRepository;
    @Autowired
    private SlaRuleService slaRuleService;
    @Autowired
    private SlaParserService slaParserService;


    public Sla create(String slaString){
        Sla sla = slaParserService.parseSlaString(slaString);
        if(sla == null){
            return null;
        }
        for(SlaRule rule : sla.getSlaRules()){
            slaRuleService.addRuleObject(rule);
        }
        return slaRepository.save(sla);
    }

    public Sla addRuleToSlaById(long slaId, long ruleId){
        SlaRule rule = slaRuleRepository.getReferenceById(ruleId);
        Sla sla = slaRepository.getReferenceById(slaId);

        if(sla.addRule(rule)){
            return slaRepository.save(sla);
        }
        return null;
    }

    public Sla removeRuleFromSla(long slaId, long ruleId){
        Sla sla = slaRepository.getReferenceById(slaId);
        sla.removeRule(ruleId);
        return slaRepository.save(sla);
    }

    public Sla getSla(long id){
        return slaRepository.getReferenceById(id);
    }

    public void removeSlaById(long id){
        slaRepository.deleteById(id);
    }

    public String getSlaString(long id){
        Sla sla = slaRepository.getReferenceById(id);
        return sla.toString();
    }

    public List<Sla> getSlaList(){
        return slaRepository.findAll();
    }

}
