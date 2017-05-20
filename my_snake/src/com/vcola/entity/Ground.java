package com.vcola.entity;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;

import com.vcola.util.Global;

public class Ground {
	
	private int[][] rocks = new int[Global.WIDTH][Global.HEIGHT];
	
	public Ground() {
		// 初始化时，在第一排和最后一排加入石头障碍物
		for(int x=0 ; x<rocks.length ; x++){
			rocks[x][0] = 1;
			rocks[x][Global.HEIGHT - 1] = 1;
		}
	}
	
	public boolean isSnakeEatRock(Snake snake){
		System.out.println("Ground isSnakeEatGround");
		for(int x=0 ; x<rocks.length ;x++){
			for(int y=0 ; y<rocks[x].length ; y++){
				if(rocks[x][y] == 1){
					if(x == snake.getHead().x && y == snake.getHead().y){
						return true;
					}
				}
			}
		}
		return false;
	}

	public void drawMe(Graphics g){
		System.out.println("Ground drawMe");
		g.setColor(Color.BLACK);
		for(int x=0 ; x<rocks.length ;x++){
			for(int y=0 ; y<rocks[x].length ; y++){
				if(rocks[x][y] == 1){
					g.fill3DRect(x * Global.CELL_SIZE, y * Global.CELL_SIZE, 
							Global.CELL_SIZE, Global.CELL_SIZE, true);
				}
			}
		}
	}
	
	public boolean isBody(Point p){
		return rocks[p.x][p.y] == 1;
	}
}
