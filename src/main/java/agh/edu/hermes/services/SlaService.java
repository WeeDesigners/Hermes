package agh.edu.hermes.services;


import agh.edu.hermes.persistance.repositories.SlaRepository;
import agh.edu.hermes.persistance.repositories.SlaRuleRepository;
import agh.edu.hermes.services.parsers.SlaParserService;
import agh.edu.hermes.persistance.entities.Sla;
import agh.edu.hermes.persistance.entities.SlaRule;
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

    public boolean addRuleToSlaById(long slaId, long ruleId){
        SlaRule rule = slaRuleRepository.getReferenceById(ruleId);
        Sla sla = slaRepository.getReferenceById(slaId);

        if(sla.addRule(rule)){
            slaRepository.save(sla);
            return true;
        }
        return false;
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

    public Sla modifySla(long id, String slaString){
        Sla newSla = slaParserService.parseSlaString(slaString);
        newSla.setId(id);
        return slaRepository.save(newSla);
    }

    public List<Sla> getSlaList(){
        return slaRepository.findAll();
    }

}
