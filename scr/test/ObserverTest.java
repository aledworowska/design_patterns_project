import org.junit.Test;
import scr.main.model.DroneBee;
import scr.main.model.QueenBee;
import scr.main.model.Swarm;
import scr.main.model.WorkerBee;

import static junit.framework.TestCase.assertEquals;

public class ObserverTest {
    @Test
    public void isNotifying() {
        Swarm swarm1 = new Swarm();
        QueenBee queenBee = QueenBee.getInstance();

        WorkerBee workerBee_prototype = new WorkerBee();
        workerBee_prototype = (WorkerBee) queenBee.createBee(50,workerBee_prototype);

        DroneBee droneBee_prototype = new DroneBee();
        droneBee_prototype = (DroneBee) queenBee.createBee(50,droneBee_prototype);

        queenBee.tasksAssignment(swarm1,workerBee_prototype,droneBee_prototype,"queen feeding",50);

        queenBee.addObserver(swarm1);

        swarm1.deactivateTask();
        queenBee.notifyAboutHunger();

        assertEquals(swarm1.isActiveTask(), true);

    }

}
