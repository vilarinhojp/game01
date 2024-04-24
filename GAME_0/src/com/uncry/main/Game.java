package com.uncry.main;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import javax.swing.JFrame;

import com.uncry.graphics.Spritesheetplayer;
import com.uncry.graphics.Spritesheetworld;
import com.uncry.world.World;

import main.uncry.entities.Entity;
import main.uncry.entities.Player;

public class Game extends Canvas implements Runnable,KeyListener{
	
	private static final long serialVersionUID = 1L;
	public JFrame frame;
	public static final int WIDTH=280;
	public static final int HEIGHT=160;
	public static final int SCALE=3;
	
	public Thread thread;
	public boolean isRunning;
	
	public BufferedImage image;
	
	public ArrayList<Entity>entidades;
	public static Player player;
	public static Spritesheetplayer sheetP;
	public static Spritesheetworld sheetW;
	public static World world;
	
	public void initFrame() {
		frame=new JFrame("GAME 0");
		frame.add(this);
		frame.pack();
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		addKeyListener(this);
	}
	public synchronized void start() {
		thread=new Thread(this);
		thread.start();
		isRunning=true;
	}
	public synchronized void stop() {
		try {
			thread.join();
		}catch(InterruptedException e) {
			e.printStackTrace();
		}
		isRunning=false;
	}
	public void tick() {
		for(int i=0;i<entidades.size();i++) {
			Entity e=entidades.get(i);
			e.tick();
		}
		world.tick();
	}
	public void render() {
		BufferStrategy bs=getBufferStrategy();
		if(bs==null) {
			createBufferStrategy(3);
			return;
		}
		Graphics g=image.getGraphics();
		g.setColor(Color.black);
		g.fillRect(0, 0, WIDTH, HEIGHT);
		world.render(g);
		for(int i=0;i<entidades.size();i++) {
			Entity e=entidades.get(i);
			e.render(g);
		}
		
		g.dispose();
		g=bs.getDrawGraphics();
		g.drawImage(image, 0, 0, WIDTH*SCALE, HEIGHT*SCALE, null);
		bs.show();
		
	}
	public void keyTyped(KeyEvent e) {
		
	}
	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode()==KeyEvent.VK_RIGHT) {
			player.right=true;
		}else if(e.getKeyCode()==KeyEvent.VK_LEFT) {
			player.left=true;
		}
		if(e.getKeyCode()==KeyEvent.VK_UP) {
			player.up=true;
		}else if(e.getKeyCode()==KeyEvent.VK_DOWN) {
			player.down=true;
		}
	}
	public void keyReleased(KeyEvent e) {
		if(e.getKeyCode()==KeyEvent.VK_RIGHT) {
			player.right=false;
		}else if(e.getKeyCode()==KeyEvent.VK_LEFT) {
			player.left=false;
		}
		if(e.getKeyCode()==KeyEvent.VK_UP) {
			player.up=false;
		}else if(e.getKeyCode()==KeyEvent.VK_DOWN) {
			player.down=false;
		}
	}
	public void run() {
		requestFocus();
		double lastTime=System.nanoTime();
		double amountOfTicks=60;
		double ns=1000000000/amountOfTicks;
		double timer=System.currentTimeMillis();
		double delta=0;
		int frames=0;
		
		while(isRunning) {
			double now=System.nanoTime();
			delta+=(now-lastTime)/ns;
			lastTime=now;
			if(delta>=1) {
				tick();
				render();
				delta--;
				frames++;
			}
			if(System.currentTimeMillis()-timer>=1000) {
				timer+=1000;
				System.out.println("FPS: "+frames);
				frames=0;
			}
		}
		stop();
	}
	public Game() {
		this.setPreferredSize(new Dimension(WIDTH*SCALE,HEIGHT*SCALE));
	    initFrame();
	    image=new BufferedImage(WIDTH,HEIGHT,BufferedImage.TYPE_INT_RGB);
	    sheetP=new Spritesheetplayer("/spritesheetplayer.png");
	    sheetW=new Spritesheetworld("/spritesheetworld.png");
	    world=new World("/map.png");
	    entidades=new ArrayList<Entity>();
	    player=new Player(50,180,60,60,sheetP.getSprite(0, 0, 60, 60));
	    entidades.add(player);
	    
	}
	public static void main(String[]args) {
		Game game=new Game();
		game.start();
	}
	
}
