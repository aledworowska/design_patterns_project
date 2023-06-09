package scr.main.model;

import scr.main.QueenBeeObserver;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class QueenBee {
    private static QueenBee instance;
    private final List<QueenBeeObserver> observers = new ArrayList<>();
    private int hunger;                                //jeśli poniżej jakiejś warości informuje
    Random rand = new Random();
    public static QueenBee getInstance() {
        if (instance == null) {
            synchronized (QueenBee.class) {
                if (instance == null) {
                    instance = new QueenBee();
                }
            }
        }
        return instance;
    }
    public void addObserver(QueenBeeObserver observer) {
        observers.add(observer);
    }

    public void notifyAboutHunger() {
        System.out.println("QueenBee: I'm hungry!");
        for (QueenBeeObserver observer : observers) {
            observer.queenHungry();
        }
    }
    public void tasksAssignment(Swarm swarm, WorkerBee workerBee1_prototype, DroneBee droneBee1_prototype, String task, Integer swarmSize){
        Random rand = new Random();

        swarm.setTaskAndStrategy(task);

        for(int i=0;i<(rand.nextInt(swarmSize)+1);i++){
            WorkerBee workerBee = (WorkerBee) workerBee1_prototype.clone();
            swarm.addBee(workerBee);
        }
        swarmSize-=swarm.getBees().size();
        for(int i=0;i<swarmSize;i++){
            DroneBee droneBee = (DroneBee) droneBee1_prototype.clone();
            swarm.addBee(droneBee);
        }
    }
    public void showBees(WorkerBee workerBee,DroneBee droneBee){
        System.out.println("\n            Worker Bees:    |             DroneBee:\n                            |");
        System.out.println("Stamina:     "+workerBee.getStamina()+"             |             "+droneBee.getStamina());
        System.out.println("Work Unit:  "+workerBee.getWorkUnit()+"             |             "+droneBee.getWorkUnit());
        System.out.println("Quickness:  "+workerBee.getQuickness()+"             | Strength:   "+droneBee.getStrength());
        System.out.println("Weight:     "+workerBee.getWeight()+"             | Smartness:  "+droneBee.getSmartness()+"\n");

    }

    public Bee createBee(double beeSkills,Bee bee) {

        bee.setMaxStamina(rand.nextInt(10)+11);
        bee.setStamina(bee.getMaxStamina());

        beeSkills-=bee.getMaxStamina();
        bee.setWorkUnit((double) (rand.nextInt((int) (beeSkills-2))+1)/10);

        if(bee instanceof WorkerBee) {
            beeSkills -= bee.getWorkUnit() * 10;
            ((WorkerBee) bee).setQuickness((double) (rand.nextInt((int) (beeSkills - 1)) + 1) / 10);

            beeSkills -= ((WorkerBee) bee).getQuickness() * 10;
            ((WorkerBee) bee).setWeight(beeSkills / 10);
        }
        else if(bee instanceof DroneBee){
            beeSkills-=bee.getWorkUnit()*10;
            ((DroneBee) bee).setStrength((double)(rand.nextInt((int) (beeSkills-1))+1)/10);

            beeSkills-= ((DroneBee) bee).getStrength()*10;
            ((DroneBee) bee).setSmartness(beeSkills/10);
        }
        return bee;
    }
    public void aggregateWorkerBee(Swarm swarm,WorkerBee bee1, WorkerBee bee2) {
        WorkerBee workerBee = new WorkerBee();

        workerBee.setMaxStamina(Math.min(bee1.getMaxStamina(),bee2.getMaxStamina()));
        workerBee.setStamina(Math.min(bee1.getStamina(),bee2.getStamina()));
        workerBee.setWorkUnit(bee1.getWorkUnit()+bee2.getWorkUnit());
        workerBee.setQuickness((bee1.getQuickness()+bee2.getQuickness()*1.1));
        workerBee.setWeight(bee1.getWeight()+ bee2.getWeight());
        workerBee.setExperience(bee1.getExperience()+bee2.getExperience()*1.3);
        workerBee.setRest(bee1.isRest());

        swarm.getBees().remove(bee1);
        swarm.getBees().remove(bee2);
        swarm.getBees().add(workerBee);

    }
    public void aggregateDroneBee(Swarm swarm,DroneBee bee1, DroneBee bee2) {
        DroneBee droneBee = new DroneBee();

        droneBee.setMaxStamina(Math.min(bee1.getMaxStamina(),bee2.getMaxStamina()));
        droneBee.setStamina(Math.min(bee1.getStamina(),bee2.getStamina()));
        droneBee.setWorkUnit(bee1.getWorkUnit()+bee2.getWorkUnit());
        droneBee.setSmartness((bee1.getSmartness()+bee2.getSmartness()*1.1));
        droneBee.setStrength(bee1.getStrength()+ bee2.getStrength());
        droneBee.setExperience(bee1.getExperience()+bee2.getExperience()*1.3);
        droneBee.setRest(bee1.isRest());

        swarm.getBees().add(droneBee);
        swarm.getBees().remove(bee1);
        swarm.getBees().remove(bee2);

    }

    public void beeDraw(Swarm swarm) {
        int beeNumber1,beeNumber2;

        beeNumber1 = (rand.nextInt((swarm.getBees().size())));
        beeNumber2 = (rand.nextInt((swarm.getBees().size())));

        if(beeNumber1!=beeNumber2 && swarm.getBees().get(beeNumber1).getClass()==swarm.getBees().get(beeNumber2).getClass()){

            if(swarm.getBees().get(beeNumber1) instanceof WorkerBee)
                aggregateWorkerBee(swarm,(WorkerBee)swarm.getBees().get(beeNumber1),(WorkerBee)swarm.getBees().get(beeNumber2));
            else
                aggregateDroneBee(swarm, (DroneBee) swarm.getBees().get(beeNumber1), (DroneBee) swarm.getBees().get(beeNumber2));
        }

    }
}