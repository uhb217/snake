import inputs.KeyboardInputs;

import java.awt.*;
import javax.swing.JPanel;


public class GamePanel extends JPanel {
	private SnakeBodyPart[] snake;

	public GamePanel() {
		setPanelSize();
		addKeyListener(new KeyboardInputs());
	}
	private void setPanelSize() {
		Dimension size = new Dimension(1280, 800);
		setPreferredSize(size);
		snake = new SnakeBodyPart[1000];
	}

	public void updateGame() {
	}

	private void drawSnake(Graphics g){
		snake[1] = new SnakeBodyPart(320,320,true);
		drawSnakeBodyPart(snake[1],g);

	}private void setupSnake(){
		snake[1] = new SnakeBodyPart(320,320,true);
	}
	private void drawSnakeBodyPart(SnakeBodyPart snake, Graphics g){
		g.setColor(Color.GREEN);
		g.fillRect(snake.x,snake.y,32,32);
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.setColor(Color.BLACK);
		g.fillRect(0,0,1280, 800);

		g.setColor(new Color(134, 127, 127));
		for (int i = 32; i < 1280; i+=32) {
			g.drawLine(i,0,i,800);
		}
		for (int i = 32; i < 800; i+=32) {
			g.drawLine(0,i,1280,i);
		}
		drawSnake(g);
	}
	public class SnakeBodyPart{
		private int x;
		private int y;
		private boolean isExist = false;
		public SnakeBodyPart(int x,int y,boolean isExist){
			this.x = x;
			this.y = y;
			this.isExist = isExist;
		}

		public int getX() {
			return x;
		}

		public void setX(int x) {
			this.x = x;
		}

		public int getY() {
			return y;
		}

		public void setY(int y) {
			this.y = y;
		}

		public boolean isExist() {
			return isExist;
		}

		public void setExist(boolean exist) {
			isExist = exist;
		}
	}

}