import java.awt.*;
import javax.swing.JPanel;


public class GamePanel extends JPanel {
	private SnakeBodyPart[] snake;
	private int counter = 0;
	private char Direction = 'U';

	public GamePanel() {
		setPanelSize();
		addKeyListener(new KeyboardInputs(this));
	}
	public void setDirection(char D){
		this.Direction = D;
	}

	private void setPanelSize() {
		Dimension size = new Dimension(1280, 800);
		setPreferredSize(size);
		setupSnake();
	}
	private void drawSnake(Graphics g){
		for (SnakeBodyPart snakeBodyPart : snake) {
			if (snakeBodyPart != null) drawSnakeBodyPart(snakeBodyPart, g);
		}
	}
	private void setupSnake(){
		snake = new SnakeBodyPart[1000];
		snake[0] = new SnakeBodyPart(320, 320);
		snake[1] = new SnakeBodyPart(352, 320);
		snake[2] = new SnakeBodyPart(384, 320);
	}
	private void drawSnakeBodyPart(SnakeBodyPart snake, Graphics g){
		g.setColor(Color.GREEN);
		g.fillRect(snake.x,snake.y,32,32);
	}
	public void move(char direction){
		int lastX = 0, lastY = 0, x, y;
		for (int i = 0; i < snake.length; i++) {
			if (snake[i] != null){
				x = lastX;
				y = lastY;
				lastX = snake[i].x;
				lastY = snake[i].y;
				if (i == 0){
					switch (direction) {
						case 'U' -> snake[i].setLocation(snake[i].x, snake[i].y - 32);
						case 'D' -> snake[i].setLocation(snake[i].x, snake[i].y + 32);
						case 'L' -> snake[i].setLocation(snake[i].x - 32, snake[i].y);
						case 'R' -> snake[i].setLocation(snake[i].x + 32, snake[i].y);
					}
				}
				else snake[i].setLocation(x,y);
			}
		}
	}
	public void updateGame() {
		if (counter == 120/Game.LEVEL){
			move(Direction);
			counter = 0;
		}
		counter++;
	}
	public void paintComponent(Graphics g) {
		super.paintComponent(g);

		//draw background
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
	public static class SnakeBodyPart{
		private int x;
		private int y;
		public SnakeBodyPart(int x,int y){
			this.x = x;
			this.y = y;
		}
		public void setLocation(int x,int y) {
			this.x = x;
			this.y = y;
		}
	}
}