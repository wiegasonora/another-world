package AnotherWorld;

import java.util.Random;

/**
 *
 * @author M. Reza Ramadhan/13514107.
 */
public class Cenayang extends MakhlukHidup {

    /**
     * Cenayang constructor.
     *
     * @param l Layar tempat Cenayang berada.
     */
    public Cenayang(final Layar l) {
        final int delta = 5;
        int ncenayang;
        String iD = "C";

        //Set ID
        ncenayang = l.getNCenayang() + 1;
        l.setnCenayang(ncenayang);
        char c = (char) (ncenayang + '0');
        iD = iD + c;
        this.id = iD;

        //Set posisi objek
        Random rand = new Random();
        int a = rand.nextInt(l.getLayarSize());
        int b = rand.nextInt(l.getLayarSize());
        while (l.isEmpty(a, b)) {
            a = rand.nextInt(l.getLayarSize());
            b = rand.nextInt(l.getLayarSize());
        }
        x = a;
        y = b;
        l.setID(id, a, b);
        deltaT = delta;
        vanished = false;
        //set umur
        umur = 0;
        //tampilkan ke layar
        draw(l);
    }

    /**
     * Mengatur gerak Cenayang.
     *
     * @param l Layar tempat Cenayang berada.
     */
    @Override
    public final void gerak(final Layar l) {
        //hapus dari layar
        String sid = getID();
        int a = getX();
        int b = getY();
        l.removeID(sid, a, b);
        //cout spasi
        l.printIsi(x, y);
        //ubah posisi
        if (b - 1 >= 0) {
            b = b - 1;
        } else {
            b = l.getLayarSize() - 1;
        }
        setX(a);
        setY(b);
        //Persaingan(L);
        setUmur(getUmur() + 1);
        final int maxumur = 5;
        if (maxumur <= getUmur()) {
            mati(l);
        }
    }

    /**
     * Mengatur Cenayang ditampilkan ke layar.
     *
     * @param l Layar tempat Cenayang berada.
     */
    @Override
    public final void draw(final Layar l) {
        int a = getX();
        int b = getY();
        l.printIsi(a, b);
        //System.out.println("drawing" + X + Y);
    }

    /**
     * Mengatur Cenayang dimusnahkan.
     *
     * @param l Layar tempat Cenayang berada.
     */
    @Override
    public final void mati(final Layar l) {
        //menghapus dia dari layar
        String sid = getID();
        int a = getX();
        int b = getY();
        l.removeID(sid, a, b);
        l.printIsi(getX(), getY());
        //menghapus dari list of makhluk
        vanished = true;
        System.out.println("Cenayang mati" + getID() + "removed" + a + " " + b);
    }
}