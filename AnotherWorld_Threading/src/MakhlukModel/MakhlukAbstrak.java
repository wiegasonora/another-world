package MakhlukModel;

import MakhlukView.Layar;

/**
 * @author Steffi Indrayani/13514063.
 */
public abstract class MakhlukAbstrak {

    /**
     * Attribute string ID.
     */
    protected String id;
    /**
     * Attribute integer abscess.
     */
    protected int x;
    /**
     * Attribute integer ordinate.
     */
    protected int y;
    /**
     * Attribute integer Delta T.
     */
    protected int deltaT;
    /**
     * Attribute boolean vanished.
     */
    protected boolean vanished;

    /**
     * Constructor MakhlukAbstrak.
     */
    public MakhlukAbstrak() {
        //Left blank
    }

    /**
     * Abstract method Gerak.
     *
     * @param l Layar tempat makhluk berada.
     */
    public abstract void gerak(Layar l);

    /**
     * Abstract method Draw.
     *
     * @param l Layar tempat makhluk berada.
     */
    public abstract void draw(Layar l);

    /**
     * Abstract method Gerak.
     *
     * @param l Layar tempat makhluk berada.
     */
    public abstract void mati(Layar l);

    /**
     * Mengembalikan nilai x.
     *
     * @return x, posisi x makhluk.
     */
    public final int getX() {
        return x;
    }

    /**
     * Mengembalikan nilai y.
     *
     * @return y, posisi y makhluk.
     */
    public final int getY() {
        return y;
    }

    /**
     * Mengembalikan nilai deltaT.
     *
     * @return deltaT, nilai deltaT makhluk.
     */
    public final int getdeltaT() {
        return deltaT;
    }

    /**
     * Mengembalikan apakah makhluk vanished atau tidak.
     *
     * @return Vanished, true jika vanished, false jika tidak.
     */
    public final boolean isVanished() {
        return vanished;
    }

    /**
     * Mengembalikan ID.
     *
     * @return ID, ID makhluk.
     */
    public final String getID() {
        return id;
    }

    /**
     * Menetapkan nilai x makhluk.
     *
     * @param x1 sebuah nilai yang akan dimasukan sebagai nilai x makhluk.
     */
    public final void setX(final int x1) {
        x = x1;
    }

    /**
     * Menetapkan nilai y makhluk.
     *
     * @param y1 sebuah nilai yang akan dimasukan sebagai nilai y makhluk.
     */
    public final void setY(final int y1) {
        y = y1;
    }

    /**
     * Menetapkan ID makhluk.
     *
     * @param s sebuah string yang akan dimasukan sebagai ID makhluk.
     */
    public final void setID(final String s) {
        id = s;
    }
}
