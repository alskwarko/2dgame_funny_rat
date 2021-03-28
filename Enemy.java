package rases;

import java.awt.Image;
import java.awt.Rectangle;

import javax.swing.ImageIcon;

public class Enemy {
	int x;
	int y;
	Image img = new ImageIcon("res/apple.png").getImage();
	Road road;
	
	public Enemy(int x, int y, Road road) {
		this.x = x;
		this.y = y;
		this.road = road;
	}
	
	public Rectangle getRect() {
		return new Rectangle(x, y, 40,40);
	}
	
	public void move() {
		x = x - road.p.v;
	}

}
