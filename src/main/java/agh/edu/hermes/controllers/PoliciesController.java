package agh.edu.hermes.controllers;


import agh.edu.hermes.services.PoliciesService;
import agh.edu.hermes.persistance.entities.Policies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/policies/active")
public class PoliciesController {

    @Autowired
    private PoliciesService policiesService;


    @GetMapping("")
    public ResponseEntity<Policies> getPoliciesObject(){
        return ResponseEntity.ok(policiesService.getPolicies());
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> addRuleToPolicies(@PathVariable("id") long id){
        if(policiesService.addRuleToPoliciesById(id) != null){
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.badRequest().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> removeRuleFromPolicies(@PathVariable("id") long id){
        if(policiesService.removeRuleFromPolicies(id) != null){
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.badRequest().build();
    }

}
