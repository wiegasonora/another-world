package AnotherWorld;

import java.util.*;


public class ManagerBawang extends Thread {
    private LinkedList<MakhlukAbstrak> lm;
    private Layar l;
    
    ManagerBawang (LinkedList<MakhlukAbstrak> list, Layar lay) {
        lm = list;
        l = lay;
    }
    
    @Override
    public void run (){
        MakhlukAbstrak B;
        synchronized (l){
            B = new BawangPutih(l);
            lm.add(B);
        }

        while (!B.isVanished() ) {
            MakhlukManager.sleep(B.getdeltaT());

            synchronized(l){
                MakhlukManager.makhlukMovement(B, l, lm);
            }
            
        }
    }
}
