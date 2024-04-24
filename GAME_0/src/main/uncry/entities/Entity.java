package main.uncry.entities;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import com.uncry.world.Camera;

public class Entity {
	
	public double x;
	public double y;
	protected int w;
	public int h;
	private BufferedImage sprite;
	
	public int getX() {
		return (int)this.x;
	}
	public int getY() {
		return (int)this.y;
	}
	public int getW() {
		return this.w;
	}
	public int getH() {
		return this.h;
	}
	
	public Entity(int x,int y, int w,int h,BufferedImage sprite) {
		this.x=x;
		this.y=y;
		this.w=w;
		this.h=w;
		this.sprite=sprite;
		
	}
	public void tick() {
		
	}
	public void render(Graphics g) {
		g.drawImage(sprite, getX()-Camera.x, getY()-Camera.y, null);
	}
}
