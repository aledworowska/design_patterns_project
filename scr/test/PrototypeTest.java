import org.junit.Test;

import scr.main.model.DroneBee;
import scr.main.model.QueenBee;
import scr.main.model.Swarm;
import scr.main.model.WorkerBee;

import static junit.framework.TestCase.assertEquals;
import static org.junit.Assert.assertNotEquals;


public class PrototypeTest {

    @Test
    public void beesAreDifferentObjects() {
        QueenBee queenBee = QueenBee.getInstance();
        Swarm swarm1 = new Swarm();

        WorkerBee workerBee_prototype = new WorkerBee();
        workerBee_prototype = (WorkerBee) queenBee.createBee(50,workerBee_prototype);

        DroneBee droneBee_prototype = new DroneBee();
        droneBee_prototype = (DroneBee) queenBee.createBee(50,droneBee_prototype);

        queenBee.tasksAssignment(swarm1,workerBee_prototype,droneBee_prototype,"queen feeding",50);

        boolean controlValue = true;
        for(int i=0;i<swarm1.getBees().size();i++){
            for(int j=i+1;j<swarm1.getBees().size();j++){
                if(swarm1.getBees().get(i)==swarm1.getBees().get(j))
                    controlValue = false;
            }
        }
        assertEquals(controlValue, true);
    }
    @Test
    public void beesAreIdentical() {
        QueenBee queenBee = QueenBee.getInstance();
        Swarm swarm1 = new Swarm();

        WorkerBee workerBee_prototype = new WorkerBee();
        workerBee_prototype = (WorkerBee) queenBee.createBee(50,workerBee_prototype);

        DroneBee droneBee_prototype = new DroneBee();
        droneBee_prototype = (DroneBee) queenBee.createBee(50,droneBee_prototype);

        queenBee.tasksAssignment(swarm1,workerBee_prototype,droneBee_prototype,"queen feeding",50);

        boolean controlValue = true;
        for(int i=0;i<swarm1.getBees().size();i++){
            for(int j=i+1;j<swarm1.getBees().size();j++){
                if(swarm1.getBees().get(i).getClass() == swarm1.getBees().get(j).getClass()){
                    if(swarm1.getBees().get(i).getStamina() != swarm1.getBees().get(j).getStamina() ||
                       swarm1.getBees().get(i).getMaxStamina() != swarm1.getBees().get(j).getMaxStamina() ||
                       swarm1.getBees().get(i).getWorkUnit() != swarm1.getBees().get(j).getWorkUnit() ||
                       swarm1.getBees().get(i).getExperience() != swarm1.getBees().get(j).getExperience()
                    ){
                        controlValue = false;
                    }
                    if(swarm1.getBees().get(i) instanceof WorkerBee){
                        if(((WorkerBee) swarm1.getBees().get(i)).getQuickness() != ((WorkerBee) swarm1.getBees().get(j)).getQuickness() ||
                           ((WorkerBee) swarm1.getBees().get(i)).getWeight() != ((WorkerBee) swarm1.getBees().get(j)).getWeight()){
                            controlValue = false;
                        }
                    }
                    if(swarm1.getBees().get(i) instanceof DroneBee){
                        if(((DroneBee) swarm1.getBees().get(i)).getStrength() != ((DroneBee) swarm1.getBees().get(j)).getStrength() ||
                                ((DroneBee) swarm1.getBees().get(i)).getSmartness() != ((DroneBee) swarm1.getBees().get(j)).getSmartness()){
                            controlValue = false;
                        }

                    }
                }
            }
        }
        assertEquals(controlValue, true);
    }
}
