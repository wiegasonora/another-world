package AnotherWorld;

import java.util.*;


public class ManagerPsikopat extends Thread {
    private LinkedList<MakhlukAbstrak> lm;
    private Layar l;
    
    ManagerPsikopat (LinkedList<MakhlukAbstrak> list, Layar lay) {
        lm = list;
        l = lay;
    }
    
    @Override
    public void run (){
        MakhlukAbstrak S;
        synchronized (l){
            S = new Psikopat(l);
            lm.add(S);
        }

        while (!S.isVanished() ) {
            MakhlukManager.sleep(S.getdeltaT());
            synchronized(l){
                MakhlukManager.makhlukMovement(S, l, lm);
            }
            
        }
    }
}
