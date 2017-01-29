package AnotherWorld;

import java.util.*;


public class ManagerHantu extends Thread {
    private LinkedList<MakhlukAbstrak> lm;
    private Layar l;
    
    ManagerHantu (LinkedList<MakhlukAbstrak> list, Layar lay) {
        lm = list;
        l = lay;
    }
    
    @Override
    public void run (){
        MakhlukAbstrak H;
        synchronized (l){
            H = new Hantu(l);
            lm.add(H);
        }

        while (!H.isVanished() ) {
            MakhlukManager.sleep(H.getdeltaT());
            synchronized(l){
                MakhlukManager.makhlukMovement(H, l, lm);
            }
            
        }
    }
}
