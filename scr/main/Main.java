package scr.main;

import scr.main.facade.BeeWorkFacade;

public class Main {
    public static void main(String[] args){

        BeeWorkFacade beeWork = new BeeWorkFacade();
        beeWork.start(45,50.0,50000,100);


    }
}
