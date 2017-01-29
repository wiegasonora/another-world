package AnotherWorld;

import java.util.Random;

/**
 *
 * @author M. Reza Ramadhan/13514107.
 */
public class BawangPutih extends MakhlukHidup {

    /**
     * BawangPutih constructor.
     *
     * @param l Layar tempat BawangPutih berada.
     */
    public BawangPutih(final Layar l) {
        int n = l.getNBawangPutih();
        l.setnBawangPutih(n + 1);
        char c = (char) ((n + 1) + '0');
        String iD = "B";
        iD = iD + c;
        this.id = iD;

        //Posisi
        Random rand = new Random();
        int a = rand.nextInt(l.getLayarSize());
        int b = rand.nextInt(l.getLayarSize());
        while (l.isEmpty(a, b)) {
            a = rand.nextInt(l.getLayarSize());
            b = rand.nextInt(l.getLayarSize());
        }
        this.x = a;
        this.y = b;
        l.setID(id, a, b);
        //Umur
        this.umur = 0;
        //Gambar
        deltaT = 0;
        draw(l);
        vanished = false;
    }

    /**
     * Mengatur gerak BawangPutih.
     *
     * @param l layar tempat BawangPutih berada.
     */
    @Override
    public final void gerak(final Layar l) {
        final int maxumur = 10;
        setUmur(getUmur() + 1);
        if (getUmur() <= maxumur) {
            l.printIsi(x, y);
        } else {
            mati(l);
            //gotoXY(GetX(),GetY());
            //cout << " ";
        }
    }

    /**
     * Mengatur BawangPutih ditampilkan ke layar.
     *
     * @param l layar tempat BawangPutih berada.
     */
    @Override
    public final void draw(final Layar l) {
        int a = getX();
        int b = getY();
        l.printIsi(a, b);
    }

    /**
     * Mengatur BawangPutih dimusnahkan.
     *
     * @param l layar tempat makhluk berada.
     */
    @Override
    public final void mati(final Layar l) {
        l.removeID(getID(), getX(), getY());
        l.printIsi(getX(), getY());
        vanished = true;
        System.out.println("Bawang putih mati" + getID()
                + "removed" + getX() + " " + getY());
    }
}