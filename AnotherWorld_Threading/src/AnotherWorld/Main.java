package AnotherWorld;

import java.util.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;
import javax.swing.JFrame;
import javax.swing.JTextField;

public class Main {

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
        screenSize = scan.nextInt();
        System.out.println("Masukan jumlah makhluk yang anda mau\n");
        System.out.println("Banyaknya Psikopat = ");
        nPsiko = scan.nextInt();
        System.out.println("Banyaknya Hantu = ");
        nHantu = scan.nextInt();
        System.out.println("Banyaknya Bawang Putih = ");
        nBawang = scan.nextInt();
        System.out.println("Banyaknya Psikiater = ");
        nPsikiater = scan.nextInt();
        System.out.println("Banyaknya Cenayang = ");
        nCenayang = scan.nextInt();
        System.out.println("\n\nStatus:\n Creating Makhluk...");
        Layar screen = new Layar(screenSize);
        LinkedList<MakhlukAbstrak> listM = new LinkedList<>();
        int sum = nCenayang + nPsiko + nBawang + nPsikiater + nHantu;
        Thread arrT[] = new Thread[sum];
        screen.drawLayar();
        //Buat objek
        int i;
        int j = 0;
        for (i = 0; i < nPsiko; i++) {
            arrT[j] = new ManagerPsikopat(listM,screen);
            //arrT[j].start();
            MakhlukManager.sleep(cTIMEDELAY);
            arrT[j].run();
            j++;
        }
        for (i = 0; i < nHantu; i++) {
            arrT[j] = new ManagerHantu(listM,screen);
            //arrT[j].start();
            MakhlukManager.sleep(cTIMEDELAY);
            arrT[j].run();
            j++;
        }
        for (i = 0; i < nBawang; i++) {
            arrT[j] = new ManagerBawang(listM,screen);
            //arrT[j].start();
            MakhlukManager.sleep(cTIMEDELAY);
            arrT[j].run();
            j++;
        }
        for (i = 0; i < nPsikiater; i++) {
            arrT[j] = new ManagerPsikiater(listM,screen);
            //arrT[j].start();
            j++;
            MakhlukManager.sleep(cTIMEDELAY);
            arrT[j].run();
        }
        for (i = 0; i < nCenayang; i++) {
            arrT[j] = new ManagerCenayang(listM,screen);
            //arrT[j].start();
            MakhlukManager.sleep(cTIMEDELAY);
            arrT[j].run();
            j++;
        }
        int itr = listM.indexOf(listM.getFirst());
        MakhlukAbstrak m = listM.get(itr);
        int duration;
        JFrame aWindow = new JFrame("Listener");
        final int height = 100;
        final int width = 30;
        aWindow.setBounds(1, 1, height, width);
        aWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        final int twenty = 20;
        JTextField typingArea = new JTextField(twenty);
        typingArea.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(final KeyEvent e) {
                //do nothing
            }
            @Override
            public void keyPressed(final KeyEvent e) {
                int id = e.getKeyCode();
                if (id == KeyEvent.VK_P) {
                    //System.out.print("p");
                    pause = !pause;
                    System.out.print(pause);
                }
                if (id == KeyEvent.VK_S) {
                    //System.out.print("s");
                    stop = true;
                }
                if (id == KeyEvent.VK_C) {
                    //System.out.print("c");
                    pause = true;
                    print = true;
                }
                if (id == KeyEvent.VK_M) {
                    add = true;
                }
            }
            @Override
            public void keyReleased(final KeyEvent e) {
                //do nothing
            }
        });
        aWindow.add(typingArea);
        aWindow.setVisible(true);
        /*final int five = 5;
        final int cTIME = 800;
        while ((!MakhlukManager.isAllVanished(listM)) && (!stop)) {
            duration = m.getdeltaT();
            MakhlukManager.sleep(duration * cTIME);
            MakhlukManager.makhlukMovement(m, screen, listM);
            itr++;
            if (itr == listM.indexOf(listM.getLast())) {
                itr = listM.indexOf(listM.getFirst());
            }
            m = listM.get(itr);
            while ((!MakhlukManager.isAllVanished(listM)) && m.isVanished()) {
                itr++;
                if (itr == listM.indexOf(listM.getLast())) {
                    itr = listM.indexOf(listM.getFirst());
                }
                m = listM.get(itr);
            }
            if (print) {
                try {
                    screen.printToFile();
                } catch (IOException e) {
                    System.out.println("Failed to write!");
                }
                print = false;
                System.out.println("Printed to file.");
                System.out.println("Filename: output.txt");
                MakhlukManager.sleep(cTIMEDELAY);
                pause = true;
            } 
            if (add) {
                int n = (int) (Math.random() * five);
                System.out.println("Creating "
                        + n + " new makhluk for each category");
                MakhlukAbstrak tmp;
                for (int a = 0; a < n; a++) {
                    tmp = new BawangPutih(screen);
                    listM.add(tmp);
                    MakhlukManager.sleep(cTIME);
                    tmp = new Cenayang(screen);
                    listM.add(tmp);
                    MakhlukManager.sleep(cTIME);
                    tmp = new Hantu(screen);
                    listM.add(tmp);
                    MakhlukManager.sleep(cTIME);
                    tmp = new Psikopat(screen);
                    listM.add(tmp);
                    MakhlukManager.sleep(cTIME);
                    tmp = new Psikiater(screen);
                    listM.add(tmp);
                }
                add = false;
            }
            final int tm = 1250;
            while (pause && !stop) {
                //do nothing
                System.out.println("Paused");
                MakhlukManager.sleep(tm);
            }
        }*/
        
        for (i = 0; i < sum; i++ ){ 
            try {
                arrT[i].join();
            } catch (InterruptedException ex) {
                System.out.println("Tidak dapat dijalankan");
            }
        }
        System.out.println("Program telah dihentikan.");
        System.out.println("\n\n\nThanks for using this application!");
        System.out.println("See You on the next version of "
                + "Another World Survival Simulation");
        System.exit(0);
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
}