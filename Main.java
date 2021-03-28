package rases;

import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.ImageIcon;
import javax.swing.JFrame;

public class Main {
	public static final int WIDTH = 1100, HEIGHT = 600;

	public static void main(String[] args) {
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		int x = screenSize.width / 2 - WIDTH / 2;
		int y = screenSize.height / 2 - HEIGHT / 2;
		
		JFrame f = new JFrame("Funny rat");
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);// закрытие окна при клике крестика и закрытие игры
        f.setBounds(x, y,WIDTH, HEIGHT);
        f.setIconImage(new ImageIcon("res/osn.jpg").getImage());
        f.add(new Road());
        f.setVisible(true);
        f.setResizable(false);
       
	}

}
