package rases;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;

public class Road extends JPanel implements ActionListener, Runnable { 
	
	public final int WIDTH = 1100, HEIGHT = 600;
	public boolean started, gameOver, gameWin;
	
	public static Audio a;	
	
	Timer timer = new Timer(20,this); // каждые 20мс запускать функцию actionPerformed(this)
	
	Image img = new ImageIcon("res/doroga.png").getImage();
	Player p = new Player();
	
	Thread enemiesFactory = new Thread(this); // новый поток, который будет выполн€тьс€ параллельно
	ArrayList <Enemy> enemies = new ArrayList<Enemy>();
	 
	public Road(){ 
		 timer.start();
		 enemiesFactory.start();
		 a  = new Audio("res/bhump.wav", 0.8);
		 addKeyListener(new MyKeyAddapter());//добавл€етс€ действи€ на клавитуру(слушатель клавиатуры)
		 setFocusable(true);//чтобы кейивент отправл€лс€ на панель если фокусабл
		 
	 }
	
	private class MyKeyAddapter extends KeyAdapter{
		public void keyPressed (KeyEvent e) {
			if (!started) {
				started = true;
			}
			
			if (gameWin) {
				gameWin = false;
				p.score = 0;
			}
			
			if (gameOver) {
				gameOver = false;
				p.score = 0;
			}
			
			p.keyPressed(e);//передаем управление методу из плэера 
		}
		public void keyReleased(KeyEvent e) {
			p.keyReleased(e);
		}
		
	}
	 public void paint(Graphics g) { // метод перерисовывает панель
		 g.drawImage(img, p.layer1, 0, 1100, 580, null); 
		 g.drawImage(img, p.layer2, 0, 1100, 580, null);
		 g.drawImage(p.img, p.x, p.y, 100,150, null);
		 
		 g.setColor(Color.WHITE);
		 Font font = new Font("Arial",Font.BOLD,100);
		 g.setFont(font);
		
			if (!started)
			{
				g.drawString("Tab -> to start!", 220, HEIGHT / 2 - 90);
			}

			if (gameOver)
			{
				g.drawString("Game Over!", 200, HEIGHT / 2 - 50);
			}

			if (!gameOver && started)
			{
				g.drawString(String.valueOf(p.score), WIDTH / 2 - 25, 100);
				Font font2 = new Font("Arial",Font.BOLD,30);
				g.setFont(font2);
				g.drawString("szybkosc: "+String.valueOf(p.v) + "km/h", 50, 35);
			}
			
			if (gameWin)
			{
				g.setFont(font);
				g.drawString("You won !!!!", 200, HEIGHT / 2 - 50);
			}

		for (Enemy e : enemies) {//перебираем €блоки и их перерисовываем 
			 e.move();
			 g.drawImage(e.img, e.x, e.y, 40, 40, null);
		}
			 
	 }
	 
	 public void actionPerformed(ActionEvent e){
		 p.move();//плеер перемещаетс€
		 repaint();
		 testWin();
	 }

	private void testWin() {
		
		if(p.s <20000 && p.score >= 10) {
			gameWin = true;
			p.v = 0;
		    p.s = 0;
		}
		
		if(p.s >= 20000 && p.score < 10) {
			gameOver = true;
			p.v = 0;
		    p.s = 0;
		}
		
		Iterator<Enemy> i = enemies.iterator();//сбор €блок
		 while(i.hasNext()) {
			 Enemy e = i.next();
			 if (p.getRect().intersects(e.getRect())) {
				 p.score = p.score + 1;
				 a.sound();
				 a.setVolume();
				 i.remove();
			 }
			 if (e.x >= 2000 || e.x <= -2000) {
				 i.remove();
			 }
		 }

	}

	@Override
	public void run() {
		while(true) {
			Random rand = new Random();
			try {
				Thread.sleep(rand.nextInt(2000));// спим
				if(p.v > 0) {
				enemies.add(new Enemy(1100, rand.nextInt(500), this));// создаем врага
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
	}
	
}
