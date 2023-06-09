package scr.main.strategy;

import scr.main.model.Bee;
import scr.main.model.Swarm;

import java.util.List;

public interface ActionStrategy {

    double CompleteTask(Swarm swarm, double amountOfWork);

    void CheckIfTaskCompleted(Swarm swarm, double amountOfWork);
}
