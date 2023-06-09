

import org.junit.Test;
import scr.main.model.QueenBee;
import scr.main.model.WorkerBee;

import java.util.Random;

import static junit.framework.TestCase.assertEquals;


public class SingletionTest {
    int COUNT = 10;
    private void sleep(int min, int max) {
        try {
            int time = new Random().nextInt(max - min) + min;
            Thread.sleep(time);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    @Test
    public void isSingleton() {

        QueenBee[] bee = new QueenBee[COUNT];

        for (int i = 0; i < COUNT; i++) {
            int j = i;
            new Thread(() -> {
                bee[j] = QueenBee.getInstance();
            }).start();
            sleep(10,50);
        }
        for(int i=0;i<COUNT - 1;i++) {
            assertEquals(bee[i].hashCode(), bee[i + 1].hashCode());
        }
    }
}
