package scr.main.strategy;

import scr.main.adapter.DroneBeeAdapter;
import scr.main.adapter.WorkerBeeAdapter;
import scr.main.model.Bee;
import scr.main.model.DroneBee;
import scr.main.model.Swarm;
import scr.main.model.WorkerBee;

import java.util.List;

public class HiveConstruction implements ActionStrategy{
    @Override
    public double CompleteTask(Swarm swarm, double amountOfWork) {
        for (Bee bee : swarm.getBees()){
            if(bee instanceof DroneBee){
                amountOfWork+=((DroneBee) bee).work();
            }
            else{
                WorkerBeeAdapter workerBeeAdapter = new WorkerBeeAdapter((WorkerBee) bee);
                amountOfWork+=workerBeeAdapter.work();
            }
        }
        return amountOfWork;
    }

    @Override
    public void CheckIfTaskCompleted(Swarm swarm, double amountOfWork) {
        if(amountOfWork>=6000){
            swarm.deactivateTask();
            System.out.println("HiveConstruction Completed!");
        }
    }
}
