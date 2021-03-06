package com.vcola.entity;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import com.vcola.listener.SnakeListener;
import com.vcola.util.Global;

public class Snake {
	
	public static final int UP = 1;
	public static final int DOWN = -1;
	public static final int LEFT = 2;
	public static final int RIGHT = -2;
	
	// 当前运动方向
	private int oldDirection, newDirection;
	
	private Point oldTail;
	
	private boolean alive;
	
	private LinkedList<Point> body = new LinkedList<Point>();
	
	private Set<SnakeListener> snakeListeners = new HashSet<SnakeListener>();
	
	public Snake(){
		init();
	}
	
	private void init(){
		int x = Global.WIDTH / 2;
		int y = Global.HEIGHT / 2;
		for(int i=0 ; i<3 ; i++){
			body.addLast(new Point(x--, y));
		}
		oldDirection = newDirection = RIGHT;
		alive = true;
	}
	
	public void move(){
		if(newDirection + oldDirection != 0){
			oldDirection = newDirection;
		}	
		// 去除尾部
		oldTail = body.removeLast();
		// 加入头部 根据当前方向
		int x = body.getFirst().x;
		int y = body.getFirst().y;
		switch (oldDirection) {
		case UP:
			y--;
			if(y < 0){
				y = Global.HEIGHT - 1;
			}
			break;
		case DOWN:
			y++;
			if(y > Global.HEIGHT){
				y = 0;
			}
			break;
		case LEFT:
			x--;
			if(x < 0){
				x = Global.WIDTH - 1;
			}
			break;
		case RIGHT:
			x++;
			if(x > Global.WIDTH){
				x = 0;
			}
			break;
		}
		Point newHead = new Point(x, y);
		body.addFirst(newHead);
	}
	
	public void changeDirection(int direction){
		this.newDirection = direction;
	}

	public void eatFood(){
		System.out.println("Snake eatFood");
		body.addLast(oldTail);
	}
	
	public boolean isEatBody(){
		for(int i=1 ; i<body.size() ; i++){
			if(body.get(i).equals(getHead())){
				return true;
			}
		}
		return false;
	}
	
	public void drawMe(Graphics g){
		g.setColor(Color.BLUE);
		for(Point point : body){
			g.fill3DRect(point.x * Global.CELL_SIZE, point.y * Global.CELL_SIZE,
					Global.CELL_SIZE, Global.CELL_SIZE, true);
		}
	}
	
	public void addSnakeListener(SnakeListener snakeListener){
		if(snakeListener != null){
			snakeListeners.add(snakeListener);
		}
	}
	
	public void start(){
		new Thread(new SnakeDriver()).start();
	}
	
	private class SnakeDriver implements Runnable{
		@Override
		public void run() {
			while(alive){
				move();
				for(SnakeListener snakeListener : snakeListeners){
					snakeListener.snakeMoved(Snake.this);
				}
				try {
					// 蛇移动的速度
					TimeUnit.MILLISECONDS.sleep(300L);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
		
	}

	public Point getHead() {
		return body.getFirst();
	}
	
	public void die() {
		System.out.println("snake is die");
		this.alive = false;
	}
	
	public boolean isBody(Point p){
		for(Point bodyPoint : body){
			if(p.equals(bodyPoint)){
				return true;
			}
		}
		return false;
	}
	
}
