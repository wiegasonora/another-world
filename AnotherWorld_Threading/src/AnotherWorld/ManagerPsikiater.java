package AnotherWorld;

import java.util.*;


public class ManagerPsikiater extends Thread {
    private LinkedList<MakhlukAbstrak> lm;
    private Layar l;
    
    ManagerPsikiater (LinkedList<MakhlukAbstrak> list, Layar lay) {
        lm = list;
        l = lay;
    }
    @Override
    public void run (){
        MakhlukAbstrak P;
        synchronized (l){
            P = new Psikiater(l);
            lm.add(P);
        }

        while (!P.isVanished() ) {
            MakhlukManager.sleep(P.getdeltaT());
            synchronized(l){
                MakhlukManager.makhlukMovement(P, l, lm);
            }
            
        }
    }
}
