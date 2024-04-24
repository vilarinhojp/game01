package com.uncry.world;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import com.uncry.main.Game;

public class World {
	
	public static int WIDTH,HEIGHT;
	public static Tile[]tile;
	public static int TILE_SIZE=60;
	
	public World(String path) {
		
		try {
			BufferedImage map=ImageIO.read(getClass().getResource(path));
			WIDTH=map.getWidth();
			HEIGHT=map.getHeight();
			int[]pixels=new int[WIDTH*HEIGHT];
			tile=new Tile[WIDTH*HEIGHT];
			map.getRGB(0,0,WIDTH,HEIGHT,pixels,0,WIDTH);
			
			for(int xx=0;xx<WIDTH;xx++) {
				for(int yy=0;yy<HEIGHT;yy++) {
					
					int indice = xx+(yy*WIDTH);
					int pixelAtual=pixels[xx+(yy*WIDTH)];
					switch(pixelAtual) {
					case(0xFF87FF97):
						//grama 1
						tile[indice]=new FloorTile(xx*60, yy*60,60,60,Tile.FLOOR_TILE);
					
					break;
					case(0xFF4F9659):
						//grama 2
						tile[indice]=new FloorTile(xx*60, yy*60,60,60, Tile.FLOOR_TILE2);
					
					break;
					case(0xFF492F71):
						//monte 1
						//col 2- lateral direita
						tile[indice]=new COL_2(xx*60, yy*60,60,60, Tile.MONTE_TILE1);
					
					break;
					case(0xFF5C4166):
						//monte 2 - lateral esquerda
						//tile[indice]=new COL_1(xx*60, yy*60,60,60, Tile.MONTE_TILE2);

						tile[indice]=new COL_1(xx*60, yy*60,60,60, Tile.MONTE_TILE2);
					
					break;
					case(0xFF492F39):
						//monte 3
						tile[indice]=new FloorTile(xx*60, yy*60,60,60, Tile.MONTE_TILE3);
					
					break;
					case(0xFF432F4A):
						//monte 4
						tile[indice]=new COL_3(xx*60, yy*60,60,60, Tile.MONTE_TILE4);
					
					break;
					case(0xFFD496EA):
						//monte 5
						tile[indice]=new FloorTile(xx*60, yy*60,60,60, Tile.MONTE_TILE5);
					
					break;
					case(0xFFCC61C6):
						//construção 1
						tile[indice]=new FloorTile(xx*60, yy*60,60,60, Tile.CONSTRUCTION_1);
					
					break;
					case(0xFFF274EB):
						//construção 2
						tile[indice]=new FloorTile(xx*60, yy*60,60,60, Tile.CONSTRUCTION_2);
					
					break;
					case(0xFFF24BE9):
						//construção 3
						tile[indice]=new FloorTile(xx*60, yy*60,60,60, Tile.CONSTRUCTION_3);
					
					break;
					case(0xFFF221E7):
						//construção 4
						tile[indice]=new FloorTile(xx*60, yy*60,60,60, Tile.CONSTRUCTION_4);
					
					break;
					default:
						tile[indice]=new FloorTile(xx*60,yy*60,60,60,Tile.FLOOR_TILE3);
					break;
					
					}
				}
			}
			
		} catch (IOException e) {
			e.printStackTrace();
				
		}
	}
	public boolean right,left,up,down;
	public void tick() {
		right=true;
		left=true;
		up=true;
		down=true;
		for(int i=0;i<tile.length;i++) {
			if(tile[i] instanceof COL_1) {
				if(Game.player.x==tile[i].x-30 && Game.player.y>=tile[i].y-60 && Game.player.y<=tile[i].y) {
					right=false;
					if (tile[i].x < Game.player.x + Game.player.getW() &&
							   tile[i].x + tile[i].w > Game.player.x &&
							   tile[i].y < Game.player.y + Game.player.h &&
							   tile[i].y + tile[i].h > Game.player.y) {
						right=false;
					}
				}
			}
		}
	}
	public void render(Graphics g) {
		
		int startX=Camera.x/60;
		int startY=Camera.y/60;
		int finalX=startX+(Game.WIDTH/60);
		int finalY=startY+(Game.HEIGHT/60);
		
		for(int xx=startX;xx<=finalX+1;xx++) {
			for(int yy=startY;yy<=finalY+1;yy++) {
				
				if(xx<0||yy<0||xx>=WIDTH||yy>=HEIGHT) {
					continue;
				}
				Tile tiles=tile[xx+(yy*WIDTH)];
				tiles.render(g);
				
			}
		}
	}
}










