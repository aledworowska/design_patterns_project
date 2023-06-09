package scr.main.strategy;

import scr.main.adapter.DroneBeeAdapter;
import scr.main.model.Bee;
import scr.main.model.DroneBee;
import scr.main.model.Swarm;
import scr.main.model.WorkerBee;

public class FlowersPollination implements ActionStrategy{

    @Override
    public double CompleteTask(Swarm swarm, double amountOfWork){
        for (Bee bee : swarm.getBees()){
            if(bee instanceof WorkerBee){
                //System.out.println("DOBRA PSZCZOLKA SIE TYM ZAJELA :"+ (int)(((WorkerBee) bee).work()));
                amountOfWork-=(int)(((WorkerBee) bee).work());
            }
            else{
                DroneBeeAdapter droneBeeAdapter = new DroneBeeAdapter((DroneBee) bee);
                //System.out.println("drone bee praca (int) : " + (int)droneBeeAdapter.work());

                amountOfWork-= (int)droneBeeAdapter.work();
            }
        }
        return amountOfWork;//ile kwiatków zostało do zapytlenia
    }

    @Override
    public void CheckIfTaskCompleted(Swarm swarm,double amountOfWork) {
        if(amountOfWork<=0){
            swarm.deactivateTask();
            System.out.println("FlowersPollination Completed!");
        }

    }
}
