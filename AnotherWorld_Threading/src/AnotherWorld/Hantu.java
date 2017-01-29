package AnotherWorld;

/**
 * @author Wiega Sonora / 13514019.
 */
public class Hantu extends MakhlukAbstrak {

    /**
     * Hantu constructor.
     *
     * @param l Layar tempat Cenayang berada.
     */
    public Hantu(final Layar l) {
        int nhantu;
        String iD = "H";
        //Set ID
        nhantu = l.getNHantu() + 1;
        char c = (char) ('0' + nhantu);
        iD = iD + c;
        l.setnHantu(nhantu);
        this.id = iD;
        //Set Posisi Objek
        int a = (int) (Math.random() * l.getLayarSize());
        int b = (int) (Math.random() * l.getLayarSize());
        while (!l.isEmpty(a, b)) {
            a = (int) (Math.random() * l.getLayarSize());
            b = (int) (Math.random() * l.getLayarSize());
        }
        x = a;
        y = b;
        l.setID(id, a, b);
        final int delta = 3;
        deltaT = delta;
        vanished = false;
        //Tampilkan ke Layar
        draw(l);
    }

    /**
     * Mengatur gerak hantu.
     *
     * @param l Layar tempat Cenayang berada.
     */
    @Override
    public final void gerak(final Layar l) {
        //Hapus dari layar
        String sid = getID();
        int a = getX();
        int b = getY();
        l.removeID(sid, a, b);
        l.printIsi(x, y);
        //Ubah Posisi
        if ((a + 2 < l.getLayarSize()) && (b + 2 < l.getLayarSize())) {
            a = a + 2;
            b = b + 2;
        } else if (a + 2 >= l.getLayarSize() && b + 2 < l.getLayarSize()) {
            a = 0;
            b = b + 2;
        } else if (b + 2 >= l.getLayarSize() && a + 2 < l.getLayarSize()) {
            b = 0;
            a = a + 2;
        } else {
            a = 0;
            b = 0;
        }
        setX(a);
        setY(b);
    }

    /**
     * Mengatur hantu ditampilkan ke layar.
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
     * Mengatur hantu dimusnahkan.
     *
     * @param l Layar tempat Cenayang berada.
     */
    @Override
    public final void mati(final Layar l) {
        //Menghapus Hantu dari Layar
        String sid = getID();
        int a = getX();
        int b = getY();
        l.removeID(sid, a, b);
        l.printIsi(getX(), getY());
        vanished = true;
        System.out.println("Hantu mati?" + getID() + "removed" + a + " " + b);
    }
}