package AnotherWorld;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.*;
/**
 *
 * @author Diastuti Utami/13514071
 */
public class Layar extends JFrame {

    /**
     * Attribute array of array of Elmt M.
     */
    private final Elmt[][] m;
    /**
     * Attribute default size.
     */
    private static final int DEFSIZE = 15;
    /**
     * Attribute integer board size.
     */
    private int boardsize;
    /**
     * Attribute integer blockpx.
     */
    private static final int BLOCKPX = 60;
    /**
     * Attribute integer board width.
     */
    private final int boardWidth = boardsize * BLOCKPX;
    /**
     * Attribute integer board height.
     */
    private final int boardHeight = boardsize * BLOCKPX;
    /**
     * Attribute color background color 1.
     */
    private static final Color CELL_BGCOLOR = new Color(168, 146, 177);
    /**
     * Attribute Color background color 2.
     */
    private static final Color CELL_BGCOLOR2 = new Color(242, 232, 180);
    /**
     * Attribute Color text color.
     */
    private static final Color TEXTCOLOR = Color.BLACK;
    /**
     * Attribute Font font style.
     */
    private static final Font FONTSTYLE = new Font("Monospaced", Font.BOLD, 22);
    /**
     * Attribute number of Hantu.
     */
    private int nHantu = 0;
    /**
     * Attribute number of BawangPutih.
     */
    private int nBawangPutih = 0;
    /**
     * Attribute number of Psikiater.
     */
    private int nPsikiater = 0;
    /**
     * Attribute number of Cenayang.
     */
    private int nCenayang = 0;
    /**
     * Attribute number of Psikopat.
     */
    private int nPsiko = 0;
    /**
     * Attribute Layar.
     */
    private final JTextField[][] layar;
    /**
     * Attribute grippanel, contentpanel, leftpanel.
     */
    private JPanel gridpanel, contentpanel, leftpanel;
    /**
     * Attribute file writer.
     */
    private FileWriter fw;
    /**
     * Attribute print writer.
     */
    private PrintWriter pw;
    /**
     * Attribute pause and print button.
     */
    private final JButton print, pause;

    /**
     * Layar constructor.
     */
    public Layar() {
        pause = new JButton("Pause");
        print = new JButton("Print");
        layar = new JTextField[DEFSIZE][DEFSIZE];
        boardsize = DEFSIZE;
        m = new Elmt[boardsize][boardsize];
        for (int i = 0; i <= boardsize; i++) { //inisialisasi nilai
            for (int j = 0; j <= boardsize; j++) {
                m[i][j] = new Elmt();
            }
        }
        print.setActionCommand("printfile");
        print.setMnemonic(KeyEvent.VK_P);
        pause.setActionCommand("pause");
    }

    /**
     * Layar constructor with parameter.
     *
     * @param size the assigned size
     */
    public Layar(final int size) {
        pause = new JButton("Pause");
        print = new JButton("Print");
        layar = new JTextField[size][size];
        boardsize = size;
        m = new Elmt[boardsize + 1][boardsize + 1];
        for (int i = 0; i <= boardsize; i++) { //inisialisasi nilai
            for (int j = 0; j <= boardsize; j++) {
                m[i][j] = new Elmt();
            }
        }
        print.setActionCommand("printfile");
        print.setMnemonic(KeyEvent.VK_P);
        pause.setActionCommand("pause");
    }

    /**
     * Print action.
     * @param listener Action listener.
     */
    public final void printActionListener(final ActionListener listener) {
        print.addActionListener(listener);
    }

    /**
     * Pause action.
     * @param listener action listener.
     */
    public final void pauseActionListener(final ActionListener listener) {
        pause.addActionListener(listener);
    }

    /**
     * Mengambil nilai ukuran layar.
     *
     * @return boardsize ukuran layar.
     */
    public final int getLayarSize() {
        return boardsize;
    }

    /**
     * Mengambil jumlah hantu yang telah dikonstruksi.
     *
     * @return nHantu jumlah Hantu.
     */
    public final int getNHantu() {
        return nHantu;
    }

    /**
     * Mengambil nilai banyaknya BawangPutih yang telah dikonstruksi.
     *
     * @return nBawangPutih banyaknya BawangPutih.
     */
    public final int getNBawangPutih() {
        return nBawangPutih;
    }

    /**
     * Mengambil nilai banyaknya Psikopat yang telah dikonstruksi.
     *
     * @return nPsiko banyaknya Psikopat.
     */
    public final int getNPsiko() {
        return nPsiko;
    }

    /**
     * Mengambil nilai banyaknya Psikiater yang telah dikonstruksi.
     *
     * @return nPsikiater banyaknya Psikiater.
     */
    public final int getNPsikiater() {
        return nPsikiater;
    }

    /**
     * Mengambil nilai banyaknya Cenayang yang telah dikonstruksi.
     *
     * @return nCenayang banyaknya Cenayang.
     */
    public final int getNCenayang() {
        return nCenayang;
    }

    /**
     * Menetapkan nilai banyaknya BawangPutih yang telah dikonstruksi.
     *
     * @param z nilai yang akan ditetapkan.
     */
    public final void setnBawangPutih(final int z) {
        nBawangPutih = z;
    }

    /**
     * Menetapkan nilai banyaknya Psikopat yang telah dikonstruksi.
     *
     * @param z nilai yang akan ditetapkan.
     */
    public final void setnPsiko(final int z) {
        nPsiko = z;
    }

    /**
     * Menetapkan nilai banyaknya Psikiater yang telah dikonstruksi.
     *
     * @param z nilai yang akan ditetapkan.
     */
    public final void setnPsikiater(final int z) {
        nPsikiater = z;
    }

    /**
     * Menetapkan nilai banyaknya Cenayang yang telah dikonstruksi.
     *
     * @param z nilai yang akan ditetapkan.
     */
    public final void setnCenayang(final int z) {
        nCenayang = z;
    }

    /**
     * Menetapkan nilai banyaknya Hantu yang telah dikonstruksi.
     *
     * @param z nilai yang akan ditetapkan.
     */
    public final void setnHantu(final int z) {
        nHantu = z;
    }

    /**
     * Menetapkan ID makhluk pada koordinat posisi tertentu.
     *
     * @param iD ID Makhluk.
     * @param x posisi x Makhluk.
     * @param y posisi y Makhluk.
     */
    public final void setID(final String iD, final int x, final int y) {
        if (x < boardsize && y < boardsize) {
            if ("B".equals(String.valueOf(iD.charAt(0)))) {
                String tmp = m[x][y].s[1];
                if (!"B".equals(String.valueOf(tmp.charAt(0)))) {
                    m[x][y].n++;
                }
                m[x][y].s[1] = iD;
            } else {
                if ("0".equals(m[x][y].s[0])) {
                    m[x][y].s[0] = iD;
                } else {
                    m[x][y].s[1] = iD;
                }
                m[x][y].n++;
            }
        }
    }

    /**
     * Mengecek apakah BawangPutih terdapat pada posisi tertentu.
     *
     * @param x Posisi x.
     * @param y Posisi y.
     * @return true jika terdapat BawangPutih, false jika tidak.
     */
    public final boolean isBawangExist(final int x, final int y) {
        boolean b = false;
        if (x < boardsize && y < boardsize) {
            String tmp;
            tmp = m[x][y].s[1];
            b = "B".equals(String.valueOf(tmp.charAt(0)));
        }
        return b;
    }

    /**
     * Menghapus ID Makhluk pada posisi tertentu.
     *
     * @param id ID Makhluk.
     * @param x posisi x makhluk.
     * @param y posisi y makhluk.
     */
    public final void removeID(final String id, final int x, final int y) {
        if (x < boardsize && y < boardsize) {
            if (id.compareTo(m[x][y].s[0]) == 0) {
                m[x][y].s[0] = "0";
                m[x][y].n--;
            } else if (id.compareTo(m[x][y].s[1]) == 0) {
                m[x][y].s[1] = "0";
                m[x][y].n--;
            }
        }
        //System.out.println("remove ID " + _ID + " " + x + " " + y);
    }

    /**
     * Mengecek apakah posisi tertentu kosong.
     *
     * @param x Posisi x.
     * @param y Posisi y.
     * @return true jika tidak ada makhluk pada posisi tersebut, false jika ada
     * makhluk.
     */
    public final boolean isEmpty(final int x, final int y) {
        return (m[x][y].n == 0);
    }

    /**
     * Menampilkan makhluk ke layar.
     *
     * @param x Posisi x Makhluk.
     * @param y Posisi y Mahluk.
     */
    public final void printIsi(final int x, final int y) {
        String tmp1, tmp2;
        if ("B".equals(String.valueOf(m[x][y].s[0].charAt(0)))) {
            tmp1 = ".";
        } else if ("0".equals(m[x][y].s[0])) {
            tmp1 = "";
        } else {
            tmp1 = String.valueOf(m[x][y].s[0].charAt(0));
        }
        if ("B".equals(String.valueOf(m[x][y].s[1].charAt(0)))) {
            tmp2 = ".";
        } else if ("0".equals(m[x][y].s[1])) {
            tmp2 = "";
        } else {
            tmp2 = String.valueOf(m[x][y].s[1].charAt(0));
        }
        layar[x][y].setText(tmp1 + " " + tmp2);
    }

    /**
     * Mengambil ID makhluk pada posisi dan indeks tertentu.
     *
     * @param x Posisi x makhluk.
     * @param y Posisi y makhluk.
     * @param n indeks makhluk, 0 jika pada indeks pertama, 1 jika pada indeks
     * kedua.
     * @return ID Makhluk.
     */
    public final String getID(final int x, final int y, final int n) {
        return String.valueOf(m[x][y].s[n]);
    }

    /**
     * Menggambar layar beserta isinya.
     */
    public final void drawLayar() {
        contentpanel = new JPanel();
        final int twenty = 20;
        contentpanel.setLayout(new FlowLayout(FlowLayout.LEFT, twenty, twenty));
        contentpanel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        leftpanel = new JPanel();
        leftpanel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        leftpanel.setLayout(new BoxLayout(leftpanel, BoxLayout.Y_AXIS));
        leftpanel.add(print);
        contentpanel.add(leftpanel);
        gridpanel = new JPanel();
        gridpanel.setLayout(new GridLayout(boardsize, boardsize));
        for (int x = 0; x < boardsize; x++) {
            for (int y = 0; y < boardsize; y++) {
                layar[x][y] = new JTextField();
                gridpanel.add(layar[x][y]);
                layar[x][y].setEditable(false);
                String tmp1, tmp2;
                if ("B".equals(String.valueOf(m[x][y].s[0].charAt(0)))) {
                    tmp1 = ".";
                } else if ("0".equals(m[x][y].s[0])) {
                    tmp1 = "";
                } else {
                    tmp1 = String.valueOf(m[x][y].s[0].charAt(0));
                }
                if ("B".equals(String.valueOf(m[x][y].s[1].charAt(0)))) {
                    tmp2 = ".";
                } else if ("0".equals(m[x][y].s[1])) {
                    tmp2 = "";
                } else {
                    tmp2 = String.valueOf(m[x][y].s[1].charAt(0));
                }
                layar[x][y].setText(tmp1 + " " + tmp2);
                layar[x][y].setBackground(CELL_BGCOLOR);
                layar[x][y].setForeground(TEXTCOLOR);
                layar[x][y].setHorizontalAlignment(JTextField.CENTER);
                layar[x][y].setFont(FONTSTYLE);
            }
        }
        gridpanel.setPreferredSize(new Dimension(boardWidth, boardHeight));
        contentpanel.add(gridpanel);
        setContentPane(gridpanel);
        pack();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Another World Survival Simulation");
        setVisible(true);
    }

    /**
     * Menyalin layar ke sebuah file teks.
     *
     * @throws IOException exception on input output.
     */
    public final void printToFile() throws IOException {
        fw = new FileWriter("output.txt");
        pw = new PrintWriter(fw);
        pw.print("II");
        final int four = 4;
        for (int i = 0; i < boardsize * four - 1; i++) {
            pw.print("=");
        }
        pw.println("II");
        int i, j;
        for (i = 0; i < boardsize; i++) {
            pw.print("II");
            for (j = 0; j < boardsize; j++) {
                pw.print(" ");
                for (int k = 0; k < 2; k++) {
                    if ("0".equals(m[i][j].s[k])) {
                        pw.print(" ");
                    } else {
                        String iD = String.valueOf(m[i][j].s[k]);
                        if ("B".equals(String.valueOf(iD.charAt(0)))) {
                            pw.print(".");
                        } else {
                            pw.print(String.valueOf(iD.charAt(0)));
                        }
                    }
                }
                if (j != boardsize - 1) {
                    pw.print("|");
                }
            }
            pw.println("II");
            if (i < boardsize - 1) {
                pw.print("II");
                for (j = 0; j < boardsize * four - 1; j++) {
                    pw.print("-");
                }
                pw.println("II");
            }
        }
        pw.print("II");
        for (i = 0; i < boardsize * four - 1; i++) {
            pw.print("=");
        }
        pw.println("II");
        pw.flush();
        pw.close();
    }

    /**
     * Melakukan metode printToFile jika suatu aksi dilakukan.
     *
     * @param e aksi yang dilakukan.
     * @throws IOException exception on input output.
     */
    public final void actionPerformed(final ActionEvent e) throws IOException {
        if ("printfile".equals(e.getActionCommand())) {
            printToFile();
        }
    }
}
