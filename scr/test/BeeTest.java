import org.junit.Assert;
import org.junit.Test;
import scr.main.model.DroneBee;
import scr.main.model.QueenBee;
import scr.main.model.Swarm;
import scr.main.model.WorkerBee;

import static junit.framework.TestCase.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class BeeTest {
    WorkerBee workerBee = new WorkerBee();
    DroneBee droneBee = new DroneBee();
    QueenBee queenBee = QueenBee.getInstance();
    public void init() {
        workerBee.setMaxStamina(18);
        workerBee.setStamina(18);
        workerBee.setWorkUnit(2.6);
        workerBee.setQuickness(0.2);
        workerBee.setWeight(0.4);
        workerBee.setRest(false);

        droneBee.setMaxStamina(18);
        droneBee.setStamina(18);
        droneBee.setWorkUnit(0.2);
        droneBee.setStrength(0.5);
        droneBee.setSmartness(2.5);
        droneBee.setRest(false);

    }
    @Test
    public void setRestWorks() {
        init();
        boolean controlValue = workerBee.isRest();
        workerBee.setRest(true);
        assertNotEquals(workerBee.isRest(),controlValue);
    }
    @Test
    public void increaseExperienceWorks() {
        init();
        double controlValue = workerBee.getExperience();
        workerBee.increaseExperience();
        assertEquals(workerBee.getExperience(),controlValue+1);
    }
    @Test
    public void increaseStaminaWorks() {
        init();
        int controlValue = workerBee.getStamina();
        workerBee.increaseStamina();
        assertEquals(workerBee.getStamina(),controlValue+1);
    }
    @Test
    public void decreaseStaminaWorks() {
        init();
        int controlValue = workerBee.getStamina();
        workerBee.decreaseStamina();
        assertEquals(workerBee.getStamina(),controlValue-1);
    }
    @Test
    public void aggregateDroneBeeWorks() {
        init();
        Swarm swarm = new Swarm();

        swarm.addBee(droneBee);
        swarm.addBee(droneBee);

        queenBee.aggregateDroneBee(swarm,droneBee,droneBee);

        assertEquals(1,swarm.getBees().size());
        assertEquals(18,swarm.getBees().get(0).getMaxStamina());
        assertEquals(18,swarm.getBees().get(0).getStamina());
        assertEquals(0.4,swarm.getBees().get(0).getWorkUnit());
        assertEquals(5.25,((DroneBee)(swarm.getBees().get(0))).getSmartness());
        assertEquals(1.0,((DroneBee)(swarm.getBees().get(0))).getStrength());
        assertEquals(0.0,swarm.getBees().get(0).getExperience());
        assertEquals(false,swarm.getBees().get(0).isRest());
    }
    @Test
    public void aggregateWorkerBeeWorks() {
        init();
        Swarm swarm = new Swarm();

        swarm.addBee(workerBee);
        swarm.addBee(workerBee);

        queenBee.aggregateWorkerBee(swarm,workerBee,workerBee);

        assertEquals(1,swarm.getBees().size());
        assertEquals(18,swarm.getBees().get(0).getMaxStamina());
        assertEquals(18,swarm.getBees().get(0).getStamina());
        assertEquals(5.2,swarm.getBees().get(0).getWorkUnit());
        assertEquals(0.42000000000000004,((WorkerBee)(swarm.getBees().get(0))).getQuickness());
        assertEquals(0.8,((WorkerBee)(swarm.getBees().get(0))).getWeight());
        assertEquals(0.0,swarm.getBees().get(0).getExperience());
        assertEquals(false,swarm.getBees().get(0).isRest());
    }
}
