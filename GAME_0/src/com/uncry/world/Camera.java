package com.uncry.world;

public class Camera {
	
	public static int x=0;
	public static int y=0;
	
	public static int Clamp(int a,int min,int max) {
		if(a>max) {
			a=max;
		}
		if(a<min) {
			a=min;
		}
		return a;
	}
}
