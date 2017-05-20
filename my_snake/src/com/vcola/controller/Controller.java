package com.vcola.controller;

import java.awt.Point;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Random;

import com.vcola.entity.Food;
import com.vcola.entity.Ground;
import com.vcola.entity.Snake;
import com.vcola.listener.SnakeListener;
import com.vcola.util.Global;
import com.vcola.view.GamePanel;

public class Controller extends KeyAdapter implements SnakeListener{
	
	private Snake snake;
	private Food food;
	private Ground ground;
	private GamePanel gamePanel;
	
	public Controller(Snake snake, Food food, Ground ground, GamePanel gamePanel) {
		this.snake = snake;
		this.food = food;
		this.ground = ground;
		this.gamePanel = gamePanel;
	}

	/**
	 * 排除障碍
	 * @return
	 */
	private Point getRandomPoint(){
		Random random = new Random();
		while(true){
			int x = random.nextInt(Global.WIDTH);
			int y = random.nextInt(Global.HEIGHT);
			Point newPoint = new Point(x, y);
			if(!ground.isBody(newPoint) && !snake.isBody(newPoint)){
				return newPoint;
			}
		}
	}
	
	public void newGame(){
		snake.start();
		food.newFood(getRandomPoint());
	}
	
	@Override
	public void keyPressed(KeyEvent e) {
		switch (e.getKeyCode()) {
		case KeyEvent.VK_UP:
			snake.changeDirection(Snake.UP);
			break;
		case KeyEvent.VK_DOWN:
			snake.changeDirection(Snake.DOWN);
			break;
		case KeyEvent.VK_LEFT:
			snake.changeDirection(Snake.LEFT);
			break;
		case KeyEvent.VK_RIGHT:
			snake.changeDirection(Snake.RIGHT);
			break;
		}
	}


	@Override
	public void snakeMoved(Snake snake) {
		gamePanel.display(snake, food, ground);
		if(food.isSnakeEatFood(snake)){
			// 蛇吃食物
			snake.eatFood();
			// 生成新的食物
			food.newFood(getRandomPoint());
		}
		
		if(ground.isSnakeEatRock(snake)){
			snake.die();
		}
		
		if(snake.isEatBody()){
			snake.die();
		}
	}	

}
