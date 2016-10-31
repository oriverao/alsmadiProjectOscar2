/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snake;

import java.awt.Dimension;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.JFrame;
import javax.swing.Timer;

/**
 *
 * @author Rivera-O'Rourke
 */
public class Snake implements ActionListener, KeyListener {

        public static Snake snake;
        public JFrame jf;
        public RenderPanel rendPan;
        public Timer timer = new Timer(20, this);
        public ArrayList<Point> snakeParts = new ArrayList<Point>();
        public static final int UP = 0, DOWN = 1, LEFT = 2, RIGHT = 3, SCALE = 10;
        public int ticks = 0, direction = DOWN, score, tailL = 10, time;
        public Point head, fruit;
        public Random random;
        public boolean over = false, paused;
        public Dimension di;
        
        public Snake(){
		di = Toolkit.getDefaultToolkit().getScreenSize();
		jf = new JFrame("Snake Game");
		jf.setVisible(true);
		jf.setSize(800, 700);
		jf.setResizable(false);
		jf.setLocation(di.width / 2 - jf.getWidth() / 2, di.height / 2 - jf.getHeight() / 2);
		jf.add(rendPan = new RenderPanel());
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jf.addKeyListener(this);
		startGame();
	}
        
        public void startGame(){
		over = false;
		paused = false;
		time = 0;
		score = 0;
		tailL = 14;
		ticks = 0;
		direction = DOWN;
		head = new Point(0, -1);
		random = new Random();
		snakeParts.clear();
		fruit = new Point(random.nextInt(79), random.nextInt(66));
		timer.start();
	}
        
        @Override
	public void actionPerformed(ActionEvent arg0){
		rendPan.repaint();
		ticks++;

		if (ticks % 2 == 0 && head != null && !over && !paused){
			time++;

			snakeParts.add(new Point(head.x, head.y));

			if (direction == UP){
				if (head.y - 1 >= 0 && noTail(head.x, head.y - 1)){
					head = new Point(head.x, head.y - 1);
				}
				else{
					over = true;

				}
			}

			if (direction == DOWN){
				if (head.y + 1 < 67 && noTail(head.x, head.y + 1)){
					head = new Point(head.x, head.y + 1);
				}
				else{
					over = true;
				}
			}

			if (direction == LEFT){
				if (head.x - 1 >= 0 && noTail(head.x - 1, head.y))
				{
					head = new Point(head.x - 1, head.y);
				}
				else
				{
					over = true;
				}
			}

			if (direction == RIGHT){
				if (head.x + 1 < 80 && noTail(head.x + 1, head.y))
				{
					head = new Point(head.x + 1, head.y);
				}
				else
				{
					over = true;
				}
			}

			if (snakeParts.size() > tailL){
				snakeParts.remove(0);

			}

			if (fruit != null){
				if (head.equals(fruit))
				{
					score++;
					tailL++;
					fruit.setLocation(random.nextInt(79), random.nextInt(66));
				}
			}
		}
	}
        
        public boolean noTail(int x, int y){
		for (Point point : snakeParts){
			if (point.equals(new Point(x, y))){
				return false;
			}
		}
		return true;
	}
        
        
    
    public static void main(String[] args) {
        snake = new Snake();
    }
    
        @Override
	public void keyPressed(KeyEvent e){
		int i = e.getKeyCode();

		if ((i == KeyEvent.VK_A || i == KeyEvent.VK_LEFT) && direction != RIGHT){
			direction = LEFT;
		}

		if ((i == KeyEvent.VK_D || i == KeyEvent.VK_RIGHT) && direction != LEFT){
			direction = RIGHT;
		}

		if ((i == KeyEvent.VK_W || i == KeyEvent.VK_UP) && direction != DOWN){
			direction = UP;
		}

		if ((i == KeyEvent.VK_S || i == KeyEvent.VK_DOWN) && direction != UP){
			direction = DOWN;
		}

		if (i == KeyEvent.VK_SPACE){
			if (over){
				startGame();
			}
			else{
				paused = !paused;
			}
		}
	}
        
        @Override
	public void keyReleased(KeyEvent e){
	}
        
        @Override
	public void keyTyped(KeyEvent e){
	}
}
