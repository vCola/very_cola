package com.vcola.controller;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import com.vcola.entity.Food;
import com.vcola.entity.Ground;
import com.vcola.entity.Snake;
import com.vcola.listener.SnakeListener;
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

	
	public void newGame(){
		snake.start();
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
		
	}	

}
