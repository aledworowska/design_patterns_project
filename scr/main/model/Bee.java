package scr.main.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
abstract public class Bee {
    private int stamina;
    private int maxStamina;
    private double workUnit;
    private double experience;
    private boolean rest;

    public Bee() {
    }
    public Bee(Bee bee) {
        if (bee != null) {
            this.maxStamina = bee.maxStamina;
            this.stamina = bee.maxStamina;
            this.workUnit = bee.workUnit;
            this.experience = 1;
            this.rest = false;
        }
    }

    private  void eat(){

    }
    public void setRest(boolean isRest){
        this.rest = isRest;
    }
    public void increaseExperience(){
        this.experience+= 1;
    }
    public void increaseStamina() {
      this.stamina+= 1;
    }
    public void decreaseStamina(){
        this.stamina-= 1;
    }
    public abstract Bee clone();
}
