package MakhlukController;

import MakhlukModel.BawangPutih;
import MakhlukModel.Cenayang;
import MakhlukModel.Hantu;
import MakhlukModel.MakhlukAbstrak;
import MakhlukModel.ManusiaBiasa;
import MakhlukModel.Psikiater;
import MakhlukModel.Psikopat;
import MakhlukView.Layar;
import java.util.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

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
            //System.out.println("Win " + m.getID()+ " vs " + mid[0]);
            win = persaingan(id, mid[1], lm, l);
            if (win == 1) {
                //menang lagi
                l.setID(id, m.getX(), m.getY());
                //System.out.println("Win " + m.getID()+ " vs " + mid[1]);
                m.draw(l);
            } else {
                //System.out.println("lose " + m.getID() + " vs " + mid[1]);
            }
        } else {
            //System.out.println("lose " + m.getID() + " vs " + mid[0]);
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
                } else if (id2.charAt(0) == 'M') {  //Kalau ada psikiater
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
                } else if (id2.charAt(0) == 'M') {  //Kalau ada psikiater
                    winner = 1;
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
                } else if (id2.charAt(0) == 'M') {  //Kalau ada psikiater
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
                } else if (id2.charAt(0) == 'M') {  //Kalau ada psikiater
                    winner = 1;
                }
            }
	    //Kalau m1 adalah manusia
            if (id1.charAt(0) == 'M') {
                //Kalau tidak ada makhluk lain
                if (id2.compareTo("0") == 0) {
                    winner = 1;
                } else if (id2.charAt(0) == 'C') { //Kalau ada cenayang
                    winner = 1;
                } else if (id2.charAt(0) == 'B') { //Kalau ada bawang putih
                    winner = 1;
                } else if (id2.charAt(0) == 'S') { //Kalau ada psikopat
                    winner = 2;
                    m1.mati(l);
                } else if (id2.charAt(0) == 'H') { //Kalau ada hantu
                    winner = 2;
                    m1.mati(l);
                } else if (id2.charAt(0) == 'P') {  //Kalau ada psikiater
                    winner = 1;
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
    /**
     * Attribute boolean stop.
     */
    private static boolean stop = false;
    /**
     * Attribute boolean pause.
     */
    private static boolean pause = false;
    /**
     * Attribute boolean print.
     */
    private static boolean print = false;
    /**
     * Attribute boolean print.
     */
    private static boolean add = false;

    /**
     * Main program.
     *
     * @param argv argument.
     */
    public static void main(String[] argv) {
        final int cTIMEDELAY = 800;
        int nPsiko, nHantu, nBawang, nPsikiater, nCenayang, screenSize;
        System.out.println("=============================="
                + "========================================");
        System.out.println("  A N O T H E R    W O R L D "
                + "  S U R V I V A L   S I M U L A T I O N  ");
        System.out.println("=============================="
                + "========================================");
        System.out.println("Welcome to Another World Survival Simulation");
        System.out.println("Di sini anda akan menyaksikan "
                + "makhluk-makhluk bertahan hidup");
        System.out.println("menghadapi makhluk lainnya. Hahaha\n\n");
        Scanner scan = new Scanner(System.in);
        System.out.println("Masukan besar layar yang anda mau = ");
        //screenSize = scan.nextInt();
        screenSize = 10;
        System.out.println("Masukan jumlah makhluk yang anda mau\n");
        System.out.println("Banyaknya Psikopat = ");
        nPsiko = 6;
        //nPsiko = scan.nextInt();
        System.out.println("Banyaknya Hantu = ");
        nHantu = 5;
        //nHantu = scan.nextInt();
        System.out.println("Banyaknya Bawang Putih = ");
        //nBawang = scan.nextInt();
        nBawang = 3;
        System.out.println("Banyaknya Psikiater = ");
        //nPsikiater = scan.nextInt();
        nPsikiater = 3;
        System.out.println("Banyaknya Cenayang = ");
        nCenayang = 3;
        //nCenayang = scan.nextInt();
        System.out.println("\n\nStatus:\n Creating Makhluk...");
        Layar screen = new Layar(screenSize);
        LinkedList<MakhlukAbstrak> listM = new LinkedList<>();
        screen.drawLayar();
        //Buat objek
	int nTotal = nPsiko + nHantu + nBawang + nPsikiater + nCenayang;
        System.out.println("TotalMakhluk: "+nTotal);
	int i;
        for (i = 0; i < nPsiko; i++) {
            MakhlukAbstrak p = new Psikopat(screen);
            listM.add(p);
            sleep(cTIMEDELAY);
        }
	for (i = 0; i < nHantu; i++) {
            MakhlukAbstrak h = new Hantu(screen);
            listM.add(h);
            sleep(cTIMEDELAY);
        }
	
	for (i = 0; i < nBawang; i++) {
            MakhlukAbstrak b = new BawangPutih(screen);
            listM.add(b);
            sleep(cTIMEDELAY);
        }
	for (i = 0; i < nPsikiater; i++) {
            MakhlukAbstrak ps = new Psikiater(screen);
            listM.add(ps);
            sleep(cTIMEDELAY);
        }
	for (i = 0; i < nCenayang; i++) {
            MakhlukAbstrak c = new Cenayang(screen);
            listM.add(c);
            sleep(cTIMEDELAY);
        }
	MakhlukAbstrak m = new ManusiaBiasa(screen);
	listM.add(m);
        Listener L = new Listener(m, screen, listM);
	
	LinkedList<Thread> A;
	A = new LinkedList<>();
	int itr = listM.indexOf(listM.getFirst());
	for (i = 0; i < nTotal ; i++) {
	    A.addFirst(new ThreadMakhluk(listM.get(itr), listM, screen, L));
	    A.getFirst().start();
	    itr++;
	    sleep(700);
	}
	System.out.println("done adding");
	while (!L.stop) {
	    //System.out.println("stop "+L.stop);
	    //System.out.println("add "+L.add);
	    
	    if (L.add) {
		Random rand = new Random();
		int n = rand.nextInt(5);
		System.out.println("addmakhluk " + n);
		for (i = 0; i < n; i++) {
		    MakhlukAbstrak p = new Psikopat(screen);
		    listM.addFirst(p);
		    A.addFirst(new ThreadMakhluk(p, listM, screen, L));
		    A.getFirst().start();
		}
		for (i = 0; i < n; i++) {
		    MakhlukAbstrak h = new Hantu(screen);
		    listM.addFirst(h);
		    A.addFirst(new ThreadMakhluk(h, listM, screen, L));
		    A.getFirst().start();
		}

		for (i = 0; i < n; i++) {
		    MakhlukAbstrak b = new BawangPutih(screen);
		    listM.addFirst(b);
		    A.addFirst(new ThreadMakhluk(b, listM, screen, L));
		    A.getFirst().start();
		}
		for (i = 0; i < n; i++) {
		    MakhlukAbstrak ps = new Psikiater(screen);
		    listM.addFirst(ps);
		    A.addFirst(new ThreadMakhluk(ps, listM, screen, L));
		    A.getFirst().start();
		}
		for (i = 0; i < n; i++) {
		    MakhlukAbstrak c = new Cenayang(screen);
		    listM.addFirst(c);
		    A.addFirst(new ThreadMakhluk(c, listM, screen, L));
		    A.getFirst().start();
		}
		L.add = false;
	    }
	    if (L.print) {
		try {
		    screen.printToFile();
		    print = false;
		} catch (IOException ex) {
		    Logger.getLogger(MakhlukManager.class.getName()).log(Level.SEVERE, null, ex);
		}
	    }
	}
	for (i = 0; i < nTotal ; i++) {
	    try {
		A.get(i).join();
	    } catch (InterruptedException ex) {
		System.out.println("Join exception");
	    }
	}
	System.exit(0);
	
        System.out.println("Program telah dihentikan.");
        System.out.println("\n\n\nThanks for using this application!");
        System.out.println("See You on the next version of "
                + "Another World Survival Simulation");
        System.exit(0);
    }
}
