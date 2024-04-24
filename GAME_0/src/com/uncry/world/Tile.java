package com.uncry.world;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import com.uncry.main.Game;

public class Tile {
	
	public int x,y,w,h;
	public static int TILE_SIZE=60;
	public BufferedImage sprite;
	
	public static BufferedImage FLOOR_TILE=Game.sheetW.getSprite(0, 0, TILE_SIZE, TILE_SIZE);
	public static BufferedImage FLOOR_TILE2=Game.sheetW.getSprite(60, 0, TILE_SIZE, TILE_SIZE);
	public static BufferedImage FLOOR_TILE3=Game.sheetW.getSprite(120, 0, TILE_SIZE, TILE_SIZE);
	
	public static BufferedImage MONTE_TILE1=Game.sheetW.getSprite(180, 60, 60,60);
	public static BufferedImage MONTE_TILE2=Game.sheetW.getSprite(120, 120, 60,60);
	public static BufferedImage MONTE_TILE3=Game.sheetW.getSprite(180, 120, 60,60);
	public static BufferedImage MONTE_TILE4=Game.sheetW.getSprite(120, 180, 60,60);
	public static BufferedImage MONTE_TILE5=Game.sheetW.getSprite(180, 180, 60,60);
	
	public static BufferedImage CONSTRUCTION_1=Game.sheetW.getSprite(6*60, 0, TILE_SIZE, TILE_SIZE);
	public static BufferedImage CONSTRUCTION_2=Game.sheetW.getSprite(7*60, 0, TILE_SIZE, TILE_SIZE);
	public static BufferedImage CONSTRUCTION_3=Game.sheetW.getSprite(6*60, 60, TILE_SIZE, TILE_SIZE);
	public static BufferedImage CONSTRUCTION_4=Game.sheetW.getSprite(7*60, 60, TILE_SIZE, TILE_SIZE);

	public Tile( int x,int y,int w,int h,BufferedImage sprite) {
		this.x=x;
		this.y=y;
		this.w=w;
		this.h=h;
		this.sprite=sprite;
	}
	
	public void render(Graphics g) {
		g.drawImage(sprite, x-Camera.x, y-Camera.y,w,h,null);
	}
}
