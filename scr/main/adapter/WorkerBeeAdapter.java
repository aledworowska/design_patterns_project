package scr.main.adapter;
import scr.main.model.DroneBee;
import scr.main.model.WorkerBee;

public class WorkerBeeAdapter extends DroneBee {
    private WorkerBee bee;

    public WorkerBeeAdapter(WorkerBee bee){
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
            workValue = Math.max((bee.getWorkUnit()*0.1+(bee.getExperience()/4)+bee.getWeight()+bee.getQuickness()*0.2),0.1);
            bee.decreaseStamina();
            bee.increaseExperience();
            if(bee.getStamina()==0){
                bee.setRest(true);
            }

        }
        else
            System.out.println("sth wrong with adapter");
        return workValue;
    }
}
