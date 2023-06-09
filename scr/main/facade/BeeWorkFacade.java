package scr.main.facade;

import scr.main.model.DroneBee;
import scr.main.model.QueenBee;
import scr.main.model.Swarm;
import scr.main.model.WorkerBee;

import java.util.Random;

public class BeeWorkFacade {
    Random rand = new Random();
    public void start(int swarmSize, double beeSkills,double flowersToPollinate, double queenHunger) {

        Swarm swarm1 = new Swarm();
        Swarm swarm2 = new Swarm();
        Swarm swarm3 = new Swarm();

        QueenBee queenBee = QueenBee.getInstance();

        WorkerBee workerBee_prototype = new WorkerBee();
        workerBee_prototype = (WorkerBee) queenBee.createBee(beeSkills,workerBee_prototype);

        DroneBee droneBee_prototype = new DroneBee();
        droneBee_prototype = (DroneBee) queenBee.createBee(beeSkills,droneBee_prototype);

        queenBee.showBees(workerBee_prototype,droneBee_prototype);

        queenBee.tasksAssignment(swarm1,workerBee_prototype,droneBee_prototype,"flowers pollination",swarmSize);
        queenBee.tasksAssignment(swarm2,workerBee_prototype,droneBee_prototype,"hive construction",swarmSize);
        queenBee.tasksAssignment(swarm3,workerBee_prototype,droneBee_prototype,"queen feeding",swarmSize/10);

        queenBee.addObserver(swarm3);

        double amountOfWork1=flowersToPollinate;
        double amountOfWork3=queenHunger;

        int turn = 0;

        while(swarm1.isActiveTask() || swarm2.isActiveTask() || swarm3.isActiveTask()){
            turn++;

            if(swarm1.isActiveTask()) {
                amountOfWork1 = swarm1.makeAction(amountOfWork1);
                queenBee.beeDraw(swarm1);
            }
            if(swarm2.isActiveTask()) {
                swarm2.makeAction(0);
                queenBee.beeDraw(swarm2);
            }
            if(swarm3.isActiveTask()) {
                amountOfWork3 = swarm3.makeAction(amountOfWork3);
                queenBee.beeDraw(swarm3);
            }
            else if(turn % 5 == 0){
                if(rand.nextInt(10)>=8) {
                    queenBee.notifyAboutHunger();
                    amountOfWork3 = queenHunger;
                }
            }

        }
        System.out.println("\nAll tasks took: "+turn);
    }
}
