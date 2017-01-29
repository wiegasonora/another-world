/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MakhlukModel;

import MakhlukView.Layar;
import java.util.Random;

/**
 *
 * @author rezaramadhan_m
 */
public class ManusiaBiasa extends MakhlukHidup {

    public ManusiaBiasa(Layar l) {
        /*Set ID*/
        String s = "M";
        id = s;
        //Set Umur
        umur = 0;
        //Cari Posisi
        Random rand = new Random();
        int a = rand.nextInt(l.getLayarSize());
        int b = rand.nextInt(l.getLayarSize());
        while (!l.isEmpty(a, b)) {
            a = rand.nextInt(l.getLayarSize());
            b = rand.nextInt(l.getLayarSize());
        }
        l.setID(getID(), a, b);
        x = a;
        y = b;
        draw(l);
        deltaT = 0;
        vanished = false;
    }
    
    @Override
    public void gerak(Layar l) {
        final int maxumur = 60;
        setUmur(getUmur() + 1);
        if (getUmur() <= maxumur) {
            l.printIsi(x, y);
        } else {
            mati(l);
            //gotoXY(GetX(),GetY());
            //cout << " ";
        }
    }

    @Override
    public void draw(Layar l) {
        int a = getX();
        int b = getY();
	System.out.println(a + " " + b);
        l.printIsi(a, b);
    }

    @Override
    public void mati(Layar l) {
        //menghapus dia dari layar
        String sid = getID();
        int a = getX();
        int b = getY();
        l.removeID(sid, a, b);
        l.printIsi(getX(), getY());
        //menghapus dari list of makhluk
        vanished = true;
        System.out.println("Cenayang mati" + getID() + "removed" + a + " " + b);
	System.exit(0);
    }
    
}
