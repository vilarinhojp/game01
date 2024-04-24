package main.uncry.entities;

import java.awt.Color;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import com.uncry.main.Game;
import com.uncry.world.Camera;
import com.uncry.world.World;

public class Player extends Entity {
	
	public static boolean right,left,up,down;
	public static double speed=2;
	public static boolean moved;
	
	public static BufferedImage[]rightPlayer;
	public static BufferedImage[]leftPlayer;
	public static BufferedImage[]downPlayer;
	
	int frames=0;
	int maxFrames=5;
	int index=0;
	int maxIndex=7;
	

	int framesD=0;
	int maxFramesD=2;
	int indexD=0;
	int maxIndexD=17;
	
	public void tick() {
		moved=false;
		
		if(right && Game.world.right) {
			moved=true;
			x+=speed;
			
		}else if(left) {
			moved=true;
			x-=speed;
			
		}
		if(up ) {
			y-=speed;
			
		}else if(down&&Game.world.down) {
			moved=true;
			y+=speed;
			
		}
		if(moved) {
			frames++;
			if(frames==maxFrames) {
				frames=0;
				index++;
				if(index>maxIndex) {
					index=0;
				}
			}
			framesD++;
			if(framesD==maxFramesD) {
				framesD=0;
				indexD++;
				if(indexD>maxIndexD) {
					indexD=0;
				}
			}
		}
		
		Camera.x=Camera.Clamp(this.getX()-((Game.WIDTH/2)-(this.getW()/2)),0,(World.WIDTH*60)-Game.WIDTH);
		Camera.y=Camera.Clamp(this.getY()-((Game.HEIGHT/2)-(this.getH()/2)),0,(World.HEIGHT*60)-Game.HEIGHT);
	}
	public void render(Graphics g) {
		int A_S=15;
		int L_S=25;
		g.setColor(new Color(0,0,0,25));
		g.fillOval((getX()+(getW()/4+2))-Camera.x,(getY()+(getH()-A_S+5))-Camera.y,L_S,A_S);
		if(right) {
			g.drawImage(rightPlayer[index], getX()-Camera.x, getY()-Camera.y,null);
		}else if(left) {
			g.drawImage(leftPlayer[index], getX()-Camera.x, getY()-Camera.y,null);
		}else if(down) {
			g.drawImage(downPlayer[indexD], getX()-Camera.x, getY()-Camera.y, null);
		}else {
			g.drawImage(Game.sheetP.getSprite(0,0,60,60),getX()-Camera.x,getY()-Camera.y,null);
		}
	}
	public Player(int x, int y, int w, int h, BufferedImage sprite) {
		super(x, y, w, h, sprite);
		rightPlayer= new BufferedImage[8];
		leftPlayer=new BufferedImage[8];
		downPlayer=new BufferedImage[18];
		
		for(int i=0;i<rightPlayer.length;i++) {
			rightPlayer[i]=Game.sheetP.getSprite(60*i, 4*60, 60, 60);
			leftPlayer[i]=Game.sheetP.getSprite(60*i, 3*60, 60, 60);
			
		}
		for(int i=0;i<2;i++) {
			for(int ii=0;ii<downPlayer.length/2;ii++) {
				downPlayer[ii+(i*9)]=Game.sheetP.getSprite(ii*60, 60+(i*60), 60, 60);
			}
		}
	}

}
