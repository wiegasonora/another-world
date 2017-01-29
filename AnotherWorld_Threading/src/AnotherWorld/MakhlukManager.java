package AnotherWorld;

import java.util.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JTextField;

/**
 *
 * @author Wiega Sonora/13514019.
 */
public class MakhlukManager {

    /**
     * Mengecek apakah semua makhluk sudah musnah.
     *
     * @param lm List of Makhluk.
     * @return true jika semua makhluk telah musnah, false jika tidak.
     */
    public static boolean isAllVanished(final LinkedList<MakhlukAbstrak> lm) {
        int iterator;
        iterator = lm.indexOf(lm.getFirst());
        while (iterator <= lm.indexOf(lm.getLast())
                && (lm.get(iterator)).isVanished()) {
            iterator++;
        }
        return iterator > lm.indexOf(lm.getLast());
    }

    /**
     * Mencari makhluk berdasarkan ID yang diberikan.
     *
     * @param id ID Makhluk yang dicari.
     * @param lm List of Makhluk.
     * @return MakhlukAbstrak yang dicari.
     */
    public static MakhlukAbstrak
        searchM(final String id, final LinkedList<MakhlukAbstrak> lm) {
        int iterator;
        iterator = lm.indexOf(lm.getFirst());
        while (iterator <= lm.indexOf(lm.getLast())
                && (lm.get(iterator)).getID().compareTo(id) == 0) {
            iterator++;
        }
        if (iterator <= lm.indexOf(lm.getLast())) {
            return lm.get(iterator);
        } else {
            return null;
        }
    }

    /**
     * Mengatur pergerakan Makhluk.
     *
     * @param m Makhluk yang diatur.
     * @param l Layar tempat makhluk berada.
     * @param lm List of Makhluk.
     */
    public static void makhlukMovement(final MakhlukAbstrak m,
            final Layar l, final LinkedList<MakhlukAbstrak> lm) {
        int a = m.getX();
        int b = m.getY();
        int win;
        String[] mid = new String[2];
        m.gerak(l);
        mid[0] = l.getID(a, b, 0);
        mid[1] = l.getID(a, b, 1);
        String id = m.getID();
        win = persaingan(id, mid[0], lm, l);
        if (win == 1) {
            //menang
            //System.out.println("Win " + M.GetID() + " vs " + M_id[0]);
            win = persaingan(id, mid[1], lm, l);
            if (win == 1) {
                //menang lagi
                l.setID(id, m.getX(), m.getY());
                //System.out.println("Win " + M.GetID() + " vs " + M_id[1]);
                m.draw(l);
            } else {
                System.out.println("lose " + m.getID() + " vs " + mid[1]);
            }
        } else {
            System.out.println("lose " + m.getID() + " vs " + mid[0]);
        }
    }

    /**
     * Mengatur persaingan antara 2 makhluk.
     *
     * @param id1 ID Makhluk pertama.
     * @param id2 ID Makhluk kedua.
     * @param lm List of Makhluk.
     * @param l Layar tempat Cenayang berada.
     * @return sebuah integer, 1 jika Makhluk pertama menang, 2 jika Makhluk
     * kedua menang.
     */
    public static int persaingan(final String id1, final String id2,
            final LinkedList<MakhlukAbstrak> lm, final Layar l) {
        int winner = 0;
        MakhlukAbstrak m1 = null;
        MakhlukAbstrak m2 = null;
        //Set m1 dan m2
        if ((id1.compareTo("0") != 0) && id2.compareTo("0") != 0) {
            m1 = searchM(id1, lm);
            m2 = searchM(id2, lm);
        } else if (id1.compareTo("0") == 0) {
            m2 = searchM(id2, lm);
        } else {
            m1 = searchM(id1, lm);
        }
        //Jika m1 = m2
        if (id1.charAt(0) == id2.charAt(0)) {
            //Kalau hantu, tidak ada umur, maka hantu 2 dipindahkan
            if (id1.charAt(0) == 'H') {
                //Pindahkan hantu 2
                Random rand = new Random();
                int a = rand.nextInt(l.getLayarSize());
                int b = rand.nextInt(l.getLayarSize());
                while (!l.isEmpty(a, b)) {
                    a = rand.nextInt(l.getLayarSize());
                    b = rand.nextInt(l.getLayarSize());
                }
                m2.setX(a);
                m2.setY(b);
                winner = 2;
            } else {
                //yang didatangi yang mati
                winner = 1;
                m2.mati(l);
            }
        } else {
            //Kalau m1 adalah hantu
            if (id1.charAt(0) == 'H') {
                //Kalau tidak ada makhluk lain
                if (id2.compareTo("0") == 0) {
                    winner = 1;
                } else if (id2.charAt(0) == 'C') { //Kalau ada cenayang
                    winner = 2;
                    m1.mati(l);
                } else if (id2.charAt(0) == 'B') {  //Kalau ada bawang putih
                    winner = 2;
                    m1.mati(l);
                } else if (id2.charAt(0) == 'S') {  //Kalau ada bawang putih
                    winner = 1;
                    m2.mati(l);
                } else if (id2.charAt(0) == 'P') {  //Kalau ada psikiater
                    winner = 1;
                    m2.mati(l);
                }
            }
            //Kalau m1 adalah cenayang
            if (id1.charAt(0) == 'C') {
                //Kalau tidak ada makhluk lain
                if (id2.compareTo("0") == 0) {
                    winner = 1;
                } else if (id2.charAt(0) == 'H') {  //Kalau ada hantu
                    winner = 1;
                    m2.mati(l);
                } else if (id2.charAt(0) == 'B') { //Kalau ada bawang putih
                    winner = 1;
                    m2.mati(l);
                } else if (id2.charAt(0) == 'S') { //Kalau ada psikopat
                    winner = 2;
                    m1.mati(l);
                } else if (id2.charAt(0) == 'P') { //Kalau ada psikiater
                    winner = 1;
                    m2.mati(l);
                }
            }
            //Kalau m1 adalah psikopat
            if (id1.charAt(0) == 'S') {
                //Kalau tidak ada makhluk lain
                if (id2.compareTo("0") == 0) {
                    winner = 1;
                } else if (id2.charAt(0) == 'C') {  //Kalau ada cenayang
                    winner = 1;
                    m2.mati(l);
                } else if (id2.charAt(0) == 'B') { //Kalau ada bawang putih
                    winner = 1;
                    m2.mati(l);
                } else if (id2.charAt(0) == 'H') { //Kalau ada hantu
                    winner = 2;
                    m1.mati(l);
                } else if (id2.charAt(0) == 'P') { //Kalau ada psikiater
                    winner = 1;
                    m2.mati(l);
                }
            }
            //Kalau m1 adalah psikiater
            if (id1.charAt(0) == 'P') {
                //Kalau tidak ada makhluk lain
                if (id2.compareTo("0") == 0) {
                    winner = 1;
                } else if (id2.charAt(0) == 'C') { //Kalau ada cenayang
                    winner = 1;
                    m2.mati(l);
                } else if (id2.charAt(0) == 'B') { //Kalau ada bawang putih
                    winner = 1;
                    m2.mati(l);
                } else if (id2.charAt(0) == 'S') { //Kalau ada psikopat
                    winner = 2;
                    m1.mati(l);
                } else if (id2.charAt(0) == 'H') { //Kalau ada hantu
                    winner = 2;
                    m1.mati(l);
                }
            }
        }
        return winner;
    }

    /**
     * Sleep.
     *
     * @param i periode sleep.
     */
    public static void sleep(final long i) {
        try {
            Thread.sleep(i);
        } catch (InterruptedException e) {
            System.out.println("Error sleeping");
        }
    }
    

    
}
