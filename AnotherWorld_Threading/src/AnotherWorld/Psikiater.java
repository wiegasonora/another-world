package AnotherWorld;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Diastuti Utami/13514071.
 */
public class Psikiater extends MakhlukHidup {

    /**
     * Psikiater Constructor.
     *
     * @param l Layar tempat Cenayang berada.
     */
    public Psikiater(final Layar l) {
        //Set ID
        String s = "P";
        String c = Integer.toString((l.getNPsikiater() + 1));
        s = s + c;
        id = s;
        //Set Umur
        umur = 0;
        //Cari Posisi
        int a = (int) (Math.random() * l.getLayarSize());
        int b = (int) (Math.random() * l.getLayarSize());
        while (!l.isEmpty(a, b)) {
            a = (int) (Math.random() * l.getLayarSize());
            b = (int) (Math.random() * l.getLayarSize());
        }
        l.setID(getID(), a, b);
        x = a;
        y = b;
        draw(l);
        l.setnPsikiater(l.getNPsikiater() + 1);
        deltaT = 2;
        vanished = false;
    }

    /**
     * Mengatur pergerakan Psikiater.
     *
     * @param l Layar tempat Psikiater berada.
     */
    @Override
    public final void gerak(final Layar l) {
        //Hapus dari Layar
        setUmur(getUmur() + 1);
        final int maxumur = 10;
        if (getUmur() <= maxumur) {
            String sid = getID();
            int a = getX();
            int b = getY();
            l.removeID(sid, a, b);
            l.printIsi(a, b);
            int size = l.getLayarSize();
            if (a + 2 < size && b + 1 < size) {
                a = a + 2;
                b = b + 1;
                setX(a);
                setY(b);
            } else if (a + 2 > size && b + 1 < size) {
                a = 1;
                b = b + 1;
                setX(a);
                setY(b);
            } else if (a + 2 < size && b + 1 > size) {
                a = a + 2;
                b = 1;
                setX(a);
                setY(b);
            } else {
                a = 1;
                b = 1;
                setX(a);
                setY(b);
            }
        } else {
            mati(l);
        }
    }

    /**
     * Mengatur tampilan Psikiater pada layar.
     *
     * @param l Layar tempat Psikiater berada.
     */
    @Override
    public final void draw(final Layar l) {
        //Ke posisi
        int a = getX();
        int b = getY();
        l.printIsi(a, b);
    }

    /**
     * Mengatur Psikiater dimusnahkan.
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
        System.out.println("Psikiater mati"
                + getID() + "removed" + getY() + " " + getY());
    }
}
