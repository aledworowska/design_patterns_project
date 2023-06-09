package scr.main.model;

import java.util.ArrayList;
import java.util.List;

import lombok.Getter;
import lombok.Setter;
import scr.main.QueenBeeObserver;
import scr.main.strategy.ActionStrategy;
import scr.main.strategy.FlowersPollination;
import scr.main.strategy.HiveConstruction;
import scr.main.strategy.QueenFeeding;

@Getter @Setter
public class Swarm implements QueenBeeObserver{
    List<Bee> bees = new ArrayList<>();
    boolean activeTask;
    String task;
    ActionStrategy strategy;

    public Swarm() {
        this.activeTask = true;
    }

    public void addBee(Bee bee) {
        this.bees.add(bee);
    }

    public void deleteBee(Bee bee) {
        this.bees.remove(bee);
    }

    public void deactivateTask(){
        this.activeTask=false;
    }
    public void activateTask(){
        this.activeTask=true;
    }

    @Override
    public void queenHungry() {
        activateTask();
    }

    public void setTaskAndStrategy(String task) {
        this.task = task;
        if(task.equals("flowers pollination")){
            strategy = new FlowersPollination();
            //System.out.println("flowers pollination");
        } else if (task.equals("hive construction")) {
            strategy = new HiveConstruction();
            //System.out.println("hive construction");
        } else if (task.equals("queen feeding")){
            strategy = new QueenFeeding();
            //System.out.println("queen feeding");
        }
        else System.out.println("Blad w przydzielaniu strategii");
    }
    public double makeAction(double amountOfWork){
        amountOfWork = strategy.CompleteTask(this,amountOfWork);
        strategy.CheckIfTaskCompleted(this,amountOfWork);
        return amountOfWork;
    }
}
