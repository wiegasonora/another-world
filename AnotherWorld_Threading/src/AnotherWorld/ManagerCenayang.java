package AnotherWorld;

import java.util.*;


public class ManagerCenayang extends Thread {
    private LinkedList<MakhlukAbstrak> lm;
    private Layar l;
    
    ManagerCenayang (LinkedList<MakhlukAbstrak> list, Layar lay) {
        lm = list;
        l = lay;
    }
    @Override
    public void run (){
        MakhlukAbstrak C;

        synchronized (l){
            C = new Cenayang(l);
            lm.add(C);
        }

        while (!C.isVanished() ) {
            MakhlukManager.sleep(C.getdeltaT());
            synchronized(l){
                MakhlukManager.makhlukMovement(C, l, lm);
            }
            
        }
    }
}
