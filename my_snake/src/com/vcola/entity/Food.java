package com.vcola.entity;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;

import com.vcola.util.Global;

@SuppressWarnings("serial")
public class Food extends Point{
	
	public boolean isSnakeEatFood(Snake snake) {
		System.out.println("Food isSnakeEatFood");
		return this.equals(snake.getHead());
	}

	public void drawMe(Graphics g){
		System.out.println("Food drawMe");
		g.setColor(Color.GREEN);
		g.fill3DRect(x * Global.CELL_SIZE, y * Global.CELL_SIZE,
				Global.CELL_SIZE, Global.CELL_SIZE, true);
	}

	
	public void newFood(Point p) {
		this.setLocation(p);
	}
	
}
