package scr.main.strategy;

import scr.main.model.Bee;
import scr.main.model.DroneBee;
import scr.main.model.Swarm;
import scr.main.model.WorkerBee;

import java.util.List;

public class QueenFeeding implements ActionStrategy{
    @Override
    public double CompleteTask(Swarm swarm, double amountOfWork) {
        for (Bee bee : swarm.getBees()){
            if(bee instanceof DroneBee){
                amountOfWork-=(int)(((DroneBee) bee).work()/5);
            }
            else{
                amountOfWork-=(int)(((WorkerBee) bee).work()/5);
            }
        }
        return amountOfWork;
    }

    @Override
    public void CheckIfTaskCompleted(Swarm swarm ,double amountOfWork) {
        if(amountOfWork<=0){
            swarm.deactivateTask();
            System.out.println("Queen Feeding Completed!");
        }
    }
}
