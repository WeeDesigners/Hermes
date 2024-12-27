package agh.edu.hermes.controllers;


import agh.edu.hermes.services.PoliciesService;
import agh.edu.hermes.storages.Policies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<?> addRuleToPolicies(@PathVariable("id") long id){
        if(policiesService.addRuleToPoliciesById(id)){
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.badRequest().build();
    }

    @DeleteMapping("/remove/{id}")
    public ResponseEntity<?> removeRuleFromPolicies(@PathVariable("id") long id){
        if(policiesService.removeRuleFromPolicies(id)){
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.badRequest().build();
    }

    @GetMapping("")
    public ResponseEntity<Policies> getPoliciesObject(){
        return ResponseEntity.ok(policiesService.getPolicies());
    }

}
