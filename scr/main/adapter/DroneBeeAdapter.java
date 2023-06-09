package scr.main.adapter;
import scr.main.model.DroneBee;
import scr.main.model.WorkerBee;

public class DroneBeeAdapter extends WorkerBee {
    private DroneBee bee;

    public DroneBeeAdapter(DroneBee bee){
        this.bee = bee;
    }
    @Override
    public double work(){
        double workValue = 0;
        if(bee.isRest()){
            bee.increaseStamina();
            if(bee.getStamina()==bee.getMaxStamina()){
                bee.setRest(false);
            }
        }
        else if(!bee.isRest()){
            workValue = Math.max((bee.getWorkUnit()*0.7+(bee.getExperience()/1.2)-bee.getStrength()+bee.getSmartness()*2),1);
            bee.decreaseStamina();
            bee.increaseExperience();
            if(bee.getStamina()==0)
                bee.setRest(true);
        }
        else
                System.out.println("sth wrong with adapter");
        return workValue;
    }
}
