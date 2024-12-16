package agh.edu.hermes.controllers;


import agh.edu.hermes.services.PoliciesService;
import agh.edu.hermes.storages.Policies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/policies")
public class PoliciesController {

    private final PoliciesService policiesService;

    @Autowired
    public PoliciesController(PoliciesService policiesService) {
        this.policiesService = policiesService;
    }

    @PostMapping("/add/{id}")
    public String addRuleToPolicies(@PathVariable("id") long id){
        if(policiesService.addRuleToPoliciesById(id)){
            return "Rule id=" + id + " added successfully to policies";
        }
        return "Rule id=" + id + " not added to policies";
    }

    @DeleteMapping("/remove/{id}")
    public String removeRuleFromPolicies(@PathVariable("id") long id){
        if(policiesService.removeRuleFromPolicies(id)){
            return "Rule id=" + id + " is removed from policies";
        }
        return "Rule id=" + id + " is not removed from policies";
    }

    @GetMapping("")
    public Policies getPoliciesObject(){
        return policiesService.getPolicies();
    }

}
