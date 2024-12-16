package agh.edu.hermes.storages;

import agh.edu.hermes.types.Sla;

import java.util.ArrayList;
import java.util.List;

public class SlaStorage {

    private final List<Sla> slaList;
    private final static SlaStorage storage = new SlaStorage();

    public static SlaStorage getInstance() {
        return storage;
    }

    private SlaStorage() {
        this.slaList = new ArrayList<>();
    }

    public List<Sla> getSlas(){
        return new ArrayList<>(this.slaList);
    }

    public void addSla(Sla sla) {
        this.slaList.add(sla);
    }

    public Sla removeSla(Sla sla) {
        if(this.slaList.remove(sla)){
            return sla;
        }
        return null;
    }

    public Sla removeSlaById(long id) {
        Sla sla = getSlaById(id);
        return removeSla(sla);
    }

    public void clearSlas() {
        this.slaList.clear();
    }

    public Sla getSlaById(long id){
        for (Sla sla : this.slaList) {
            if(sla.id == id){
                return sla;
            }
        }
        return null;
    }

}
