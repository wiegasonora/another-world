/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MakhlukController;

import MakhlukModel.MakhlukAbstrak;
import MakhlukView.Layar;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.LinkedList;
import javax.swing.JFrame;
import javax.swing.JTextField;

/**
 *
 * @author rezaramadhan_m
 */
public class Listener{
    public boolean pause;
    public boolean stop;
    public boolean print;
    public boolean add;
    private MakhlukAbstrak m;
    private Layar l;
    private LinkedList<MakhlukAbstrak> list;
    
    public Listener (MakhlukAbstrak mhk, Layar lyr, LinkedList<MakhlukAbstrak> lm) {
	stop = false;
	print = false;
	add = false;
	pause = false;
	JFrame aWindow = new JFrame("Listener");
	m = mhk;
	l = lyr;
	list = lm;
	aWindow.setBounds(1, 1, 100, 30);
	aWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	JTextField typingArea = new JTextField(20);
	typingArea.addKeyListener(new KeyListener() {

	  public void keyTyped(KeyEvent e) {
	    //do nothing
	  }

	  public void keyPressed(KeyEvent e) {
		int id = e.getKeyCode();
		if (id == KeyEvent.VK_P) {
		    System.out.println("p");
		    pause = !pause;
		    System.out.print(pause);
		}
		if (id == KeyEvent.VK_S) {
		    System.out.println("s");
		    stop = true;
		}
		if (id == KeyEvent.VK_C) {
		    System.out.println("c");
		    pause = true;
		    print = true;
		}
		if (id == KeyEvent.VK_M) {
		    System.out.println("M");
		    add = true;
		}
		if (id == KeyEvent.VK_LEFT) {
		    System.out.println("up");
		    int b = m.getY();
		    if (b > 0) {
			l.removeID(m.getID(), m.getX(), b);
			m.setY(b-1);
			l.setID(m.getID(), m.getX(), b-1);
		    }
		    MakhlukManager.persaingan(m.getID(), l.getID(m.getX(), b-1, 0), list, l);
		    System.out.println("y " + b);
		    l.printIsi(m.getX(), b);
		    m.draw(l);
		}
		if (id == KeyEvent.VK_RIGHT) {
		    System.out.println("down");
		    int b = m.getY();
		    if (b < l.getLayarSize()-1) {
			l.removeID(m.getID(), m.getX(), b);
			m.setY(b+1);
			l.setID(m.getID(), m.getX(), b+1);
		    }
		    MakhlukManager.persaingan(m.getID(), l.getID(m.getX(), b+1, 0), list, l);
		    l.printIsi(m.getX(), b);
		    System.out.println("y " + b);
		    m.draw(l);
		}
		if (id == KeyEvent.VK_UP) {
		    System.out.println("left");
		    int a = m.getX();
		    if (a > 0) {
			l.removeID(m.getID(), a, m.getY());
			m.setX(a-1);
			l.setID(m.getID(), a-1, m.getY());
		    }
		    MakhlukManager.persaingan(m.getID(), l.getID(a-1, m.getY(), 0), list, l);
		    System.out.println("x " + a);
		    l.printIsi(a, m.getY());
		    m.draw(l);
		}
		if (id == KeyEvent.VK_DOWN) {
		    System.out.println("right");
		    int a = m.getX();
		    if (a < (l.getLayarSize()-1)) {
		        l.removeID(m.getID(), a, m.getY());
			m.setX(a+1);
			l.setID(m.getID(), a+1, m.getY());
		    }
		    MakhlukManager.persaingan(m.getID(), l.getID(a+1, m.getY(), 0), list, l);
		    System.out.println("x " + a);
		    l.printIsi(a, m.getY());
		    m.draw(l);
		}
	  }

	  /** Handle the key released event from the text field. */
	  public void keyReleased(KeyEvent e) {
	      //do nothing
	  }

	});
	aWindow.add(typingArea);
	aWindow.setVisible(true);
    }
}
