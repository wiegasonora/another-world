/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MakhlukController;

import MakhlukModel.MakhlukAbstrak;
import MakhlukView.Layar;
import java.util.LinkedList;

/**
 *
 * @author rezaramadhan_m
 */
public class ThreadMakhluk extends Thread {
    MakhlukAbstrak makhluk;
    LinkedList<MakhlukAbstrak> list;
    Layar layar;
    Listener ltn;
    public ThreadMakhluk(MakhlukAbstrak ma, LinkedList<MakhlukAbstrak> list1, Layar la, Listener L) {
	makhluk = ma;
	list = list1;
	layar = la;
	ltn = L;
    }
    
    @Override
    public void run() {
	while (!makhluk.isVanished() && !ltn.stop ) {
            MakhlukManager.sleep(1000*makhluk.getdeltaT());
	    while (ltn.pause) {
		System.out.println("paused");
	    }
	    synchronized (list) {
		MakhlukManager.makhlukMovement(makhluk, layar, list);
	    }

        }
	makhluk.mati(layar);
    }
}
