package AnotherWorld;

/**
 * @author Steffi Indrayani/13514063
 */
public abstract class MakhlukHidup extends MakhlukAbstrak {

    /**
     * Attribute integer umur.
     */
    protected int umur;

    /**
     * Construktor MakhlukHidup.
     */
    public MakhlukHidup() {
       //Left Blank
    }

    /**
     * Mengembalikan nilai umur.
     *
     * @return umur umur makhluk.
     */
    public final int getUmur() {
        return umur;
    }

    /**
     * Menetapkan nilai umur makhluk.
     *
     * @param age sebuah integer yang dimasukkan sebagai nilai umur mahluk.
     */
    public final void setUmur(final int age) {
        umur = age;
    }
}
