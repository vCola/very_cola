package com.vcola.view;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

import com.vcola.entity.Food;
import com.vcola.entity.Ground;
import com.vcola.entity.Snake;
import com.vcola.util.Global;

public class GamePanel extends JPanel {
	private static final long serialVersionUID = 1L;
	
	private Snake snake;
	private Food food;
	private Ground ground;

	public void display(Snake snake, Food food, Ground ground) {
		System.out.println("GamePanel display");
		this.snake = snake;
		this.food = food;
		this.ground = ground;
		// 会调用paintComponent方法
		this.repaint();
	}

	@Override
	protected void paintComponent(Graphics g) {
		g.setColor(new Color(0xcfcfcf));
		g.fill3DRect(0, 0, Global.WIDTH * Global.CELL_SIZE, 
				Global.HEIGHT * Global.CELL_SIZE, true);
		// 重新显示
		if(ground != null && snake != null && food != null){
			ground.drawMe(g);
			snake.drawMe(g);
			food.drawMe(g);
		}
	}

}
