package MakhlukModel;

import MakhlukView.Layar;
import java.util.Random;

/**
 * @author Steffi Indrayani/13514063
 */
public class Psikopat extends MakhlukHidup {

    /**
     * Konstruktor Psikopat.
     *
     * @param l Layar tempat Psikopat berada.
     */
    public Psikopat(final Layar l) {
        /*Set ID*/
        String s = "S";
        char c = (char) ('0' + (l.getNPsiko() + 1));
        s = s + c;
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
        l.setnPsiko(l.getNPsiko() + 1);
        deltaT = 2;
        vanished = false;
    }

    /**
     * Mengatur pergerakan Psikopat.
     *
     * @param l Layar tempat Psikopat berada.
     */
    @Override
    public final void gerak(final Layar l) {
        //Hapus dari Layar
        setUmur(getUmur() + 1);
        final int maxumur = 15;
        if (getUmur() <= maxumur) {
            String sid = getID();
            int a = getX();
            int b = getY();
            l.removeID(sid, a, b);
            l.printIsi(x, y);
            //Pindah Posisi
            int size = l.getLayarSize();
            final int three = 3;
            if (a + 1 < size && b - 1 >= 0) {
                a = a + 1;
                b = b - 1;
                setX(a);
                setY(b);
            } else if (a + 1 > size && b - 1 >= 0) {
                a = 0;
                b = b - 1;
                setX(a);
                setY(b);
            } else if (a + 1 < size && b - 1 < 0) {
                a = a + 1;
                b = 0;
                setX(a);
                setY(b);
            } else {
                a = 0;
                b = 0;
                setX(a);
                setY(b);
            }
        } else {
            mati(l);
        }
    }

    /**
     * Mengatur tampilan Psikopat pada layar.
     *
     * @param l Layar tempat Psikopat berada.
     */
    @Override
    public final void draw(final Layar l) {
        //Ke posisi
        int a = getX();
        int b = getY();
        l.printIsi(a, b);
    }

    /**
     * Mengatur Psikopat dimusnahkan.
     *
     * @param l Layar tempat Psikopat berada.
     */
    @Override
    public final void mati(final Layar l) {
        //hapus dari layar
        l.removeID(getID(), getX(), getY());
        l.printIsi(getX(), getY());
        //hapus dari list of makhluk
        vanished = true;
        //System.out.println("Psikopat mati" + getID()
        //        + "removed" + getX() + " " + getY());
    }

}
