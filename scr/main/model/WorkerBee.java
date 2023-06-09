package scr.main.model;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class WorkerBee extends Bee {

    private double quickness;
    private double weight;
    public WorkerBee() {
    }
    public WorkerBee(WorkerBee workerBee) {
        super(workerBee);
        if (workerBee != null) {
            this.quickness = workerBee.quickness;
            this.weight = workerBee.weight;
        }
    }
    @Override
    public Bee clone() {
        return new WorkerBee(this);
    }
    public double work(){
        double workValue = 0;
        if(this.isRest()){
            this.increaseStamina();
            if(this.getStamina()==this.getMaxStamina()){
                this.setRest(false);
            }
        }
        else if(!this.isRest()){
            workValue = Math.max((getWorkUnit()*1.5*getExperience()*getQuickness())-getWeight(),1);
            this.decreaseStamina();
            this.increaseExperience();
            if(this.getStamina()==0)
                this.setRest(true);
        }
        else
        System.out.println("sth wrong with bee");
        return workValue;
    }
}
