package com.vcola.game;

import java.awt.BorderLayout;

import javax.swing.JFrame;

import com.vcola.controller.Controller;
import com.vcola.entity.Food;
import com.vcola.entity.Ground;
import com.vcola.entity.Snake;
import com.vcola.util.Global;
import com.vcola.view.GamePanel;

public class Game {

	public static void main(String[] args) {
		Snake snake = new Snake();
		Food food = new Food();
		Ground ground = new Ground();
		GamePanel gamePanel = new GamePanel();

		Controller controller = new Controller(snake, food, ground, gamePanel);

		JFrame frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		gamePanel.setSize(Global.WIDTH * Global.CELL_SIZE, 
				Global.HEIGHT * Global.CELL_SIZE);
		frame.setSize(Global.WIDTH * Global.CELL_SIZE + 10, 
				Global.HEIGHT * Global.CELL_SIZE + 35);
		frame.add(gamePanel, BorderLayout.CENTER);

		gamePanel.addKeyListener(controller);
		snake.addSnakeListener(controller);

		frame.addKeyListener(controller);
		frame.setVisible(true);

		controller.newGame();

	}

}
