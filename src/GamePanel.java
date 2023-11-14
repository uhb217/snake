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
		setupSnake();
	}

	public void updateGame() {
	}

	private void drawSnake(Graphics g){
		for (int i = 0; i < snake.length; i++) {
			if (snake[i] != null){
				drawSnakeBodyPart(snake[i],g);
			}
		}

	}
	private void setupSnake(){
		snake = new SnakeBodyPart[1000];
		snake[1] = new SnakeBodyPart(320,320);
		snake[2] = new SnakeBodyPart(352,320);
		snake[3] = new SnakeBodyPart(384,320);
//		for (int i = 4; i < snake.length; i++) {
//			snake[i] = new SnakeBodyPart(0,0,false);
//		}
	}
	private void drawSnakeBodyPart(SnakeBodyPart snake, Graphics g){
		g.setColor(Color.GREEN);
		g.fillRect(snake.x,snake.y,32,32);
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.setColor(Color.BLACK);
		g.fillRect(0,0,1280, 800);

		drawSnake(g);

		//draw lines
		g.setColor(new Color(134, 127, 127));
		for (int i = 32; i < 1280; i+=32) {
			g.drawLine(i,0,i,800);
		}
		for (int i = 32; i < 800; i+=32) {
			g.drawLine(0,i,1280,i);
		}
	}
	public class SnakeBodyPart{
		private int x;
		private int y;
		public SnakeBodyPart(int x,int y){
			this.x = x;
			this.y = y;
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
	}

}