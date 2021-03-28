package rases;

import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

public class Player {
	public static final int MAX_V = 100;
	public static final int MAX_TOP = 10;
	public static final int MAX_BOTTOM = 500;
	
	Image img = new ImageIcon("res/osn2.png").getImage();//загрузка картинки машинки
	
	public int score = 0;
	int v = 0;
	int dv = 0; // ускорение
	int s = 0; 
	int x = 30; // нач координаты
	int y = 100;
	int dy = 0;
	int layer1 = 0; // первый слой(дорога)
	int layer2 = 1100;// второй слой(дорога) чтобы дорога не заканчивалась( выезжает дорога)
	
	public Rectangle getRect() {
		return new Rectangle(x, y, 100,150);
	}
	
	public void move() { // метод движения
		s += v; // путь увеличивается
		v += dv;
		if(v <= 0) v = 0;
		if(v >= MAX_V) v = MAX_V;
		y+=dy;
		if(y <= MAX_TOP) y = MAX_TOP;
		if(y >= MAX_BOTTOM) y = MAX_BOTTOM; 
		if (layer2 -v <= 0){
			layer1 = 0;
			layer2 = 1100;
		}
		else{
			layer1 -= v;//слой уменьшается
			layer2 -= v;
		}
	
	}
	
	public void keyPressed (KeyEvent e) {
 	
		int key = e.getKeyCode();
	
		if (key == KeyEvent.VK_RIGHT) {
			dv = 5;
		}
		
		if (key == KeyEvent.VK_LEFT) {
			dv = -5;
		}
		
		if(key == KeyEvent.VK_UP) {
			dy = -10;
		}
		
		if(key == KeyEvent.VK_DOWN) {
			dy = 10;
		}

	}
	
	public void keyReleased(KeyEvent e) {
		int key = e.getKeyCode();
		if (key == KeyEvent.VK_RIGHT || key == KeyEvent.VK_LEFT) {
			dv = 0;
		}
		
		if(key == KeyEvent.VK_UP || key == KeyEvent.VK_DOWN) {
			dy = 0;
		}
	}

}
