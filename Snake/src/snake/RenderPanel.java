/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snake;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import javax.swing.JPanel;

/**
 *
 * @author Rivera-O'Rourke
 */
public class RenderPanel extends JPanel {

	

	@Override
	protected void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		Snake snake = Snake.snake;
                g.setColor(Color.darkGray);
		g.fillRect(0, 0, 800, 700);
                g.setColor(Color.BLUE);

		for (Point point : snake.snakeParts){
			g.fillRect(point.x * Snake.SCALE, point.y * Snake.SCALE, 
                                Snake.SCALE, Snake.SCALE);
		}
		
		g.fillRect(snake.head.x * Snake.SCALE, snake.head.y * 
                        Snake.SCALE, Snake.SCALE, Snake.SCALE);
		
                g.setColor(Color.GREEN);
		
                g.fillRect(snake.fruit.x * Snake.SCALE, snake.fruit.y * 
                        Snake.SCALE, Snake.SCALE, Snake.SCALE);
		
                String string = "Score: " + snake.score + ", Length: " + 
                        snake.tailL + ", Time: " + snake.time / 20;
		
		g.setColor(Color.white);
                if (snake.time == 0 && snake.paused == true){
                    String string2 = "Controls: W=UP A=LEFT S=DOWN D=RIGHT";
                    string = "Welcome! To play press Space";
                    g.drawString(string2, (int) (getWidth() / 2 - string2.length() 
                                * 2.5f), (int) snake.di.getHeight() / 4);
                }
		g.drawString(string, (int) (getWidth() / 2 - string.length() * 2.5f), 10);
                string = "Game Over! Press Space to Restart!";

		if (snake.over){
			g.drawString(string, (int) (getWidth() / 2 - string.length() 
                                * 2.5f), (int) snake.di.getHeight() / 4);
		}

		string = "Paused";

		if (snake.paused && !snake.over && snake.time != 0){
			g.drawString(string, (int) (getWidth() / 2 - string.length() 
                                * 2.5f), (int) snake.di.getHeight() / 4);
		}
	}
}
