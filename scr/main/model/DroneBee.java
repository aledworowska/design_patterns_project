package scr.main.model;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class DroneBee extends Bee{
    private double smartness;
    private double strength;

    public DroneBee() {
    }
    public DroneBee(DroneBee droneBee) {
        super(droneBee);
        if (droneBee != null) {
            this.smartness = droneBee.smartness;
            this.strength = droneBee.strength;
        }
    }

    @Override
    public Bee clone() {
        return new DroneBee(this);
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
            workValue = Math.max((getWorkUnit()+getExperience()/2*getStrength())+getSmartness(),0.1);
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
