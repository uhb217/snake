import java.awt.*;
import java.util.Random;
import javax.swing.JPanel;


public class GamePanel extends JPanel {
	Random r = new Random();
	private SnakeBodyPart[] snake;
	private int appleX, appleY;
	private int counter = 0;
	private char Direction = 'U';
	public boolean changeDirection = true, play = true;

	public GamePanel() {
		setup();
		addKeyListener(new KeyboardInputs(this));
	}
	public void setDirection(char direction){
		if (changeDirection){
			if (Direction == 'U' && direction == 'D') return;
			if (Direction == 'D' && direction == 'U') return;
			if (Direction == 'L' && direction == 'R') return;
			if (Direction == 'R' && direction == 'L') return;
			Direction = direction;
			changeDirection = false;
		}
	}
	private void drawSnake(Graphics g){
		for (SnakeBodyPart snakeBodyPart : snake) {
			if (snakeBodyPart != null) drawSnakeBodyPart(snakeBodyPart, g);
		}
	}
	private void setup(){
		Dimension size = new Dimension(Game.WIDTH, Game.HEIGHT);
		setPreferredSize(size);

		snake = new SnakeBodyPart[1000];
		snake[0] = new SnakeBodyPart(320, 320);
		snake[1] = new SnakeBodyPart(352, 320);
		snake[2] = new SnakeBodyPart(384, 320);

		spawnApple();
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
		changeDirection = true;
	}
	private void spawnApple(){
		appleX = r.nextInt(Game.WIDTH/32) * 32;
		appleY = r.nextInt(Game.HEIGHT/32) * 32;
		for (SnakeBodyPart snakeBodyPart : snake) {
			if (snakeBodyPart != null && snakeBodyPart.x == appleX && snakeBodyPart.y == appleY)
				spawnApple();
		}
	}
	private void appleContact(){
		if (snake[0].x == appleX && snake[0].y == appleY){
			spawnApple();
			for (int i = 0; i < snake.length; i++) {
				if (snake[i] == null){
					snake[i] = new SnakeBodyPart(10000,10000);
					break;
				}
			}
		}
	}
	private boolean isLose(){
		SnakeBodyPart head = snake[0];
		if (head.x >= Game.WIDTH) return true;
		else if (head.x < 0) return true;
		else if (head.y >= Game.HEIGHT) return true;
		else if (head.y < 0) return true;
		for (int i = 1; i < snake.length; i++) {
			for (int j = 0; j < snake.length; j++) {
				if (snake[i] != null && snake[j] != null && j != i && snake[i].x == snake[j].x && snake[i].y == snake[j].y) return true;
			}
		}
		return false;
	}
	public void updateGame() {
		if (!isLose() && play == true){
			if (counter == 120/Game.LEVEL){
				move(Direction);
				counter = 0;
				appleContact();
			}
			counter++;
		}
	}
	public void paintComponent(Graphics g) {
		super.paintComponent(g);

		//draw background
		g.setColor(Color.BLACK);
		g.fillRect(0,0,Game.WIDTH, Game.HEIGHT);

		drawSnake(g);

		//draw lines
		g.setColor(new Color(134, 127, 127));
		for (int i = 32; i < Game.WIDTH; i+=32) {
			g.drawLine(i,0,i,Game.HEIGHT);
		}
		for (int i = 32; i < Game.HEIGHT; i+=32) {
			g.drawLine(0,i,Game.WIDTH,i);
		}
		g.setColor(Color.RED);
		g.fillRect(appleX,appleY,32,32);
		g.setFont(new Font("f",Font.BOLD,80));
		if (isLose()) g.drawString("Game Over",100,100);
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